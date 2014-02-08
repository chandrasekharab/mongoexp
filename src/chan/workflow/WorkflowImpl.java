package chan.workflow;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import chan.db.DocumentObject;
import chan.db.Schema;
import chan.db.impl.MongoDocumentObject;
import chan.db.util.DBHelper;
import chan.db.util.MongoConstant;

public class WorkflowImpl implements Workflow{

	private List<Map<Object, Object>> activities = null;
	private Map<String, Object> in;
	private String flowName=null;
	private DocumentObject docObject = null;
	private Map<Object, Object> currentActivity = null;
	
	public WorkflowImpl() {
	}
	
	public WorkflowImpl(String flowName, String flowId) {
		WorkflowDB wdb = new WorkflowDB();
		this.docObject = wdb.getWorkflowInstance(flowName, flowId);		
		init(this.docObject, flowName);
	}
	
	@Override
	public void start(Object flowName) throws FlowNotFoundException {
		WorkflowDB wdb = new WorkflowDB();
		this.flowName = (String)flowName;
		this.docObject = wdb.createNewWorkflowInstance((String)flowName, "c");
		init(this.docObject, (String)flowName);
		Map<Object, Object> activity = activities.get(0);
		executeActivity(activity, this.in);
	}

	@Override
	public Object getCurrentStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object setCurrentStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCurrentActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void runNextActivity(Map<Object, Object> data) {
		if (this.activities.size() > 0) {			
			Map<Object, Object> activity = getNextExecuteActivity();			
			updateActivityStatus(this.docObject, this.currentActivity, WorkflowConstant.STATUS.COMPLETE);
			
			if (activity != null) {
				executeActivity(activity, data);
			}
		}
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runActivity(String activityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runActivity(Activity activity) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setInAttribute(Object input) {
		
		
	}
	
	public void updateActivityStatus(DocumentObject docObject, Map<Object, Object> activity, String status) {
		Schema schema = DBHelper.getSchema(this.flowName);
		//schema.update( { _id: 4, "activities.name": "s1" }, { $set: { "grades.$.status" : "inprogress" } } )
		Map<Object, Object> old = new HashMap<>();
		old.put(MongoConstant.ID, new ObjectId(docObject.getValue(MongoConstant.ID).toString()));
		old.put(WorkflowConstant.ACTIVITIES_NAME, activity.get(WorkflowConstant.NAME));
		
		Map<Object, Object> newDoc = new HashMap<>();
		Map<Object, Object> c = new HashMap<>();
		//status.put(WorkflowConstant.STATUS, "inprogress");
		c.put(WorkflowConstant.ACTIVITIES_$_STATUS, status);
		newDoc.put(MongoConstant.$SET, c);		
		schema.updateDocumentObject(new MongoDocumentObject(old), newDoc);
	}
	
	private void init(DocumentObject docObj, String flowName) {
		List<Map<Object, Object>> actList = (List<Map<Object, Object>>)docObj.getValue(WorkflowConstant.ACTIVITIES);
		this.activities = actList;
		this.flowName = flowName;
		this.currentActivity = getInProgressActivity();
	}
	
	private Map<Object, Object> getInProgressActivity() {
		Map<Object, Object> activity = null;
		for (int i=0; i<this.activities.size(); i++) {
			Map<Object, Object> temp = this.activities.get(i);
			String status = (String)temp.get(WorkflowConstant.ACTIVITIES_STATUS);
			if (status != null && status.equals(WorkflowConstant.STATUS.INPROGRESS)) {
				activity = temp;
				break;
			}
		}		
		return activity;
	}
	
	private Map<Object, Object> getNextExecuteActivity() {
		Map<Object, Object> activity = null;
		Map<Object, Object> prev = getInProgressActivity();
		int preSeq = (int)prev.get(WorkflowConstant.SEQUENCE);
		
		for (int i=0; i<this.activities.size(); i++) {
			Map<Object, Object> temp = this.activities.get(i);
			int seq = (int)temp.get(WorkflowConstant.SEQUENCE);
			if (seq == (preSeq + 1)) {
				activity = temp;
				break;
			}
		}	
		return activity;
	}
	
	private void executeActivity(Map<Object, Object> activity, Object data) {
		String actClass = (String)activity.get(WorkflowConstant.CLASS);
		try {
			Class<?> act = Class.forName(actClass);
			Object actObj = act.newInstance(); 
			Method execute = act.getDeclaredMethod("execute", Object.class);			
			execute.invoke(actObj, data);
			updateActivityStatus(this.docObject, activity, WorkflowConstant.STATUS.INPROGRESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}

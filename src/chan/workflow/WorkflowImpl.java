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
	
	public WorkflowImpl() {
	}
	
	public WorkflowImpl(String flowName, String flowId) {
		WorkflowDB wdb = new WorkflowDB();
		DocumentObject docObject = wdb.getWorkflowInstance(flowName, flowId);		
		List<Map<Object, Object>> actList = (List<Map<Object, Object>>)docObject.getValue(WorkflowConstant.ACTIVITIES);
		this.activities = actList;
		this.flowName = flowName;
	}
	
	@Override
	public void start(Object flowName) throws FlowNotFoundException {
		WorkflowDB wdb = new WorkflowDB();
		this.flowName = (String)flowName;
		DocumentObject docObject = wdb.createNewWorkflowInstance((String)flowName, "c");
		List<Map<Object, Object>> actList = (List<Map<Object, Object>>)docObject.getValue(WorkflowConstant.ACTIVITIES);
		this.activities = actList;
		Map<Object, Object> activity = activities.get(0);
		String actClass = (String)activity.get(WorkflowConstant.CLASS);
		
		try {
			Class<?> act = Class.forName(actClass);
			Object actObj = act.newInstance(); 
			Method execute = act.getDeclaredMethod("execute", Object.class);			
			execute.invoke(actObj, this.in);
			updateActivityStatus(docObject, activity, WorkflowConstant.STATUS.INPROGRESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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
	public void runNextActivity() {
		// TODO Auto-generated method stub
		
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
		old.put(MongoConstant.ID, new ObjectId((String)docObject.getValue(MongoConstant.ID)));
		old.put(WorkflowConstant.ACTIVITIES_NAME, activity.get(WorkflowConstant.NAME));
		
		Map<Object, Object> newDoc = new HashMap<>();
		Map<Object, Object> c = new HashMap<>();
		//status.put(WorkflowConstant.STATUS, "inprogress");
		c.put(WorkflowConstant.ACTIVITIES_$_STATUS, status);
		newDoc.put(MongoConstant.$SET, c);		
		schema.updateDocumentObject(new MongoDocumentObject(old), newDoc);
	}
	
}

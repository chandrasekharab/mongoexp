package chan.workflow;

import java.util.HashMap;
import java.util.Map;

import chan.db.Constant;
import chan.db.DocumentObject;
import chan.db.Schema;
import chan.db.impl.MongoDocumentObject;
import chan.db.util.DBHelper;
import chan.security.Base64;

public class WorkflowDB {

	public String getWorkflow(String workflowName) {		
		Schema schema = DBHelper.getSchema("workflow");
		DocumentObject workflow = schema.getDocumentObject("name", workflowName);
		String flow64 = (String)workflow.getValue("flow");
		String flow = Base64.decode(flow64);
		return flow;		
	}
	
	public String createNewWorkflowInstance(String workflowName, String currentUser) {
		String flow = getWorkflow(workflowName);
		Schema schema = DBHelper.getSchema(workflowName);
		Map<Object, Object> data = new HashMap<>();
		
		data.put("createdBy", currentUser);
		data.put("flow", flow);
		DocumentObject dObj = new MongoDocumentObject(data);
		schema.insertDocumentObject(dObj);
		return (String)dObj.getValue(Constant.OBJECT_ID);
	}	
}

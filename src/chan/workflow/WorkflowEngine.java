package chan.workflow;

import java.util.Map;

public class WorkflowEngine {

	public void startWorkflow(String workflow) {
		
		Workflow wf = new WorkflowImpl();
		
		try {
			wf.start(workflow);
		} catch (FlowNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
		
	public void stopWorkflow(Workflow workflow) {
	
	}
	
	public void processFlow(String flowName, String flowId, Map<Object, Object> data) {		
		Workflow wf = new WorkflowImpl(flowName, flowId);
		wf.runNextActivity(data);
	}
}

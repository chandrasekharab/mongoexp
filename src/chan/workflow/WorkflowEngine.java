package chan.workflow;

import java.util.Map;

import chan.logging.LogManager;
import chan.logging.Logger;

public class WorkflowEngine {
	private Logger oLog;
	
	public WorkflowEngine() {
		oLog = LogManager.getLoggerInstance();
	}
	
	
	
	public void startWorkflow(String workflow) {
		
		Workflow wf = new WorkflowImpl();
		
		try {
			wf.start(workflow);
		} catch (FlowNotFoundException e) {
			oLog.error(e.getStackTrace());
		}		
	}
		
	public void stopWorkflow(String workflow) {
	
	}
	
	public void processFlow(String flowName, String flowId, Map<Object, Object> data) {
		Workflow wf = new WorkflowImpl(flowName, flowId);
		wf.runNextActivity(data);
	}
}

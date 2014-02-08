package chan.workflow.test;

import org.junit.Test;

import chan.workflow.WorkflowEngine;

public class WorkflowEngineTest {

	public void testStartWorkflow() {		
		WorkflowEngine engine = new WorkflowEngine();
		engine.startWorkflow("testflow");
	}
	
	@Test
	public void testProcessFlow() {
		WorkflowEngine engine = new WorkflowEngine();
		engine.processFlow("testflow", "52f60cb537c38ea7b7ee30b2", null);
	}
}

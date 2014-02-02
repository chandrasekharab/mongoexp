package chan.workflow.test;

import org.junit.Test;

import chan.workflow.WorkflowEngine;

public class WorkflowEngineTest {

	@Test
	public void testStartWorkflow() {		
		WorkflowEngine engine = new WorkflowEngine();
		engine.startWorkflow("approve");
	}
}

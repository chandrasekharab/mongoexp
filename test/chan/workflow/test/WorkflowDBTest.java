package chan.workflow.test;

import org.junit.Assert;
import org.junit.Test;

import chan.workflow.WorkflowDB;

public class WorkflowDBTest {

	/*@Test
	public void testGetWorkflow() {
		WorkflowDB d = new WorkflowDB();
		Assert.assertNotNull(d.getWorkflow("approve"));
	}
	
	@Test
	public void testGetWorkflowInstance() {
		WorkflowDB d = new WorkflowDB();
		Assert.assertNotNull(d.createNewWorkflowInstance("approve", "c"));
	}*/
	
	@Test
	public void testCreateNewWorkflowInstance() {
		WorkflowDB d = new WorkflowDB();
		Assert.assertNotNull(d.createNewWorkflowInstance("approve", "c"));
	}
}

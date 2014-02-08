package chan.retailer.workflow;

import chan.workflow.Activity;

public class SimpleActivity2 implements Activity{

	@Override
	public void execute(Object work) {
		System.out.println("This is second activity");		
	}

	@Override
	public void onComplete(Object work) {
		// TODO Auto-generated method stub
		
	}

}

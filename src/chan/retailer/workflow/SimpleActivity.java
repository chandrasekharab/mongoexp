package chan.retailer.workflow;

import chan.workflow.Activity;

public class SimpleActivity implements Activity{

	@Override
	public void execute(Object work) {
		System.out.println(work);
		System.out.println("This is first activity");
	}

	@Override
	public void onComplete(Object work) {
		// TODO Auto-generated method stub		
	}

}

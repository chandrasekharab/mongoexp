package chan.workflow;

public interface Workflow {

	/**
	 * 
	 */
	public void start(Object flowName) throws FlowNotFoundException;
	
	/**
	 * 
	 * @return
	 */
	public Object getCurrentStatus();
	
	/**
	 * 
	 * @return
	 */
	public Object setCurrentStatus();
	
	/**
	 * 
	 * @return
	 */
	public Object getCurrentActivity();
	
	/**
	 * 
	 */
	public void runNextActivity();
	
	/**
	 * 
	 */
	public void restart();
	
	/**
	 * 
	 */
	public void resume();
	
	/**
	 * 
	 * @param activityId
	 */
	public void runActivity(String activityId);
	
	/**
	 * 
	 * @param activity
	 */
	public void runActivity(Activity activity);
	
	/**
	 * 
	 * @param input
	 */
	public void setInAttribute(Object input);
}

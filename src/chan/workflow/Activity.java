package chan.workflow;

public interface Activity {
	
	/**
	 * 
	 * @param work
	 */
	public void execute(Object work);
	
	/**
	 * 
	 * @param work
	 */
	public void onComplete(Object work);
}

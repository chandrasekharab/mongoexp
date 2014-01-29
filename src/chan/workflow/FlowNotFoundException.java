package chan.workflow;

public class FlowNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public FlowNotFoundException(){
		super();
	}
	
	public FlowNotFoundException(String message){
		super(message);
	}
}

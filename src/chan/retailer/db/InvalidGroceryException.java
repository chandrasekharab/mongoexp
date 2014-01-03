package chan.retailer.db;

public class InvalidGroceryException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidGroceryException() {
		super();
	}
	
	public InvalidGroceryException(String message) {
		super(message);
	}
}

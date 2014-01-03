package chan.retailer.db;

import java.util.Date;
import java.util.Map;

import chan.db.DocumentObject;

public interface Grocery {

	/**
	 * 
	 * @return
	 */
	public double getPrice();
	
	/**
	 * 
	 * @return
	 */
	public String getName();
	
	/**
	 * 
	 * @return
	 */
	public Map<Object, Object> getMetadata();
	
	/**
	 * 
	 */
	public Date getExpiryDate();
	
	/**
	 * 
	 * @return
	 */	
	public String getManufacturer();
	
	/**
	 * 
	 * @return
	 */
	public Map<Object, Object> getCompleteDetails();
	
	/**
	 * 
	 */
	public boolean validate() throws InvalidGroceryException;
	
	/**
	 * 
	 */
	public DocumentObject getDocumentObject();
	
}

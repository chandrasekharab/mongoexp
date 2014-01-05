package chan.retailer.db;

import java.util.Map;

public interface Retailer {

	/**
	 * 
	 * @param userData
	 * @return
	 * @throws Exception 
	 */
	public boolean createUser(Map<Object, Object> userData) throws Exception;
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Basket createBasket(String user, String name);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean deleteBasket(String user, String name);
	
	/**
	 * 
	 * @param userData
	 * @return
	 */
	public boolean deleteUser(String userId);
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public boolean updateShippingAddress(String user, Map<Object, Object> data);
	
	/**
	 * 
	 * @param user
	 * @param createIfNotExist
	 * @return
	 */
	public Basket getLatestBasket(String user, boolean createIfNotExist);
}

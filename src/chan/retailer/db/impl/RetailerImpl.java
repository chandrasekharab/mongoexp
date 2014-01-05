package chan.retailer.db.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import chan.db.DocumentObject;
import chan.db.Schema;
import chan.db.util.DBHelper;
import chan.retailer.RetailerConstants;
import chan.retailer.db.Basket;
import chan.retailer.db.Retailer;
import chan.security.Password;

public class RetailerImpl implements Retailer{

	@Override
	public boolean createUser(Map<Object, Object> userData) throws Exception {
		
		String emailId = (String) userData.get(RetailerConstants.EMAIL_ID);
		String password = (String) userData.get(RetailerConstants.PASSWORD);
		if (emailId.isEmpty() || password.isEmpty()) {
			throw new Exception("Email ID or Password is not defined");
		}		
		Schema schema = DBHelper.getSchema(emailId); 
		if(schema == null) {
			throw new Exception("Collection creation failed..");
		}
		
		userData.put(RetailerConstants.PASSWORD, Password.getSaltedHash(password));
		schema.insertDocumentObject(DBHelper.getDocumentObjectInstance(userData));		
		return true;
	}

	/**
	 * 
	 */
	@Override
	public Basket createBasket(String user, String name) {
		Schema userSchema = DBHelper.getSchema(user);		
		Map<Object, Object> basket = new HashMap<>();
		basket.put(RetailerConstants.IS_ACTIVE, true);
		basket.put(RetailerConstants.CREATION_TIME, new Date().getTime());		
		DocumentObject docObj = DBHelper.getDocumentObjectInstance(basket);		
		userSchema.insertDocumentObject(docObj);				
		return new BasketImpl(docObj, userSchema);
	}

	@Override
	public boolean deleteBasket(String user, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateShippingAddress(String user, Map<Object, Object> data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Basket getLatestBasket(String user, boolean createIfNotExist) {
		
		Schema userSchema = DBHelper.getSchema(user);
		DocumentObject dObj = userSchema.getDocumentObject(RetailerConstants.IS_ACTIVE, true);
		if (dObj == null && createIfNotExist) {
			return createBasket(user, "");
		} else {
			return new BasketImpl(dObj, userSchema);
		}
	}

}

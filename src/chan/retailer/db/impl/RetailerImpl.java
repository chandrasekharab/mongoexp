package chan.retailer.db.impl;

import java.util.Map;

import chan.db.Schema;
import chan.db.util.DBHelper;
import chan.retailer.db.Basket;
import chan.retailer.db.Retailer;

public class RetailerImpl implements Retailer{

	@Override
	public boolean createUser(Map<Object, Object> userData) throws Exception {
		
		String emailId = (String) userData.get("emailId");
		String password = (String) userData.get("pass");
		if (emailId.isEmpty() || password.isEmpty()) {
			throw new Exception("Email ID or Password is not defined");
		}		
		Schema schema = DBHelper.getSchema(emailId); 
		if(schema == null) {
			throw new Exception("Collection creation failed..");
		}
		schema.insertDocumentObject(DBHelper.getDocumentObjectInstance(userData));		
		return true;
	}

	@Override
	public Basket createBasket(String user, String name) {
		// TODO Auto-generated method stub
		return null;
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
	public Basket getLatestBasket(String user) {
		// TODO Auto-generated method stub
		return null;
	}

}

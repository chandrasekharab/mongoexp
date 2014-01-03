package chan.retailer.db.impl;

import java.util.Date;
import java.util.Map;

import chan.db.DocumentObject;
import chan.db.util.DBHelper;
import chan.retailer.db.Grocery;
import chan.retailer.db.InvalidGroceryException;
import chan.retailer.db.RetailerConstants;

public class GroceryImpl implements Grocery{

	private Map<Object, Object> item = null;
	
	public GroceryImpl(Map<Object, Object> item) {
		this.item = item;
	}
	
	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getExpiryDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getManufacturer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> getCompleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validate() throws InvalidGroceryException{
		
		if (item == null) {
			throw new InvalidGroceryException("Grocery item is null");
		}
		
		if (item.get(RetailerConstants.GROCERY_NAME) == null) {
			throw new InvalidGroceryException("Grocery item name is null");
		}
		
		if (item.get(RetailerConstants.EXPIRY_DATE) == null) {
			throw new InvalidGroceryException("Grocery item expiry date is null");
		}		
		return true;
	}

	@Override
	public DocumentObject getDocumentObject() {		
		return DBHelper.getDocumentObjectInstance(item);
	}

}

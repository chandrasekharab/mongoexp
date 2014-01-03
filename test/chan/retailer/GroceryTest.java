package chan.retailer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import chan.db.util.DBHelper;
import chan.db.util.PropertiesUtil;
import chan.retailer.db.InvalidGroceryException;
import chan.retailer.db.RetailerConstants;
import chan.retailer.util.RetailerHelper;

public class GroceryTest {

	@Before
	public void setUp() {
		
		try {
			DBHelper.initConnections(PropertiesUtil.getPropertiesMap(), 1);
		} catch (Exception e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testInsertGrocery() {
		Map<Object, Object> item = new HashMap<>();
		item.put(RetailerConstants.GROCERY_NAME, "Close Up");
		item.put(RetailerConstants.EXPIRY_DATE, "dfd");
		try {
			RetailerHelper.insertGrocery(item);
		} catch (IOException | InvalidGroceryException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}		
	}
	
	@After
	public void shutDown() {
		DBHelper.closeConnections();
	}
}

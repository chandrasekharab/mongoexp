package chan.retailer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import chan.db.util.DBHelper;
import chan.db.util.PropertiesUtil;
import chan.retailer.db.InvalidGroceryException;
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
	
	//@Test
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
	
	@Test
	public void testGetAllGroceries() {
		try {
			List<Map<Object, Object>> data = RetailerHelper.getAllGroceries();
			data.get(0);
		} catch (IOException e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@After
	public void shutDown() {
		DBHelper.closeConnections();
	}
}

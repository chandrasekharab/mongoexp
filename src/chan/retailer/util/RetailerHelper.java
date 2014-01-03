package chan.retailer.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import chan.db.Schema;
import chan.db.util.DBHelper;
import chan.db.util.PropertiesUtil;
import chan.retailer.db.Grocery;
import chan.retailer.db.InvalidGroceryException;
import chan.retailer.db.impl.GroceryImpl;

public class RetailerHelper {
	
	private static Schema grocerySchema = null;
	
	public static void insertGrocery(Map<Object, Object> item) throws FileNotFoundException, IOException, InvalidGroceryException {
		if (grocerySchema == null) {
			grocerySchema = DBHelper.getSchema(PropertiesUtil.getGroceryStoreName());
		}
		Grocery gro = getGroceryInstance(item);
		gro.validate();
		grocerySchema.insertDocumentObject(gro.getDocumentObject());
	}
	
	public static Grocery getGroceryInstance(Map<Object, Object> item) {
		return new GroceryImpl(item);
	}
	
}

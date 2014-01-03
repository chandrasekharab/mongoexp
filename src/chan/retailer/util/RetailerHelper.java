package chan.retailer.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import chan.db.Schema;
import chan.db.util.DBHelper;
import chan.db.util.PropertiesUtil;
import chan.retailer.BasicHandler;
import chan.retailer.Handler;
import chan.retailer.db.Grocery;
import chan.retailer.db.InvalidGroceryException;
import chan.retailer.db.impl.GroceryImpl;
import chan.retailer.view.RetailerView;
import chan.retailer.view.View;

public class RetailerHelper {
	
	private static Schema grocerySchema = null;
	
	/**
	 * 
	 */
	public static void insertGrocery(Map<Object, Object> item) throws FileNotFoundException, IOException, InvalidGroceryException {
		Grocery gro = getGroceryInstance(item);
		insertGrocery(gro);
	}
	
	/**
	 * 
	 * @param item
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidGroceryException
	 */
	public static void insertGrocery(Grocery item) throws FileNotFoundException, IOException, InvalidGroceryException {
		if (grocerySchema == null) {
			grocerySchema = DBHelper.getSchema(PropertiesUtil.getGroceryStoreName());
		}		
		item.validate();
		grocerySchema.insertDocumentObject(item.getDocumentObject());
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	public static Grocery getGroceryInstance(Map<Object, Object> item) {
		return new GroceryImpl(item);
	}
	
	/**
	 * 
	 * @return
	 */
	public static Handler getServletHandler() {
		return new BasicHandler();
	}
	
	/**
	 * 
	 * @return
	 */
	public static View getView() {
		return new RetailerView();
	}
}

package chan.retailer.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import chan.db.DocumentObject;
import chan.db.Schema;
import chan.db.util.DBHelper;
import chan.db.util.PropertiesUtil;
import chan.retailer.BasicHandler;
import chan.retailer.GroceryHandler;
import chan.retailer.Handler;
import chan.retailer.RetailerConstants;
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
	public static Handler getServletHandler(HttpServletRequest request) {
		String context = Util.getContextValue(request);
		Handler handler;
		switch (context) {
			case RetailerConstants.GROCERY_CONTEXT:
				handler = new GroceryHandler();
				break;
	
			default:
				handler = new BasicHandler();
				break;
		}

		return handler;
	}
	
	/**
	 * 
	 * @return
	 */
	public static View getView() {
		return new RetailerView();
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static List<Map<Object, Object>> getAllGroceries() throws FileNotFoundException, IOException {
		
		Schema schema = getGrocerySchema();
		List<Map<Object, Object>> listItem = new ArrayList<>();
		
		List<DocumentObject> docList = schema.getDocumentObjects(0, 0, 100);		
		for (DocumentObject documentObject : docList) {
			listItem.add(documentObject.getValues());
		}
		return listItem;
	} 
	
	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static Schema getGrocerySchema() throws FileNotFoundException, IOException {
		if (grocerySchema == null) {
			grocerySchema = DBHelper.getSchema(PropertiesUtil.getGroceryStoreName());
		}
		return grocerySchema;
	}
	
}

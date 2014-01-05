package chan.retailer.db.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

import chan.db.DocumentObject;
import chan.db.Schema;
import chan.retailer.RetailerConstants;
import chan.retailer.db.Basket;
import chan.retailer.db.Grocery;
import chan.retailer.util.RetailerHelper;

public class BasketImpl implements Basket{

	private DocumentObject docObj;
	private Schema schema;
	
	public BasketImpl(DocumentObject docObj, Schema schema) {
		this.docObj = docObj;
		this.schema = schema;
	}
	
	@Override
	public boolean insertGrocery(Grocery gro) {
		Map<Object, Object> data = gro.getDocumentObject().getValues();
		Map<Object, Object> grocery = new HashMap<>();		
		grocery.put(RetailerConstants.GROCERIES, data.get(RetailerConstants.GROCERY_ID));
		Map<Object, Object> newObj = new HashMap<>();
		newObj.put("$push", grocery);		
		this.schema.updateDocumentObject(this.docObj, newObj);
		return true;
	}

	@Override
	public boolean deleteGrocery(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getGrocery(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Grocery> getAllGroceries() {
		if (docObj == null) {
			return null;
		}		
		DocumentObject dObj = schema.getDocumentObject(this.docObj.getValues());
		BasicDBList gList = (BasicDBList) dObj.getValue(RetailerConstants.GROCERIES);
		Iterator<Object> ite = gList.iterator();
		List<Grocery> groList = new ArrayList<>(); 
		while (ite.hasNext()) {
			Object item = ite.next();
			if (item != null) {				
				try {
					Grocery gro = RetailerHelper.getGrocery(RetailerConstants.MONGO_ID, new ObjectId((String)item));
					if (gro != null) {
						groList.add(gro);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return groList;
	}

}

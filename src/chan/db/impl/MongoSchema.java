package chan.db.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import chan.db.Constant;
import chan.db.DocumentObject;
import chan.db.Schema;

public class MongoSchema implements Schema{

	private DBCollection mongoDBCol;
	
	public MongoSchema(DBCollection mongoCol) {
		this.mongoDBCol = mongoCol;
	}
	
	@Override
	public boolean insertDocumentObject(DocumentObject docObj) {
		BasicDBObject bdbObj = new BasicDBObject(docObj.getValues());
		mongoDBCol.insert(bdbObj);
		docObj.append(Constant.OBJECT_ID, bdbObj.get(Constant.OBJECT_ID).toString());
		return true;
	}

	@Override
	public boolean deleteDocumentObject(DocumentObject docObj) {
		BasicDBObject bdbObj = new BasicDBObject(docObj.getValues());
		mongoDBCol.remove(bdbObj);
		return true;
	}

	@Override
	public boolean updateDocumentObject(DocumentObject docObj, Map<Object, Object> values) {		
		mongoDBCol.update(new BasicDBObject(docObj.getValues()), new BasicDBObject(values));
		return true;
	}

	@Override
	public DocumentObject getDocumentObject(String objName) {
		//mongoDBCol.find(new Bas)
		DBCursor cursor = mongoDBCol.find();
		Map<Object, Object> data = new HashMap<>();
		while (cursor.hasNext()) {
			DBObject dbObj = cursor.next();
			Object obj = dbObj.get(objName);
			if (obj == null) {
				continue;
			}
			
			if (!(obj instanceof Map)) {
				data.put(objName, obj);
				break;
			}  else {
				data = (Map<Object, Object>)dbObj.get(objName);
				break;
			}			
		}
		return new MongoDocumentObject(data);
	}

	@Override
	public List<DocumentObject> getDocumentObjects(int startIndex,
			int endIndex, int maxRecords) {
		
		//For example, to get an array of the 1000-1100th elements of a cursor, use
		//List obj = collection.find( query ).skip( 1000 ).limit( 100 ).toArray();
		
		DBCursor cursor = mongoDBCol.find();
		List<DocumentObject> docList = new ArrayList<>(); 
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();			
			docList.add(new MongoDocumentObject(obj.toMap()));
		}
		return docList;
	}

	@Override
	public DocumentObject getDocumentObject(String key, Object value) {
		DBObject dbObj = mongoDBCol.findOne(new BasicDBObject(key, value));		
		if (dbObj != null) {
			return new MongoDocumentObject(dbObj.toMap());
		}
		return null;
	}

	@Override
	public DocumentObject getDocumentObject(Map<Object, Object> values) {
		DBObject dbObj = mongoDBCol.findOne(new BasicDBObject(values));		
		if (dbObj != null) {
			return new MongoDocumentObject(dbObj.toMap());
		}
		return null;
	}

	@Override
	public Map<Object, Object> search(String key, String pattern) {
		Map<Object, Object> data = new HashMap<>();
		DBCursor cursor = mongoDBCol.find(new BasicDBObject(key, "/.*"+pattern+"/i"));
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			data.putAll(obj.toMap());
		}
		return data;
		
	}

}

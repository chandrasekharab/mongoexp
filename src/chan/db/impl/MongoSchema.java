package chan.db.impl;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DocumentObject getDocumentObject(String objName) {
		//mongoDBCol.find(new Bas)
		DBCursor cursor = mongoDBCol.find();
		Map<Object, Object> data = new HashMap<>();
		while (cursor.hasNext()) {
			DBObject dbObj = cursor.next();
			Object obj = dbObj.get(objName);
			if (data == null) {
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

}

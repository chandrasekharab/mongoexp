package chan.db.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import chan.db.DB;
import chan.db.Schema;

public class MongoDB implements DB{

	com.mongodb.DB mongoDB;
	
	public MongoDB(com.mongodb.DB db) {
		this.mongoDB = db;
	}
	
	@Override
	public List<Schema> getSchemas() {
		Set<String> collections = mongoDB.getCollectionNames();
		List<Schema> schemas = new ArrayList<>();
		
		for (String coll : collections) {
			schemas.add(new MongoSchema(mongoDB.getCollection(coll)));
		}	
		return schemas;
	}

	@Override
	public Schema getSchema(String schemaName) {
		DBCollection coll = mongoDB.getCollection(schemaName);
		if (coll == null) {
			return null;
		}
		Schema schema = new MongoSchema(coll);
		return schema;
	}

	@Override
	public Schema createSchema(String schemaName) {
		DBObject options = BasicDBObjectBuilder.start().add("capped", true).add("size", 2000000000l).get();		
		DBCollection coll = mongoDB.getCollection(schemaName);		
		return new MongoSchema(coll);
	}

	@Override
	public boolean deleteSchema(String schemaName) {
		DBCollection dbColl = mongoDB.getCollection(schemaName);
		dbColl.drop();
		
		return true;
	}

}

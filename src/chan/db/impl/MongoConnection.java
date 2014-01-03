package chan.db.impl;

import com.mongodb.MongoClient;

import chan.db.Connection;
import chan.db.DB;

public class MongoConnection implements Connection {

	private MongoClient mc;
	
	public MongoConnection(MongoClient mc) {
		this.mc = mc;
	}
	
	@Override
	public DB getDB(String dbName) {		
		return new MongoDB(this.mc.getDB(dbName));
	}

	@Override
	public void close() {
		mc.close();		
	}

}

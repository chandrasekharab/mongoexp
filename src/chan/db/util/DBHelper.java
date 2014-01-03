package chan.db.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chan.db.Connection;
import chan.db.DB;
import chan.db.DocumentObject;
import chan.db.ObjectDB;
import chan.db.Schema;
import chan.db.impl.MongoConnection;
import chan.db.impl.MongoDocumentObject;
import chan.db.impl.MongoObjectDB;

public class DBHelper {

	private static List<Connection> connectionPool = null;  
	private static ObjectDB objectDb = null;
	private static DB db = null; 
	
	/**
	 * 
	 * @param connectionParams
	 * @param count
	 * @throws Exception
	 */
	public static void initConnections(Map<Object, Object> connectionParams, int count) throws Exception {
		getConnection(connectionParams);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	public static Connection getConnection(Map<Object, Object> data) throws Exception {
		if (connectionPool == null) {
			connectionPool = new ArrayList<>();
			connectionPool.add(getObjectDB().createConnection(data));
		}
		return connectionPool.get(0);
	}
	
	/**
	 * 
	 * @return
	 */
	public static Connection getConnection () {
		return connectionPool.get(0);
	}
	
	/**
	 * 
	 * @return
	 */
	public static ObjectDB getObjectDB() {
		if (objectDb == null) {
			objectDb = new MongoObjectDB();
		}
		return objectDb;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Schema getSchema(String collectionName) {
		if (db == null) {
			try {
				db = getConnection().getDB(PropertiesUtil.getHost());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Schema col = db.getSchema(collectionName);
		if (col == null) {
			col = db.createSchema(collectionName);
		}
		
		return col;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static DocumentObject getDocumentObjectInstance(Map<Object, Object> data) {
		return new MongoDocumentObject(data);
	}
	
	/**
	 * 
	 */
	public static void closeConnections() {		
		connectionPool.get(0).close();
	}
	
}

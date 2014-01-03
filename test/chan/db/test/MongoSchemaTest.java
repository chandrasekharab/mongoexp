package chan.db.test;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import chan.db.DocumentObject;
import chan.db.Schema;
import chan.db.impl.MongoDocumentObject;
import chan.db.impl.MongoSchema;

public class MongoSchemaTest {

	private DB db;
	private MongoClient mongoClient;
	
	
	@Before
	public void setUp() {
		// To connect to mongodb server
		try {
			mongoClient = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
		// Now connect to your databases
		db = mongoClient.getDB("testdb");	
	}
	
	@Test
	public void testGetDocumentObject() {
		DBCollection col = db.getCollection("test@test.com");
		Schema schema = new MongoSchema(col); 
		
		Map<Object, Object> data = new HashMap<Object, Object>();
		data.put("test123", "123");
		schema.insertDocumentObject(new MongoDocumentObject(data));
		
		DocumentObject dobj = schema.getDocumentObject("test123");
		Map<Object, Object> dataRet = dobj.getValues();
		Assert.assertNotNull(dobj);
		
		schema.deleteDocumentObject(dobj);
		DocumentObject dobj1 = schema.getDocumentObject("test123");
		Map<Object, Object> dataRet1 = dobj.getValues();
		//Assert.assertNull(dobj1);		
	}
		
	@After
	public void cleanUp() {
		DBCollection col = db.getCollection("test@test.com");
		col.drop();
		mongoClient.close();
	}
	
}

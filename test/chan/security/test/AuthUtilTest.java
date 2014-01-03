package chan.security.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import chan.db.DocumentObject;
import chan.db.Schema;
import chan.db.impl.MongoDocumentObject;
import chan.db.util.DBHelper;
import chan.security.AuthUtil;

public class AuthUtilTest {

	@Before
	public void setUp() {
		Map<Object, Object> connectionParams = new HashMap<>();
		connectionParams.put("host", "localhost");
		connectionParams.put("dbname", "testdb");
		try {
			DBHelper.initConnections(connectionParams, 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testRegisterUser() {
		Map<Object, Object> userData = new HashMap<>();
		userData.put("emailId", "test@test.com");
		userData.put("pass", "test");
		try {
			AuthUtil.registerUser(userData);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		assertTrue(true);
	}
	
	@Test
	public void testGetCollection() {
		Schema schema = DBHelper.getSchema("test@test.com");
		Map<Object, Object> data = new HashMap<>();
		data.put("test", "test");
		schema.insertDocumentObject(new MongoDocumentObject(data));
	}

	@Test
	public void testGetObject() {
		Schema schema = DBHelper.getSchema("test@test.com");
		DocumentObject d0 = schema.getDocumentObject("test");
		d0.getValues();
	}
}

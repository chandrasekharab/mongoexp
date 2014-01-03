package chan.db.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

	private static String host;
	private static String dbName;
	private static String groceryStoreName;
	private static Properties properties = null;
	
	private static final String HOST = "host";
	private static final String DBNAME = "database";
	private static final String GROCERY_STORE = "grocerystore";
	private static final String UPLOAD_DIRECTORY = "uploadDirectory";
	
	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getHost() throws FileNotFoundException, IOException {
		if (host == null) {
			host = "localhost";
					//getProperties().getProperty(HOST); 
		}
		return host;
	}
	
	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getDBName() throws FileNotFoundException, IOException {
		if (dbName == null) {
			dbName = "testdb";
					//getProperties().getProperty(DBNAME);
		}
		return dbName;
	}
	
	public static String getGroceryStoreName() throws FileNotFoundException, IOException {
		if (groceryStoreName == null) {
			groceryStoreName = "makssa_groceries"; 
					//getProperties().getProperty(GROCERY_STORE);
		}
		return groceryStoreName;
	}
	
	private static Properties getProperties() throws FileNotFoundException, IOException {
		if (properties == null) {
			properties = new Properties();
			InputStream inputs = Thread.currentThread().getContextClassLoader().getResourceAsStream("/config/dbconnection.properties");
			properties.load(inputs);
		}
		return properties;
	}
	
	public static Map<Object, Object> getPropertiesMap() throws FileNotFoundException, IOException {
		Map<Object, Object> data = new HashMap<>();
		data.put(HOST, getHost());
		data.put(DBNAME, getDBName());
		data.put(GROCERY_STORE, getGroceryStoreName());
		
		return data;
	}
	
	public static String getUploadDirectoryPath() throws FileNotFoundException, IOException {
		return "D:/tempImages";//getProperties().getProperty(UPLOAD_DIRECTORY);
	}
}

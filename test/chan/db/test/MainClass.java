package chan.db.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import chan.retailer.RetailerConstants;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MainClass {

	public static void main(String args[]) {
		// testDateTime();
		//testMongo();
		
		try {
			maptoxml();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testDateTime() {

		Date date = new Date();
		System.out.println(date.getTime());
	}

	private static void testMongo() {
		try {
			// To connect to mongodb server
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			// Now connect to your databases
			DB db = mongoClient.getDB("testdb");
			System.out.println("Connect to database successfully");
			// boolean auth = db.authenticate(myUserName, myPassword);
			// System.out.println("Authentication: " + auth);
			DBCollection coll = db.getCollection("mycol");
			System.out.println("Collection mycol selected successfully");
			/*
			 * BasicDBObject doc = new BasicDBObject("title", "MongoDB")
			 * .append("description", "database").append("likes", 100)
			 * .append("url", "http://www.tutorialspoint.com/mongodb/")
			 * .append("by", "tutorials point"); coll.insert(doc);
			 */

			DBObject old = new BasicDBObject("www", "wwww");
			/*
			 * List<String> data = new ArrayList<>(); data.add("three");
			 * data.add("four"); data.add("five");
			 */

			/*
			 * int data[] = {1,2,3};
			 * 
			 * DBObject newD = new BasicDBObject("aaa", data);
			 * 
			 * coll.update(old, new BasicDBObject("$pushAll", newD));
			 */

			DBObject dbo = coll.findOne(old);
			Map data = dbo.toMap();
			BasicDBList data123 = (BasicDBList) data.get("array");
			Iterator ite = data123.iterator();
			while (ite.hasNext()) {
				Object item = ite.next();
				System.out.println(item);
			}

			System.out.println("Document inserted successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	private static void maptoxml() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(DocumentRoot.class);
		Map<Object, Object> test = new HashMap<>();
		Map<Object, Object> test2 = new HashMap<>();
		
		test.put("root", test2);
		test2.put("b", "456");
		test2.put("c", "456");
		
		DocumentRoot dr = new DocumentRoot();
		dr.getMap().put("root", test);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(dr, System.out);

	}
	
	
}

@XmlRootElement
class DocumentRoot {
	
	@XmlElement(name="flow")
	private Map<Object, Object> data = new HashMap<>();	
	
	public Map<Object, Object> getMap() {
		return this.data;
	}
	
	public void setMap(Map<Object, Object> data) {
		this.data = data;
	}
}
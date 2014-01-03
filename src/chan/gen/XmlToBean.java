package chan.gen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import chan.gen.dom.Util;

public class XmlToBean {

	private static final String DEST_BEAN_FOLDER = "src/chan/test/mongodb/beans";
	//private static String classbean = "";
//	private static String getterssetters = "";
	//private static boolean init = false;
	private static String TABLE_SELECTOR = "/table/@name";
	//private static String COLUMN_NAME_SELECTOR = "/column/@name";
	//private static String COLUMN_TYPE_SELECTOR = "/column/@type";
	
	public static void main(String arg[]) {
		File schemaFolder = new File("config/schema");
		File schemas[] = schemaFolder.listFiles();
		for (File schema : schemas) {
			buildJavaBean(schema);
		}
	}

	public static void buildJavaBean(File schema) {
		Document doc;
		try {
			doc = Util.getDocument(schema);	
			File stylesheet = new File("config/template/classbean.xslt");
			TransformerFactory tFctory = TransformerFactory.newInstance();
			StreamSource stylesource = new StreamSource(stylesheet); 
	        Transformer transformer = tFctory.newTransformer(stylesource);
	        
	        StreamResult result = new StreamResult(new FileOutputStream(new File(DEST_BEAN_FOLDER + "/" + getSchemaName(doc) + ".java"))); 
	        transformer.transform(new DOMSource(doc), result);
	        
		} catch (SAXException  | IOException | TransformerException | XPathExpressionException e) {
			e.printStackTrace();
		}
		
	}

	private static String getSchemaName(Document doc) throws XPathExpressionException {		
		XPath xPath =  XPathFactory.newInstance().newXPath();
				 
		//read a string value
		String email = xPath.compile(TABLE_SELECTOR).evaluate(doc);		 
		//read an xml node using xpath
		Node node = (Node) xPath.compile(TABLE_SELECTOR).evaluate(doc, XPathConstants.NODE);		
		return node.getNodeValue();
		
	}
	/*private static void initTemplates() {
		init = true;
		classbean = readFile("config/template/classbean.tmpl");
		getterssetters = readFile("config/template/getterssetters.tmpl");
	}

	private static String readFile(String filepath) {
		File file = new File(filepath);
		
		String theString = "";// the final string
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				theString = theString + line;
			}
			scanner.close();
			System.out.println(theString);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return theString;

	}*/
	
	
}

package chan.gen.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Util {

	public static DocumentBuilder getDocumentBuilder(boolean validation,
			boolean whiteSpace) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		}
		return builder;
	}

	public static Document getDocument(Object file) throws SAXException,
			IOException {
		File fileObj;
		if (file instanceof File) {
			fileObj = (File)file;			
		} else {
			fileObj = new File((String)file);
		}
		
		DocumentBuilder builder = getDocumentBuilder(true, true);
		Document doc = builder.parse(fileObj);
		return doc;
	}

}

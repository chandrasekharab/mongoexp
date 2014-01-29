package chan.workflow;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WorkflowImpl implements Workflow{

	private Document workflow = null;
	private Map<String, Object> in;
	
	public WorkflowImpl(Document doc, Map<String, Object> in) {
		this.workflow = doc;
		this.in = in;
	}
	
	@Override
	public void load(Object flow) {
		WorkflowDB wdb = new WorkflowDB();
		workflow = createDocument((String)flow);		
	}

	@Override
	public void start() throws FlowNotFoundException {
		String activity = getActivityClass(0);
		try {
			Class<?> act = Class.forName(activity);
			Object actObj = act.newInstance(); 
			Method execute = act.getDeclaredMethod("execute", Object.class);			
			execute.invoke(actObj, this.in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public Object getCurrentStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object setCurrentStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCurrentActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void runNextActivity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runActivity(String activityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runActivity(Activity activity) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setInAttribute(Object input) {
		
		
	}
	
	private Document createDocument(String xmlString) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = null;
	    try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new InputSource(new StringReader(xmlString)));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return document;
	}

	private String getActivityClass(int index) {
		XPathFactory xpathFactory = XPathFactory.newInstance();
	    XPath xpath = xpathFactory.newXPath();
	    String className = null;
		try {
			className = xpath.evaluate("/flow/activity/@class", workflow);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return className;
	}
	
	private Map<String, Object> getInOutObject() {
		Map<String, Object> inout = new HashMap<>();		
		inout.put("input", getActivityAttributes("input"));
		inout.put("output", getActivityAttributes("output"));
		return inout;
	}
	
	private Map<String, Object> getActivityAttributes(String attr) {
		Map<String, Object> data = new HashMap<>();
		
		XPathFactory xpathFactory = XPathFactory.newInstance();
	    XPath xpath = xpathFactory.newXPath();
	    Node inputs = null;
		try {
			String path = "/flow/activity/" + attr;
			inputs = (Node) xpath.evaluate(path, workflow, XPathConstants.NODE);
			NodeList nodes = inputs.getChildNodes();
			int len = nodes.getLength();
			for (int i=0; i< len; i++) {
				Node nd = nodes.item(i);
				data.put(nd.getNodeName(), nd.getNodeValue());				
			}
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return data;
	}

	
}

package chan.workflow;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.bson.types.ObjectId;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import chan.db.DocumentObject;
import chan.db.Schema;
import chan.db.impl.MongoDocumentObject;
import chan.db.util.DBHelper;
import chan.db.util.MongoConstant;
import chan.security.Base64;

public class WorkflowDB {

	public String getWorkflow(String workflowName) {		
		Schema schema = DBHelper.getSchema("workflow");
		DocumentObject workflow = schema.getDocumentObject("name", workflowName);
		String flow64 = (String)workflow.getValue("flow");
		String flow = Base64.decode(flow64);
		return flow;		
	}
	
	public DocumentObject getWorkflowInstance(String flowName, String flowId) {
		Schema schema = DBHelper.getSchema(flowName);
		return schema.getDocumentObject(MongoConstant.ID, new ObjectId(flowId));
	}
	
	public DocumentObject createNewWorkflowInstance(String workflowName, String currentUser) {
		String flow = getWorkflow(workflowName);
		
		Document activitiesNode = createDocument(flow);
		NodeList actList = getActivityNode(activitiesNode);
		int size = actList.getLength();
		List<Map<String, Object>> activityList = new ArrayList<>();
		for (int i=0; i<size; i++) {
			Map<String, Object> act = new HashMap<>();			
			Node node = actList.item(i);
			
			NamedNodeMap attr = node.getAttributes();			
			Node attName = attr.getNamedItem(WorkflowConstant.NAME);
			act.put(WorkflowConstant.NAME, attName.getNodeValue());
			
			Node attClass = attr.getNamedItem(WorkflowConstant.CLASS);
			act.put(WorkflowConstant.CLASS, attClass.getNodeValue());
			
			act.put(WorkflowConstant.SEQUENCE, i+1);
			
			//act.put(WorkflowConstant.STATUS, "OPEN");
			
			/*Element elm = (Element)node;
			Node input = elm.getElementsByTagName(WorkflowConstant.INPUT).item(0);*/
			
			
			activityList.add(act);
		}		
		Schema schema = DBHelper.getSchema(workflowName);
		
		Map<Object, Object> flowData = new HashMap<>();
		flowData.put(WorkflowConstant.NAME, workflowName);
		flowData.put("createdBy", currentUser);				
		flowData.put(WorkflowConstant.ACTIVITIES, activityList);		
		
		DocumentObject dObj = new MongoDocumentObject(flowData);
		schema.insertDocumentObject(dObj);
		return dObj;
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
	
	private NodeList getActivityNode(Document doc) {
		XPathFactory xpathFactory = XPathFactory.newInstance();
	    XPath xpath = xpathFactory.newXPath();
	    NodeList nodes=null;
		try {
			nodes = (NodeList)xpath.evaluate("/flow/activity", doc, XPathConstants.NODESET);
			return nodes;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
}

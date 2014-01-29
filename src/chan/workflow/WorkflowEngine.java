package chan.workflow;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WorkflowEngine {

	public void startWorkflow(String workflow) {		
		String flow = "<flow name=\"approve\"><activity id=\"s1\" class=\"chan.retailer.workflow.SimpleActivity\">"
				+ "<input><id /><name /></input>"
				+ "<output><oId /><oName /></output>"
				+ "</activity></flow>";
		
		Document doc = createDocument(flow);
		Map<String, Object> in = new HashMap<>();
		in.put("id", "chan");
		in.put("name", "chandra");		
		Workflow wf = new WorkflowImpl(doc, in);
		
		try {
			wf.start();
		} catch (FlowNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
		
	public void stopWorkflow(Workflow workflow) {
		
	}
	
	public void runActivity(String workflowId, String activityId, Object activityData) {
		// Get the data from db
		WorkflowDB wdb = new WorkflowDB();		
		String flowXml = wdb.getWorkflow(workflowId);
		Workflow workflow = new WorkflowImpl(createDocument(flowXml), null);
		workflow.runActivity(activityId);	
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
}

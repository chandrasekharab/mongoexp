package chan.workflow;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WorkflowEngine {

	public void startWorkflow(String workflow) {
		
		Workflow wf = new WorkflowImpl();
		
		try {
			wf.start(workflow);
		} catch (FlowNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
		
	public void stopWorkflow(Workflow workflow) {
	
	}
	
	public void processFlow(String flowName, String flowId) {
		Workflow wf = new WorkflowImpl(flowName, flowId);		
		wf.runNextActivity();
	}
	
	public void runActivity(String workflowId, String activityId, Object activityData) {
		// Get the data from db
		WorkflowDB wdb = new WorkflowDB();		
		String flowXml = wdb.getWorkflow(workflowId);
		Workflow workflow = new WorkflowImpl();
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

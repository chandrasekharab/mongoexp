package chan.db;

import java.util.List;
import java.util.Map;

public interface Schema {

	/**
	 * 
	 * @param docObj
	 * @return
	 */
	public boolean insertDocumentObject(DocumentObject docObj);
	
	/**
	 * 
	 * @param docObj
	 * @return
	 */
	public boolean deleteDocumentObject(DocumentObject docObj);
	
	/**
	 * 
	 * @param docObj
	 * @param values
	 * @return
	 */
	public boolean updateDocumentObject(DocumentObject docObj, Map<Object, Object> values);
	
	/**
	 * 
	 * @param objName
	 * @return
	 */
	public DocumentObject getDocumentObject(String objName);
	
	/**
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @param maxRecords
	 * @return
	 */
	public List<DocumentObject> getDocumentObjects(int startIndex, int endIndex, int maxRecords);
}

package chan.db;

import java.util.Map;

public interface DocumentObject {

	/**
	 * 
	 * @return
	 */
	public Map<Object, Object> getValues();
	
	/**
	 * 
	 * @param values
	 * @return
	 */
	public boolean update(Map<Object, Object> values);
	
	/**
	 * 
	 * @param values
	 * @return
	 */
	public boolean append(Map<Object, Object> values);
	
	/**
	 * 
	 * @param values
	 * @return
	 */
	public boolean create(Map<Object, Object> values);
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Object getValue(String key);
}

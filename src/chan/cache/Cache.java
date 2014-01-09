package chan.cache;

public interface Cache {

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Object get(Object key);
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Object insert(Object key, Object value);
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Object update(Object key, Object value);
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Object delete(Object key);
}

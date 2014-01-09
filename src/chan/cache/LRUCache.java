package chan.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<Object, Object> implements Cache{

	private static final long serialVersionUID = 1L;

	private int maxSize;
	
	public LRUCache(int maxSize) {
		super(1, 0.75f, true);
		this.maxSize = maxSize;
				
	}
	
	protected boolean removeEldestEntry(final Map.Entry eldest) {
		return size() > maxSize;
	}
	
	@Override
	public Object insert(Object key, Object value) {
		return put(key, value);
	}

	@Override
	public Object update(Object key, Object value) {
		return put(key, value);
	}

	@Override
	public Object delete(Object key) {
		return remove(key);
	}
}

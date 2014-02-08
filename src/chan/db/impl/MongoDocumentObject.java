package chan.db.impl;

import java.util.HashMap;
import java.util.Map;

import chan.db.DocumentObject;

public class MongoDocumentObject implements DocumentObject{

	private Map<Object, Object> values;
	
	public MongoDocumentObject(Map<Object, Object> values) {
		this.values = values;
	}
	
	public MongoDocumentObject(Object key, Object value) {		
		this.values = new HashMap<>();
		this.values.put(key, value);
	}
	
	@Override
	public Map<Object, Object> getValues() {		
		return this.values;
	}

	@Override
	public boolean update(Map<Object, Object> values) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean append(Map<Object, Object> values) {
		this.values.putAll(values);
		return true;
	}

	@Override
	public boolean create(Map<Object, Object> values) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValue(String key) {
		return this.values.get(key);
	}

	@Override
	public String toJson() {
		
		return null;
	}

	@Override
	public String toXml() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentObject append(Object key, Object value) {
		this.values.put(key, value);
		return this;
	}
	
	

}

package chan.db.impl;

import java.util.Map;

import chan.db.DocumentObject;

public class MongoDocumentObject implements DocumentObject{

	private Map<Object, Object> values;
	
	public MongoDocumentObject(Map<Object, Object> values) {
		this.values = values;
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
	

}

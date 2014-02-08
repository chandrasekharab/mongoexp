package chan.logging;

import chan.db.Schema;
import chan.db.impl.MongoDocumentObject;
import chan.db.util.DBHelper;

public class MongoAppender implements Appender{

	Schema schema = null;
	
	public MongoAppender() {
		this.schema = DBHelper.getSchema("system_logging");
	}
	
	@Override
	public void write(Object data) {		
		this.schema.insertDocumentObject(new MongoDocumentObject(LoggerConstants.LOG, data));		
	}

}

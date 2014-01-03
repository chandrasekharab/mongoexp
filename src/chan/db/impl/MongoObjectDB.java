package chan.db.impl;

import java.net.UnknownHostException;
import java.util.Map;

import com.mongodb.MongoClient;

import chan.db.Connection;
import chan.db.ObjectDB;

public class MongoObjectDB implements ObjectDB{

	@Override
	public Connection createConnection(Map<Object, Object> connectionParams) throws Exception {
		MongoClient mongoClient;
		try {			
			String host = (String)connectionParams.get("host");
			Object port = connectionParams.get("port");
			if (!host.isEmpty() && port != null) {
				mongoClient = new MongoClient(host, (int)port);
			} else {
				mongoClient = new MongoClient();
			}
			
		} catch (UnknownHostException e) {
			throw new Exception(e.getMessage());
		}
		return new MongoConnection(mongoClient);
	}

	@Override
	public boolean closeConnection(Connection connection) {
		connection.close();
		return false;
	}

}

package chan.db;

import java.util.Map;

public interface ObjectDB {

	/**
	 * Creates a Connection to the database
	 * @param connectionParams, map to represent the connection credentials.
	 * General format is
	 * user: userid
	 * pass: password
	 * connectionString: url
	 * server: server
	 * etc., 
	 *  
	 * @return, the Connection object.
	 * @throws Exception 
	 */
	public Connection createConnection(Map<Object, Object> connectionParams) throws Exception;
	
	/**
	 * Closes a connection to the database.
	 * @param connection, The connection object.
	 * @return, the boolean value to indicate connection closed.
	 */
	public boolean closeConnection(Connection connection);
}

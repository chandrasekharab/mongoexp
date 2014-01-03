package chan.db;

public interface Connection {

	/**
	 * Return the database connection.
	 * @param dbName, database name.
	 * @return DB, returns the DB object.
	 */
	public DB getDB(String dbName);

	/**
	 * 
	 */
	public void close();
}

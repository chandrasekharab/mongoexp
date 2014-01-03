package chan.db;

import java.util.List;

public interface DB {

	/**
	 * 
	 * @return
	 */
	public List<Schema> getSchemas();
	
	/**
	 * 
	 * @param schemaName
	 * @return
	 */
	public Schema getSchema(String schemaName);
	
	/**
	 * 
	 * @param schemaName
	 * @return
	 */
	public Schema createSchema(String schemaName);
	
	/**
	 * 
	 * @param schemaName
	 * @return
	 */
	public boolean deleteSchema(String schemaName);
}

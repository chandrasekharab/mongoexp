package chan.retailer.db;

import java.util.List;

public interface Basket {

	/**
	 * 
	 * @param gro
	 * @return
	 */
	public boolean insertGrocery(Grocery gro);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean deleteGrocery(String name);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean getGrocery(String name);
	
	/**
	 * 
	 */
	public List<Grocery> getAllGroceries();
}

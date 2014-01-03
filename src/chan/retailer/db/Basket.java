package chan.retailer.db;

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
	public Grocery[] getAllGroceries();
}

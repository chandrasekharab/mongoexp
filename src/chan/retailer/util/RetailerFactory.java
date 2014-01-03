package chan.retailer.util;

import chan.retailer.db.Retailer;
import chan.retailer.db.impl.RetailerImpl;

public class RetailerFactory {

	public static Retailer getRetailerInstance() {
		return new RetailerImpl();
	}
}

package chan.retailer.search;

import java.io.IOException;

import chan.retailer.RetailerConstants;
import chan.retailer.util.RetailerHelper;

public class BasicSearch implements Search{

	@Override
	public Object search(String pattern) {
		try {
			return RetailerHelper.searchGroceries(RetailerConstants.GROCERY_NAME, pattern);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}

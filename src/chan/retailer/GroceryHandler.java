package chan.retailer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chan.retailer.model.SimpleModel;
import chan.retailer.util.RetailerHelper;
import chan.retailer.util.Util;

public class GroceryHandler extends BasicHandler {

	@Override
	public boolean canHandle(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();		
		return pathInfo.indexOf(RetailerConstants.GROCERY_CONTEXT) == 0;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		String serviceName = Util.getServiceName(request);
		
		switch (serviceName) {
			case RetailerConstants.GET_ALL_GROCERIES:
				try {
					RetailerHelper.getView().render(new SimpleModel(RetailerHelper.getAllGroceries()), request, response);
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
			default:
				
			break;
		}
	}

	
}

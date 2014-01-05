package chan.retailer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chan.retailer.db.Basket;
import chan.retailer.db.Grocery;
import chan.retailer.model.SimpleModel;
import chan.retailer.util.RetailerFactory;
import chan.retailer.util.RetailerHelper;
import chan.retailer.util.Util;
import chan.security.AuthUtil;

public class BasketHandler extends BasicHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		String serviceName = Util.getServiceName(request);
		
		switch (serviceName) {
			case RetailerConstants.INSERT:
				Basket basket = RetailerFactory.getRetailerInstance().getLatestBasket(AuthUtil.getCurrentUser(request) , true);
				basket.insertGrocery(RetailerHelper.getGroceryInstance(Util.getRequestParamsMap(request)));
			break;
			
			case RetailerConstants.GET_ALL_GROCERIES:
				Basket basket2 = RetailerFactory.getRetailerInstance().getLatestBasket(AuthUtil.getCurrentUser(request) , true);
				List<Grocery> groList = basket2.getAllGroceries();
				List<Map<Object, Object>>  gList = new ArrayList<>();
				for (Grocery grocery : groList) {
					gList.add(grocery.getDocumentObject().getValues());
				}
				
				RetailerHelper.getView().render(new SimpleModel(gList), request, response);				
			default:
				
			break;
		}
	}
}

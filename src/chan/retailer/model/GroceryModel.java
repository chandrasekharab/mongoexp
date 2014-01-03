package chan.retailer.model;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import chan.retailer.db.Grocery;
import chan.retailer.util.RetailerHelper;

public class GroceryModel implements Model {
	
	private HttpServletRequest request = null;
	private Grocery grocery = null;
	
	public GroceryModel(HttpServletRequest request) {
		this.request = request;
		initGrocery();
	}
	
	public void setHttpRequest(HttpServletRequest request) {
		this.request = request;
		initGrocery();
	}
	
	private void initGrocery() {
		Map<Object, Object> item = new HashMap<>();
		Enumeration<String> parameterNames = this.request.getParameterNames();
		String paramName = "";
		while (parameterNames.hasMoreElements()) {
			paramName = parameterNames.nextElement();			
			item.put(paramName, this.request.getParameter(paramName));
		}		
		this.grocery = RetailerHelper.getGroceryInstance(item);
	}

	@Override
	public Object getModelData() {
		return this.grocery.getDocumentObject().getValues();
	}
}

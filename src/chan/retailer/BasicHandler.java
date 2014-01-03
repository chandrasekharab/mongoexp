package chan.retailer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chan.retailer.db.Grocery;
import chan.retailer.db.InvalidGroceryException;
import chan.retailer.model.GroceryModel;
import chan.retailer.model.Model;
import chan.retailer.util.RetailerHelper;

public class BasicHandler implements Handler {

	@Override
	public boolean canHandle(HttpServletRequest request) {		
		return true;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		Model model = new GroceryModel(request);		
		// For update
		/*try {
			//RetailerHelper.insertGrocery((Grocery)model.getModelData());
		} catch (IOException | InvalidGroceryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/		
		RetailerHelper.getView().render(model, request, response);	
	}
}

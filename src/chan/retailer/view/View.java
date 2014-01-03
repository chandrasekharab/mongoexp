package chan.retailer.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chan.retailer.model.Model;

public interface View {
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void render(Model model, HttpServletRequest request, HttpServletResponse response);
}

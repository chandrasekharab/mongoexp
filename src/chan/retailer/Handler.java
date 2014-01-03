package chan.retailer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {

	/**
	 * 
	 * @return
	 */
	public boolean canHandle(HttpServletRequest request);
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response);
	
}

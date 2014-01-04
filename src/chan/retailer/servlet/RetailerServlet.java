package chan.retailer.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chan.retailer.Handler;
import chan.retailer.util.RetailerHelper;

public class RetailerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {	
		Handler handler = RetailerHelper.getServletHandler(request);
		handler.handle(request, response);				
	}	
}

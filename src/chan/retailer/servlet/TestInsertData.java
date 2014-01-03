package chan.retailer.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import chan.retailer.Handler;
import chan.retailer.util.RetailerHelper;
import chan.security.AuthUtil;

public class TestInsertData extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Handler handler = RetailerHelper.getServletHandler();
		if (handler.canHandle(request)) {
			handler.handle(request, response);
		} else {
			throw new ServletException("Handler not found...");
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if (AuthUtil.isValidUser(req)) {
			PrintWriter pw = res.getWriter();
			pw.print("Hello welcome...");
		} else {
			res.sendRedirect("");
		}
	}
	

}
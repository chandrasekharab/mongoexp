package chan.retailer.servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import chan.security.AuthUtil;

public class TestInsertData extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String connectionURL = "jdbc:mysql://localhost/testdb";
		Connection connection;
		try {
			String id = request.getParameter("id");
			String uname = request.getParameter("uname");
			String password = request.getParameter("password");
			pw.println(uname);
			pw.println(password);
			Class.forName("org.gjt.mm.mysql.Driver");
			connection = DriverManager.getConnection(connectionURL, "root",
					"root");
			PreparedStatement pst = connection
					.prepareStatement("insert into login values(?,?,?)");
			pst.setString(1, id);
			pst.setString(2, uname);
			pst.setString(3, password);
			int i = pst.executeUpdate();
			if (i != 0) {
				pw.println("<br>Date has been inserted in to Datebase");
			} else {
				pw.println("failed to insert the data");
			}
		} catch (Exception e) {
			pw.println(e);
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
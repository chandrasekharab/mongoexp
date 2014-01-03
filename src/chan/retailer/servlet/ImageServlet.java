package chan.retailer.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String filePath = "D:\\apache-tomcat-7.0.27\\webapps\\mongodbexp_images\\" ;
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String prefix = "http://localhost:8080/mongodbexp_images/";
		
		File imageFolder = new File(filePath);
		
		PrintWriter out = response.getWriter();
		  out.println("<html>");
	      out.println("<head>");
	      out.println("<title>Servlet upload</title>");  
	      out.println("</head>");
	      out.println("<body>");
		File images[] = imageFolder.listFiles();
		for (File image : images) {
			out.write("<img src='"+ prefix + image.getName()+"'>");			
		}
		  out.println("</body>");
	      out.println("</html>");
	}
}

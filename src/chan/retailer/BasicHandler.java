package chan.retailer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import chan.retailer.model.SimpleModel;
import chan.retailer.util.RetailerHelper;
import chan.retailer.util.Util;
import chan.security.AuthUtil;

public class BasicHandler implements Handler {

	private String filePath = "D:\\apache-tomcat-7.0.27\\webapps\\mongodbexp_images\\";
	
	@Override
	public boolean canHandle(HttpServletRequest request) {		
		return true;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		
		switch (Util.getContextValue(request)) {
			case RetailerConstants.USER_REGISTRATION:
				try {
					AuthUtil.registerUser(Util.getRequestParamsMap(request));
					Map<Object, Object> succeed = new HashMap<>();
					succeed.put("success", "success");
					RetailerHelper.getView().render(new SimpleModel(succeed), request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
			
			case RetailerConstants.LOGIN:
				String user = request.getParameter(RetailerConstants.EMAIL_ID);
				String pass = request.getParameter(RetailerConstants.PASSWORD);
			try {
				if (AuthUtil.authenticate(user, pass)) {
					HttpSession session = request.getSession();					
					session.setAttribute("user", user);
					session.setMaxInactiveInterval(30*60);					
					Cookie cookie = new Cookie("user", user);
					cookie.setMaxAge(30*60);
					response.addCookie(cookie);
					Map<Object, Object> succeed = new HashMap<>();
					
					request.getRequestDispatcher("/AllGroceries.html").include(request, response);
					//succeed.put("success", "success");
					//RetailerHelper.getView().render(new SimpleModel(succeed), request, response);
				} else {
					Map<Object, Object> succeed = new HashMap<>();
					succeed.put("status", "loginfailed");
					RetailerHelper.getView().render(new SimpleModel(succeed), request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		/*if (ServletFileUpload.isMultipartContent(request)) {
			handleMultipart(request, response);
		} else {
			
		}*/
	}
	
	protected void handleMultipart(HttpServletRequest request, HttpServletResponse response) {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("c:\\tmp"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		// upload.setSizeMax( maxFileSize );

		File file;
		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();
			
			Map<Object, Object> data = new HashMap<>();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				String fieldName = fi.getFieldName();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					
					String fileName = fi.getName();
					
					data.put(RetailerConstants.IMAGE_URL, "http://localhost:8080/mongodbexp_images/" + fileName);
					
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(
								filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\")));
					} else {
						file = new File(
								filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\") + 1));
					}
					fi.write(file);
				} else {
					data.put(fieldName, fi.getString());
				}
			}
			
			RetailerHelper.insertGrocery(data);
			RetailerHelper.getView().render(new SimpleModel(data), request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package chan.retailer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import chan.retailer.db.Grocery;
import chan.retailer.db.InvalidGroceryException;
import chan.retailer.model.GroceryModel;
import chan.retailer.model.Model;
import chan.retailer.model.SimpleModel;
import chan.retailer.util.RetailerHelper;

public class BasicHandler implements Handler {

	private String filePath = "D:\\apache-tomcat-7.0.27\\webapps\\mongodbexp_images\\";
	
	@Override
	public boolean canHandle(HttpServletRequest request) {		
		return true;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		if (ServletFileUpload.isMultipartContent(request)) {
			handleMultipart(request, response);
		} else {
			/*try {
				//request.getRequestDispatcher("/AllGroceries.html").forward(request, response);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
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

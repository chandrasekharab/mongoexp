package chan.retailer.servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import chan.retailer.util.RetailerHelper;

public class FileUploadHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;

	public void init() {
		// Get the file location where it would be stored.
		filePath = "D:\\apache-tomcat-7.0.27\\webapps\\mongodbexp_images\\";

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		RetailerHelper.getServletHandler(request).handle(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		throw new ServletException("GET method used with "
				+ getClass().getName() + ": POST method required.");
	}
}
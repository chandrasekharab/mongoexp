/*
 * $Id$
 *
 * Copyright (c) 2013  Pegasystems Inc.
 * All rights reserved.
 *
 * This  software  has  been  provided pursuant  to  a  License
 * Agreement  containing  restrictions on  its  use.   The  software
 * contains  valuable  trade secrets and proprietary information  of
 * Pegasystems Inc and is protected by  federal   copyright law.  It
 * may  not be copied,  modified,  translated or distributed in  any
 * form or medium,  disclosed to third parties or used in any manner
 * not provided for in  said  License Agreement except with  written
 * authorization from Pegasystems Inc.
*/

package chan.retailer.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import chan.db.util.DBHelper;
import chan.retailer.RetailerConstants;

public class Util {
	
	/**
	 * 
	 * @param schemaName
	 * @param attribute
	 * @return
	 */
	public static String getAttributeValue(String schemaName, String attribute) {				
		Map<Object, Object> data = DBHelper.getSchema(schemaName).getDocumentObject(attribute).getValues();		
		return (String)data.get(attribute);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String getContextValue(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.isEmpty()) {
			return "";
		}
		
		String[] tokens = pathInfo.split("/");
		if (tokens.length > 1){
			return tokens[1];
		}		
		return pathInfo;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String getServiceName(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();
		String context = getContextValue(request);
		if (pathInfo.equals(context)) {
			return "";
		}
		return pathInfo.substring(context.length() + 2);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static Map<Object, Object> getRequestParamsMap(HttpServletRequest request) {
		Map<Object, Object> item = new HashMap<>();
		Enumeration<String> parameterNames = request.getParameterNames();
		String paramName = "";
		while (parameterNames.hasMoreElements()) {
			paramName = parameterNames.nextElement();			
			item.put(paramName, request.getParameter(paramName));
		}
		
		return item;
	}
}

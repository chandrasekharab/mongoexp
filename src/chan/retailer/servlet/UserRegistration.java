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

package chan.retailer.servlet;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chan.security.AuthUtil;


public class UserRegistration extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) {		
		String emailId = req.getParameter("emailid");
		String pass = req.getParameter("pass");
		Map<Object, Object> data = new HashMap<>();
		data.put("emailId", emailId);
		data.put("pass", pass);
		try {
			AuthUtil.registerUser(data);
			res.sendRedirect("registered.htm");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				res.sendError(201, "Registration failed..");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
	}
}

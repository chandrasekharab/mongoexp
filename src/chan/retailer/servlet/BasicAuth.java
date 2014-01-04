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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chan.retailer.util.RetailerHelper;
import chan.security.AuthUtil;

public class BasicAuth extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		
		String lUser = req.getParameter("user");
		String iPass = req.getParameter("pass");
		
		try {
			if (AuthUtil.authenticate(lUser, iPass)) {
				
				HttpSession session = req.getSession();
				
				session.setAttribute("user", lUser);
				session.setMaxInactiveInterval(30*60);
				
				Cookie cookie = new Cookie("user", lUser);
				cookie.setMaxAge(30*60);
				res.addCookie(cookie);
				try {
					res.sendRedirect("loginSuccess.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {		
		RetailerHelper.getServletHandler(request);
	}
}

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

package chan.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import chan.retailer.util.Util;
import chan.retailer.util.RetailerFactory;


public class AuthUtil {
	
	public static boolean isValidUser(HttpServletRequest req) {
		if (req == null) {
			return false;
		}
		HttpSession session = req.getSession();
		if (session != null) {
			String user = (String) session.getAttribute("user");
			if (user!= null && !user.isEmpty()) {
				return true;
			}
		}		
		return false;
		
	}

	public static boolean authenticate(String user, String pass) throws Exception {		
		return Password.check(pass, Util.getAttributeValue(user, pass));
	}
	
	public static void registerUser(Map<Object, Object> userData) throws Exception {
		RetailerFactory.getRetailerInstance().createUser(userData);		
	}
	
}

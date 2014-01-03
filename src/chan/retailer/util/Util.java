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

import java.util.Map;

import chan.db.util.DBHelper;

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
	
}

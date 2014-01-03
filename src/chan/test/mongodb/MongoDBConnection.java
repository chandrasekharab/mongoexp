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

package chan.test.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDBConnection {
	
	private static MongoClient mongoClient;
	private static DB testDb;
	static {
		try {
			mongoClient = new MongoClient();
			testDb = mongoClient.getDB("testdb");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static DB getTestDb() {
		return testDb;
	}

}

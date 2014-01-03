package chan.db.util.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import chan.db.util.PropertiesUtil;

public class PropertiesUtilTest {
	
	@Test
	public void testGetHost() {
		try {
			PropertiesUtil.getHost();
		} catch (IOException e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetDBName() {
		try {
			PropertiesUtil.getDBName();
		} catch (IOException e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}

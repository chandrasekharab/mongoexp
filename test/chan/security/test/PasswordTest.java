package chan.security.test;


import org.junit.Assert;
import org.junit.Test;

import chan.security.Password;

public class PasswordTest {

	@Test
	public void testGetSaltedHash() {
		String password = "test";
		String stored = "";
		try {
			stored = Password.getSaltedHash(password);
			System.out.println(stored);
			Assert.assertTrue(Password.check(password, stored));
		} catch (Exception e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
		
	}
}

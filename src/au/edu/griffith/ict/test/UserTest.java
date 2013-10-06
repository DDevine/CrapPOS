package au.edu.griffith.ict.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/** JUnit test for the User class
 *	@author Josh Stover 
 */
public class UserTest {

	User user1, user2;
	
	@Before
	public void setUp() throws Exception {
		user1 = new User(1234, "mypassword", true);
		user2 = new User(4321, "qwerty", false);
	}

	@Test
	public void testUser() {
		assertNotNull(user1);
		assertNotNull(user2);
	}

	@Test
	public void testGetStaffNo() {
		assertEquals(user1.getStaffNo(), 1234);
	}

	@Test
	public void testGetPassword() {
		assertEquals(user1.getPassword(), "mypassword");
	}

	@Test
	public void testIsAdmin() {
		assertTrue(user1.isAdmin());
		assertFalse(user2.isAdmin());
	}

}

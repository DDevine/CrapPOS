package au.edu.griffith.ict.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import au.edu.griffith.ict.Customer;
import au.edu.griffith.ict.CustomerManager;

/** JUnit test for the CustomerManager class
 *	@author Josh Stover 
 */
public class CustomerManagerTest {

	CustomerManager cm;
	Customer c = new Customer("0412345678","10 Downing St.", "Sir Bob");
	String tmpfile = System.getProperty("user.dir") + File.separatorChar + "customermanager.txt";
	
	@Before
	public void setUp() throws Exception {
		cm = new CustomerManager();
		cm.loadDatabase(tmpfile);
	}

	@Test
	public void testNewCustomer() {
		cm.newCustomer(c);
		assertEquals(c, cm.get(c.getPhoneNo()));
	}

	@Test
	public void testUpdate() {
		cm.newCustomer(c);
		c.setName("Johnny B. Goode");
		cm.update(c);
		assertEquals(c, cm.get(c.getPhoneNo()));
	}

	@Test
	public void testRemove() {
		cm.newCustomer(c);
		cm.remove(c);
		assertNull(cm.get(c.getPhoneNo()));
	}

	@Test
	public void testSave() {
		cm.update(c);
		assertTrue(new File(tmpfile).exists());
	}

}

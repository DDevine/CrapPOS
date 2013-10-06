package au.edu.griffith.ict.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.edu.griffith.ict.Customer;

/** JUnit test for the Customer class
 *	@author Josh Stover 
 */
public class CustomerTest {

	Customer cust;
	
	@Before
	public void setUp() throws Exception {
		cust = new Customer("0412345678","10 Downing St.", "Sir Bob");
	}

	@Test
	public void testCustomer() {
		assertNotNull(cust);
	}

	@Test
	public void testGetPhoneNo() {
		assertEquals("0412345678", cust.getPhoneNo());
	}

	@Test
	public void testGetAddress() {
		assertEquals("10 Downing St.", cust.getAddress());
	}

	@Test
	public void testGetName() {
		assertEquals("Sir Bob", cust.getName());
	}

	@Test
	public void testSetPhoneNo() {
		cust.setPhoneNo("55556666");
		assertEquals("55556666", cust.getPhoneNo());
	}

	@Test
	public void testSetAddress() {
		cust.setAddress("5 Hell St., Limbo");
		assertEquals("5 Hell St., Limbo", cust.getAddress());
	}

	@Test
	public void testSetName() {
		cust.setName("Miss Piggy");
		assertEquals("Miss Piggy", cust.getName());
	}

}

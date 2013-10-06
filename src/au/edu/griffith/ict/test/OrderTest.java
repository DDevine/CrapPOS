package au.edu.griffith.ict.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.edu.griffith.ict.Customer;
import au.edu.griffith.ict.MenuItem;
import au.edu.griffith.ict.Order;
import au.edu.griffith.ict.OrderStatus;

/** JUnit test for the Order class
 *	@author Josh Stover 
 */
public class OrderTest {

	Customer cust;
	Order order;
	MenuItem item;
	
	@Before
	public void setUp() throws Exception {
		cust = new Customer("0412345678","10 Downing St.", "Sir Bob");
		order = new Order(cust);
		item = new MenuItem(1234,"banana", 4.20F);
		order.setCreationDate("1999-01-01");
	}

	@Test
	public void testOrder() {
		assertNotNull(order);
	}

	@Test
	public void testGetCustomer() {
		assertEquals(order.getCustomer(), cust);
	}

	@Test
	public void testIsDelivery() {
		assertFalse(order.isDelivery());
		order.setDelivery(true);
		assertTrue(order.isDelivery());
	}

	@Test
	public void testAdd() {
		order.add(item, 20);
		assertEquals(order.getQty(item), 20);
	}

	@Test
	public void testRemove() {
		order.add(item, 20);
		order.remove(item, 15);
		assertEquals(15, order.getQty(item));
	}
	
	public void testSetQty() {
		order.add(item, 20);
		System.out.println(order);
		order.setQty(item, 15);
		System.out.println(order);
		assertEquals(15, order.getQty(item));
	}
	

	@Test
	public void testGetTotal() {
		order.add(item, 25);
		assertEquals(item.getPrice()*25, order.getTotal(), 0.01);
	}

	@Test
	public void testClose() {
		assertFalse(order.isClosed());
		order.close();
		assertTrue(order.isClosed());
	}

	@Test
	public void testIsCash() {
		assertFalse(order.isCash());
		order.setCash(true);
		assertTrue(order.isCash());
	}

	@Test
	public void testStatus() {
		assertEquals(OrderStatus.NEW, order.getStatus());
		order.setStatus(OrderStatus.PAID);
		assertEquals(OrderStatus.PAID, order.getStatus());
	}

	@Test
	public void testGetCreationDate() {
		assertEquals("1999-01-01", order.getCreationDate());
	}

}

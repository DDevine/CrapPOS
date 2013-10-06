package au.edu.griffith.ict.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.edu.griffith.ict.MenuItem;

/** JUnit test for the MenuItem class
 *	@author Josh Stover 
 */
public class MenuItemTest {

	MenuItem item;
	
	@Before
	public void setUp() throws Exception {
		item = new MenuItem(1234,"banana", 4.20F);
	}

	@Test
	public void testMenuItem() {
		assertNotNull(item);
	}

	@Test
	public void testGetPrice() {
		assertEquals(4.20, item.getPrice(), 0.001);
	}

	@Test
	public void testSetPricePerUnit() {
		item.setPricePerUnit(1.80F);
		assertEquals(1.80, item.getPrice(), 0.001);
	}

	@Test
	public void testGetName() {
		assertEquals("banana", item.getName());
	}

	@Test
	public void testGetItemNo() {
		assertEquals(1234, item.getItemNo());
	}

}

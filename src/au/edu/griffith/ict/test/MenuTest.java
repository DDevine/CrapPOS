package au.edu.griffith.ict.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import au.edu.griffith.ict.Menu;
import au.edu.griffith.ict.MenuItem;

/** JUnit test for the Menu class
 *	@author Josh Stover 
 */
public class MenuTest {

	Menu menu;
	MenuItem item;
	
	@Before
	public void setUp() throws Exception {
		menu = new Menu();
		
		File.createTempFile("menu", null);
        menu.loadDatabase(System.getProperty("user.dir") + File.separatorChar + "menu.txt");
        
		item = new MenuItem(0,"banana", 2.20F);
		menu.newItem(item);
	}

	@Test
	public void testMenu() {
		assertNotNull(menu);
	}
	
	@Test
	public void testGetItem() {
		assertEquals(item, menu.getItem(0));
		
	}

	@Test
	public void testRemoveItem() {
		menu.removeItem(0);
		assertNull(menu.getItem(0));
	}

}

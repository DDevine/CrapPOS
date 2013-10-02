package au.edu.griffith.ict;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
	// custManager: CustomerManager
	// itemManager: Menu
	// orders: Order[*]
	// users: User[*]
	
	/** A reference to the Customer Manager*/
	private CustomerManager customers;
	
	/** A reference to the Menu */
	private Menu menu;
	
	/** A non-persistent list of Orders */
	private LinkedList<Order> orders;
	
	/** A non-presistent list of Users */
	private HashMap<Integer, User> users;
	
	/** A running total of the day's takings. A day is considered to be the time since the program started. */
	private float dayTotal; //This wasn't in the design docs.

    /** The Main method. */
	public static void main(String[] args){
		System.out.println("Hello World");
	}	

	/**
	* Adds an Order to the list of orders.
	* @param order The Order to add to the list.
	*/
	public void addOrder(Order order){
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	//void removeOrder(Order order){
		// Removes an Order from the database/array
	//}

	//Order[]  getOrders(Customer customer){
		// Gets an array of Order objects for a given Customer.
	//}

	//float getDayTotal(){
		// Return the total takings for the day... 
	//}
	
	//void display(){
		// Display... what?
	//}
	/*private Order buildOrder(){
		Order
	}*/
	
	/** Prints the menu out to the user */
	private void displayMenu(){
		for(int i = 0; i < menu.getItems(); i++){
			MenuItem item = menu.getItem(i);
			if(item == null) continue;
			System.out.printf("%.4f %.20s %.2f", item.getItemNo(), item.getName(), item.getPrice());
		}
	}
	
	/** 
	 * Requests the user to input a new menu item
	 * @return Displays the menu to the user and requests they enter an item.
	 * Returns null if there is no new item.
	 */
	private MenuItem requestItem(){
		System.out.println("Please select a menu item. To cancel, just press enter.");
		displayMenu();
		System.out.println("Menu item ID: ");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		if(s.isEmpty()) return null;
		try{
			MenuItem item = menu.getItem(Integer.parseInt(s));
			return item;
		}
		catch(NumberFormatException e){
			System.out.println(s + " is not a valid number! Cancelling.");
		}
		return null;
	}
	/** Requests the user input a new integer
	 * @return The int they entered	
	 */
	private int requestNumber(){
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		sc.close();
		return i;
	}
}

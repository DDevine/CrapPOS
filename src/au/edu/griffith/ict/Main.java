package au.edu.griffith.ict;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
	// custManager: CustomerManager
	// itemManager: Menu
	// orders: Order[*]
	// users: User[*]
	private CustomerManager customers;
	private Menu menu;
	private LinkedList<Order> orders;
	private HashMap<Integer, User> users;

	public static void main(String[] args){
		System.out.println("Hello World");
	}	

	//void addOrder(Order order){
		// Adds an order to the database/array
	//}
	
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
	/** Requests the user to input a new menu item */
	private MenuItem requestItem(){
		System.out.println("Please select a menu item.");
		for(int i = 0; i < menu.getItems(); i++){
			MenuItem item = menu.getItem(i);
			if(item == null) continue;
			System.out.printf("%.4f %.20s %.2f", item.getItemNo(), item.getName(), item.getPrice());
		}
		System.out.println("Menu item ID: ");
		Scanner sc = new Scanner(System.in);
		MenuItem item = menu.getItem(sc.nextInt());
		return item;
	}
}

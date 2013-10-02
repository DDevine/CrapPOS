package au.edu.griffith.ict;

import java.io.File;
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
    
    /** A running total of the day's takings. */
    private float dayTotal; //This wasn't in the design docs.

    /** The Main method. */
    public static void main(String[] args){
        System.out.println("Hello World");
        new Main();
    }
    public Main(){
    	customers = new CustomerManager();
    	menu = new Menu();
    	orders = new LinkedList<Order>();
    	users = new HashMap<Integer, User>();
    	String cwd = System.getProperty("user.dir");
    	menu.loadDatabase(cwd + File.separatorChar + "menu.txt");
        customers.loadDatabase(cwd + File.separator + "customers.txt");
        
        //Begin login system
        Scanner sc = new Scanner(System.in);
        User user = null;
        while(user == null){
            System.out.println("User ID: ");
            try{
                user = users.get(Integer.parseInt(sc.nextLine()));
                if(user == null){
                    System.out.println("No such user found!");
                    continue;
                }
                
                System.out.print("Password: ");
                String pass = sc.nextLine();
                if(pass.equals(user.getPassword()) == false){
                    System.out.println("Password incorrect.");
                    user = null;
                    continue;
                }
            }
            catch(NumberFormatException e){
                System.out.println("Invalid user ID# given!");
            }
        }
        System.out.println("Logged in as user #" + user.getStaffNo() + ".");
        sc.close();
        //End login system
        
    	
    	
    	//TODO: We need the login system and loads more here.
    	Order o = buildOrder();
    	
    	this.addOrder(o);
    	dayTotal += o.getTotal();
    }

    /**
    * Adds an Order to the list of orders.
    * @param order The Order to add to the list.
    */
    public void addOrder(Order order){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
    * Removes an order from the list of orders, effectively deleting the order.
    * @param order The order to be removed.
    */
    public void removeOrder(Order order){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
    * Gets an array of orders that belong to a given customer.
    * @param customer The customer to query for.
    * @return An array of Order objects.
    */
    public Order[]  getOrders(Customer customer){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
    * Get the total takings for the day. 
    * A day is considered to be the time since the program started.
    * @return A float representing the day's takings.
    */
    public float getDayTotal(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
    * Display the answer to the meaning of life (42).
    */
    public void display(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
    * An Order factory.
    * @return An initialised Order object.
    */
    private Order buildOrder(){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Customer Phone #: ");
        String phone = sc.nextLine();
        Customer c = customers.get(phone);
        if(c == null){
        	System.out.println("Creating new customer.");
        	System.out.print("Name: ");
        	String name = sc.nextLine();
        	System.out.print("Address: ");
        	String address = sc.nextLine();
        	c = new Customer(phone, address, name);
        }
        
        Order o = new Order(c);
        
        displayMenu();
        System.out.println("Please select the menu items from above.");
        System.out.println("Enter a blank line once completed.");
        System.out.print("Item ID: ");
        String s = sc.nextLine();
        while(s.isEmpty() == false){
        	
        	
        	try{
        		MenuItem item = menu.getItem(Integer.parseInt(s));
        		System.out.print("Quantity: ");
        		s = sc.nextLine();
        		int amount = Integer.parseInt(s);
        		
        		System.out.print("Confirm (Y/N) " + item.toString() + " x" + amount + "?: ");
        		s = sc.nextLine();
        		if(s.equalsIgnoreCase("Y") == false){
        			System.out.println("Item cancelled.");
        			continue;
        		}
        		else{
        			o.add(item, amount);
        			System.out.println("Item added.");
        		}
        	}
        	catch(NumberFormatException e){
        		System.out.println("Invalid number given: " + s);
        	}
        	System.out.print("Item ID: ");
        	s = sc.nextLine();
        }
        System.out.print("Delivery (Y/N)?: ");
        s = sc.nextLine();
        //TODO: We need a method to set the order as delivery
        
        System.out.print("Cash (Y/N)?: ");
        s = sc.nextLine();
        o.setIsCash(s.equalsIgnoreCase("Y"));
        System.out.println("Order completed.");
        sc.close();
        return o;
    }
    
    /** Prints the menu out to the user */
    private void displayMenu(){
    	System.out.printf("%-3s %-20.20s Price\n", "ID", "Item");
        for(int i = 0; i < menu.getItems(); i++){
            MenuItem item = menu.getItem(i);
            if(item == null) continue;
            System.out.printf("%-3d %-20.20s $%.2f\n", item.getItemNo(), item.getName(), item.getPrice());
        }
    }
}

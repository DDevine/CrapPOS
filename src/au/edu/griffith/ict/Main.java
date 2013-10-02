package au.edu.griffith.ict;

import java.io.File;
import java.io.IOException;
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
    
    /** The input for the program */
    private Scanner sc;
    public Main(){
        sc = new Scanner(System.in);
        customers = new CustomerManager();
        menu = new Menu();
        orders = new LinkedList<Order>();
        users = new HashMap<Integer, User>();
        String cwd = System.getProperty("user.dir");
        menu.loadDatabase(cwd + File.separatorChar + "menu.txt");
        customers.loadDatabase(cwd + File.separator + "customers.txt");
        
        //Begin loading users file
        File usersFile = new File(cwd + File.separator + "users.txt");
        try{
            usersFile.createNewFile();
            Scanner userSc = new Scanner(usersFile);
            while(userSc.hasNextLine()){
                String s = userSc.nextLine();
                try{
                    //USERID--Password--isAdmin
                    String[] data = s.split("--");
                    int id = Integer.parseInt(data[0]);
                    String pass = data[1];
                    boolean isAdmin = data[2].equalsIgnoreCase("Y");
                    User u = new User(id, pass, isAdmin);
                    users.put(u.getStaffNo(), u);
                }
                catch(Exception e){
                    System.out.println("Error loading user: " + s + ", continuing");
                    continue;
                }
            }
            userSc.close();
        }
        catch(IOException e){
            e.printStackTrace();
            return;
        }
        //End loading users
        
        while(true){
	        //Begin login system
	        User user = null;
	        while(user == null){
	            System.out.print("User ID: ");
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
	        //End login system
	        
	        while(user != null){
	        	System.out.println();
		        System.out.println("What do you wish to do?");
		        System.out.println("Order handling (O)");
		        System.out.println("Menu Handling (M)");
		        System.out.println("User Handling (U)");
		        System.out.print("Option: ");
		        
		        String s = sc.nextLine();
		        System.out.println();
		        
		        if(s.isEmpty()) continue;
		        
		        s = s.substring(0, 1).toUpperCase();
		        
		        if(s.equals("O")){
		        	System.out.println("=== Customer Handling ===");
			        System.out.println("Create Order (C)");
			        System.out.println("List Orders (L)");
			        System.out.println("Close Order (F)");
			        System.out.println("Delete Orders (D)");
			        System.out.print("Option: ");
			        
		        	s = sc.nextLine().substring(0, 1).toUpperCase();
		        	System.out.println();
		        	if(s.equals("C")){
		        		Order o = buildOrder();
			        	this.addOrder(o);
			            dayTotal += o.getTotal();
			            System.out.println("Order #" + orders.indexOf(o) + " added: \n" + o.toString());
		        	}
		        	else if(s.equals("L")){
		        		System.out.println("Orders (" + orders.size() + "):");
			        	for(int i = 0; i < orders.size(); i++){
			        		Order o = orders.get(i);
			        		System.out.println("Order #" + i + ":\n" + o.toString());
			        	}
		        	}
		        	else if(s.equals("F")){
		        		try{
		        			System.out.println("Orders (" + orders.size() + "):");
				        	for(int i = 0; i < orders.size(); i++){
				        		Order o = orders.get(i);
				        		System.out.println("Order #" + i + ":\n" + o.toString());
				        	}
		        			System.out.print("Close order #: ");
		        			int id = Integer.parseInt(sc.nextLine());
		        			if(id < 0 || id >= orders.size()){
		        				System.out.println("Invalid order requested.");
		        				continue;
		        			}
		        			Order o = orders.get(id);
		        			o.close();
		        			System.out.println("Order closed.");
		        		}
		        		catch(NumberFormatException e){
		        			System.out.println("Invalid ID given.");
		        			continue;
		        		}
		        	}
		        	else if(s.equals("D")){
		        		try{
		        			System.out.println("Orders (" + orders.size() + "):");
				        	for(int i = 0; i < orders.size(); i++){
				        		Order o = orders.get(i);
				        		System.out.println("Order #" + i + ":\n" + o.toString());
				        	}
		        			System.out.print("Delete/Remove order #: ");
		        			int id = Integer.parseInt(sc.nextLine());
		        			if(id < 0 || id >= orders.size()){
		        				System.out.println("Invalid order requested.");
		        				continue;
		        			}
		        			Order o = orders.remove(id);
		        			o.close();
		        			System.out.println("Order closed & Deleted.");
		        		}
		        		catch(NumberFormatException e){
		        			System.out.println("Invalid ID given.");
		        			continue;
		        		}
		        	}
		        	else{
		        		System.out.println("Unrecognised command.");
		        		continue;
		        	}
		        }
		        else if(s.equals("M")){
		        	System.out.println("=== Menu Handling ===");
			        System.out.println("Add Menu Item (A)");
			        System.out.println("Remove Menu Item (R)");
			        System.out.println("Display Menu (L)");
			        System.out.print("Option: ");
			        
		        	s = sc.nextLine().substring(0, 1).toUpperCase();
		        	System.out.println();
		        	if(s.equals("A")){
		        		try{
			        		//Add a menu item.
				        	System.out.print("Item Name: ");
				        	String name = sc.nextLine();
				        	System.out.print("Item Price: $");
				        	float price = Float.parseFloat(sc.nextLine());
				        	
				        	MenuItem item = new MenuItem(menu.nextItemNo(), name, price);
				        	menu.newItem(item);
				        	System.out.println("New item added: " + item.toString());
			        	}
			        	catch(NumberFormatException e){
			        		System.out.println("Invalid number supplied.");
			        	}
		        	}
		        	else if(s.equals("R")){
		        		try{
			        		//Add a menu item.
				        	System.out.print("Item ID: ");
				        	String st = sc.nextLine();
				        	int id = Integer.parseInt(st);
				        	if(menu.removeItem(id) == false){
				        		System.out.println("Item ID " + st + " not found.");
				        	}
				        	else{
				        		System.out.println("Success.");
				        	}
			        	}
			        	catch(NumberFormatException e){
			        		System.out.println("Invalid number supplied.");
			        	}
		        	}
		        	else if(s.equals("L")){
		        		System.out.println(menu.toString());
		        	}
		        	else{
		        		System.out.println("Unrecognised command.");
		        		continue;
		        	}
		        }
		        else if(s.equals("U")){
		        	System.out.println("=== User Handling ===");
			        System.out.println("Logout (Q)");
			        System.out.println("Create User (C)");
			        System.out.println("Delete User (D)"); 
			        System.out.println("Edit User (E)"); 
			        System.out.print("Option: ");
			        
		        	s = sc.nextLine().substring(0, 1).toUpperCase();
		        	System.out.println();
		        	if(s.equals("C")){
		        		//Create user
			        	try{
				        	System.out.print("User ID: ");
				        	int id = Integer.parseInt(sc.nextLine());
				        	System.out.print("Password: ");
				        	String pass = sc.nextLine();
				        	System.out.print("Give Admin Rights? (Y/N): ");
				        	boolean isAdmin = sc.nextLine().equalsIgnoreCase("Y");
				        	User u = new User(id, pass, isAdmin);
				        	users.put(u.getStaffNo(), u);
				        	System.out.println("User created!");
			        	}
			        	catch(NumberFormatException e){
			        		System.out.println("Invalid User ID supplied.");
			        	}
		        	}
		        	else if(s.equals("D")){
		        		System.out.print("Delete User ID: ");
			        	try{
			        		int id = Integer.parseInt(sc.nextLine());
			        		if(users.remove(id) != null){
			        			System.out.println("Deletion success.");
			        		}
			        		else{
			        			System.out.println("User not found.");
			        		}
			        	}
			        	catch(NumberFormatException e){
			        		System.out.println("Invalid ID specified!");
			        	}
		        	}
		        	else if(s.equals("E")){
		        		System.out.print("Edit User ID: ");
			        	try{
			        		int id = Integer.parseInt(sc.nextLine());
			        		if(users.containsKey(id) == false){ //No such user
			        			System.out.println("Invalid user ID specified!");
			        			continue;
			        		}
			        		
			        		System.out.print("New Password: ");
				        	String pass = sc.nextLine();
				        	System.out.print("Give Admin Rights? (Y/N): ");
				        	boolean isAdmin = sc.nextLine().equalsIgnoreCase("Y");
				        	User u = new User(id, pass, isAdmin);
				        	users.put(u.getStaffNo(), u);
				        	System.out.println("User modified!");
			        	}
			        	catch(NumberFormatException e){
			        		System.out.println("Invalid ID specified!");
			        	}
		        	}
		        	else if(s.equals("Q")){
		        		user = null;
		        	}
		        	else{
		        		System.out.println("Unrecognised command.");
		        		continue;
		        	}
		        }
		        else{
		        	System.out.println("Unrecognised command.");
	        		continue;
		        }
	        }
        }
        
    }

    /**
    * Adds an Order to the list of orders.
    * @param order The Order to add to the list.
    */
    public void addOrder(Order order){
        orders.add(order);
        dayTotal += order.getTotal();
    }
    
    /**
    * Removes an order from the list of orders, effectively deleting the order.
    * @param order The order to be removed.
    */
    public void removeOrder(Order order){
    	if(!order.getClosed()) order.close();
    	if(orders.remove(order)){
    		//We had the order listed, so we should remove its total from the days total if we're deleting it.
    		dayTotal -= order.getTotal();
    	}
    }

    /**
    * Gets an array of orders that belong to a given customer.
    * @param customer The customer to query for.
    * @return An array of Order objects.
    */
    public Order[]  getOrders(Customer customer){
        LinkedList<Order> orders = new LinkedList<Order>();
        for(Order o : this.orders){
            if(o.getCustomer().equals(customer)){
                orders.add(o);
            }
        }
        return orders.toArray(new Order[orders.size()]);
    }

    /**
    * Get the total takings for the day. 
    * A day is considered to be the time since the program started.
    * @return A float representing the day's takings.
    */
    public float getDayTotal(){
        return dayTotal;
    }
    
    /**
    * Display the answer to the meaning of life (42).
    * Is this meant to display the menu or something?
    */
    public void display(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
    * An Order factory.
    * @return An initialised Order object.
    */
    private Order buildOrder(){
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
        
        System.out.println(menu.toString());
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
        o.setIsDelivery(s.equalsIgnoreCase("Y"));
        
        System.out.print("Cash (Y/N)?: ");
        s = sc.nextLine();
        o.setIsCash(s.equalsIgnoreCase("Y"));
        System.out.println("Order completed.");
        return o;
    }
}

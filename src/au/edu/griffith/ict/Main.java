package au.edu.griffith.ict;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * The main class for the program. Also contains UI.
 *
 * @author Dirk Jamieson, Alex Cumberland, Daniel Devine
 */
public class Main {
    /** The file which the users are stored in */
    private final String USERS_FILE = "users.txt";
    
    /** A reference to the Customer Manager*/
    private CustomerManager customers;
    
    /** A reference to the Menu */
    private Menu menu;
    
    /** A non-persistent list of Orders */
    private LinkedList<Order> orders;
    
    /** A running total of the day's takings. */
    private float dayTotal; //This wasn't in the design docs.
        
    /** The input for the program */
    private Scanner sc;

    /** The Main method. */
    public static void main(String[] args){
        new Main();
    }
    
    public Main(){
        sc = new Scanner(System.in);
        customers = new CustomerManager();
        menu = new Menu();
        orders = new LinkedList<Order>();
        String cwd = System.getProperty("user.dir");
            
        menu.loadDatabase(cwd + File.separatorChar + "menu.txt");
        customers.loadDatabase(cwd + File.separator + "customers.txt");
            
        while(true){
            // Main Menu Options
            System.out.println();
            System.out.println("What do you wish to do?");
            System.out.println("Order handling (O)");
            System.out.println("Menu Handling (M)");
            System.out.println("User Handling (U)");
            System.out.println("Sales Summary (S)");
            System.out.print("Option: ");
            
            String s = sc.nextLine();
            System.out.println();
            
            //This would cause an error otherwise.
            if(s.isEmpty()) continue;
            //Only take the first letter
            s = s.substring(0, 1).toUpperCase();
            
            // Order Menu
            if(s.equals("O")){
                System.out.println("=== Order Handling ===");
                System.out.println("Create Order (C)");
                System.out.println("Pay Order (P)");
                System.out.println("List Orders (L)");
                System.out.println("Close Order (F)");
                System.out.println("Delete Orders (D)");
                System.out.print("Option: ");
                    
                s = sc.nextLine();
                if(s.isEmpty()) continue;
                s = s.substring(0, 1).toUpperCase();
                System.out.println();
                    
                // Create order
                if(s.equals("C")){
                    Order o = buildOrder();
                    this.addOrder(o);
                    dayTotal += o.getTotal();
                    System.out.println("Order #" + orders.indexOf(o) + " added: \n" + o.toString());
                }
                    
                // List order
                else if(s.equals("L")){
                    System.out.println("Orders (" + orders.size() + "):");
                    for(int i = 0; i < orders.size(); i++){
                        Order o = orders.get(i);
                        System.out.println("Order #" + i + ":\n" + o.toString());
                    }
                }
                    
            // Pay order
            else if(s.equals("P")){
                    try{
                        System.out.println("Orders (" + orders.size() + "):");
                        for(int i = 0; i < orders.size(); i++){
                            Order o = orders.get(i);
                            System.out.println("Order #" + i + ":\n" + o.toString());
                        }
                        System.out.print("Pay order #: ");
                        int id = Integer.parseInt(sc.nextLine());
                        if(id < 0 || id >= orders.size()){
                            System.out.println("Invalid order requested.");
                            continue;
                        }
                        Order o = orders.get(id);
                        o.setStatus(OrderStatus.PAID);
                        System.out.println("Order status is now Paid.");
                    }
                    catch(NumberFormatException e){
                        System.out.println("Invalid ID given.");
                        continue;
                    }
                }
                    
                // Close order
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
                
                // Delete order
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
            
            // * Menu Menu (Ha!)
            else if(s.equals("M")){
                System.out.println("=== Menu Handling ===");
                System.out.println("Add Menu Item (A)");
                System.out.println("Edit Menu Item (E)");
                System.out.println("Remove Menu Item (R)");
                System.out.println("Display Menu (L)");
                System.out.print("Option: ");
                
                s = sc.nextLine();
                if(s.isEmpty()) continue;
                s = s.substring(0, 1).toUpperCase();
                System.out.println();
                    
                // Add a new menu item
                if(s.equals("A")){
                    try{
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
            
                // Edit Menu Item
                else if(s.equals("E")){
                    try{
                        System.out.print("Item ID: ");
                        String st = sc.nextLine();
                        int id = Integer.parseInt(st);
                        MenuItem item = menu.getItem(id);
                        if(item == null){
                            System.out.println("Invalid menu item ID supplied.");
                            continue;
                        } else {
                            System.out.print("Price Per Unit: ");
                            st = sc.nextLine();
                            float p = Float.parseFloat(st);
                            item.setPricePerUnit(p);
                            menu.updateItem(item);
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Invalid number supplied.");
                    }
                }
            
                // Remove menu item 
                else if(s.equals("R")){
                    try{
                        System.out.print("Item ID: ");
                        String st = sc.nextLine();
                        int id = Integer.parseInt(st);
                        if(menu.removeItem(id) == false){
                            System.out.println("Item ID " + st + " not found.");
                        } else {
                            System.out.println("Success.");
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Invalid number supplied.");
                    }
                }
            
                // List menu
                else if(s.equals("L")){
                    System.out.println(menu.toString());
                } else {
                    System.out.println("Unrecognised command.");
                    continue;
                }
            }
            
            //Sales Summary Menu
            else if(s.equals("S")){
                System.out.printf("==== COES Sales Report ====\n");
                System.out.printf("%-10s %10s %10s\n", "Date", "Sales #", "Total");
                HashMap<String, LinkedList<Order>> data = new HashMap<String, LinkedList<Order>>();
                for(Order o : this.orders){
                    LinkedList<Order> list = data.get(o.getCreationDate());
                    if(list == null){
                        list = new LinkedList<Order>();
                        data.put(o.getCreationDate(), list);
                    }
                    list.add(o);
                }
            
                LinkedList<String> dates = new LinkedList<String>(data.keySet());
                Collections.sort(dates);
            
                for(String k : dates){
                    LinkedList<Order> orders = data.get(k);
                    int sales = 0;
                    float total = 0;
                    for(Order o : orders){
                        sales++;
                        total += o.getTotal();
                    }
                
                    System.out.printf("%10s ", k);
                    System.out.printf("%10d ", sales);
                    System.out.printf("$%10.2f\n", total);
                }
            } else {
                System.out.println("Unrecognised command.");
                continue;
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
        
        String s = null;
        
        while(s == null){
            System.out.print("Creation Date (YYYY-MM-DD): ");
            s = sc.nextLine();
            try{
                o.setCreationDate(s);
            }
            catch(IllegalArgumentException e){
                System.out.println("Invalid date supplied: " + e.getMessage());
                s = null; //Continue loop
            }
        }
        
        System.out.print("Delivery (Y/N)?: ");
        s = sc.nextLine();
        o.setIsDelivery(s.equalsIgnoreCase("Y"));
        
        System.out.print("Cash (Y/N)?: ");
        s = sc.nextLine();
        o.setIsCash(s.equalsIgnoreCase("Y"));
        
        System.out.println(menu.toString());
        System.out.println("Please select the menu items from above.");
        System.out.println("Enter a blank line once completed.");
        System.out.print("Item ID: ");
        s = sc.nextLine();
        while(s.isEmpty() == false){
            try{
                MenuItem item = menu.getItem(Integer.parseInt(s));
                if(item == null){
                    System.out.println("Invalid menu item supplied.");
                }
                else{
                    System.out.print("Quantity: ");
                    s = sc.nextLine();
                    int amount = Integer.parseInt(s);
                    
                    System.out.print("Confirm (Y/N) " + item.toString() + " x" + amount + "?: ");
                    s = sc.nextLine();
                    if(s.equalsIgnoreCase("Y") == false){
                        System.out.println("Item cancelled.");
                    }
                    else{
                        o.add(item, amount);
                        System.out.println("Item added.");
                    }
                }
            }
            catch(NumberFormatException e){
                System.out.println("Invalid number given: " + s);
            }
            System.out.printf("Total: $%.2f\n", o.getTotal());
            System.out.print("Item ID: ");
            s = sc.nextLine();
        }
        
        System.out.println("Order completed.");
        return o;
    }
}

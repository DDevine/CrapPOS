package au.edu.griffith.ict;

import java.util.HashMap;

public class Order{

    /** 
    * An order contains items - stored as an array of MenuItem objects.
    */    
    //private MenuItem[] items;
    private HashMap<MenuItem, Integer> items = new HashMap<MenuItem, Integer>();
    
    /**
    * The Customer to which the order belongs to.
    */
    private Customer cust;
    
    /**
    * True if the order is to be delivered rather than picked up.
    */
    private boolean delivery;
    
    /**
    * The total price of an order which must be updated as items are added and removed.
    */
    private float runningTotal;
    
    /**
    * Keep track of whether an order has been filled or not.
    */
    private boolean isClosed;
    
    /**
    * If True, the order is to be paid in cash.
    */
    private boolean isCash;
    
    /**
     * Creates a new order for the given customer.
     * This must be registered under the order manager
     * @param c The customer
     */
    public Order(Customer c){
        this.cust = c;
    }
    
    /**
    * Returns a reference to the customer.
    * @return Retruns a reference to a customer Object, or null.
    */
    public Customer getCustomer(){
        //throw new UnsupportedOperationException("Not implemented yet.");
        return cust;
    }
    
    /**
    * Returns whether the order is to be delivered or not.
    * @return Returns a boolean. True if the order is to be delivered, else False.
    */
    public boolean isDelivery(){
        //throw new UnsupportedOperationException("Not implemented yet.");
        return delivery;
    }
    
    /**
    * Add a number of MenuItems to the order.
    * @param item       A MenuItem object to be added.
    * @param quantity   The number of the MenuItem to be added.
    */
    public void add(MenuItem item, int quantity){
        //throw new UnsupportedOperationException("Not implemented yet.");
        if(items.containsKey(item)){
            int n = items.get(item);
            quantity += n;
        }
        items.put(item, quantity);
    }
    
    /**
    * Remove a number of items from an order.
    * @param item       A MenuItem object to remove from the order.
    * @param quantity   The number of items to remove.
    */
    public void remove(MenuItem item, int quantity){
        //throw new UnsupportedOperationException("Not implemented yet.");
        if(items.containsKey(item)){
            int n = items.get(item);
            quantity -= n;
        }
        if(quantity <= 0){
            items.remove(item);
        }
        else{
            items.put(item, quantity);
        }
    }
    
    /**
    * Set/change the number of MenuItems in an order.
    * @param item       The MenuItem object of which you want to change the number of.
    * @param quantity   The number of MenuItem objects the order is to have.
    */
    public void set(MenuItem item, int quantity){
        //throw new UnsupportedOperationException("Not implemented yet.");
        items.put(item, quantity);
    }
    
    /**
    * Get the quantity of a particular item on the order.
    * @param item       The MenuItem of which you want to retrieve the quantity for.
    */
    public int get(MenuItem item){
        //throw new UnsupportedOperationException("Not implemented yet.");
        if(items.containsKey(item)){
            /* Long story why this isn't simply "return items.get(item);"...
             * The map maps OBJECTS to OBJECTS. When an int is put in the map, it is wrapped as an INTEGER (Thanks to java's auto boxing)
             * If the map returns NULL for the value (as it should if the map does not contain that key), then this NULL value is casted
             * to the given type (Eg, a null INTEGER object).
             * 
             * Since we're dealing with basic types that cannot be null (int), when this NULL INTEGER is casted to an int, it will throw
             * a null pointer... because the INTEGER object is null.
             */
            return items.get(item); 
        }
        else{
            return 0;
        }
    }
    
    /**
    * Get the total price of the items on the order.
    */
    public float getTotal(){
        //throw new UnsupportedOperationException("Not implemented yet.");
        return runningTotal;
    }
    
    /**
    * Get the string representation of the order.
    */
    public String toString(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
    * Return whether the order has been closed or not.
    */
    public boolean getClosed(){
        //throw new UnsupportedOperationException("Not implemented yet.");
        return isClosed;
    }
    
    /**
    * Close the order.
    */
    public void close(){
        isClosed = true;
    }
    
    /**
    * Set whether an order is to be paid in Cash or not.
    * @param isCash     If True an order is paid with Cash.
    */
    public void setIsCash(boolean cash){
        isCash = cash;
    }
    
    /**
    * Return whether an order is to be paid in cash or not.
    */
    public boolean getIsCash(){
        return isCash;
    }
}

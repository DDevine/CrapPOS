package au.edu.griffith.ict;

public class Order{

    /** 
    * An order contains items - stored as an array of MenuItem objects.
    */    
    private MenuItem[] items;
    
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
    * Returns a reference to the customer.
    * @return Retruns a reference to a customer Object, or null.
    */
    public Customer getCustomer(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
    * Returns whether the order is to be delivered or not.
    * @return Returns a boolean. True if the order is to be delivered, else False.
    */
    public boolean isDelivery(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
    * Add a number of MenuItems to the order.
    * @param item       A MenuItem object to be added.
    * @param quantity   The number of the MenuItem to be added.
    */
    public void add(MenuItem item, int quantity){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
    * Remove a number of items from an order.
    * @param item       A MenuItem object to remove from the order.
    * @param quantity   The number of items to remove.
    */
    public void remove(MenuItem item, int quantity){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
    * Set/change the number of MenuItems in an order.
    * @param item       The MenuItem object of which you want to change the number of.
    * @param quantity   The number of MenuItem objects the order is to have.
    */
    public void set(MenuItem item, int quantity){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
    * Get the quantity of a particular item on the order.
    * @param item       The MenuItem of which you want to retrieve the quantity for.
    */
    public int get(MenuItem item){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
    * Get the total price of the items on the order.
    */
    public float getTotal(){
        throw new UnsupportedOperationException("Not implemented yet.");
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
        throw new UnsupportedOperationException("Not implemented yet.");
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
    public void(boolean cash){
        isCash = cash;
    }
    
    /**
    * Return whether an order is to be paid in cash or not.
    */
    public boolean getIsCash(){
        return isCash
    }
}

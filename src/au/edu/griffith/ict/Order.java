package au.edu.griffith.ict;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Josh Stover, Alex Cumberland
 */
public class Order{

    /** 
    * An order contains items - stored as an array of MenuItem objects.
    */    
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
     * NEW or PAID.
     */
    private OrderStatus status = OrderStatus.NEW;
    
    /**
     * The date the order was created. Formatted as yyyy-mm-dd (eg, 2012-07-25)
     */
    private String createdDate;
    
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
        return cust;
    }
    
    /**
    * Returns whether the order is to be delivered or not.
    * @return Returns a boolean. True if the order is to be delivered, else False.
    */
    public boolean isDelivery(){
        return delivery;
    }
    
    /**
     * Sets the delivery status of this order (Whether it is to be delivered or not)
     * @param deliver Whether the order is to be delivered
     */
    public void setDelivery(boolean deliver){
        this.delivery = deliver;
    }
    
    /**
    * Add a number of MenuItems to the order.
    * @param item       A MenuItem object to be added.
    * @param quantity   The number of the MenuItem to be added.
    */
    public void add(MenuItem item, int quantity){
        if(items.containsKey(item)){
            int n = items.get(item);
            quantity += n;
        }
        this.setQty(item, quantity);
    }
    
    /**
    * Remove a number of items from an order.
    * @param item       A MenuItem object to remove from the order.
    * @param quantity   The number of items to remove.
    */
    public void remove(MenuItem item, int quantity){
        if(items.containsKey(item)){
            int n = items.get(item);
            quantity -= n;
        }
        if(quantity <= 0){
            this.setQty(item, 0);
        }
        else{
            this.setQty(item, quantity);
        }
    }
    
    /**
    * Set/change the number of MenuItems in an order.
    * @param item       The MenuItem object of which you want to change the number of.
    * @param quantity   The number of MenuItem objects the order is to have.
    */
    public void setQty(MenuItem item, int quantity){
        if(items.get(item) != null){
            int amount = items.get(item);
            runningTotal -= (amount * item.getPrice());
        }
        if(quantity <= 0){
            items.remove(item);
            return;
        }
        items.put(item, quantity);
        runningTotal += quantity * item.getPrice();
    }
    
    /**
    * Get the quantity of a particular item on the order.
    * @param item       The MenuItem of which you want to retrieve the quantity for.
    */
    public int getQty(MenuItem item){
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
        return runningTotal;
    }
    
    /**
    * Return whether the order has been closed or not.
    */
    public boolean isClosed(){
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
    public void setCash(boolean cash){
        isCash = cash;
    }
    
    /**
    * Return whether an order is to be paid in cash or not.
    */
    public boolean isCash(){
        return isCash;
    }
    
    /**
     * Returns the status of this order (NEW, or PAID)
     * @return the status of this order (NEW, or PAID)
     */
    public OrderStatus getStatus(){
        return status;
    }
    
    /**
     * Marks this order with the given status (NEW or PAID)
     * @param status The status (NEW or PAID)
     */
    public void setStatus(OrderStatus status){
        this.status = status;
    }
    
    /**
     * The date that this order was created, in the format YYYY-MM-DD
     * @return The date that this order was created, in the format YYYY-MM-DD
     */
    public String getCreationDate(){
        return createdDate;
    }
    
    /**
     * Sets the date this order was created, in the format YYYY-MM-DD
     * @param date The date this order was created in the format YYYY-MM-DD
     * @throws IllegalArgumentException if the date is not in the format YYYY-MM-DD
     */
    public void setCreationDate(String date){
        Pattern p = Pattern.compile("\\d\\d\\d\\d-[0-1]\\d-[0-3]\\d"); 
        Matcher m = p.matcher(date);
        if(m.matches() == false){
            throw new IllegalArgumentException("Invalid date given: " + date + ", must be of format yyyy-mm-dd.");
        }
        this.createdDate = date;
    }
    
    /**
     * A string representation of this order.
     * This is returned in the format:
     * 
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Customer: " + cust.getPhoneNo());
        sb.append("Name: " + cust.getName());
        sb.append("\n=======");
        sb.append("\nItems:\n");
        
        sb.append(String.format("%-3s %-20.20s Price\n", "ID", "Item"));
        float total = 0;
        for(MenuItem item : items.keySet()){
            if(item == null) continue;
            int amount = items.get(item);
            sb.append(String.format("%-3d %-20.20s $%-3.2f x%d\n", item.getItemNo(), item.getName(), item.getPrice(), amount));
            total += amount * item.getPrice();
        }
        sb.append("=======\n");
        sb.append(String.format("Total: $%.2f\n", total));
        sb.append("Delivered: " + (delivery ? "Y" : "N"));
        if(delivery) sb.append("Address: " + cust.getAddress());
        sb.append("\nCash: " + (isCash ? "Y" : "N"));
        sb.append("\nClosed: " + (isClosed ? "Y" : "N"));
        sb.append("\nStatus: " + status);
        sb.append("\n");
        
        return sb.toString();
    }
}

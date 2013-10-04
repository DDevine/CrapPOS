package au.edu.griffith.ict;

/**
 * The Customer class represents information about a customer.
 * @author Ryan Beruldsen
 */
public class Customer{
    /** The customers phone number */
    private String phNo;
    /** The customers home address */
    private String address;
    /** The customers name */
    private String name;
    
    /**
     * Creates a new customer. This constructor does not save the custmoer into a CustomerManager's database, that must be done manually.
     * @param phNumber The phone number for the customer
     * @param address The address of the customer
     * @param name The full name of the customer
     */
    public Customer(String phNumber, String address, String name){
        this.phNo = phNumber;
        this.address = address;
        this.name = name;
    }
    
    /**
     * @return The customers phone number
     */     
    public String getPhoneNo(){
        return phNo;
    }
    
    /**
     * @return The customers home address
     */
    public String getAddress(){
        return address;
    }
    
    /**
     * @return The customers full name
     */
     public String getName(){
        return name;
    }
    
    /**
     * Sets the customers phone number (Should not this field be final? -Dirk)
     * @param phNo The customers phone number
     */
    public void setPhoneNo(String phNo){
        this.phNo = phNo;
    }
    
    /**
     * Sets the customers address
     * @param address The address of the customer
     */
    public void setAddress(String address){
        this.address = address;
    }
    
    /**
     * Sets the customers name
     * @param name The name of the customer
     */
    public void setName(String name){
        this.name = name;
    }
}

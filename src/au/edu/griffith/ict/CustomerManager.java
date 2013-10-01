package au.edu.griffith.ict;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents a CustomerManager class, which manages and controls customer loading and saving.
 *
 * @author Dirk
 */
public class CustomerManager implements Manager{
	/** The file which the CustomerManager is based on */
	private File file;
	/** A map of Phone Number to Customer */
	private HashMap<String, Customer> customers = new HashMap<String, Customer>();
	
	/**
	 * Saves and stores the given customer in the database.
	 * @param cust The customer to save and store.
	 * This saves to file.
	 */
	public void newCustomer(Customer cust){
		customers.put(cust.getPhoneNo(), cust);
		this.save();
	}
	/**
	 * Updates the given customer in the database if it's fields have changed.
	 * @param cust The customer to update.
	 * This saves to file.
	 */
	public void update(Customer cust){
		this.save();
	}
	/**
	 * Removes the given customer form the database.
	 * @param cust The customer to remove
	 * This saves to file.
	 */
	public void remove(Customer cust){
		customers.remove(cust.getPhoneNo());
		this.save();
	}
	/**
	 * Retrieves a customer from the database with the give phone number.
	 * @param phNo The phone number to search by.
	 * @return The customer, or null if it was not found.
	 */
	public Customer get(String phNo){
		return customers.get(phNo);
	}
	
	public boolean loadDatabase(String file){
		try{
			File f = new File(file);
			this.file = f;
			f.createNewFile(); //This doesn't create a new file if it exists already.
			Scanner sc = new Scanner(f);
			
			String s;
			while(sc.hasNextLine()){
				s = sc.nextLine();
				
				try{
					//God forgive me for being this lazy.
					String[] data = s.split("--");
					String name = data[0];
					String phNum = data[1];
					String address = data[2];
					
					Customer c = new Customer(phNum, address, name);
					customers.put(c.getPhoneNo(), c);
				}
				catch(IndexOutOfBoundsException e){
					e.printStackTrace(); //DEBUG
					//Skip this customer, the entry seems to be invalid.
					continue;
				}
				
				
			}
		}
		catch(IOException e){
			e.printStackTrace(); //DEBUG info
			return false;
		}
		return true;
	}
	/**
	 * Saves the database to disk.
	 * The loadDatabase() method must have been called first.
	 */
	protected void save(){
		if(file == null){
			throw new RuntimeException("The CustomerManager class has not been loaded: it cannot be saved!");
		}
		
		try {
			PrintStream out = new PrintStream(file);
			for(Customer c : customers.values()){
				out.print(c.getName() + "--");
				out.print(c.getPhoneNo() + "--");
				out.print(c.getAddress());
				out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace(); //DEBUG
			//Irrecoverable.
		}
	}
}
package au.edu.griffith.ict;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Menu implements Manager{
	/**
	 * A map of ItemIDNumber to MenuItem
	 */
	private HashMap<Integer, MenuItem> menuItems = new HashMap<Integer, MenuItem>();
	
	/**
	 * The next ID number which can be used in the menu
	 */
    private int nextIDNo;
    
    /**
     * The file to read/write this menu to/from
     */
    private File file;
    
    /**
     * Fetches the item with the given unique ID
     * @param itemNo The item number to search for.
     * @return The item number, or null if it was not found.
     */
    public MenuItem getItem(int itemNo){
       return menuItems.get(itemNo);
    }
    
    /**
     * Saves this menu to disk.
     * @param item The item that was modified.
     */
    public void updateItem(MenuItem item){
        this.save();
    }
    
    @Override
    public boolean loadDatabase(String file) {
        try{
            this.file = new File(file);
            this.file.createNewFile();
            
            Scanner sc = new Scanner(this.file);
            String s;
            while(sc.hasNextLine()){
                try{
                    s = sc.nextLine();
                    String[] data = s.split("--");
                    
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    float price = Float.parseFloat(data[2]);
                    
                    MenuItem item = new MenuItem(id, name, price);
                    
                    menuItems.put(item.getItemNo(), item);
                    
                    nextIDNo = Math.max(nextIDNo, item.getItemNo() + 1);
                }
                catch(Exception e){
                    e.printStackTrace(); //DEBUG
                    //Something went wrong with loading this item. (Corrupt?)
                    continue; //Continue trying the next
                }
            }
        }
        catch(IOException e){
            e.printStackTrace(); //DEBUG
            return false;
        }
        return true;
    }
    
    /**
     * Saves this menu to file 
     */
    private void save(){
        try{
            PrintStream ps = new PrintStream(file);
            for(MenuItem it : menuItems.values()){
            	if(it == null) continue;
            	ps.print(it.getItemNo() + "--");
                ps.print(it.getName() + "--");
                ps.print(it.getPrice() + "--");
                ps.println();
            }
            ps.close();
        }
        catch(IOException e){
            //Irrecoverable error
            e.printStackTrace();
        }
    }

    /**
     * Registers a new MenuItem on this menu.
     * @param item The item to register
     */
    public void newItem(MenuItem item){
    	menuItems.put(item.getItemNo(), item);
        this.save();
    }
    
    /**
     * The next menu item's unique ID. Call this method
     * when creating a new menu item.
     * @return The menu item's ID
     */
    public int nextItemNo(){
        return nextIDNo++;
    }

    /**
     * Removes the item with the given item number from the menu.
     * @param itemNo The unique ID of the item to remove
     * @return true on success, false if the item was not on the menu.
     */
    public boolean removeItem(int itemNo){
        if(menuItems.remove(itemNo) != null){
        	this.save();
            return true;
        }
        return false; //Not in the item list
    }
    
    /**
     * Returns a text representation of the menu in the format:
     * ID	Item	Price
     * 0	Pizza	$9.50
     * 1	Apples	$0.30
     */
    @Override
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(String.format("%-3s %-20.20s Price\n", "ID", "Item"));
    	for(MenuItem item : menuItems.values()){
            if(item == null) continue;
            sb.append(String.format("%-3d %-20.20s $%.2f\n", item.getItemNo(), item.getName(), item.getPrice()));
        }
        return sb.toString();
    }
}

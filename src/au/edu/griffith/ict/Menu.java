package au.edu.griffith.ict;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Menu implements Manager{
    //This would be so much easier with an array list.
    /** The menuItems array stores items that are on a menu. */
    private MenuItem[] menuItems = new MenuItem[3]; // Use menuItem.getNo() as UNIQUE ID here.
    /** Keeps track of the next available ID number */
    private int nextIDNo;
    /** A reference to the file that the menu is serialised to and deserialised from. */
    private File file;
    
    //Why the hell does our UML have a price field in the MENU for?\
    
    /** 
    * Get a MenuItem from the menu identified by it's item number.
    * @param itemNo The identifier for a particular item.
    * @return A MenuItem object that matches the identifier, else null.
    */
    public MenuItem getItem(int itemNo){
        //Error checking
        if(menuItems.length <= itemNo || itemNo < 0){
            return null;
        }
        return menuItems[itemNo];
    }
    
    /**
    * This method does not make sense.
    */
    public void updateItem(MenuItem item){
        this.save();
    }
    
    /**
    * Deserialise a Menu from a database given a file to use as a database.
    * @param file The file to use as the database.
    * @return Return True if the database was found and successfully loaded.
    */
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
                    
                    if(menuItems.length <= item.getItemNo()){
                        //Resize the array
                        MenuItem[] newItems = new MenuItem[item.getItemNo() + 10]; //10 = magic number.
                        System.arraycopy(menuItems, 0, newItems, 0, menuItems.length);
                        this.menuItems = newItems;
                    }
                    
                    menuItems[item.getItemNo()] = item;
                    
                    //There should be a better way of doing this, without item numbers. (Eg a freaking list)
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
    * Serialise the MenuItems on the Menu and write them to the database.
    */
    private void save(){
        try{
            PrintStream ps = new PrintStream(file);
            for(int i = 0; i < menuItems.length; i++){
                if(menuItems[i] == null) continue; //Skip null items.
                MenuItem it = menuItems[i];
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
    * Add a new item to the menu.
    * @param item The item to be added to the menu.
    */
    public void newItem(MenuItem item){
        if(menuItems.length <= item.getItemNo()){
            //Resize the array
            MenuItem[] newItems = new MenuItem[item.getItemNo() + 10]; //10 = magic number.
            System.arraycopy(menuItems, 0, newItems, 0, menuItems.length);
            this.menuItems = newItems;
        }
        menuItems[item.getItemNo()] = item;
        this.save();
    }
    
    /**
    * Increment and return the next available Item ID number.
    * @return An int representing the next unused item ID number.
    */
    public int nextItemNo(){
        return nextIDNo++;
    }
    
    /**
    * Return the number of items in the menu.
    * @return An integer which represents the number of items in the menu.
    */
    public int getItems(){
        return nextIDNo - 1;
    }

    /**
    * Remove an item from the menu.
    * @param itemNo The item number of the item to be removed.
    * @return Returns True if the item was successfully removed. If the item number does not exist within the menu it will return False.
    */
    public boolean removeItem(int itemNo){
        if(itemNo < 0 || itemNo >= menuItems.length || menuItems[itemNo] == null){
            return false;
        }
        menuItems[itemNo] = null;
        this.save();
        return true;
    }
}

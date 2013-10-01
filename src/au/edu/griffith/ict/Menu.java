package au.edu.griffith.ict;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Menu implements Manager{
	//This would be so much easier with an array list.
	private MenuItem[] menuItems = new MenuItem[3]; // Use menuItem.getNo() as UNIQUE ID here.
	private int nextIDNo;
	private File file;
	
	//Why the hell does our UML have a price field in the MENU for?\
	
	public MenuItem getItem(int itemNo){
		//Error checking
		if(menuItems.length <= itemNo || itemNo < 0){
			return null;
		}
		return menuItems[itemNo];
	}
	
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
	
	public int nextItemNo(){
		return nextIDNo;
	}

	
	public boolean removeItem(int itemNo){
		if(itemNo < 0 || itemNo >= menuItems.length || menuItems[itemNo] == null){
			return false;
		}
		menuItems[itemNo] = null;
		this.save();
		return true;
	}
}

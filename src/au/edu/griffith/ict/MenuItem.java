package au.edu.griffith.ict;

public class MenuItem{
	private String name;
	private float price;
	private int itemNo;
	
	public MenuItem(int itemNo, String name, float price){
		this.itemNo = itemNo;
		this.name = name;
		this.price = price;
	}
	
	public float getPrice(){
		return price;
	}
	public void setPricePerUnit(float ppu){
		this.price = ppu;
	}
	public String getName(){
		return name;
	}
	public int getItemNo(){
		return itemNo;
	}
}
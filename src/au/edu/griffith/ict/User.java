package au.edu.griffith.ict;

public class User{
	private String password;
	private int staffNo;
	private boolean admin;
	
	public User(int staffNo, String pass, boolean admin){
		this.password = pass;
		this.staffNo = staffNo;
		this.admin = admin;
	}
	
	public int getStaffNo(){
		return staffNo;
	}
	public String getPassword(){
		return password;
	}
	public boolean isAdmin(){
		return admin;
	}
}
package au.edu.griffith.ict;

public class User{

    /** The user's password */
	private String password;
	
	/** The user's staff number */
	private int staffNo;
	
	/** The user's administrative status */
	private boolean admin;
	
	public User(int staffNo, String pass, boolean admin){
		this.password = pass;
		this.staffNo = staffNo;
		this.admin = admin;
	}
	
	/**
	* Get the user's staff number.
	* @return An int which represents the stuff number.
	*/
	public int getStaffNo(){
		return staffNo;
	}
	
	/**
	* Get the user's password
	* @return A String containing the user's password.
	*/
	public String getPassword(){
		return password;
	}
	
	/**
	* Get the user's Administrator status.
	* @return If the user is an Administrator, returns True.
	*/
	public boolean isAdmin(){
		return admin;
	}
}
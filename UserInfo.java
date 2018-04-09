package cu.cs.cpsc215.project2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author gmeader
 * Creates user objects
 * for storing login information
 * later stored in an accouts database
 * @see AccDatabase
 */

public class UserInfo implements Serializable{
	
	private String username;
	private String password;
	private boolean admin;
	private String name;
	private String email;
	private String phone; 
	private int ID;
	private BookDatabase checked = new BookDatabase();
	
	public void CreateAdmin()
	{
		username = "admin";
		password = "";
		admin = true;
		name = null;
		email = null;
		phone = null;
		ID = 0;	
	}
	public UserInfo(String user, String pass, boolean a, String b, String E, String Number, int i) {
		username = user;
		password = pass;
		admin = a;
		name = b;
		email = E;
		phone = Number;
		ID = i;
	}
	public UserInfo() {
		username = null;
		password = null;
		admin = false;
		name = null;
		email = null;
		phone = null;
		
	}

	public void setChecked(BookDatabase T)
	{
		checked = T;
	}
	public void removeChecked(BookDatabase T)
	{
		for(int i = 0; i < T.getList().size(); i++)
		{
			if(checked.getList().contains(T.getList().get(i))){
				
				checked.getList().remove(i);
			}
		}
	}
	public BookDatabase getChecked()
	{
		return this.checked;
	}
	public void SetPass(String b) {
		// TODO Auto-generated method stub
		this.password = b;
	}
	public void SetName(String a)
	{
		this.username = a;
	}
	public void Setadmin(boolean a)
	{
		this.admin = a;
	}
	public void SetFullName(String a)
	{
		this.name = a;
	}
	public void SetEmail(String a)
	{
		this.email = a;
	}    
	public void SetPhone(String a)
	{
		this.phone = a;
	}
	public void SetID(int i)
	{
		ID = i;
	}
	
	public String getUsername(){
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}
	public boolean getAdmin(){
		return this.admin;
	}
	public String getName() {
		
		return this.name;
	}
	public int getID(){
		return this.ID;
	}
	public String getEmail() {
		return this.email;
	}
	public String getPhone() {
		
		return this.phone;
	}
	

}

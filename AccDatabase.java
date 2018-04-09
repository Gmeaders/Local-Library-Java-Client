package cu.cs.cpsc215.project2;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPasswordField;

/**
 * 
 * @author gmeader
 * Database for accounts
 * used in login and view accounts
 * stores an arraylist of UserInfo types
 * 
 */
public class AccDatabase implements Serializable {
	
	private ArrayList<UserInfo> list;
	private int ID;
	AccDatabase()
	{
		list = new ArrayList<UserInfo>();
	}
	AccDatabase(ArrayList<UserInfo> l)
	{
		list = l;
	}
public void Update(UserInfo i)
{
	for(int j = 0; j < list.size(); j++)
	{
		if(list.get(j) == i){
			list.get(j).equals(i);
		}
	}
}
public void add()
{
	ID++;
}
public int getID()
{
	return ID;
}
public int size() {
	
	return list.size();
}
public void add(UserInfo a) {
	list.add(a);
	
}
public UserInfo get(int i) {
	// TODO Auto-generated method stub
	return list.get(i);
}
public UserInfo getUser(int i) {
	
	return list.get(i);
}
public void remove(int i) {
	
	list.remove(i);
	
}
public ArrayList<UserInfo> getList()
{
	return list;
}
public void set(int i, UserInfo newUser) {
	// TODO Auto-generated method stub
	list.set(i, newUser);
	
}

}
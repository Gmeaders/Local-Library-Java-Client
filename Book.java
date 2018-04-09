package cu.cs.cpsc215.project2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author gmeader
 * Creates a book type to hold
 * information about books stored
 * for the library
 * Each book is stored in a larger database
 * @see BookDatabse
 */
public class Book implements Serializable {
	
	private String Title;
	private String Author;
	private String Genre;
	private ArrayList<String> Tags;
	private boolean checkedout;
	private UserInfo customer;
	Book()
	{
		Title = null;
		Author = null;
		Genre = null;
		Tags = new ArrayList<String>();	
		checkedout = false;
		customer = new UserInfo();
	}
	Book(String a, String B, String C, ArrayList<String> L, boolean q, UserInfo J)
	{
		Title = a;
		Author = B;
		Genre = C;
		Tags = L;
		checkedout = q;
		customer = J;
	}
	
	public void setTitle(String T)
	{
		Title = T;
		
	}
	public void setAuthor(String T)
	{
		Author = T;
	}
	public void setGenre(String T)
	{
		Genre = T;
	}
	public void setTags(ArrayList<String> T)
	{
		Tags = T;
	}
	public void setCheckedOut(boolean b)
	{
		this.checkedout = b;
	}
	public String getTitle()
	{
		return this.Title;
	}
	public String getAuthor()
	{
		return this.Author;
	}
	public String getGenre()
	{
		return this.Genre;
	}
	public ArrayList<String> getTags()
	{
		return this.Tags;
	}
	public boolean getCheckedOut()
	{
		return this.checkedout;
	}
	public UserInfo getCustomer()
	{
		return this.customer;
	}

}

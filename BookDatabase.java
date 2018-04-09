package cu.cs.cpsc215.project2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author gmeader
 * Holds a list of books
 * used throughout program to store
 * and load books for the catalog and 
 * checkout classes
 */
public class BookDatabase implements Serializable {
	
	private ArrayList<Book> list;
	
	
	BookDatabase()
	{
		list = new ArrayList<Book>();
	}
	BookDatabase(ArrayList<Book> l)
	{
		list = l;
	}
	
	public ArrayList<Book> getList(){
		return list;
	}

	public int size() {
	
		return list.size();
	}

	public void add(Book a) {
		list.add(a);
	}

	public Book get(int i) {
		// TODO Auto-generated method stub
		return list.get(i);
	}
	public Book getBook(int i) {
	
		return list.get(i);
	}
	public void remove(int i) {
	
		list.remove(i);
	
	}
	public void set(int i, Book newUser) {
		
		list.set(i, newUser);
		
	}
}


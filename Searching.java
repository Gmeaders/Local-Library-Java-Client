package cu.cs.cpsc215.project2;

import java.util.ArrayList;

public class Searching implements StrategySearch{

	/**
	 * Class to implement the strategysearch
	 * nSearch will look for exact matches in books and tags
	 * fSearch will search for strings containing the input as
	 * well as ignoring case differences
	 */
	@Override
	public BookDatabase nSearch(BookDatabase list, String b) {
		String userInput = b;
		BookDatabase searchBook = new BookDatabase();
		ArrayList<Book> booklist = list.getList();
        for (int q = 0; q < booklist.size(); q++) 
        {
        	
        	String author = booklist.get(q).getAuthor();
        	String title = booklist.get(q).getTitle();
        	String Genre = booklist.get(q).getGenre();
     
           if (userInput.equals(author) || (userInput.equals(title)) || (userInput.equals(Genre)))
           {
               searchBook.add(list.get(q));
           }
           else{
        	   for(int i = 0; i < list.getBook(q).getTags().size(); i++)
        	   {
        		   if(userInput.equals(list.getBook(q).getTags().get(i)))
        		   {
        			   searchBook.add(list.getBook(q));

        		   }
        	   }
           }
	
        }
       
		return searchBook;
	}

	@Override
	public BookDatabase fSearch(BookDatabase list, String b) {
		String userInput = b;
		BookDatabase searchBook = new BookDatabase();
		ArrayList<Book> booklist = list.getList();
        for (int q = 0; q < booklist.size(); q++) 
        {
        	
        	String author = booklist.get(q).getAuthor();
        	String title = booklist.get(q).getTitle();
        	String Genre = booklist.get(q).getGenre();
     
           if (userInput.equalsIgnoreCase(author) || (userInput.equalsIgnoreCase(title)) || (userInput.equalsIgnoreCase(Genre)) 
        		   || (author.toLowerCase().contains(userInput)) || (title.toLowerCase().contains(userInput)) || (Genre.toLowerCase().contains(userInput))) 
           {
               searchBook.add(list.get(q));
           }
           else{
        	   for(int i = 0; i < list.getBook(q).getTags().size(); i++)
        	   {
        		   if(userInput.equalsIgnoreCase(list.getBook(q).getTags().get(i)) || (list.getBook(q).getTags().get(i).toLowerCase().contains(userInput.toLowerCase())))
        		   {
        			   searchBook.add(list.getBook(q));
   
        		   }
        	   }
           }
	
        }
		return searchBook;
	}
	
}
	



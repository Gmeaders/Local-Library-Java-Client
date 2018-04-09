package cu.cs.cpsc215.project2;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author gmeader
 * checkout table 1, used for the left
 * table in the checkout window
 * @param List is a link to the database used to
 * store information for this table.
 */

public class CheckoutTable1 extends AbstractTableModel {

	private BookDatabase list = new BookDatabase();
	
	CheckoutTable1(BookDatabase list2)
	{
		list = list2;
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if(columnIndex == 0) return list.get(rowIndex).getTitle();
		if(columnIndex == 1) return list.get(rowIndex).getAuthor();
		return list.get(rowIndex).getGenre();
	}
		
	public BookDatabase GetTable(){
		return list;
	}

	@Override
	public String getColumnName(int column) { 
		if(column == 0) return "Title";
		if(column == 1) return "Author";
	    return "Genre";
	}

	
} 



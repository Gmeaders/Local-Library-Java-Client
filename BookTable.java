package cu.cs.cpsc215.project2;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author gmeader
 * table for the 2 catalogue windows
 * displays books and if they have been checked out or
 * not
 */
public class BookTable extends AbstractTableModel {

	private BookDatabase list = new BookDatabase();
	
	BookTable(BookDatabase list2)
	{
		list = list2;
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if(columnIndex == 0) return list.get(rowIndex).getTitle();
		if(columnIndex == 1) return list.get(rowIndex).getAuthor();
		if(columnIndex == 2) return list.get(rowIndex).getGenre();
		if(list.get(rowIndex).getCheckedOut() == true){
				return "Yes";
			}
		else return "No";
		}
		
	public BookDatabase GetTable(){
		return list;
	}

	@Override
	public String getColumnName(int column) { 
		if(column == 0) return "Title";
		if(column == 1) return "Author";
		if(column == 2) return "Genre";
		return "Checked Out";
	}

	
}



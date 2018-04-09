package cu.cs.cpsc215.project2;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author gmeader
 * just like CheckoutTable1
 * used for the right side table in CheckoutWindow
 * @param list is the data used in the right checkout table
 */
public class CheckoutTable2 extends AbstractTableModel {

	private BookDatabase list = new BookDatabase();
	
	CheckoutTable2(BookDatabase list2)
	{
		list = list2;
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if(columnIndex == 0){
			if (list.get(rowIndex).getCheckedOut() == true)
			{
				return "Return";
			}
			else return "CheckOut";
		
	}
		
		return list.get(rowIndex).getTitle();
		
	}
		
	public BookDatabase GetTable(){
		return list;
	}

	@Override
	public String getColumnName(int column) { 
		if(column == 0) return "Action";
	    return "Book";
	
	}

	
} 

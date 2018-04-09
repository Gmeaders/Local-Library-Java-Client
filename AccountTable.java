package cu.cs.cpsc215.project2;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author gmeader
 * Table used for setting up the
 * account window
 * @param list is the total list of accounts
 */
public class AccountTable extends AbstractTableModel {

	private AccDatabase list = new AccDatabase();
	
	AccountTable(AccDatabase list2)
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
		
		if(columnIndex == 0) return list.get(rowIndex).getID();
		if(columnIndex == 1) return list.get(rowIndex).getUsername();
		if(columnIndex == 2) return list.get(rowIndex).getName();
		if(list.get(rowIndex).getAdmin() == true){
				return "Staff";
			}
		else return "Member";
		}
		
	public AccDatabase GetTable(){
		return list;
	}

	@Override
	public String getColumnName(int column) { 
		if(column == 0) return "ID";
		if(column == 1) return "Username";
		if(column == 2) return "Name";
		return "Type";
	}

	
}
	



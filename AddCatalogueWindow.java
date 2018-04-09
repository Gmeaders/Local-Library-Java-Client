package cu.cs.cpsc215.project2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AddCatalogueWindow extends JFrame implements ActionListener, Serializable, WindowListener{
	

	private BookTable table;
	private CheckoutTable2 newtable;
	private JScrollPane scrollPane;
	private JButton SelectBook;
	private JButton delBook;
	private JButton done;
	private JButton search;
	private JTable T;
	private BookDatabase list;
	private BookDatabase returnbook;
	private AccDatabase accounts;
	private UserInfo CurrentUser;
	private JTextField searchText = new JTextField(20);
	private Book ViewBook;
	private BookDatabase searchBook;
	private BookTable SearchTable;
	private JButton fuzzySearch;
	
	public AddCatalogueWindow(BookDatabase list2, UserInfo I, AccDatabase list3, CheckoutTable2 table2, BookDatabase booksForCheck){
		
		super("Book Catalogue");
		CurrentUser = I;
		list = list2;
		accounts = list3;
		newtable = table2;
		returnbook = booksForCheck;
		
		this.setSize(650, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JPanel jpanel = new JPanel(new BorderLayout());
		JPanel buttons = new JPanel();
		JPanel searchpanel = new JPanel();
	
		table = new BookTable(list);
		T = new JTable(table);
		T.getTableHeader();
		
	    jpanel.add(new JScrollPane(T, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				   JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	    
	    SelectBook = new JButton("Select");
		done = new JButton("Close");
		fuzzySearch = new JButton("Fuzzy Search");

	    PlaceButtons(buttons);
	    placeComponents(searchpanel);
		
	    this.add(searchpanel, BorderLayout.NORTH);
	    this.getContentPane().add(buttons, BorderLayout.CENTER);
	    this.getContentPane().add(jpanel, BorderLayout.EAST);
		setLocationRelativeTo(null);
		
		
		SelectBook.addActionListener(this);
		done.addActionListener(this);
		search.addActionListener(this);
		fuzzySearch.addActionListener(this);	
		
		
	}

private void placeComponents(JPanel panel) {
		
		search = new JButton("Search");
		search.setBounds(10, 10, 70, 20);
		panel.add(search);
		
		fuzzySearch = new JButton("Fuzzy Search");
		fuzzySearch.setBounds(5, 10, 60, 20);
		panel.add(fuzzySearch);
		
		searchText.setBounds(100, 10, 80, 25);
		panel.add(searchText);
	}
private void PlaceButtons(JPanel taco)
{
	taco.setLayout(null);
	SelectBook.setBounds(35, 25, 120, 30);
	taco.add(SelectBook);
	
	
	done.setBounds(35, 300, 120, 30);
	taco.add(done);
	
}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == SelectBook)
		{
			if(list.get(T.getSelectedRow()).getCheckedOut() == true)
			{
				JOptionPane.showMessageDialog(null, "Book already checked out", "Error", JOptionPane.WARNING_MESSAGE);
			}
			else{
			returnbook.add(list.get(T.getSelectedRow()));
			newtable.fireTableStructureChanged();
			this.setVisible(false);
			}
		}
		
		if(e.getSource() == done)
		{
			this.setVisible(false);
		}
		if(e.getSource() == fuzzySearch){
			String userInput = searchText.getText();
			searchBook = new BookDatabase();
			Searching A = new Searching();
			searchBook = A.fSearch(list, userInput);
            table = new BookTable(searchBook);
            T.setModel(table);
    		table.fireTableDataChanged();
		}
		if(e.getSource() == search){
			String userInput = searchText.getText();
			Searching A = new Searching();
			searchBook = new BookDatabase();
			searchBook = A.nSearch(list, userInput);
            table = new BookTable(searchBook);
            T.setModel(table);
    		table.fireTableDataChanged();		
		}
	}
	

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		table.fireTableDataChanged();
	}

}
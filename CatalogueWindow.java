package cu.cs.cpsc215.project2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

public class CatalogueWindow extends JFrame implements ActionListener, Serializable, WindowListener{
	

	private BookTable table;
	private JScrollPane scrollPane;
	private JButton addBook;
	private JButton delBook;
	private JButton done;
	private JButton search;
	private JTable T;
	private String	columnNames[];
	private BookDatabase list;
	private AccDatabase accounts;
	private UserInfo CurrentUser;
	private JTextField searchText = new JTextField(20);
	private Book ViewBook;
	private BookDatabase searchBook;
	private BookTable SearchTable;
	private JButton fuzzySearch;
	
	public CatalogueWindow(BookDatabase list2, UserInfo I, AccDatabase list3){
		
		super("Book Catalogue");
		CurrentUser = I;
		list = list2;
		accounts = list3;
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
	    
	    addBook = new JButton("Add");
		delBook = new JButton("Delete");
		done = new JButton("Close");
		fuzzySearch = new JButton("Fuzzy Search");

		if(CurrentUser.getAdmin() == false)
		{
			addBook.setEnabled(false);
			delBook.setEnabled(false);
		}
	    PlaceButtons(buttons);
	    placeComponents(searchpanel);
		
	    this.add(searchpanel, BorderLayout.NORTH);
	    this.getContentPane().add(buttons, BorderLayout.CENTER);
	    this.getContentPane().add(jpanel, BorderLayout.EAST);
		setLocationRelativeTo(null);
		
		
		addBook.addActionListener(this);
		delBook.addActionListener(this);
		done.addActionListener(this);
		search.addActionListener(this);
		fuzzySearch.addActionListener(this);
		
		T.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
				  
			      if (e.getClickCount() == 2) {
			         JTable target = (JTable)e.getSource();
			         int row = target.getSelectedRow();
			         
			         ViewBook = list.get(T.getSelectedRow());
			         JFrame view = new ViewBookWindow(ViewBook, list, table, T.getSelectedRow(), CurrentUser);
			         view.setVisible(true);
			         }
			   }
			   
			   
			});
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
	addBook.setBounds(35, 25, 120, 30);
	//addBook.setAlignmentY(CENTER_ALIGNMENT);
	taco.add(addBook);
	
	delBook.setBounds(35, 75, 120, 30);
	//delBook.setAlignmentY(CENTER_ALIGNMENT);
	taco.add(delBook);
	
	done.setBounds(35, 300, 120, 30);
	taco.add(done);
	
}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addBook)
		{
			JFrame add = new AddBookWindow(list, table);
			add.setVisible(true);
			table.fireTableDataChanged();
			
		}
		if(e.getSource() == delBook)
		{
			list.remove(T.getSelectedRow());
			searchBook.remove(T.getSelectedRow());
			table.fireTableStructureChanged();
	        try {
	        	String filename = "Book.file";
	            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
	            oos.writeObject(table.GetTable());
	            oos.close();
	        }
	        catch(IOException n) {
	            System.out.println("There was a problem creating file: " + n);
	            return;
	        }
		}
		if(e.getSource() == done)
		{
			this.setVisible(false);
			JFrame main = new MainWindow(CurrentUser, accounts);
			main.setVisible(true);	
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

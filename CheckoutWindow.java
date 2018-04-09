package cu.cs.cpsc215.project2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CheckoutWindow extends JFrame implements ActionListener, WindowListener{

	private CheckoutTable1 table;
	private CheckoutTable2 table2; 
	private JTable T;
	private JTable T2;
	private UserInfo CurrentUser = new UserInfo();
	private AccDatabase accounts = new AccDatabase();
	private BookDatabase list = new BookDatabase();
	private BookDatabase BooksNew1 = new BookDatabase();
	private BookDatabase BooksForCheck = new BookDatabase();
	private JButton AddCheckout = new JButton();
	private JButton okay = new JButton();
	private JButton cancel = new JButton();
	private int id;
	private JTextField field;
	private JButton Button;
	private JFrame display;
	
	CheckoutWindow(UserInfo information, AccDatabase l,BookDatabase bookList) {
	
		super.setTitle("Checkout");
		CurrentUser = information;
		accounts = l;
		list = bookList;
		BooksNew1 = CurrentUser.getChecked();
		if(information.getAdmin() == true){
		
		for(int i = 0; i < accounts.getList().size(); i++)
		{
			if(accounts.getList().get(i).getID() == id){
				CurrentUser = accounts.getList().get(i);
			}
		}
		}
		else CurrentUser = information;
	
		
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JPanel jpanel = new JPanel();
		JPanel buttons = new JPanel(new BorderLayout());
		buttons.setLayout(new BorderLayout());
		table = new CheckoutTable1(BooksNew1);
		T = new JTable(table);
		T.getTableHeader();
		
		table2 = new CheckoutTable2(BooksForCheck);
		T2 = new JTable(table2);
		T2.getTableHeader();
		
		jpanel.setLayout(new GridLayout(1, 2));

		jpanel.add(new JScrollPane(T, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				   JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		jpanel.add(new JScrollPane(T2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		
		AddCheckout = new JButton("Add Checkout");
		okay = new JButton("Okay");
		cancel = new JButton("Cancel");
		buttons.setLayout(new GridLayout(1, 3));
		buttons.add(AddCheckout);
		buttons.add(okay);
		buttons.add(cancel);
		cancel.addActionListener(this);
		AddCheckout.addActionListener(this);
		okay.addActionListener(this);
			
		this.add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(jpanel);
		setLocationRelativeTo(null);
		
		T2.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
				  
			      if (e.getClickCount() == 2) {
			         JTable target = (JTable)e.getSource();
			         int row = target.getSelectedRow();
			         BooksNew1.add(BooksForCheck.get(T2.getSelectedRow()));
			         BooksForCheck.remove(T2.getSelectedRow());
			         table.fireTableDataChanged();
			         table2.fireTableDataChanged();        
			         }
			   } 
			});
		T.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
				  
			      if (e.getClickCount() == 2) {
			         JTable target = (JTable)e.getSource();
			         int row = target.getSelectedRow();
			         BooksForCheck.add(BooksNew1.get(T.getSelectedRow()));
			         BooksNew1.remove(T.getSelectedRow());
			         table.fireTableDataChanged();
			         table2.fireTableDataChanged();        
			         }
			   } 
			});	
		this.addWindowListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancel)
		{
			this.setVisible(false);
			JFrame main = new MainWindow(CurrentUser, accounts);
			main.setVisible(true);	
		}
		if(e.getSource() == Button)
		{
			id = Integer.parseInt(field.getText());
			display.setVisible(false);
		}
		if(e.getSource() == AddCheckout)
		{
			JFrame catalogue = new AddCatalogueWindow(list, CurrentUser, accounts, table2, BooksForCheck);
			catalogue.setVisible(true);
			table2.fireTableStructureChanged();
		}
		if(e.getSource() == okay)
		{
			
			
			for(int q = 0; q < BooksForCheck.getList().size(); q++)
			{
				list.getList().get(q).setCheckedOut(false);
			}
			CurrentUser.removeChecked(BooksForCheck);
			for(int i = 0; i < BooksNew1.getList().size(); i++)
			{
					if(list.getList().contains(BooksNew1.getList().get(i))){
						list.getList().get(i).setCheckedOut(true);
					}
			}
			CurrentUser.setChecked(BooksNew1);
			accounts.Update(CurrentUser);
			
			
			String filename = "data.file";
			
	        try {
	            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
	            oos.writeObject(accounts);
	            oos.close();
	        }
	        catch(IOException b) {
	            System.out.println("There was a problem creating file: " + b);
	            return;
	        }
	        
	        String filename2 = "Book.file";
			
	        try {
	            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename2));
	            oos.writeObject(list);
	            oos.close();
	        }
	        catch(IOException c) {
	            System.out.println("There was a problem creating file: " + c);
	            return;
	        }
	        JFrame main = new MainWindow(CurrentUser, accounts);
	        this.setVisible(false);
	        main.setVisible(true);
			}
		}
		
		
	


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		if(CurrentUser.getAdmin() == true){
			display = new JFrame();
			display.setVisible(true);
			display.setSize(200, 200);
			display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			display.setResizable(false);
			JPanel panel = new JPanel();
			field = new JTextField(5);
			field.setBounds(100, 10, 80, 25);
			
			
			JLabel thing = new JLabel("Please enter member id");
			thing.setBounds(50, 10, 80, 25);
			panel.add(thing);
			panel.add(field);
			Button = new JButton("OK");
			Button.setBounds(150, 10, 80, 25);
			Button.addActionListener(this);
			panel.add(Button);
			display.getContentPane().add(panel);
			display.setLocationRelativeTo(null);
		}
			
		}
	}



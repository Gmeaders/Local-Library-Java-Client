package cu.cs.cpsc215.project2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener, Serializable, WindowListener{
	
	private JButton ViewCat;
	private JButton ViewAccs;
	private JButton BeginCheckout;
	private JButton logout;
	private AccDatabase list = new AccDatabase();
	private UserInfo information = new UserInfo();
	private BookDatabase BookList = new BookDatabase();
	
	public MainWindow(UserInfo i, AccDatabase list2) {
		
		super("Main Window");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.setResizable(false);
		setLocationRelativeTo(null);
		information = i;
		list = list2;
			
		JPanel taco = new JPanel();
		JPanel bell = new JPanel();
		
		//taco.setLayout(new GridLayout(5, 1));
		
		ViewCat = new JButton("View Catalog");
		ViewAccs = new JButton("View Accounts");
		BeginCheckout = new JButton("Begin Checkout");
		logout = new JButton("Logout");
		
		PlaceButtons(taco);
		this.getContentPane().add(bell, BorderLayout.NORTH);
		placeComponents(bell);
		this.getContentPane().add(taco);
		this.addWindowListener(this);
		
		
		if(information.getAdmin() == true)
		{
			//ViewAccs.setEnabled(false);
		}
		
		ViewCat.addActionListener(this);
		ViewAccs.addActionListener(this);
		BeginCheckout.addActionListener(this);
		logout.addActionListener(this);
	}
	
	private void placeComponents(JPanel panel) {
		
		JLabel userLabel = new JLabel("Username: ");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);
		
		JLabel currentUser = new JLabel(information.getUsername());
		userLabel.setBounds(100, 10, 80, 25);
		panel.add(currentUser);	
	}
	private void PlaceButtons(JPanel taco)
	{
		taco.setLayout(null);
		ViewCat.setBounds(50, 25, 200, 40);
		taco.add(ViewCat);
		
		BeginCheckout.setBounds(50, 75, 200, 40);
		taco.add(BeginCheckout);
		
		ViewAccs.setBounds(50, 125, 200, 40);
		taco.add(ViewAccs);
		
		logout.setBounds(50, 175, 200, 40);
		
		if(information.getAdmin() == false)
		{
			ViewAccs.setEnabled(false);
		}
		taco.add(logout);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ViewAccs)
		{
			if(information.getAdmin() == false)
			{
				JOptionPane.showMessageDialog(null, "Only staff may access accounts.", "Error", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				this.setVisible(false);
				JFrame account = new AccountWindow(list, information);
				account.setVisible(true);	
			}
		}
		if(e.getSource() == ViewCat)
		{
			this.setVisible(false);
			JFrame catalogue = new CatalogueWindow(BookList, information, list);
			catalogue.setVisible(true);
		}
		if(e.getSource() == logout){
			this.setVisible(false);
			JFrame login = new LoginWindow();
			login.setVisible(true);
		}
		if(e.getSource() == BeginCheckout){
			this.setVisible(false);
			JFrame checkout = new CheckoutWindow(information, list, BookList);
			checkout.setVisible(true);
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {
		String filename = "Book.file";
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            BookList = (BookDatabase) ois.readObject();
        }
        catch(Exception b) {
            BookList = new BookDatabase();
        }
		
	}
 
	
} 

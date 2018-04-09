package cu.cs.cpsc215.project2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.event.MouseInputListener;


public class AccountWindow extends JFrame implements ActionListener, Serializable{
	

	private AccountTable table;
	private JScrollPane scrollPane;
	private JButton addacc;
	private JButton delacc;
	private JButton done;
	private JTable T;
	private String	columnNames[];
	private AccDatabase list;
	private UserInfo CurrentUser;
	private int id = 1;
	private MouseInputListener m;
	private UserInfo ViewUser;
	
	public AccountWindow(AccDatabase list2, UserInfo I){
		
		super("Accounts");
		CurrentUser = I;
		list = list2;
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JPanel jpanel = new JPanel(new BorderLayout());
		JPanel buttons = new JPanel(new BorderLayout());
		buttons.setLayout(new BorderLayout());
		
		table = new AccountTable(list);
		T = new JTable(table);
		T.getTableHeader();
	
	    jpanel.add(new JScrollPane(T, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				   JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	    
	    addacc = new JButton("Add");
		delacc = new JButton("Delete");
		done = new JButton("Done");
		buttons.setLayout(new GridLayout(1, 3));
	    buttons.add(addacc);
		buttons.add(delacc);
		buttons.add(done);
		
	    this.add(buttons, BorderLayout.SOUTH);
	    this.getContentPane().add(jpanel, BorderLayout.NORTH);
		setLocationRelativeTo(null);
		
		
		addacc.addActionListener(this);
		delacc.addActionListener(this);
		done.addActionListener(this);
		
		T.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
			      if (e.getClickCount() == 2) {
			    	 
			         ViewUser = list.get(T.getSelectedRow());
			         JFrame view = new ViewAccountWindow(ViewUser, list, table, id, T.getSelectedRow());
			         view.setVisible(true);
			         }
			   }
			});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addacc)
		{
			JFrame add = new AddAccountWindow(list, table, id);
			add.setVisible(true);
			id++;
			table.fireTableDataChanged();
			
		}
		else if(e.getSource() == delacc)
		{
			if(CurrentUser == list.get(T.getSelectedRow()))
			{
				JOptionPane.showMessageDialog(null, "Can't Delete Yourself, Please login to seperate account.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else{
			list.remove(T.getSelectedRow());
			table.fireTableStructureChanged();
			
			}
	        try {
	        	String filename = "data.file";
	            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
	            oos.writeObject(table.GetTable());
	            oos.close();
	        }
	        catch(IOException n) {
	            System.out.println("There was a problem creating file: " + n);
	            return;
	        }
		}
		else if(e.getSource() == done)
		{
			this.setVisible(false);
			JFrame main = new MainWindow(CurrentUser, list);
			main.setVisible(true);	
		}
	}
}



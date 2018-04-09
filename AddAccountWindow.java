package cu.cs.cpsc215.project2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;



public class AddAccountWindow extends JFrame implements ActionListener, WindowListener, Serializable{
	
	private AccDatabase list = new AccDatabase();
	private JTextField TextUserN = new JTextField(20);
	private JPasswordField TextUserPass = new JPasswordField(20);
	private JTextField TextType = new JTextField(20);
	private JTextField TextNameFull = new JTextField(30);
	private JTextField TextEmail = new JTextField(20);
	private JTextField TextPhone = new JTextField(20);
	private JButton Add;
	private JButton Cancel;
	private AccountTable table;
	

	AddAccountWindow(AccDatabase list2, AccountTable t, int id){
		
		super("Add Account");
		table = t;
		list = list2;
		this.setSize(300, 300);
		
		this.getContentPane();
		this.setResizable(false);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		JPanel buttons = new JPanel(new BorderLayout());
		JPanel sort = new JPanel(new BorderLayout());
		
		Add = new JButton("Save");
		Cancel = new JButton("Cancel");
		
		buttons.setLayout(new BorderLayout());
		sort.setLayout(new BorderLayout());
		buttons.add(Add, BorderLayout.WEST);
		buttons.add(Cancel, BorderLayout.EAST);
		sort.add(buttons);
		
		this.getContentPane().add(panel, BorderLayout.CENTER);
		this.getContentPane().add(sort, BorderLayout.SOUTH);
		placeComponents(panel);
		
		Cancel.addActionListener(this);
		Add.addActionListener(this);
		
	    
	}
	private void placeComponents(JPanel panel) {
		
		panel.setLayout(null);

		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);
		
		TextUserN.setBounds(100, 10, 160, 25);
		panel.add(TextUserN);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		TextUserPass.setBounds(100, 40, 160, 25);
		panel.add(TextUserPass); 
		
		JLabel typelabel = new JLabel("Type:");
		typelabel.setBounds(10, 70, 80, 25);
		panel.add(typelabel);
		
		TextType.setBounds(100, 70, 160, 25);
		panel.add(TextType);
		
		JLabel NameLabel = new JLabel("Name:");
		NameLabel.setBounds(10, 100, 80, 25);
		panel.add(NameLabel);
		
		TextNameFull.setBounds(100, 100, 160, 25);
		panel.add(TextNameFull);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(10, 130, 80, 25);
		panel.add(emailLabel);
		
		TextEmail.setBounds(100, 130, 160, 25);
		panel.add(TextEmail);
		
		JLabel PhoneLabel = new JLabel("Phone:");
		PhoneLabel.setBounds(10, 160, 80, 25);
		panel.add(PhoneLabel);
		
		TextPhone.setBounds(100, 160, 160, 25);
		panel.add(TextPhone);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == Cancel)
		{
			this.dispose();
		}
		else if(e.getSource() == Add){
			
			String a = new String(TextUserPass.getPassword());
			UserInfo NewUser = new UserInfo();
			NewUser.SetName(TextUserN.getText());
			NewUser.SetPass(a);
			if(TextType.getText().equals("Staff") || TextType.getText().equals("staff")){
					NewUser.Setadmin(true);
			}
			else NewUser.Setadmin(false);
			
			NewUser.SetFullName(TextNameFull.getText());
			NewUser.SetEmail(TextEmail.getText());
			NewUser.SetPhone(TextPhone.getText());
			if(NewUser.getAdmin() == false)
			{
				list.add();
				NewUser.SetID(list.getID());
				
			}
			else NewUser.SetID(0);
			list.add(NewUser);
			table.fireTableStructureChanged();
			this.addWindowListener(this);
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				
		}
		
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) { 
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		String filename = "data.file";
		
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(table.GetTable());
            oos.close();
        }
        catch(IOException e) {
            System.out.println("There was a problem creating file: " + e);
            return;
        }
        
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {}
	@Override
	public void windowIconified(WindowEvent arg0) {}
	@Override
	public void windowOpened(WindowEvent arg0) {}

}

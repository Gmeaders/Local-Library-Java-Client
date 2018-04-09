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

public class LoginWindow extends JFrame implements ActionListener, WindowListener, Serializable {

	private JButton loginButton;
	private JButton CloseButton;
	private JTextField userText = new JTextField(20);
	private JPasswordField passwordText = new JPasswordField(20);
	private AccDatabase list = new AccDatabase();

	static UserInfo information = new UserInfo();
	
	
	public LoginWindow() {
		super("Library Login");
		this.setSize(300, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel taco = new JPanel();
		JPanel bell = new JPanel();
		
		this.getContentPane().add(bell, BorderLayout.CENTER);
		placeComponents(bell);
		this.getContentPane().add(taco, BorderLayout.SOUTH);
		
		taco.setLayout(new GridLayout(1, 3));
		loginButton = new JButton("login");
		CloseButton = new JButton("close");
		
		
		taco.add(loginButton);
		taco.add(CloseButton);
		
		loginButton.addActionListener(this);
		CloseButton.addActionListener(this);
		this.addWindowListener(this);
	}

	private void placeComponents(JPanel panel) {
		
		panel.setLayout(null);

		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);
		
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText); 
		
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == loginButton)
		{
			String a = new String(passwordText.getPassword());
			information.SetName(userText.getText());
			information.SetPass(a);
						
			if((information.getUsername().equals("admin") || information.getUsername().equals("Admin"))
				&& information.getPassword().equals(""))
			{
				information = list.getUser(0);
				this.setVisible(false);
				JFrame main = new MainWindow(information, list);
				main.setVisible(true);				
			}
			
			else if(this.exsists(information) != null)
			{
				this.setVisible(false);
				JFrame main = new MainWindow(information, list);
				main.setVisible(true);
			}
			else{
				 JOptionPane.showMessageDialog(null, "Invalid Username/password", "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		if(e.getSource() == CloseButton)
		{
			System.exit(0);
		}
	}


	public UserInfo exsists(UserInfo a){
		for(int i = 0; i < list.size(); i++) 
		{
				if(list.get(i).getPassword().equals(a.getPassword()) && list.get(i).getUsername().equals(a.getUsername())) {
				information = list.get(i);
				return list.get(i);
				}
		}
		return null;
	
	}


	public void addUser(UserInfo a) {
		// TODO Auto-generated method stub
		list.add(a);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) {}
	@Override
	public void windowDeactivated(WindowEvent arg0) {}
	@Override
	public void windowDeiconified(WindowEvent arg0) {}
	@Override
	public void windowIconified(WindowEvent arg0) {}

	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		String filename = "data.file";
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            list = (AccDatabase) ois.readObject();
        }
        catch(Exception b) {
            list = new AccDatabase();
            UserInfo admin = new UserInfo();
			admin.CreateAdmin();
			this.addUser(admin);
        }
		
	}
}


	


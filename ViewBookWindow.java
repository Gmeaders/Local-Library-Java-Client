package cu.cs.cpsc215.project2;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;

public class ViewBookWindow extends JFrame implements ActionListener, Serializable, WindowListener {
	
	 
	    private BookDatabase list = new BookDatabase();
		private JTextField TextAuthorN = new JTextField(20);
		private JTextField TextTitle = new JTextField(20);
		private JTextField TextGenre = new JTextField(30);
		private JTextField TextTags= new JTextField(100);
		private JButton Add;
		private JButton Cancel;
		private BookTable table;
		private Book thisBook = new Book();
		private ArrayList<String> taglist = new ArrayList<String>();
		int i;
		private UserInfo currentuser = new UserInfo();
		

		ViewBookWindow(Book selected, BookDatabase list2, BookTable t, int q, UserInfo cur)
		{	
			super.setTitle("Account Details");
			this.setSize(300, 200);
			thisBook = selected;
			table = t;
			list = list2;
			i = q;
			currentuser = cur;
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
			
			if(currentuser.getAdmin() == false)
			{
				Add.setEnabled(false);
				
			}
			
			Cancel.addActionListener(this);
			Add.addActionListener(this);
		}
		private void placeComponents(JPanel panel) {
			
			panel.setLayout(null);

			JLabel userLabel = new JLabel("Title:");
			userLabel.setBounds(10, 10, 80, 25);
			panel.add(userLabel);
			
			TextTitle.setText(thisBook.getTitle());
			TextTitle.setBounds(100, 10, 160, 25);
			panel.add(TextTitle);
			
			JLabel passwordLabel = new JLabel("Author:");
			passwordLabel.setBounds(10, 40, 80, 25);
			panel.add(passwordLabel);

			TextAuthorN.setText(thisBook.getAuthor());
			TextAuthorN.setBounds(100, 40, 160, 25);
			panel.add(TextAuthorN); 
			
			JLabel typelabel = new JLabel("Genre:");
			typelabel.setBounds(10, 70, 80, 25);
			panel.add(typelabel);
		

			TextGenre.setText(thisBook.getGenre());
			TextGenre.setBounds(100, 70, 160, 25);
			panel.add(TextGenre);
			
	
			JLabel PhoneLabel = new JLabel("Tags:");
			PhoneLabel.setBounds(10, 100, 80, 25);
			panel.add(PhoneLabel);
			
			taglist = thisBook.getTags();
			String holdthem = new String();
			for(int i = 0; i < taglist.size(); i++)
			{
				holdthem = holdthem + taglist.get(i) + " ";
			}
			
			TextTags.setText(holdthem);
			TextTags.setBounds(100, 100, 160, 25);
			panel.add(TextTags);
		}
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == Cancel)
			{
				this.dispose();
			}
			else if(e.getSource() == Add){
				
				
				Book NewUser = new Book();
				NewUser.setTitle(TextTitle.getText());
				NewUser.setAuthor(TextAuthorN.getText());
				NewUser.setGenre(TextGenre.getText());
				String[] parts = TextTags.getText().split(" ");
				ArrayList<String> tag = new ArrayList<String>();
				for(int i = 0; i < parts.length; i++)
				{
					tag.add(parts[i]);
				}
				NewUser.setTags(tag);
		
				list.set(i, NewUser);
				table.fireTableStructureChanged();
				this.addWindowListener(this);
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				
				
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
			String filename = "Book.file";
			
	        try {
	            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
	            oos.writeObject(table.GetTable());
	            oos.close();
	        }
	        catch(IOException b) {
	            System.out.println("There was a problem creating file: " + b);
	            return;
	        }
	        
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
			
		}
}
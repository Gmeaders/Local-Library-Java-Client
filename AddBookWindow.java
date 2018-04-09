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

public class AddBookWindow extends JFrame implements ActionListener, WindowListener, Serializable{
	
	private BookDatabase list = new BookDatabase();
	private JTextField TextBookName = new JTextField(20);
	private JTextField TextAuthor = new JTextField(20);
	private JTextField TextGenre = new JTextField(30);
	private JTextField TextTags = new JTextField(100);
	private JButton Add;
	private JButton Cancel;
	private BookTable table;
	

	AddBookWindow(BookDatabase list2, BookTable t){
		
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

		JLabel TitleLabel = new JLabel("Title:");
		TitleLabel.setBounds(10, 10, 80, 25);
		panel.add(TitleLabel);
		
		TextBookName.setBounds(100, 10, 160, 25);
		panel.add(TextBookName);
		
		JLabel AuthorLabel = new JLabel("Author:");
		AuthorLabel.setBounds(10, 40, 80, 25);
		panel.add(AuthorLabel);

		TextAuthor.setBounds(100, 40, 160, 25);
		panel.add(TextAuthor); 
		
		JLabel Genrelabel = new JLabel("Genre:");
		Genrelabel.setBounds(10, 70, 80, 25);
		panel.add(Genrelabel);
		
		TextGenre.setBounds(100, 70, 160, 25);
		panel.add(TextGenre);
		
		JLabel tags = new JLabel("Tags: ");
		tags.setBounds(10, 100, 80, 25);
		panel.add(tags);
		
		TextTags.setBounds(100, 100, 160, 25);
		panel.add(TextTags);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == Cancel)
		{
			this.dispose();
		}
		else if(e.getSource() == Add){
			
			
			Book NewBook = new Book();
			NewBook.setAuthor(TextAuthor.getText());
			NewBook.setTitle(TextBookName.getText());
			NewBook.setGenre(TextGenre.getText());
			NewBook.setCheckedOut(false);
			String[] parts;
			if(TextTags.getText().contains(",")){
				parts = TextTags.getText().split(", ");
			}
			else parts = TextTags.getText().split(" ");
			ArrayList<String> tag = new ArrayList<String>();
			for(int i = 0; i < parts.length; i++)
			{
				tag.add(parts[i]);
			}
			NewBook.setTags(tag);
			list.add(NewBook);
			table.fireTableStructureChanged();
			for(int q = 0; q < NewBook.getTags().size(); q++)
			{
				System.out.println(NewBook.getTags().get(q));
			}
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
		String filename = "Book.file";
		
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(table.GetTable());
            oos.close();
            System.out.println("saved books faggot");
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



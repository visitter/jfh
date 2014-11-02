package Lecture10DB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class Librarian extends JFrame{
	private static final long serialVersionUID = -4516255560367989913L;

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem close;
	private JMenuItem authors;
	private JMenuItem books;	
	
	private AuthorsRegister authorReg;
	private BooksRegister authorReg1;
	private Librarian self;
	
	private JdbcConnector con;
	
	private JdbcConnector initConnection(){		
		try{			
			return new JdbcConnector();			
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
			return null;
		}
	}
	
	public void close(){		
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	
	public Librarian(){
		self = this;
		authors = new JMenuItem("Authors");
		books   = new JMenuItem("Books");
		close   = new JMenuItem("Close");
				
		menu = new JMenu("Operation");
		menu.add(authors);
		menu.add(books);
		menu.add(close);
		
		menuBar = new JMenuBar();
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
		
		addListeners();
		
		con = initConnection();		
	}
	
	private void addListeners(){
		authors.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				if( authorReg1!=null)
					authorReg1.dispose();
				
				if( authorReg==null){
					authorReg = new AuthorsRegister();					
					authorReg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					authorReg.setAutoscrolls(true);
					authorReg.setCon(con);
					self.add(authorReg);
				}
				authorReg.setVisible(true);				
			}
		});
		
		books.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if( authorReg!=null)
					authorReg.dispose();
				
				if( authorReg1==null){
					authorReg1 = new BooksRegister();
					authorReg1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					authorReg1.setAutoscrolls(true);
					authorReg1.setTitle("Books");	
					authorReg1.setCon(con);
					self.add(authorReg1);
				}
				authorReg1.setVisible(true);
			}
		});
		close.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				close(); 
			}
		});
		
	}
}
package Lecture10DB;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BookView extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private JTextField eTitle;
	private JComboBox<Author> cbAuthor;
	private JTextField eYear;
	private JTextField eISBN;
	
	private JLabel lTitle;
	private JLabel lAuthor;
	private JLabel lISBN;
	private JLabel lYear;
	
	private JButton btnOK;
	private JButton btnCancel;
	private int modalResult = 0;
	public int mode = 0;//0-add,1-edit,2....
	public int bookId = -1;
	
	private JdbcConnector con = null;
	public void setCon( JdbcConnector con ){
		this.con = con;
	}
	public JdbcConnector getCon(){
		return this.con;
	}
	
	public BookView(){
		setLayout( new GridLayout(5,2,10,5));
		setTitle("Book viewer");
		
		lTitle  = new JLabel("Title");		
		lISBN   = new JLabel("ISBN");
		lYear   = new JLabel("Year");
		lAuthor = new JLabel("Author");
		
		eTitle = new JTextField();
		eTitle.setPreferredSize(new Dimension(100,22));		
		eISBN = new JTextField();
		eISBN.setPreferredSize(new Dimension(100,22));
		eYear = new JTextField();
		eYear.setPreferredSize(new Dimension(100,22));		
		cbAuthor = new JComboBox<Author>();		
		cbAuthor.setPreferredSize(new Dimension(100,22));
		
		btnOK = new JButton("OK");
		btnCancel    = new JButton("Cancel");
		
		add(lTitle);
		add(eTitle);
		add(lISBN);
		add(eISBN);
		add(lYear);
		add(eYear);
		add(lAuthor);
		add(cbAuthor);
		
		add(btnOK);
		add(btnCancel);
		
		addListeners();
	}	
	public int showModal(){
		fillAuthors();
		if( mode>0 ){
			if( bookId>0 )
				getBook(bookId);
			else
				return -1;
		}
		
		setVisible(true);
		return modalResult;
	}
	public void close(){
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	private void addListeners(){
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if( e.getSource()==btnOK){
					if( mode == 0){
						if( checkForm() ){
							if(insertBook()){
								JOptionPane.showMessageDialog(null, "Успешно добавена книга");
								modalResult = 1;
								close();
							}
						}
					}else if( mode == 1){
						if( checkForm() ){
							if(editBook()){
								JOptionPane.showMessageDialog(null, "Успешно редактирана книга");
								modalResult = 1;
								close();
							}
						}
					}
				}else if( e.getSource()==btnCancel){
					modalResult = 0;
					close();
				}
			}
		});
		
		btnCancel.addActionListener(btnOK.getActionListeners()[0]);
		
	}
	private boolean checkForm(){
		if(eTitle.getText().length()==0){ 
			return false;
		}else if(cbAuthor.getSelectedIndex()<0){
			return false;
		}else if(eISBN.getText().length()==0){
			return false;
		}
		return true;
	}
	
	private boolean insertBook(){
		if( con!=null ){
			return con.addBook(
									eTitle.getText().trim(),
									Integer.parseInt(eYear.getText().trim()),
									eISBN.getText().trim(),
									((Author)cbAuthor.getSelectedItem()).getId()
								);			
		}else return false;
	}
	
	private boolean editBook(){
		if( con!=null ){
			return con.editBook(
									eTitle.getText().trim(),
									Integer.parseInt(eYear.getText().trim()),
									eISBN.getText().trim(),
									bookId,
									((Author)cbAuthor.getSelectedItem()).getId()
								);			
		}else return false;
	}
	
	
	private void fillAuthors(){
		for( Author item:con.getAllAuthors()){
			cbAuthor.addItem(item);
		}
	}
	
	private boolean getBook(Integer id){
		if( con!=null ){
			Book book = con.getBookById(id);
			if( book!=null){
				eTitle.setText(book.getTitle());
				eYear.setText(book.getYear().toString());
				eISBN.setText(book.getIsbn());
				
				for(int i=0; i<cbAuthor.getItemCount(); i++){
					if( ((Author)cbAuthor.getItemAt(i)).getId() == book.getAuthorId() )
						cbAuthor.setSelectedIndex(i);	 
				}
				
				return true;
			}else return false;
		}else return false;
	}
}

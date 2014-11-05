package Lecture10DB;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AuthorView extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private JTextField eFName;
	private JTextField eLName;
	
	private JLabel lFName;
	private JLabel lLName;;	
	
	private JButton btnOK;
	private JButton btnCancel;
	private int modalResult = 0;
	
	public int mode = 0;//0-add,1-edit,2....
	public int authorId = -1;
	
	private JdbcConnector con = null;
	public void setCon( JdbcConnector con ){
		this.con = con;
	}
	public JdbcConnector getCon(){
		return this.con;
	}
	
	public AuthorView(){
		setLayout( new GridLayout(5,2,10,5));
		setTitle("Author viewer");
		
		lFName  = new JLabel("First name");		
		lLName   = new JLabel("Last name");
				
		eFName = new JTextField();
		eFName.setPreferredSize(new Dimension(100,22));		
		eLName = new JTextField();
		eLName.setPreferredSize(new Dimension(100,22));
				
		btnOK = new JButton("OK");
		btnCancel    = new JButton("Cancel");
		
		add(lFName);
		add(eFName);
		add(lLName);
		add(eLName);
				
		add(btnOK);
		add(btnCancel);
		
		addListeners();
	}	
	public int showModal(){		
		if( mode>0 ){
			if( authorId>0 )
				getBook(authorId);
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
							if(insertAuthor()){
								JOptionPane.showMessageDialog(null, "Успешно добавен автор");
								modalResult = 1;
								close();
							}
						}
					}else if( mode == 1){
						if( checkForm() ){
							if(editAuthor()){
								JOptionPane.showMessageDialog(null, "Успешно редактиран автор");
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
		if(eFName.getText().length()==0){ 
			return false;
		}else if(eLName.getText().length()==0){
			return false;
		}
		return true;
	}
	
	private boolean insertAuthor(){
		if( con!=null ){
			return con.addAuthor(
									eFName.getText().trim(),									
									eLName.getText().trim()									
								);			
		}else return false;
	}
	
	private boolean editAuthor(){
		if( con!=null ){
			return con.editAuthor(
									eFName.getText().trim(),									
									eLName.getText().trim(),
									authorId									
								);			
		}else return false;
	}
	
	private boolean getBook(Integer id){
		if( con!=null ){
			Author author = con.getAuthorById(id);
			if( author!=null){
				eFName.setText(author.getFirstName());
				eLName.setText(author.getLastName());
				return true;
			}else return false;
		}else return false;
	}
}

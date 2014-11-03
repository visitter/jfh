package Lecture10DB;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BookView extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField eTitle;
	private JTextField eAuthor;
	private JTextField eISBN;
	
	private JLabel lTitle;
	private JLabel lAuthor;
	private JLabel lISBN;
	
	private JButton btnOK;
	private JButton btnCancel;
	private int modalResult = 0;
	
	public BookView(){
		setLayout( new GridLayout(4,2,10,5));
		setTitle("Book viewer");
		
		lTitle  = new JLabel("Title");
		lAuthor = new JLabel("Author");
		lISBN   = new JLabel("ISBN");
		
		eTitle = new JTextField();
		eTitle.setPreferredSize(new Dimension(100,22));
		eAuthor = new JTextField();		
		eAuthor.setPreferredSize(new Dimension(100,22));
		eISBN = new JTextField();
		eISBN.setPreferredSize(new Dimension(100,22));
		
		btnOK = new JButton("OK");
		btnCancel    = new JButton("Cancel");
		
		add(lTitle);
		add(eTitle);		
		add(lAuthor);
		add(eAuthor);		
		add(lISBN);
		add(eISBN);		
		
		add(btnOK);
		add(btnCancel);
		
		addListeners();
	}	
	public int showModal(){
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
					if( checkForm() ){
						
						modalResult = 1;
						close();
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
		}else if(eAuthor.getText().length()==0){
			return false;
		}else if(eISBN.getText().length()==0){
			return false;
		}
		return true;
	}
	
	private boolean insertAuthor(){
		return true;
	}
}


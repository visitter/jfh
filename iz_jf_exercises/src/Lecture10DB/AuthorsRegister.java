package Lecture10DB;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class AuthorsRegister extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private JTextField eFName;
	private JTextField eLName;
	
	private JLabel lFName;
	private JLabel lLName;
	
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnEdit;
	
	private JPanel pnlFilter;
	
	private JScrollPane scrollPane;
	private BorderLayout bl;
	
	private MyTable table;
	private String[] columnTitles = {"Номер", "Име", "Фамилия"};
	
	private JdbcConnector con = null;
	public void setCon( JdbcConnector con ){
		this.con = con;
	}
	public JdbcConnector getCon(){
		return this.con;
	}
	
	public AuthorsRegister(){
		bl = new BorderLayout();
		setLayout( bl );
		setTitle("Authors");
		setSize(640, 480);
		setResizable(false);
		
		lFName = new JLabel("First name");
		lLName = new JLabel("Last name");
		
		eFName = new JTextField();
		eFName.setPreferredSize(new Dimension(100,22));
		eLName = new JTextField();
		eLName.setPreferredSize(new Dimension(100,22));
		
		btnSearch = new JButton("Search");
		btnAdd    = new JButton("Add");
		btnEdit   = new JButton("Edit");
		
		pnlFilter = new JPanel();
		pnlFilter.add(lFName);
		pnlFilter.add(eFName);
		pnlFilter.add(lLName);		
		pnlFilter.add(eLName);
		pnlFilter.add(btnSearch);
		pnlFilter.add(btnAdd);
		pnlFilter.add(btnEdit);
		pnlFilter.setPreferredSize(new Dimension(100,65));
		
		table = new MyTable(null, columnTitles);		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize( new Dimension(100, getHeight()-175));
		table.setFillsViewportHeight(true);		
		
		add(pnlFilter,BorderLayout.NORTH);
		add(scrollPane,BorderLayout.SOUTH);
		
		addListeners();
	}
	
	private void addListeners(){
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				table.deleteAllRows();
				Object[] obj = new Object[3];
				
				if( (eFName.getText().trim().length()==0) && (eLName.getText().trim().length()==0) ){
					for(Author au: con.getAllAuthors()){
						obj[0]=au.getId();
						obj[1]=au.getFirstName();
						obj[2]=au.getLastName();					
						table.addRow(obj);
					}	
				}else if(eFName.getText().length()>0){
					for(Author au: con.getAuthorsByFirstName(eFName.getText().trim()) ){					
						obj[0]=au.getId();
						obj[1]=au.getFirstName();
						obj[2]=au.getLastName();					
						table.addRow(obj);
					}
				}else if(eLName.getText().length()>0){
					for(Author au: con.getAuthorsByLastName(eLName.getText().trim()) ){
						obj[0]=au.getId();
						obj[1]=au.getFirstName();
						obj[2]=au.getLastName();					
						table.addRow(obj);
					}
				}				
			}
		});
	}
}

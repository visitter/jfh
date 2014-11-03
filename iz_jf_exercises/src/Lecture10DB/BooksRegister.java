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


public class BooksRegister extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private JTextField eTitle;
	private JTextField eAuthor;
	private JTextField eISBN;
	
	private JLabel lTitle;
	private JLabel lAuthor;
	private JLabel lISBN;
	
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnEdit;
	
	private JPanel pnlFilter;
	
	private JScrollPane scrollPane;
	private BorderLayout bl;
	
	private MyTable table;
	private String[] columnTitles = {"Номер", "Заглавие", "Автор", "ISBN"};
	
	private JdbcConnector con = null;
	public void setCon( JdbcConnector con ){
		this.con = con;
	}
	public JdbcConnector getCon(){
		return this.con;
	}
	
	public BooksRegister(){
		bl = new BorderLayout();
		setLayout( bl );
		setTitle("Authors");
		setSize(640, 480);
		setResizable(false);
		
		lTitle  = new JLabel("Title");
		lAuthor = new JLabel("Author");
		lISBN   = new JLabel("ISBN");
		
		eTitle = new JTextField();
		eTitle.setPreferredSize(new Dimension(100,22));
		eAuthor = new JTextField();		
		eAuthor.setPreferredSize(new Dimension(100,22));
		eISBN = new JTextField();
		eISBN.setPreferredSize(new Dimension(100,22));
		
		btnSearch = new JButton("Search");
		btnAdd    = new JButton("Add");
		btnEdit   = new JButton("Edit");
		
		pnlFilter = new JPanel();
		
		pnlFilter.add(lTitle);
		pnlFilter.add(eTitle);		
		pnlFilter.add(lAuthor);
		pnlFilter.add(eAuthor);		
		pnlFilter.add(lISBN);
		pnlFilter.add(eISBN);		
		
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
				Object[] obj = new Object[4];
				
				if( (eTitle .getText().trim().length()==0)&&
					(eAuthor.getText().trim().length()==0)&&
					(eISBN  .getText().trim().length()==0) ){
					
					for(Book b: con.getBooks(0,null,null,null)){
						obj[0] =b.getId();
						obj[1] =b.getTitle();
						obj[2] =b.getYear();
						obj[3] =b.getIsbn();
						table.addRow(obj);
					}	
				}else if(eTitle.getText().length()>0){					
					for(Book b: con.getBooks(1,null,eTitle.getText().trim(),null) ){					
						obj[0] =b.getId();
						obj[1] =b.getTitle();
						obj[2] =b.getYear();
						obj[3] =b.getIsbn();				
						table.addRow(obj);
					}
					
				}else if(eISBN.getText().length()>0){					
					for(Book b: con.getBooks(3,null,null,eISBN.getText().trim()) ){					
						obj[0] =b.getId();
						obj[1] =b.getTitle();
						obj[2] =b.getYear();
						obj[3] =b.getIsbn();				
						table.addRow(obj);
					}
				}				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookView bv = new BookView();
				bv.setSize(200,140);
				bv.setModal(true);
				bv.setLocationRelativeTo(null);
				System.out.println( bv.showModal());
				
				
			}
		});
	}
}

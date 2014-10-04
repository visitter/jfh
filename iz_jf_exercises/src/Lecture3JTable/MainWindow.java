package Lecture3JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame{	
	private static final long serialVersionUID = 1L;
	
	JPanel pnl;
	JButton btnAdd;
	JTable table;	
	
	String[] columnTitles = {"Име", "Фамилия", "Град", "Години"};	
		
	DefaultTableModel dfm;
	
	public void close(){
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	public MainWindow(){
		super();
		
		setLayout(null);
		setResizable(false);
		
		btnAdd = new JButton("Добави");
		btnAdd.setSize(80, 20);
		btnAdd.setLocation(10, 10);

		dfm = new DefaultTableModel(null, columnTitles);
		table = new JTable(null, columnTitles);
		table.setModel(dfm);
		
				
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(0, 50);
		scrollPane.setSize(500, 200);
		table.setFillsViewportHeight(true);

		setTitle("Главен прозорец");
		setSize(500,300);
		setLocationRelativeTo(null);
		
		addListeners();
		
		add(btnAdd);		
		add(scrollPane);
	}
	
	private void addRow( Object[] info){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow( info ) ;
	}
	
	private void addListeners(){
		btnAdd.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JInputData oDlg = new JInputData();
				
				if( oDlg.showDlg()){
					//System.out.println("OK");
					
					Object[] obj = new Object[4];
					obj[0] =oDlg.getPerson().getcFName();
					obj[1] =oDlg.getPerson().getcLName();
					obj[2] =oDlg.getPerson().getcCity();
					obj[3] =oDlg.getPerson().getcYears();

					addRow(obj);
				}else{
					//System.out.println("Cancel");
				}
							
			}
		});
	}	
}
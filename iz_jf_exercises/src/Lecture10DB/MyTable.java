package Lecture10DB;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyTable extends JTable{
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dfm;	
	
	public MyTable(){		
		super();
	}
	public MyTable(Object[][] rowdata, String[] columnTitles){
		super(null,columnTitles);
		
		dfm = new DefaultTableModel(null, columnTitles);
		setModel(dfm);
	}		
	public void addRow( Object[] info){
		dfm.addRow(info);		
		//DefaultTableModel model = (DefaultTableModel) getModel();		
		//model.addRow( info ) ;
	}
	public void insertRow(int row, Object[] info){
		dfm.insertRow(row, info);		
	}	
	public void deleteRow( int row){
		dfm.removeRow(row);		
	}
	public void deleteAllRows(){
		while( dfm.getRowCount()>0){
			dfm.removeRow(0);
		}
	}
}

package Lecture3JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class JInputData extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private JLabel lbFName;
	private JLabel lbLName;
	private JLabel lbCity;
	private JLabel lbYears;
	private JTextField txtFName;
	private JTextField txtLName;
	private JTextField txtCity;
	private JFormattedTextField txtYears;
	private JButton btnOK;
	private JButton btnCancel;

	private boolean lOK = false;	
	public Person person;
	
	public JInputData(){
		super();
		setSize(190, 280);
		setLayout(null);
		setTitle("Данни за човек");
		
		
		lbFName = new JLabel("Име");
		lbLName = new JLabel("Фамилия");
		lbCity  = new JLabel("Град");
		lbYears = new JLabel("Години");
		
		lbFName.setSize(80,20);
		lbLName.setSize(80,20);
		lbCity .setSize(80,20);
		lbYears.setSize(80,20);
		
		txtFName = new JTextField();
		txtLName = new JTextField();
		txtCity  = new JTextField();
		
		txtYears = new JFormattedTextField(createMask("##"));		
		txtYears.setText("00");
		
		txtFName.setSize(155,20);
		txtLName.setSize(155,20);
		txtCity .setSize(155,20);
		txtYears.setSize(155,20);
		
		lbFName.setLocation(10,10);
		txtFName.setLocation(10,30);
		
		lbLName.setLocation(10,50);
		txtLName.setLocation(10,70);
		
		lbCity.setLocation(10,90);
		txtCity.setLocation(10,110);	
		
		lbYears.setLocation(10,130);
		txtYears.setLocation(10,150);  
		
		btnOK = new JButton("OK");
		btnCancel = new JButton("Отказ");
		
		btnOK.setLocation(10, 190);		
		btnOK.setSize(75, 22);		
		
		btnCancel.setLocation(90, 190);
		btnCancel.setSize(75, 22);		
		
		add(lbFName);
		add(txtFName);
		
		add(lbLName);
		add(txtLName);
		
		add(lbCity);
		add(txtCity);
		
		add(lbYears);
		add(txtYears);
		
		add(btnOK);
		add(btnCancel);
		
		addListeners();
		
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public void setPerson( Person person ){
		this.person = person;
		if( this.person!=null){
			txtFName.setText(this.person.getcFName());
			txtLName.setText(this.person.getcLName());
			txtCity .setText(this.person.getcCity());
			txtYears.setText(this.person.getcYears());
		}
	}	
	public Person getPerson(){
		return this.person;
	}
	public boolean showDlg(){
		setVisible(true);
		return lOK; 
	}
	public void close(){
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	private MaskFormatter createMask(String mask){
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(mask);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatter;
	}
	private void addListeners(){
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( checkValues() ){					
					person = new Person();
					person.setcFName(txtFName.getText());
					person.setcLName(txtLName.getText());
					person.setcCity (txtCity.getText());
					person.setcYears(txtYears.getText());				
					lOK = true;
					close();
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();				
			}
		});		
	}
	private boolean checkValues(){
		
		if( txtFName.getText().trim().isEmpty() ){
			JOptionPane.showMessageDialog(null, "Невалидно име", "Error", JOptionPane.ERROR_MESSAGE);
			txtFName.requestFocus();
			return false;
		}else if( txtLName.getText().trim().isEmpty() ){
			JOptionPane.showMessageDialog(null, "Невалидна фамилия", "Error", JOptionPane.ERROR_MESSAGE);
			txtLName.requestFocus();
			return false;
		}else if( txtCity.getText().trim().isEmpty() ){
			JOptionPane.showMessageDialog(null, "Невалиден град", "Error", JOptionPane.ERROR_MESSAGE);
			txtCity.requestFocus();
			return false;
		}else if( txtYears.getText().trim().isEmpty() ){
			JOptionPane.showMessageDialog(null, "Невалидни години", "Error", JOptionPane.ERROR_MESSAGE);
			txtYears.requestFocus();
			return false;
		}else return true;
	}
}
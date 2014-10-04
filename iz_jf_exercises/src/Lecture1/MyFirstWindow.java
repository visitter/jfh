package Lecture1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//Experimenting with JFrame class
class MyFirstWindow extends JFrame{
	private class MyAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if( e.getSource()==txtFText){
				lblFname.setText(e.getActionCommand());
			}else if(e.getSource()==txtLText){
				lblLname.setText(e.getActionCommand());
			}else if(e.getSource()==cbTest){
				JOptionPane.showMessageDialog(null, cbTest.getSelectedItem().toString());
			}
		}		
	}
	
	private static final long serialVersionUID = 1L;
	
	JLabel lblFname;
	JLabel lblLname;
	JTextField txtFText;
	JTextField txtLText;
	JButton btnOK;
	JButton btnCancel;
	JComboBox<String> cbTest;
	String[] items = {"1","2","3"};
	MyAction action;
	
	public MyFirstWindow() {
		//setLayout(new GridLayout());
		super();
		init();
	}	
	public MyFirstWindow(String title){
		super(title);
		init();
	}	
	public void init(){
		setLayout(null);
		int sizeW= 80;
		int sizeH = 22;
		
		setSize(250, 200);
		
		lblFname = new JLabel("First name:");
		lblFname.setLocation(10, 10);
		lblFname.setSize(sizeW, sizeH);		
		
		lblLname = new JLabel("Last name: ");
		lblLname.setLocation(10, 40);
		lblLname.setSize(sizeW, sizeH);
		
		txtFText = new JTextField("Type something...");
		txtFText.setLocation(sizeW+10, 10);
		txtFText.setSize(70, 22);
		
		txtLText = new JTextField("Type something...");
		txtLText.setLocation(sizeW+10, 40);
		txtLText.setSize(70, 22);		
		
		cbTest = new JComboBox<String>(items);
		cbTest.setLocation(sizeW+10, 70);
		cbTest.setSize(70, 22);
		
		add(lblFname);
		add(lblLname);
		add(txtFText);
		add(txtLText);
		add(cbTest);
		
		action = new MyAction();
		txtFText.addActionListener(action);
		txtLText.addActionListener(action);
		cbTest.addActionListener(action);
	}	
	void addMyListeners(){
		txtFText.addKeyListener( new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==10 ){
					lblFname.setText(txtFText.getText());
				}				
			}
		});
		txtLText.addKeyListener( new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==10 ){
					lblLname.setText(txtLText.getText());
				}				
			}
		});
	}
}

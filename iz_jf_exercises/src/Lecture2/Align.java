package Lecture2;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

class Align extends WindowTask{
	private static final long serialVersionUID = 1L;
	
	private JCheckBox chbToGrid;
	private JCheckBox chbShowGrid;
	private JLabel lblX;
	private JLabel lblY;
	private JTextField txtX;
	private JTextField txtY;
	private JButton btnOK;
	private JButton btnCancel;
	private JButton btnHelp;
	
	public final String cTitle = "Align";
	
	public Align(){
		setLayout(null);
		
		chbToGrid = new JCheckBox("Snap to Grid", false);
		chbToGrid.setToolTipText("Snaps to Grid");
		chbShowGrid = new JCheckBox("Show Grid", false);
		
		lblX = new JLabel("X:");
		lblY = new JLabel("Y:");
		
		txtX = new JTextField("8");
		txtY = new JTextField("8");
		
		btnOK = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		btnHelp = new JButton("Help");
		
		setSize(300, 150);
		chbToGrid.setLocation(5, 30);
		chbToGrid.setSize(100, 16);
		chbShowGrid.setLocation(5, 50);
		chbShowGrid.setSize(100, 16);
		
		lblX.setLocation(110, 15);
		lblX.setSize(25, 21);		
		lblY.setLocation(110, 55);
		lblY.setSize(25, 21);
		
		txtX.setLocation(140, 15);
		txtX.setSize(50, 21);
		txtY.setLocation(140, 58);
		txtY.setSize(50, 21);
		
		btnOK.setLocation(200, 10);
		btnOK.setSize(80, 22);
		btnCancel.setLocation(200, 40);
		btnCancel.setSize(80, 22);
		btnHelp.setLocation(200, 70);
		btnHelp.setSize(80, 22);
		
		add(chbToGrid);
		add(chbShowGrid);
		add(lblX);
		add(lblY);
		add(txtX);
		add(txtY);
		add(btnOK);
		add(btnCancel);
		add(btnHelp);
		
		setResizable(false);
		setTitle(cTitle);
		setCentered();
	}
}
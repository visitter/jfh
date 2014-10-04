package Lecture2;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

class ColorSelector extends WindowTask{
	private static final long serialVersionUID = 3694073413478543676L;
	private JComboBox<String> cbColor;
	private JCheckBox chbBkg;
	private JCheckBox chbFrg;
	private JButton btnOK;
	private JButton btnCancel;
	
	private String[] colors = {"RED","GREEN","BLUE"};

	public ColorSelector(){
		setLayout(null);
		setSize(300, 120);
		setTitle("Color select");
		cbColor = new JComboBox<String>(colors);
		cbColor.setLocation(2, 2);
		cbColor.setSize(getWidth()-10, 22);
		
		chbBkg = new JCheckBox("Background", false);
		chbBkg.setToolTipText("Background");
		chbFrg = new JCheckBox("Foreground", false);
		
		chbBkg.setLocation(40, 30);
		chbBkg.setSize(100, 16);
		chbFrg.setLocation(150, 30);
		chbFrg.setSize(100, 16);
		
		btnOK = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		
		btnOK.setLocation(60, 50);
		btnOK.setSize(60, 22);
		btnCancel.setLocation(140, 50);
		btnCancel.setSize(80, 22);	
		
		add(cbColor);
		add(chbBkg);
		add(chbFrg);
		add(btnOK);
		add(btnCancel);
		setVisible(true);
		setResizable(false);
		setCentered();
	}
}
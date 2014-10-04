package Lecture2;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

class PrinterSelector extends WindowTask{
	private static final long serialVersionUID = -272997249962083892L;
	private JLabel lblPrinter;
	private JLabel lblQuality;
	
	private JCheckBox chbImage;
	private JCheckBox chbText;
	private JCheckBox chbCode;
	private JCheckBox chbPrint;
	
	private JTextArea taOne;
	private JTextArea taTwo;
	private JTextArea taThree;
	
	private JRadioButton rbSel;
	private JRadioButton rbAll;
	private JRadioButton rbApplet;
	private ButtonGroup rgGroup;
	
	private JComboBox<String> cbQuality;
	
	private JButton btnOK;
	private JButton btnCancel;
	private JButton btnSetup;
	private JButton btnHelp;
	
	private String[] arrQuality = {"HIGH","MEDIUM","LOW"};
	
	public PrinterSelector(){
		setLayout(null);
		setSize(450,180);
		setTitle("Printer");
		setResizable(false);
		setCentered();
		
		chbImage = new JCheckBox("Image", false);		
		chbText = new JCheckBox("Text", false);
		chbCode = new JCheckBox("Code", false);
		chbPrint = new JCheckBox("Print to file", false);
		
		lblPrinter = new JLabel("Printer: MyPrinter");		
		lblQuality = new JLabel("Print Quality:");
		
		taOne = new JTextArea();
		taTwo = new JTextArea();
		taThree = new JTextArea();
		
		rbSel = new JRadioButton("Selection");
		rbAll = new JRadioButton("All");		
		rbApplet = new JRadioButton("Applet");
		
		rgGroup = new ButtonGroup();
		rgGroup.add(rbSel);
		rgGroup.add(rbAll);
		rgGroup.add(rbApplet);
		
		cbQuality = new JComboBox<String>(arrQuality);		
		
		btnOK = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		btnSetup = new JButton("Setup");
		btnHelp = new JButton("Help");		
		
		lblPrinter.setLocation(50, 10);
		lblPrinter.setSize(100, 22);		
		taOne.setLocation(50, 35);
		taOne.setSize(45,60);	
		lblQuality.setLocation(55, 100);
		lblQuality.setSize(100, 22);
		cbQuality.setLocation(155, 100);
		cbQuality.setSize(60, 22);
				
		chbImage.setLocation(95, 35);
		chbImage.setSize(60, 16);
		chbText.setLocation(95, 55);
		chbText.setSize(60, 16);
		chbCode.setLocation(95, 75);
		chbCode.setSize(60, 16);
		chbPrint.setLocation(225, 100);
		chbPrint.setSize(100, 16);
		
		taTwo.setLocation(160, 35);
		taTwo.setSize(45,60);
		
		rbSel.setLocation(205, 35);
		rbSel.setSize(80, 16);
		rbAll.setLocation(205, 55);
		rbAll.setSize(80, 16);
		rbAll.setSelected(true);
		rbApplet.setLocation(205, 75);
		rbApplet.setSize(80, 16);
		
		taThree.setLocation(285, 35);
		taThree.setSize(45,60);
		
		btnOK.setLocation(335, 10);
		btnOK.setSize(80, 22);
		btnCancel.setLocation(335, 45);
		btnCancel.setSize(80, 22);
		btnSetup.setLocation(335, 80);
		btnSetup.setSize(80, 22);
		btnHelp.setLocation(335, 115);
		btnHelp.setSize(80, 22);
		
		add(lblPrinter);
		add(taOne);
		add(chbImage);
		add(chbText);
		add(chbCode);
		add(taTwo);
		add(rbSel);
		add(rbAll);
		add(rbApplet);
		add(taThree);		
		add(btnOK);
		add(btnCancel);
		add(btnSetup);
		add(btnHelp);
		add(lblQuality);
		add(cbQuality);
		add(chbPrint);
	}
}
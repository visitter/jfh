package Lecture2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class TempCalculator extends JFrame{
	private static final long serialVersionUID = 314808628801883879L;
	private JTextField txtInputTemp;
	private JLabel lblResultTemp;
	private JLabel lblResultTempK;	
	private JRadioButton rbCtoF;
	private JRadioButton rbFtoC;		
	private ButtonGroup bgGroup;
	private ActionListener myAction;
	private DecimalFormat df;
	
	public TempCalculator(){
		super();		
		setSize(200,100);
		//setLayout(null);
		setLayout(new FlowLayout());
		setTitle("Temp calculator");	
		setResizable(false);
		
		txtInputTemp = new JTextField(3);
		txtInputTemp.setSize(50,22);
		txtInputTemp.setLocation(10, 10);
		df = new DecimalFormat("#.##");		
		
		myAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				double temp = Double.parseDouble(txtInputTemp.getText());
				Double res;
				if( rbCtoF.isSelected() ){
					res = (temp*9/5+32);
					lblResultTemp.setText("Temp in Farenheit is: "+  df.format(res));
					lblResultTempK.setText("Temp in Kelvin is: "+  df.format(temp+253));
				}else{
					res = ((temp-32)*5/9);					
					lblResultTemp.setText("Temp in Celsius is: "+ df.format(res));
					lblResultTempK.setText("Temp in Kelvin is: "+  df.format(res+253));
				}				
			}
		};
		
		txtInputTemp.addActionListener(myAction);
		txtInputTemp.setText("25");
		
		lblResultTemp = new JLabel("Temp = ");
		lblResultTempK= new JLabel("Temp in K = ");
		
		bgGroup = new ButtonGroup();
		rbCtoF  = new JRadioButton();
		rbFtoC  = new JRadioButton();		
		
		bgGroup.add(rbCtoF);
		bgGroup.add(rbFtoC);
		rbCtoF.setSelected(true);
		rbCtoF.setText("C to F");
		rbFtoC.setText("F to C");
		
		rbCtoF.addActionListener(myAction);
		rbFtoC.addActionListener(myAction);	
		
		add(rbCtoF);
		add(rbFtoC);
		add(txtInputTemp);
		add(lblResultTemp);
		add(lblResultTempK);
		
		rbCtoF.doClick();		
	}
}
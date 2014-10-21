package Lecture8;
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class MathApplet extends JApplet{	
	private static final long serialVersionUID = 1L;	
	private Double firstNumber;		
	private Double secondNumber;
	private Double[] res = new Double[4];
	
	public void init(){
		boolean lOK = false; 
		while( !lOK ){
			try{
				firstNumber  = Double.parseDouble( JOptionPane.showInputDialog("Въведете първото число:") );		
				secondNumber = Double.parseDouble( JOptionPane.showInputDialog("Въведете второто число:") );
				
				if( secondNumber == 0) 
					throw new NullPointerException("Второто число не може да е 0");
				
				lOK	= true;
				
				res[0]=firstNumber+secondNumber;
				res[1]=firstNumber*secondNumber;
				res[2]=firstNumber-secondNumber;
				res[3]=firstNumber/secondNumber;
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, "Моля, въведете число", "Error", JOptionPane.ERROR_MESSAGE);			
			}catch( NullPointerException npe ){
				String message = npe.getMessage();
				if( message!=null)
					JOptionPane.showMessageDialog(null, npe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Моля, въведете число: ", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawString("sum of "    +firstNumber+"+"+secondNumber+" = "+res[0], 10, 10);
		g.drawString("product of "+firstNumber+"*"+secondNumber+" = "+res[1], 10, 30);
		g.drawString("diff of "   +firstNumber+"-"+secondNumber+" = "+res[2], 10, 50);
		g.drawString("division "  +firstNumber+"/"+secondNumber+" = "+res[3], 10, 70);		
	}
}

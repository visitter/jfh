package Lecture8;
import java.awt.Graphics;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class CompareApplet extends JApplet{
	private static final long serialVersionUID = 1L;	
	private Double firstNumber;		
	private Double secondNumber;
	private String result;
	private final String text = "От числата %.2f and %.2f, %.2f е по-голямо";
	
	public void init(){
		boolean lOK = false; 
		while( !lOK ){
			try{
				firstNumber  = Double.parseDouble( JOptionPane.showInputDialog("Въведете първото число:") );		
				secondNumber = Double.parseDouble( JOptionPane.showInputDialog("Въведете второто число:") );				 
				
				lOK = true;
				
				if( firstNumber>secondNumber)
					result=String.format( text, firstNumber,secondNumber,firstNumber);
				else if( firstNumber<secondNumber)
					result=String.format( text, secondNumber,firstNumber,secondNumber);
				else// if( firstNumber.equals(secondNumber) )
					result=String.format("Въведените числа са равни");
				
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, "Моля, въведете число", "Error", JOptionPane.ERROR_MESSAGE);			
			}catch( NullPointerException npe ){
				JOptionPane.showMessageDialog(null, "Моля, въведете число: ", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawString(result, 10, 10);
	}
}

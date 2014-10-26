package Lecture8;

import java.awt.Graphics;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class CircleApplet extends JApplet{
	private static final long serialVersionUID = 1L;	
	private Double   radius;		
	private Double []results = new Double[3];
	
	public void init(){
		boolean lOK = false; 
		
		while( !lOK ){
			try{
				radius  = Double.parseDouble( JOptionPane.showInputDialog("Въведете радиус:") );									 
				
				lOK = true;
				
				results[0] = 2*radius;
				results[1] = 2*Math.PI*radius;
				results[2] = Math.PI*Math.pow(radius, 2);
				
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, "Моля, въведете число", "Error", JOptionPane.ERROR_MESSAGE);			
			}catch( NullPointerException npe ){
				JOptionPane.showMessageDialog(null, "Моля, въведете число: ", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawString("diameter : "+results[0].toString(), 10, 10);
		g.drawString("perimeter: "+results[1].toString(), 10, 30);
		g.drawString("surface  : "+results[2].toString(), 10, 50);
	}
}

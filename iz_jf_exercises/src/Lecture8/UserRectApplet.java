package Lecture8;

import java.awt.Graphics;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class UserRectApplet extends JApplet{
	private static final long serialVersionUID = 1L;	
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;		
		
	public void init(){
		boolean lOK = false; 
		
		while( !lOK ){
			try{
				x      = Integer.parseInt( JOptionPane.showInputDialog("Въведете X координата:") );
				y      = Integer.parseInt( JOptionPane.showInputDialog("Въведете Y координата:") );
				width  = Integer.parseInt( JOptionPane.showInputDialog("Въведете ширина:") );
				height = Integer.parseInt( JOptionPane.showInputDialog("Въведете височина:") );
				
				if( (x<0)||(y<0)||(width<0)||(height<0)	)
					throw new NumberFormatException();
				
				lOK = true;				
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, "Моля, въведете числа >=0", "Error", JOptionPane.ERROR_MESSAGE);			
			}catch( NullPointerException npe ){
				JOptionPane.showMessageDialog(null, "Моля, въведете числа >=0", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		//task 5
		g.drawRect(x, y, width, height);
		
		//task 6
		g.drawOval(x, y, width, height);
	}
}

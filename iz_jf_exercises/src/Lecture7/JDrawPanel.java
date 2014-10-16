package Lecture7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class JDrawPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private int diameter = 100;
	private boolean random = true;
	private Color color;

	public JDrawPanel(){
		
	}
	public void setRandomSize( boolean random){
		this.random = random;
		if( random ){
			//random size
			Random rand = new Random();		
			setDiameter(rand.nextInt(getWidth()>getHeight()? getHeight():getWidth()));
		}else{
			//resize with self
			setDiameter(getWidth()-5);
		}
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);		
		g.setColor(color);
		if( random )
			g.fillOval(5, 5, diameter, diameter);
		else
			g.fillOval(0, 0, getWidth(), getHeight());
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getDiameter() {
		return diameter;
	}
	public void setDiameter(int diameter) {		
		this.diameter = diameter;
		repaint();
	}
	public boolean isRandom() {
		return random;
	}
	public void setRandom(boolean random) {
		this.random = random;
	}
	
}

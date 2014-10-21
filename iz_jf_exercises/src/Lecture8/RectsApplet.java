package Lecture8;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.JApplet;

public class RectsApplet extends JApplet{
	private static final long serialVersionUID = 1L;	
	private Random rand;
	public void init(){
		rand = new Random();
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawRect(rand.nextInt(80), rand.nextInt(80), rand.nextInt(100), rand.nextInt(100));		
	}
}

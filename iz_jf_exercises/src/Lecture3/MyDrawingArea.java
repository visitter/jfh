package Lecture3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JPanel;

//Tasks 1,2,3,4,5
class MyDrawingArea extends JPanel{
	private static final long serialVersionUID = 1L;	
	private Random rand = new Random();
	public int taskId = 1;	
	
	public MyDrawingArea(int taskId){
		super();
		this.taskId = taskId;
	}	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		switch (taskId){
			case 1:{ concentricCircles(g); break; }
			case 2:{ concentric2DCircles((Graphics2D)g); break; }
			case 3:{ randomLines((Graphics2D)g); break; }
			case 4:{ randomChars(g); break; }
			case 5:{ randomTriangles((Graphics2D)g); break; }
			
			default:concentricCircles(g);
		}
	}	
	//task 1
	public void concentricCircles(Graphics g){
		int step = 10;
		int h = 10;
		int w = 10;
		for( int ii=0; ii<10; ii++){
			g.drawOval(150-(w+ii*step/2), 150-(h+ii*step/2), w+ii*step, h+ii*step);
		}
	}
	//task 2	
	public void concentric2DCircles(Graphics2D g2d){
		int stepW = this.getWidth()/10;
		int stepH = this.getHeight()/10;
	
		Random rand = new Random();
		
		final float dash1[] = {8.0f};
		
		BasicStroke dashed;
		
		for( int ii=1; ii<11; ii++ ) {		
			//coloring		
			g2d.setColor( getRandomColor() );
			
			//coloring
			dashed = new BasicStroke( rand.nextInt(50)/1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);			
			g2d.setStroke(dashed);
			
			g2d.draw( new Ellipse2D.Double(this.getWidth()/2-(ii*stepW/2), this.getHeight()/2-(ii*stepH/2), ii*stepW, ii*stepH));
		}
	}
	//task 3
	public void randomLines(Graphics2D g2d){
		final int len = 200;
		int bx =0, by=0;
		
		for( int i = 0; i<5; i++){
			bx = rand.nextInt(len);
			by = rand.nextInt(len);	
		
			g2d.setStroke( new BasicStroke( rand.nextInt(5)*1f));
			g2d.setColor( getRandomColor() );
			g2d.draw( new Line2D.Double(bx*1d,by*1d,rand.nextInt(len)*1d, rand.nextInt(len)*1d ) );
		}
	}	
	//task 4
	public void randomTriangles(Graphics2D g2d){
		final int len = 200;
		int bx =0, by=0;
		
		GeneralPath[] triangle = new GeneralPath[5];
		
		for( int i = 0; i<5; i++){
			bx = rand.nextInt(len);
			by = rand.nextInt(len);
			
			//setting up triangles
			triangle[i] = new GeneralPath();			
			triangle[i].moveTo(bx, by);			
			triangle[i].lineTo(rand.nextInt(len), rand.nextInt(len));
			triangle[i].lineTo(rand.nextInt(len), rand.nextInt(len));			
			triangle[i].lineTo(bx, by);
			
			//random colors
			g2d.setColor( getRandomColor() );
			
			//drawing
			g2d.draw(triangle[i]);		
		}
	}	
	//task 5
	public void randomChars(Graphics g){
		final int len = 200;
		final int offset = 65;
		int bx =0, by=0;
		
		
		for( int i = 0; i<10; i++){
			bx = rand.nextInt(len);
			by = rand.nextInt(len);
			
			g.setColor( getRandomColor() );
			
			char[] arr = Character.toChars(rand.nextInt(51)+offset);
			
			String fontName = "Arial";

			switch( rand.nextInt(3) ) {
				case 0 :{ fontName ="Times New Roman"; break; }
				case 1 :{ fontName ="Courier New"; break; }
				case 2 :{ fontName ="Monospace"; break; }
				case 3 :{ fontName ="Tahoma"; break; }
				default:{ fontName ="Arial"; break; }
			}
			g.setFont( new Font( fontName, rand.nextInt(2) , rand.nextInt(48)));						
			g.drawString( fontName+":"+Character.toString(arr[0]), bx, by);			
		}
	}
	public Color getRandomColor(){
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);	
		
		return new Color(r,g,b);
	}
}
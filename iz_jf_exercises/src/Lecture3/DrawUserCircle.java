package Lecture3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//Tasks 6,7
public class DrawUserCircle extends JFrame{	
	private static final long serialVersionUID = 1L;
	private class DrawArea extends JPanel{
		private static final long serialVersionUID = 1L;
		public Shape shape = null;		
		
		public DrawArea( Shape shape ){
			super();
			this.shape = shape;
		}		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			drawThisShape((Graphics2D)g, shape);
			drawThisShapeInfo((Graphics2D)g, shape);
		}		
		public void drawThisShape(Graphics2D g2d, Shape shape){
			if( shape!=null)
				g2d.draw(shape);
		}
		public void drawThisShapeInfo(Graphics2D g2d, Shape shape){
			if( shape instanceof Ellipse2D){
				double bx = ((Ellipse2D) shape).getX()+((Ellipse2D) shape).getWidth()+10;
				double by = ((Ellipse2D) shape).getY()+20;//((Ellipse2D) shape).getHeight()+20;
				
				g2d.drawString(String.format("diameter = %.2f", ((Ellipse2D) shape).getWidth()), (int)bx, (int)by);
				g2d.drawString(String.format("circumference = %.2f", Math.PI*((Ellipse2D) shape).getWidth()), (int)bx, (int)by+20);
				g2d.drawString(String.format("area = %.2f", Math.PI*Math.pow( ((Ellipse2D) shape).getWidth()/2, 2)), (int)bx, (int)by+40);				
			}
		}
	}
	
	private Boolean lRunning = false;
	private DrawArea canvas;
	private Random rand = new Random();
	private JPanel pnl = new JPanel();
	private Point point;
	
	public DrawUserCircle(){
		super();
				
		WindowAdapter wa = new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				//task 6
				/*
				canvas = new DrawArea( createCircleByUser() );
				canvas.setSize(getWidth(), getHeight());
				add(canvas);				
				canvas.repaint();
				*/
			}
		};		
		KeyAdapter ka = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				lRunning=false;
				//System.out.println("Key pressed");
			}
		};		
		MouseAdapter ma = new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				lRunning=false;
				//System.out.println("mousePressed");
			}
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if( point!=null){
					if( (point.x!=arg0.getXOnScreen())||(point.y!=arg0.getYOnScreen()) )
						lRunning=false;
				}else{
					point = new Point(arg0.getXOnScreen(),arg0.getYOnScreen());
				}
				//System.out.println("mouseMoved: "+arg0.toString());	
			}
		};
		
		addWindowListener(wa);
		addKeyListener(ka);
		addMouseListener(ma);
		addMouseMotionListener(ma);
	}
	public void drawUserCircle(){
		canvas = new DrawArea( createCircleByUser() );
		canvas.setSize(getWidth(), getHeight());
		add(canvas);				
		canvas.repaint();		
	}
	public Shape createCircleByUser(){
		float radius = 0f;
		int x =0 ,y = 0;
		point = new Point(0,0);
		
		while( (radius == 0)||(x==0)||(y==0) ){
			try{			
				if( radius == 0f ){ 
					radius = Float.parseFloat(JOptionPane.showInputDialog(this, "Please, enter the circle radius:"));
				}
				
				if( x == 0 ){
					x = Integer.parseInt(JOptionPane.showInputDialog(this, "Please, enter the circle X coordinate:"));
				}
				
				if( y == 0 ){				
					y = Integer.parseInt(JOptionPane.showInputDialog(this, "Please, enter the circle Y coordinate:"));
				}
				
			}catch(NumberFormatException nme){
				JOptionPane.showMessageDialog(this, "Please, input a valid number!");
			}
		}		
		return new Ellipse2D.Double(x,y,2*radius,2*radius);
	}
	
	//screen saver method
	public void createScreenSaver(){
		this.setLocation(0, 0);
		
		pnl.setSize(getWidth(), getHeight());
		pnl.setBackground(Color.BLACK);
		add(pnl);
		pnl.repaint();
		
		Graphics g = pnl.getGraphics();
		
		int lenX = getWidth();
		int lenY = getHeight();
		int nCount = 100;		
		
		lRunning = true;
		while( lRunning ){
			
			try{
				for( int i=0; i<nCount; i++){
					if( !lRunning )break;			
					
					g.setColor(getRandomColor());
					g.drawLine( rand.nextInt(lenX),rand.nextInt(lenY),rand.nextInt(lenX), rand.nextInt(lenY));
			
					Thread.sleep(100);
				}
				pnl.repaint();
			}catch (InterruptedException e) {				
				e.printStackTrace();
			}			
		}
		//System.out.println("Exiting");
		close();
	}
	public void close(){
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	public Color getRandomColor(){
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);	
				
		return new Color(r,g,b);
	}	
}
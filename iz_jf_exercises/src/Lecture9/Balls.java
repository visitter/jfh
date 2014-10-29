package Lecture9;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Balls extends JFrame{
	private static final long serialVersionUID = 4357229956332472295L;
		
	private JPanel drawArea = null;
	private int ballsCount=0;
	private Random colorGen;
	
	public Balls(){
		setTitle("Press the left mouse button");
		System.out.println( java.lang.Thread.activeCount());
		colorGen = new Random(256);
		
		drawArea = new JPanel();
		drawArea.setLocation(0,0);
		drawArea.setSize(500,500);	
		drawArea.setLayout(null);		
		drawArea.setDoubleBuffered(true);
		
		add(drawArea);
		
		addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				System.out.println("Thread before new ball = "+java.lang.Thread.activeCount());
				MyBall ball1 = new MyBall();
				
				ball1.setForeground(new Color(colorGen.nextInt()));
				ball1.setLocation(0, ballsCount*50);
				//ball1.setSize(50, 50);				
				//ball1.timeout=colorGen.nextInt(20);
				//ball1.hstep=colorGen.nextInt(100);
				//ball1.vstep=colorGen.nextInt(100);
				drawArea.add(ball1);				
				(new Thread(ball1)).start();
								
				if( ((ballsCount-2)*50)>getHeight())
					ballsCount = 0;
				
				ballsCount++;
				
			}
		});
	}
}

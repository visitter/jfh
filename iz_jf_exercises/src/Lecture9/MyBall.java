package Lecture9;

import java.awt.Graphics;
import javax.swing.JComponent;

public class MyBall extends JComponent implements Runnable{
	private static final long serialVersionUID = 1L;
	int hDirection = 0;
	int vDirection = 0;
	
	
	int hstep      = 5;
	int vstep      = 9;
	
	int bounces    = 0;	
	int timeout    = 29;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		g.fillOval(0, 0, getWidth(), getHeight());
	}
	
	public MyBall(){		
		super();
		setSize(25,25);
	}
	
	
	@Override
	public void run() {
		int size = getY()/(getParent().getHeight()/getWidth()-1 );
		while( bounces<8 ){
			switch( hDirection ){
				case 0://left
					{	
						if( getX()<getParent().getWidth()-getWidth()/*450*/ ){							
							setLocation(getX()+hstep, getY());
							
						}else {
							hDirection=1;
							bounces++;							
						}
						break;
				}
				
				case 1://right
					{	
						if( getX()>0 ){
							setLocation(getX()-hstep, getY());
						}else{
							hDirection = 0;
							bounces++;							
						}
						break;
					}
				default:{
					bounces++;
					break;
				}
			}
			
			switch( vDirection ){
				case 0://bottom
					{	
						if( getY()<getParent().getHeight()-getHeight()/*450*/ ){							
							setLocation(getX(), getY()+vstep);
						}else {
							vDirection=1;						
						}
						break;
					}
			
				case 1://top
					{	
						if( getY()>0 ){
							setLocation(getX(), getY()-vstep);
						}else{
							vDirection = 0;					
						}
						break;
					}
				default:{
					bounces++;
					break;
				}
			}
			
			size = getY()/(getParent().getHeight()/getWidth()-1 );
			
			if( hDirection<1 )
				getParent().getGraphics().clearRect(getX()-hstep, getParent().getHeight()-2, getX()+size, 2);
			else				
				getParent().getGraphics().clearRect(getX()-hstep, getParent().getHeight()-2, getX()+size, 2);
			
			getParent().getGraphics().drawRect (getX()      , getParent().getHeight()-2,  size    , 2);
			//System.out.println(getParent().getHeight()/50);
			try {
				Thread.sleep(timeout);				
			} catch (InterruptedException e) {
				// 	TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println( "Thread "+Thread.currentThread().getId()+" left. Count="+(java.lang.Thread.activeCount()-1));
		getParent().remove(this);
	}
}

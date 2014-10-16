package Lecture7;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;

public class JWindow2 extends JWindow {

	private static final long serialVersionUID = -8425532087680902164L;
	private JSlider slider;
	public JWindow2(){
		super();
		slider = new JSlider(10,200);
		slider.setPaintTicks(true);		
		slider.setValue(200);
		slider.setSnapToTicks(true);
		slider.setBorder( getPanel().getBorder() );
		slider.setMajorTickSpacing(10);
		slider.addMouseListener( new MouseAdapter() {		
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				getPanel().setRandom(true);
				getPanel().setDiameter(slider.getValue());
				calculateInfo();
				System.out.println(getPanel().getDiameter());
			}
		}); 
		
		add(slider,BorderLayout.SOUTH);
		
		addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				slider.setMaximum(getPanel().getWidth()>getPanel().getHeight()?getPanel().getHeight():getPanel().getWidth());
				slider.setValue(slider.getMaximum());
				calculateInfo();
			}
		});		
	}

}

package Lecture7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class JWindow extends JFrame{
	private static final long serialVersionUID = -1866038260631599619L;
	private JDrawPanel panel;
	private JScrollPane pane;
	private JTextArea taInfo;
	private JSlider slider;
	
	public JWindow (){		
		setLayout(new BorderLayout(5,5));
		panel = new JDrawPanel();
		panel.setColor( Color.YELLOW );
		panel.setBorder( BorderFactory.createLineBorder(Color.GRAY, 2));
		
		taInfo = new JTextArea();		
		taInfo.setEditable(false);
		pane = new JScrollPane(taInfo);
		pane.setBorder( panel.getBorder());
		
		slider = new JSlider(10,200);
		slider.setPaintTicks(true);		
		slider.setValue(200);
		slider.setSnapToTicks(true);
		slider.setBorder( panel.getBorder() );
		slider.setMajorTickSpacing(10);
		slider.addMouseListener( new MouseAdapter() {		
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				panel.setRandom(true);
				panel.setDiameter(slider.getValue());
				calculateInfo();
				System.out.println(panel.getDiameter());
			}
		}); 
		
		add(pane,BorderLayout.WEST);
		add(panel,BorderLayout.CENTER);		
		add(slider,BorderLayout.SOUTH);
		
		addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				panel.setRandomSize(false);
				calculateInfo();
				slider.setMaximum(panel.getWidth()>panel.getHeight()?panel.getHeight():panel.getWidth());
				slider.setValue(slider.getMaximum());
			}
		});		
	}
	
	public void calculateInfo(){		
		taInfo.setText("");
		taInfo.append( String.format("Diameter: %.2f\n", panel.getDiameter()*1f));		
		taInfo.append( String.format("Perimeter: %.2f\n", Math.PI * panel.getDiameter()*1f));
		taInfo.append( String.format("Area: %.2f\n", Math.PI *
									 			 Math.pow(
															(panel.getDiameter()/2)*1f,
															2*1f)
														  )
									);
	}
}

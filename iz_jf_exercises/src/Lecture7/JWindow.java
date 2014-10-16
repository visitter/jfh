package Lecture7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JWindow extends JFrame{
	private static final long serialVersionUID = -1866038260631599619L;
	private JDrawPanel panel;
	private JScrollPane pane;
	private JTextArea taInfo;		
	
	public JDrawPanel getPanel() {
		return panel;
	}

	public void setPanel(JDrawPanel panel) {
		this.panel = panel;
	}

	public JWindow (){
		super();
		setLayout(new BorderLayout(5,5));
		panel = new JDrawPanel();
		panel.setColor( Color.YELLOW );
		panel.setBorder( BorderFactory.createLineBorder(Color.GRAY, 2));
		
		taInfo = new JTextArea();		
		taInfo.setEditable(false);
		pane = new JScrollPane(taInfo);
		pane.setBorder( panel.getBorder());
		
		
		
		add(pane,BorderLayout.WEST);
		add(panel,BorderLayout.CENTER);		
		
		
		addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				panel.setRandomSize(false);
				calculateInfo();	
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

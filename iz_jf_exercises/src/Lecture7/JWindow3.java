package Lecture7;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

//TASK 6
public class JWindow3 extends JFrame{
	private static final long serialVersionUID = 8149092905311380812L;
	
	private GridBagLayout gbLayout;
	private GridBagConstraints gbConstraints;
	
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JTextField tf1;
	private JPanel pnl;
	
	public JWindow3(){
		super("Some new layout");
		gbLayout = new GridBagLayout();
		gbConstraints = new GridBagConstraints();
		setLayout( gbLayout );
		
		btn1 = new JButton("B1");
		btn2 = new JButton("B2");
		btn3 = new JButton("B3");
		tf1  = new JTextField("Some text");
		pnl  = new JPanel();
		pnl.setBackground(Color.GREEN);
		
		gbConstraints.weightx = 0;
		gbConstraints.weighty = 0;
		
		addComponentWithConstraints(1, 1, 1, 3, GridBagConstraints.BOTH, tf1);
		//experimenting as directed
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 1;
		addComponentWithConstraints(2, 1, 2, 1, GridBagConstraints.NONE, btn1);
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 0;
		addComponentWithConstraints(2, 3, 1, 1, GridBagConstraints.VERTICAL, btn2);		
		addComponentWithConstraints(3, 3, 1, 1, GridBagConstraints.HORIZONTAL, btn3);
		addComponentWithConstraints(2, 2, 2, 1, GridBagConstraints.BOTH, pnl);

	}
	
	private void addComponentWithConstraints(int col, int row, int colSpan, int rowSpan, int fillDirection, JComponent comp){
		gbConstraints.gridx = col;
		gbConstraints.gridy = row;
		gbConstraints.gridwidth = colSpan;
		gbConstraints.gridheight = rowSpan;
		gbConstraints.fill = fillDirection;		
		gbLayout.setConstraints(comp, gbConstraints);
		add(comp);		
	}
}

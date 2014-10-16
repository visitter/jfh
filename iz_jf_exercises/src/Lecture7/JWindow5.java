package Lecture7;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JWindow5 extends JFrame {	

	private static final long serialVersionUID = 1L;
	private JButton btnChoose;
	private JPanel panel;
	public JWindow5(){
		super();
		panel = new JPanel();
		btnChoose = new JButton("Choose color");
		
		add(panel,BorderLayout.CENTER);
		add(btnChoose,BorderLayout.SOUTH);
		
		btnChoose.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JMyColorChooser cc = new JMyColorChooser();
				cc.setLocationRelativeTo(null);
				if( cc.showDlg()){	
										
					panel.setBackground(cc.getColor());					
				}	
				cc.dispose();
			}
		});
	}
}

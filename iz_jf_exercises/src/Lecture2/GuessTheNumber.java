package Lecture2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GuessTheNumber extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private final String computerSaysAsk    = "I have a number between 1-1000. Can you guess it?";
	private final String computerSaysOpps 	= "Oops, too far away :( ";
	private final String computerSaysCloser = "You are getting closer :) ";
	private final String computerSaysLow 	= "Try a LOWER number than ";
	private final String computerSaysHi 	= "Try a HIGHER number than ";
	private final String computerSaysOK 	= "Correct! You won.";
	
	private JLabel lbComputer;
	private JLabel lbUser;
	private JTextField txtUser;	
	private JButton btnReset;
	
	private int nTheNumber;
	private Random rand = new Random();

	public GuessTheNumber(){
		super();
		setTitle("Guess the number");
		setLayout(null);
		setSize(400, 100);
		setResizable(false);
		
		lbComputer		= new JLabel();		
		lbUser			= new JLabel();
		txtUser			= new JTextField();		
		btnReset		= new JButton("New Game");
		
		lbComputer.setLocation(10, 10);
		lbComputer.setSize(400, 20);
		
		lbUser.setLocation(10, 30);
		lbUser.setSize(50, 20);
				
		txtUser.setLocation(70, 30);
		txtUser.setSize(50, 21);
		
		btnReset.setLocation(121, 30);
		btnReset.setSize(100,21);
		
		add(lbComputer);
		add(lbUser);
		add(txtUser);
		add(btnReset);	
		
		ActionListener al = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if( e.getSource() == txtUser){
					checkUserGuess();
				}else if( e.getSource()==btnReset ){
					init();
				}
			}
		};
		txtUser.addActionListener(al);
		btnReset.addActionListener(al);
		
		init();
	}
	
	private void init(){
		nTheNumber = rand.nextInt(1000);
		
		lbComputer.setText("Computer: "+computerSaysAsk);		
		lbUser	  .setText("You: ");	
				
		txtUser	  .setText("0");
		txtUser	  .setEnabled(true);		
		txtUser   .requestFocus();
		txtUser   .setForeground(Color.WHITE);
		txtUser   .setBackground(Color.BLACK);
		
		btnReset  .setEnabled(false);
	}
	
	private void checkUserGuess(){
		try {
			String comp;
			int number = Integer.parseInt( txtUser.getText() );
			if( number == nTheNumber){
				lbComputer.setText("Computer: "+computerSaysOK);
				
				txtUser   .setEnabled(false);				
				btnReset  .setEnabled(true);
			}else{
				int color = Math.abs(number-nTheNumber);
				if( color > 255 ){
					comp = computerSaysOpps;
					if( (color-255)>255 )
						color = 255;
					else
						color-= 255;
					color = color<100 ? 100 : color;
					
					txtUser.setBackground( new Color(0, 0, color));					
				}else{
					comp = computerSaysCloser;
					color = color>100 ? 100 : color;
					txtUser.setBackground( new Color(255-color, 0, 0));
				}								
				
				if( number>nTheNumber){
					lbComputer.setText("Computer: "+comp+computerSaysLow+txtUser.getText());
				}else{
					lbComputer.setText("Computer: "+comp+computerSaysHi+txtUser.getText());
				}
				txtUser.setText("");
				
				//lbComputer.setText(lbComputer.getText()+nTheNumber);
				//lbUser.setText(txtUser.getBackground().getRed()+" "+txtUser.getBackground().getGreen()+" "+txtUser.getBackground().getBlue());
			}
			
		} catch (NumberFormatException nfe) {			
			JOptionPane.showMessageDialog(this, "Invalid number "+nfe.getMessage()+" Try again", "Oops", JOptionPane.ERROR_MESSAGE);
			txtUser.setText("");
		}
	}
}

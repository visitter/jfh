package Lecture7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class JMyColorChooser extends JDialog{
	private static final long serialVersionUID = 1261355420069758571L;
	private JSlider sliderR;
	@SuppressWarnings("unused")
	private JLabel lbValueR;
	private JTextField tfValueR;
	
	private JSlider sliderG;
	@SuppressWarnings("unused")
	private JLabel lbValueG;
	private JTextField tfValueG;
	
	private JSlider sliderB;
	@SuppressWarnings("unused")
	private JLabel lbValueB;
	private JTextField tfValueB;	
	
	private JPanel containerPanelR;
	private JPanel containerPanelG;
	private JPanel containerPanelB;
	private JPanel containerPanel;
	private JPanel containerPanelViewPort;
	
	private Color color;	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
		containerPanelViewPort.setBackground(color);		
	}
	private boolean lOK = false;
	
	private JButton btnOK;	
	
	public boolean showDlg(){
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
		return lOK;
	}
	public JMyColorChooser(){
		super();	
		setTitle("JMyColorChooser");
		setSize(300, 300);
		setResizable(false);

		JComponent[] arrComp = new JComponent[4];		
		
		createGroup( arrComp, "RED", Color.GRAY);
		sliderR        =(JSlider)arrComp[0];
		lbValueR       =(JLabel)arrComp[1];
		tfValueR	   =(JTextField)arrComp[2];
		containerPanelR=(JPanel)arrComp[3];
		
		createGroup( arrComp, "GREEN", Color.GRAY);
		sliderG        =(JSlider)arrComp[0];
		lbValueG       =(JLabel)arrComp[1];
		tfValueG	   =(JTextField)arrComp[2];
		containerPanelG=(JPanel)arrComp[3];
		
		createGroup( arrComp, "BLUE", Color.GRAY);
		sliderB        =(JSlider)arrComp[0];
		lbValueB       =(JLabel)arrComp[1];
		tfValueB	   =(JTextField)arrComp[2];
		containerPanelB=(JPanel)arrComp[3];
		
		/*Create globals*/	
		
		containerPanelViewPort = new JPanel();
		containerPanelViewPort.setBorder( BorderFactory.createLineBorder(Color.GRAY, 1));		
		containerPanelViewPort.setLocation(5, 190);
		containerPanelViewPort.setPreferredSize( new Dimension(200, 30));
		setInternalColor();
		
		containerPanel = new JPanel();
		containerPanel.setBorder( BorderFactory.createLineBorder(Color.GRAY, 1));
		containerPanel.setPreferredSize( new Dimension(220, 50));
	
		containerPanel.add(containerPanelR);		
		containerPanel.add(containerPanelG);
		containerPanel.add(containerPanelB);
		containerPanel.add(containerPanelViewPort);		
		
		btnOK = new JButton("OK");
		
		add(containerPanel, BorderLayout.CENTER);
		add(btnOK,BorderLayout.SOUTH);
		
		addActions();
	}
	private void addActions(){
		sliderR.addChangeListener( new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				if(arg0.getSource() == sliderR) {
					tfValueR.setText(String.valueOf(sliderR.getValue()));
				}else if(arg0.getSource() == sliderG) {
					tfValueG.setText(String.valueOf(sliderG.getValue()));
				}else if(arg0.getSource() == sliderB) {
					tfValueB.setText(String.valueOf(sliderB.getValue()));
				}
				setInternalColor();
			}
		});
		sliderG.addChangeListener(sliderR.getChangeListeners()[0]);
		sliderB.addChangeListener(sliderR.getChangeListeners()[0]);
		
		tfValueR.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() instanceof JTextField) {
					Integer val=0;
					try{
						val = Integer.parseInt(((JTextField)arg0.getSource()).getText());
						if( val>255 ) val= 255;
						if( val<0   ) val = 0;
						
						if(arg0.getSource() == tfValueR) {						
							sliderR.setValue( val );
						}else if(arg0.getSource() == tfValueG) {						
							sliderG.setValue( val );
						}else if(arg0.getSource() == tfValueB) {						
							sliderB.setValue( val );
						}
						((JTextField)arg0.getSource()).setText(val.toString());
						
					}catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Въвеждайте само цифри в интервала 0-255", "Грешка", JOptionPane.ERROR_MESSAGE);
						
						if(arg0.getSource() == tfValueR) {						
							((JTextField)arg0.getSource()).setText(String.valueOf(sliderR.getValue())); 
						}else if(arg0.getSource() == tfValueG) {						
							((JTextField)arg0.getSource()).setText(String.valueOf(sliderG.getValue()));
						}else if(arg0.getSource() == tfValueB) {						
							((JTextField)arg0.getSource()).setText(String.valueOf(sliderB.getValue()));
						}
						
						((JTextField)arg0.getSource()) .requestFocus();
					}
					setInternalColor();
				}	
			}
		});
		tfValueG.addActionListener(tfValueR.getActionListeners()[0]);
		tfValueB.addActionListener(tfValueR.getActionListeners()[0]);
		
		btnOK.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lOK =(arg0.getSource()==btnOK);			
				close();
			}
		});
	}
	
	private void setInternalColor(){
		color = new Color(sliderR.getValue(), sliderG.getValue(), sliderB.getValue());
		containerPanelViewPort.setBackground(color);
	}
	public void close(){
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	private void createGroup( JComponent[] arrComp, String label, Color color){

		arrComp[0] = new JSlider(0, 255);
		((JSlider)arrComp[0]).setMajorTickSpacing(((JSlider)arrComp[0]).getMaximum()/16);
		((JSlider)arrComp[0]).setPaintTicks(true);
		arrComp[0].setLocation(5, 5);
		arrComp[0].setSize(100, 50);
		((JSlider)arrComp[0]).setValue(255);
		
		arrComp[1] = new JLabel(label);
		arrComp[1].setLocation(110, 5);
		arrComp[1].setSize(80, 20);
		
		arrComp[2] = new JTextField();
		((JTextField)arrComp[2]).setText(String.valueOf(((JSlider)arrComp[0]).getValue()));
		arrComp[2].setPreferredSize( new Dimension(100, 22));
		arrComp[2].setLocation(110, 25);
		arrComp[2].setSize(80, 20);
		
		arrComp[3] = new JPanel();
		arrComp[3].setBorder( BorderFactory.createLineBorder(color, 1));
		arrComp[3].setLayout(null);
		arrComp[3].setPreferredSize( new Dimension(200, 60));
		arrComp[3].add(arrComp[2]);
		arrComp[3].add(arrComp[1]);
		arrComp[3].add(arrComp[0]);		
		arrComp[3].setLocation(5, 5);				
	}
}

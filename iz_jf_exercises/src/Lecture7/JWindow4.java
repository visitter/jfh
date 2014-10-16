package Lecture7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

public class JWindow4 extends JFrame {
	private static final long serialVersionUID = -2969991098084349386L;
	
	private class JDrawPanelCaption extends JPanel{
		private static final long serialVersionUID = 1L;
		private String strToDraw;
		public void setStrToDraw(String strToDraw) {
			this.strToDraw = strToDraw;
			repaint();
		}
		public JDrawPanelCaption(){
			
		}
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			if( strToDraw!=null )
				g.drawString(strToDraw, getWidth()/2-strToDraw.length(), getHeight()/2);			
		}	
	}

	
	private JSlider slider;
	private JLabel lbValue;
	private JTextField tfValue;
	private JDrawPanelCaption panel;
	private JPanel containerPanel;
	private FlowLayout flowLayout;	
	
	public JWindow4(){
		super();
		slider = new JSlider(0, 255);
		slider.setMajorTickSpacing(slider.getMaximum()/16);
		slider.setPaintTicks(true);		
		
		lbValue = new JLabel("Slider value");
		
		tfValue = new JTextField();
		tfValue = new JFormattedTextField(createMask("###"));
		tfValue.setText(String.valueOf(slider.getValue()));
		tfValue.setPreferredSize( new Dimension(100, 22));			
		
		panel = new JDrawPanelCaption();
		panel.setStrToDraw(tfValue.getText());
		
		flowLayout = new FlowLayout(10);
		containerPanel = new JPanel();
		containerPanel.setBorder( BorderFactory.createLineBorder(Color.GRAY, 1));
		containerPanel.setLayout(flowLayout);
		containerPanel.setPreferredSize( new Dimension(120, 100));
		containerPanel.add(lbValue);
		containerPanel.add(tfValue);
		
		addActions();
		
		add(containerPanel, BorderLayout.WEST);		
		add(slider, BorderLayout.SOUTH);		
		add(panel,BorderLayout.CENTER);
	}
	private void addActions(){
		slider.addChangeListener( new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				tfValue.setText(String.valueOf(slider.getValue()));	
				panel.setStrToDraw(tfValue.getText());
			}
		});
		
		tfValue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == tfValue) {
					try{
						slider.setValue( Integer.parseInt(((JTextField)arg0.getSource()).getText()));
					}catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Грешка", "Въвеждайте само цифри", JOptionPane.ERROR_MESSAGE);
						slider.setValue(128);
					}
				}				
			}
		});
	
	}
	
	private MaskFormatter createMask(String mask){
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(mask);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatter;
	}
}

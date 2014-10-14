package Lecture6Exercising;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AnswerDlg extends JDialog{
	private static final long serialVersionUID = 1L;
	private JLabel lbNo;
	public JTextField tfNo;	
	
	private JLabel lbText;
	private JTextField tfText;
		
	private JCheckBox chbCorrect;
	private JButton btnOK;
	private JButton btnCancel;
	
	private Answer result = null;
	
	public Answer answer;	
	public void setAnswer(Answer answer) {
		this.answer = answer;
		if( this.answer!=null){
			tfNo.setText(this.answer.getNumber().toString());
			tfText.setText(this.answer.getText());
			chbCorrect.setSelected(this.answer.getCorrect());
		}
	}
	
	{
		lbNo = new JLabel("No");
		lbNo.setLocation(10, 10);
		lbNo.setSize(50, 22);
		
		tfNo = new JTextField();
		tfNo.setLocation(65, 10);
		tfNo.setSize(50, 22);
		tfNo.setEditable(false);
		
		lbText = new JLabel("Answer");
		lbText.setLocation(10, 40);
		lbText.setSize(50, 22);
		
		tfText = new JTextField();
		tfText.setLocation(65, 40);
		tfText.setSize(180, 22);
	
		chbCorrect = new JCheckBox("Correct");
		chbCorrect.setLocation(60, 70);
		chbCorrect.setSize(90, 22);
		
		btnOK = new JButton("OK");
		btnOK.setLocation(10, 100);
		btnOK.setSize(80, 22);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setLocation(165, 100);
		btnCancel.setSize(80, 22);
	}
	
	public AnswerDlg(){
		setLayout(null);
		setSize(260, 160);
		setResizable(false);
		
		add(lbNo);
		add(tfNo);
		add(lbText);
		add(tfText);
		add(chbCorrect);
		add(btnOK);
		add(btnCancel);
		
		addListeners();
	}
	private void addListeners(){
		btnOK.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(  e.getSource()==btnOK ){
					if(answer==null){
						answer = new Answer(Integer.parseInt(tfNo.getText()), tfText.getText(), chbCorrect.isSelected());						
					}else{
						answer.setNumber(Integer.parseInt(tfNo.getText()));
						answer.setText(tfText.getText());
						answer.setCorrect(chbCorrect.isSelected());
					}
					result = answer;
				}else result = null;
				dispose();
			}
		});
		btnCancel.addActionListener(btnOK.getActionListeners()[0]);
	}
	public Answer showDlg(){
		setVisible(true);
		return result;
	}
	
}

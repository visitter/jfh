package Lecture6Exercising;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class JMyCheckbox extends JCheckBox{

	private static final long serialVersionUID = 1L;	
	public Answer answer;
	public JMyCheckbox(String text){
		super(text);
	}
}

public class QuizFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JLabel lbQuestionNoLabel;
	private JLabel lbQuestionNoValue;
	private JLabel lbQuestion;
	private JTextArea taQuestionText;
	private JScrollPane spQPane;
	private JPanel panelAnswers;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnLoad;
	private JButton btnReset;	
	private JMenuBar menuBar;
	private JMenu menu;
	
	private DefaultListModel<String> listModel;
	private JScrollPane spAPane;
	private JList<String> listAnswers;
	
	private ArrayList<Question> questionList = new ArrayList<Question>();	
	private ListIterator<Question> iterator;
	
	private String cTitle = "Quiz :)";
	private boolean lNext = true;
	
	public void close(){
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}	

	private void setMenus(){
		menu = new JMenu("Main");
		menu.add( new JMenuItem("Load"));
		menu.add( new JMenuItem("Reset"));
		menu.add( new JMenuItem("Exit"));
		
		
		menu.getItem(0).addActionListener(btnLoad.getActionListeners()[0]);
		menu.getItem(1).addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resetTest();
			}
		});
		
		menu.getItem(2).addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
			}
		});
		
		menuBar = new JMenuBar();
		menuBar.add(menu);
		menuBar.setVisible(true);
		setJMenuBar(menuBar);		
	}

	public QuizFrame(){
		
		lbQuestionNoLabel = new JLabel("Question No: ");
		lbQuestionNoLabel.setLocation(100, 10);
		lbQuestionNoLabel.setSize(80, 10);		
		
		lbQuestionNoValue = new JLabel("1");
		lbQuestionNoValue.setLocation(180, 10);
		lbQuestionNoValue.setSize(70, 10);		
		
		lbQuestion = new JLabel("Question");
		lbQuestion.setLocation(10, 30);
		lbQuestion.setSize(70, 10);		
		
		taQuestionText = new JTextArea();
		taQuestionText.setEditable(false);
		spQPane = new JScrollPane(taQuestionText);
		spQPane.setLocation(80, 30);
		spQPane.setSize(200, 40);	
		
		btnPrev = new JButton("Prev");
		btnPrev.setLocation(80,200);
		btnPrev.setSize(80, 22);
		
		btnNext = new JButton("Next");
		btnNext.setLocation(165,200);
		btnNext.setSize(80, 22);
		
		btnLoad = new JButton("Load");
		btnLoad.setLocation(250,200);
		btnLoad.setSize(80, 22);
		
		btnReset = new JButton("Reset");
		btnReset.setLocation(250,200);
		btnReset.setSize(80, 22);
		
		panelAnswers = new JPanel();
		panelAnswers.setLocation(80, 80);
		panelAnswers.setSize(200, 100);
		panelAnswers.setLayout(null);
		panelAnswers.setBorder( BorderFactory.createLineBorder(Color.GRAY, 1));
		
		listModel = new DefaultListModel<String>();
		listAnswers = new JList<String>(listModel);		
		spAPane = new JScrollPane(listAnswers);
		spAPane.setLocation(80, 80);
		spAPane.setSize(200, 100);
		spAPane.setVisible(false);
		
		questionList = new ArrayList<Question>();
		
		setSize(400, 300);
		setLayout(null);
		setResizable(false);
		setTitle(cTitle);
		
		add(lbQuestionNoLabel);		
		add(lbQuestionNoValue);
		add(lbQuestion);
		add(spQPane);				
		add(panelAnswers);
		add(btnPrev);
		add(btnNext);
		add(btnLoad);
		add(spAPane);
		
		addListeners();
		setMenus();
	}

	private void createAnswers( ArrayList<Answer> answerList ){
		panelAnswers.removeAll();

		int i=0;
		for( Answer ans:answerList ){	
			JMyCheckbox answer = new JMyCheckbox(ans.getNumber().toString()+" "+ans.getText());
			answer.setSelected(ans.getSelect());
			answer.answer = ans;
			
			answer.setSize(100, 20);
			answer.setLocation(5, i*22+5);
			panelAnswers.add(answer);
			i++;
			answer.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					((JMyCheckbox)arg0.getSource()).answer.setSelect(  ((JMyCheckbox)arg0.getSource()).isSelected() );
				}
			});
		}
		panelAnswers.repaint();
		
	}
	
	private void addListeners(){
		btnNext.addActionListener( new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				setQuestion(e.getSource());
			}
		});
		
		btnPrev.addActionListener( btnNext.getActionListeners()[0]);
		
		btnLoad.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				FileOperations fop = new FileOperations();
				
				JFileChooser jfc = new JFileChooser();
				File file;
				if( jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					file = jfc.getSelectedFile();
					try{
						if( file.exists()){
							questionList = (ArrayList<Question>) fop.loadFromFile(file.getAbsolutePath());
							if( questionList!=null ){
								if( questionList.size()>0 ){
									JOptionPane.showMessageDialog(null, "File Loaded OK");
									iterator = questionList.listIterator();
									setQuestion(e.getSource());
								}									
							}
						}
					}catch( ClassCastException cce){
						JOptionPane.showMessageDialog(null, "Invalid file format", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if( spAPane.isVisible() ){
					spAPane.setVisible(false);
					panelAnswers.setVisible(true);
				}
			}
		});
	}
	
	private void setQuestion( Object obj ){
		if( iterator!=null ){			
			try{	
				Question q = null;
				if( (obj == btnNext)|| (obj==btnLoad) || (obj instanceof JMenuItem) ){
					if( !lNext )
						if( iterator.hasNext())
							q = iterator.next();
					
					if( iterator.hasNext()){
						q = iterator.next();
						lNext = true;
					}else{
						JOptionPane.showMessageDialog(null, "No more questions\nReady for result?", "", JOptionPane.YES_NO_OPTION);
						printResults();
						lNext = false;
					}
					
					
				}else{					
					if( spAPane.isVisible() ){
						spAPane.setVisible(false);
						panelAnswers.setVisible(true);
					}
					
					if( lNext )
						if( iterator.hasPrevious() )
							q = iterator.previous();						
					
					if( iterator.hasPrevious() )
						q = iterator.previous();
					else
						JOptionPane.showMessageDialog(null, "You are at the begining of the test.", "", JOptionPane.YES_OPTION);
					lNext = false;
				}
				
				if( q!=null ){
					taQuestionText.setText(q.getText());
					lbQuestionNoValue.setText(q.getNumber().toString());
					createAnswers( q.getAnswers());
				}
			}catch(NoSuchElementException nse){

				JOptionPane.showMessageDialog(null, "No more questions\nReady for result?", "", JOptionPane.YES_OPTION);

				if( obj == btnNext){
					JOptionPane.showMessageDialog(null, "No more questions\nReady for result?", "", JOptionPane.INFORMATION_MESSAGE);
					printResults();
				}else{
					JOptionPane.showMessageDialog(null, "You are at the begining of the test.", "", JOptionPane.YES_OPTION);
				}

			}
		}else JOptionPane.showMessageDialog(null, "No questions", "Error", JOptionPane.ERROR_MESSAGE);		
	}
	
	private void printResults(){
		spAPane.setVisible(true);
		panelAnswers.setVisible(false);
		listModel.clear();
		StringBuilder str = new StringBuilder();
		
		for( Question q:questionList){
			str.setLength(0);
			for( Answer ans:q.getAnswers()){
				
				if( ans.getSelect()){
					
					if( str.length()>0 ){
						str.append(", ");
					}		
					
					if( ans.getCorrect() )
						str.append( q.getNumber().toString()+" "+q.getText()+" = "+ans.getText()+" Вярно" );						
					else 
						str.append(q.getNumber().toString()+" "+q.getText()+" = "+ans.getText()+" Грешка");
				}				
			}
			listModel.addElement(str.toString());
		}
	}
	
	private void resetTest(){
		if( spAPane.isVisible() ){
			spAPane.setVisible(false);
			panelAnswers.setVisible(true);
		}
		for( Question q:questionList){
			for( Answer ans:q.getAnswers())				
				ans.setSelect(false);
		}
		if( !questionList.isEmpty() ){
			lNext = true;
			iterator = questionList.listIterator();
			setQuestion(btnNext);
		}
	}
}

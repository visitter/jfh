package Lecture6Exercising;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class QuizFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel lbQuestionNoLabel;
	private JLabel lbQuestionNoValue;
	private JLabel lbQuestion;
	private JTextArea taQuestionText;
	private JScrollPane spQPane;
	private JPanel panelAnswers;
	private JButton btnNext;
	private JButton btnLoad;
	
	private ArrayList<Question> questionList = new ArrayList<Question>();
//	private ArrayList<Answer> answerList = new ArrayList<Answer>();
	private ListIterator<Question> iterator;
	
	private String cTitle = "Quiz :)";
//	private final QuizFrame self = this;
	
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
		spQPane = new JScrollPane(taQuestionText);
		spQPane.setLocation(80, 30);
		spQPane.setSize(200, 40);	
		
		btnNext = new JButton("Next");
		btnNext.setLocation(80,200);
		btnNext.setSize(80, 22);
		
		btnLoad = new JButton("Load");
		btnLoad.setLocation(200,200);
		btnLoad.setSize(80, 22);
		
		panelAnswers = new JPanel();
		panelAnswers.setLocation(80, 80);
		panelAnswers.setSize(200, 100);
		//panelAnswers.setBackground(Color.YELLOW);
		panelAnswers.setLayout(null);
		panelAnswers.setBorder( BorderFactory.createLineBorder(Color.GRAY, 1));
		//createAnswers(null);
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
		add(btnNext);
		add(btnLoad);
		
		addListeners();
	}

	private void createAnswers( ArrayList<Answer> answerList ){
		panelAnswers.removeAll();

		int i=0;
		for( Answer ans:answerList ){	
			JCheckBox answer = new JCheckBox(ans.getNumber().toString()+" "+ans.getText());
			answer.setSize(100, 20);
			answer.setLocation(5, i*22+5);
			panelAnswers.add(answer);
			i++;
		}
		panelAnswers.repaint();
		
	}
	
	private void addListeners(){	
		btnNext.addActionListener( new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				setQuestion();
			}
		});
		
		btnLoad.addActionListener(new ActionListener() {		
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				FileOperations fop = new FileOperations();
				
				JFileChooser jfc = new JFileChooser();
				File file;
				if( jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					file = jfc.getSelectedFile();
					
					if( file.exists()){
						questionList = (ArrayList<Question>) fop.loadFromFile(file.getAbsolutePath());
						if( questionList!=null ){
							if( questionList.size()>0 ){
								JOptionPane.showMessageDialog(null, "File Loaded OK");
								iterator = questionList.listIterator();
								setQuestion();
							}									
						}
					}						
				}				
			}
		});
	}
	
	private void setQuestion(){
		if( iterator!=null ){			
			try{				
				Question q = iterator.next();
				if( q!=null ){
					taQuestionText.setText(q.getText());
					createAnswers( q.getAnswers());
				}
			}catch(NoSuchElementException nse){
				JOptionPane.showMessageDialog(null, "No more questions\nReady for result?", "", JOptionPane.YES_OPTION);
			}
		}else JOptionPane.showMessageDialog(null, "No questions", "Error", JOptionPane.ERROR_MESSAGE);
		Integer[] arr = {1,2,4,5};
		test(arr);
	}
	
	private <KT> void test(KT[] arr){
		
	}
	
}

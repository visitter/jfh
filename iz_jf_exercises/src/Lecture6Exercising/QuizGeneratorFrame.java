package Lecture6Exercising;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.ListIterator;




import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;


public class QuizGeneratorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lbQuestionNoLabel;
	private JLabel lbQuestionNoValue;
	private JLabel lbQuestion;
	private JTextArea taQuestionText;
	private JScrollPane spQPane;
	
	private JLabel lbAnswers;
	private DefaultListModel<Answer> listModel;
	private JList<Answer> listAnswers;		
	private JScrollPane spPane;	
	
	private JButton btnAddAnswer;
	private JButton btnEditAnswer;
	private JButton btnDelAnswer;	
	private JButton btnNext;
	private JButton btnSave;
	
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private ArrayList<Answer> answerList = new ArrayList<Answer>();
	//private ListIterator<Question> iterator;	
	
	private String cTitle = "Quiz generator";
	private final QuizGeneratorFrame self = this;
	
	{	
		setLayout(null);
		
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
		
		/*---------------------------*/
		
		lbAnswers = new JLabel("Answers: ");
		lbAnswers.setLocation(10, 85);
		lbAnswers.setSize(80, 10);	
		
		listModel = new DefaultListModel<Answer>();
		listAnswers = new JList<Answer>(listModel);
		listAnswers.setLayoutOrientation(JList.VERTICAL);
		listAnswers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		spPane = new JScrollPane(listAnswers);
		spPane.setLocation(80, 85);
		spPane.setSize(200, 85);
		
		/*---------------------------*/
		
		btnAddAnswer = new JButton("Add");
		btnAddAnswer.setLocation(285,85);
		btnAddAnswer.setSize(80, 22);
		
		btnEditAnswer = new JButton("Edit");
		btnEditAnswer.setLocation(285,115);
		btnEditAnswer.setSize(80, 22);
		
		btnDelAnswer = new JButton("Del");
		btnDelAnswer.setLocation(285,145);
		btnDelAnswer.setSize(80, 22);
		
		/*---------------------------*/
		btnNext = new JButton("Next");
		btnNext.setLocation(80,200);
		btnNext.setSize(80, 22);
		
		btnSave = new JButton("Save");
		btnSave.setLocation(200,200);
		btnSave.setSize(80, 22);
		
		questionList = new ArrayList<Question>();
		//iterator = questionList.listIterator();
	}
	
	public QuizGeneratorFrame(){
		setSize(400, 300);
		setLayout(null);
		setResizable(false);
		setTitle(cTitle);
		
		add(lbQuestionNoLabel);		
		add(lbQuestionNoValue);
		add(lbQuestion);
		add(spQPane);
		add(lbAnswers);
		add(spPane);
		add(btnAddAnswer);
		add(btnEditAnswer);
		add(btnDelAnswer);		
		add(btnNext);
		add(btnSave);
		
		addListeners();
	}
	
	private void addListeners(){
		btnAddAnswer.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				AnswerDlg oDlg = new AnswerDlg();
				oDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				oDlg.setLocationRelativeTo(self);
				oDlg.setModal(true);				
				oDlg.tfNo.setText( String.valueOf(answerList.size()+1));
				
				Answer answer = oDlg.showDlg();				
				if( answer!=null ){
					System.out.println(answer.toString());
					answerList.add(answer);
					//za vizualizacia
					listModel.addElement(answer);
				}
			}
		});
		
		btnEditAnswer.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx = listAnswers.getSelectedIndex();
				
				if( idx>-1 ){
					
					AnswerDlg oDlg = new AnswerDlg();
					oDlg.setAnswer( listAnswers.getSelectedValue() );
					oDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					oDlg.setLocationRelativeTo(self);
					oDlg.setModal(true);					
					Answer answer = oDlg.showDlg();
					if( answer!=null ){
						System.out.println(answer.toString());
						listModel.set(idx, answer);
						answerList.get(idx).setNumber(answer.getNumber());
						answerList.get(idx).setText(answer.getText());
						answerList.get(idx).setCorrect(answer.getCorrect());
					}								
				}else 
					System.out.println("No items");				
			}
		});
		
		btnDelAnswer.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = listAnswers.getSelectedIndex();
				
				if( idx>-1 ){
					answerList.remove(idx);
					listModel.remove(idx);
				}
				
				for(int i=0; i<answerList.size();i++){
					answerList.get(i).setNumber(i+1);
					listModel.set(i, answerList.get(i));
				}
			}
		});
		
		btnNext.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if( !taQuestionText.getText().trim().isEmpty()){
					if( answerList.size()>0){
						Boolean hasCorrectAnswer = false;
						for(int i=0;i<answerList.size();i++){
							if(answerList.get(i).getCorrect()){
								hasCorrectAnswer = answerList.get(i).getCorrect();
								break;
							}
						}
						if( hasCorrectAnswer ){
							questionList.add(new Question(questionList.size()+1, taQuestionText.getText(), new ArrayList<Answer>(answerList)));
							taQuestionText.setText("");
							answerList.clear();
							listModel.clear();
							taQuestionText.requestFocus();
							for(int i=0;i<questionList.size();i++){								
								System.out.println(questionList.get(i).toString());
							}
							lbQuestionNoValue.setText(String.valueOf(questionList.size()+1));
						}else{
							JOptionPane.showMessageDialog(self, "Enter at least one CORRECT answer", "Error", JOptionPane.ERROR_MESSAGE);
							btnAddAnswer.requestFocus();
						}
					}else{
						JOptionPane.showMessageDialog(self, "Enter at least one answer", "Error", JOptionPane.ERROR_MESSAGE);
						btnAddAnswer.requestFocus();
					}
				}else {
					JOptionPane.showMessageDialog(self, "Enter a question", "Error", JOptionPane.ERROR_MESSAGE);
					taQuestionText.requestFocus();
				}
			}
		});
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( !questionList.isEmpty() ){
					FileOperations fop = new FileOperations();
					
					JFileChooser jfc = new JFileChooser();
					File file;
					if( jfc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
						file = jfc.getSelectedFile();
						try {
							if( file.createNewFile()||file.exists()){
								if( fop.saveToFile(	questionList, file.getAbsolutePath()) ){
									JOptionPane.showMessageDialog(self, "Quiz saved successfully");
								}
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else{
					JOptionPane.showMessageDialog(self, "Enter at least one question with one correct answer", "Error", JOptionPane.ERROR_MESSAGE);
					taQuestionText.requestFocus();
				}
			}
		});
	}
}

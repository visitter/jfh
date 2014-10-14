package Lecture6Exercising;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable{
	private static final long serialVersionUID = -1849362266868981426L;
	private Integer number;
	private String text;
	private ArrayList<Answer> answers;
	
	public Question( Integer number, String text, ArrayList<Answer> answers){
		this.number = number;
		this.text = text;
		this.answers = answers;
	}

	public String getText() {
		return text;
	}
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	public String toString(){
		String cTmp = "";
		if( answers.size()>0){
			for(int i=0;i<answers.size();i++){
				cTmp+=answers.get(i).toString();
			}
		}
		return number.toString()+" "+text+" "+cTmp;
	}
}


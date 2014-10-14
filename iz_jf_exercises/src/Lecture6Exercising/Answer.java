package Lecture6Exercising;

import java.io.Serializable;

public class Answer  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer number;
	private String text;
	private Boolean correct;

	public Answer( Integer number, String text, Boolean correct){
		this.number = number;
		this.text = text;
		this.correct = correct;
	}	
	public Integer getNumber() {
		return number;
	}

	public String getText() {
		return text;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public String toString(){
		return getNumber()+" "+getText()+" "+getCorrect();
	}
	
}

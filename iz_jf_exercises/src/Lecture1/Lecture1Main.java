package Lecture1;

import javax.swing.*;

public class Lecture1Main {
	public static void showSum(){
		String firstNumber = JOptionPane.showInputDialog("Enter first integer");
		String secondNumber = JOptionPane.showInputDialog("Enter second integer");
		
		int number1 = Integer.parseInt(firstNumber);
		int number2 = Integer.parseInt(secondNumber);
		
		int sum = number1+number2;
		JOptionPane.showMessageDialog(null, "The sum is "+sum, "Sum of integers", JOptionPane.ERROR_MESSAGE);		
	}
	public static void showName(){
		String firstName = JOptionPane.showInputDialog("Enter first name");
		String secondName = JOptionPane.showInputDialog("Enter family name");
		
		if(( !firstName.isEmpty())&&(!secondName.isEmpty())){
			String name = firstName+" "+secondName;
			JOptionPane.showMessageDialog(null, "Your name is "+name, "Person name ", JOptionPane.INFORMATION_MESSAGE);
		}else
			JOptionPane.showMessageDialog(null, "Enter names please", "Person name ", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void main(String[] args) {
		//showSum();
		//showName();		
		MyFirstWindow oWin = new MyFirstWindow("Win1");				
		oWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		oWin.setLocationRelativeTo(null);
		oWin.setVisible(true);
	}
}
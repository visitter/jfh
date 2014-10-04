package Lecture2;

import javax.swing.JFrame;

public class Lecture2Main {

public static void main(String[] args) {
		/*
		TempCalculator oObj = new TempCalculator();
		oObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		oObj.setLocationRelativeTo(null);
		oObj.setVisible(true);
		
		WindowTask oWin = new Align();
		oWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		oWin.setVisible(true);
		
		WindowTask oCalc = new Calculator();
		oCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		oCalc.setVisible(true);
		
		WindowTask oColor = new ColorSelector();
		oColor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		oColor.setVisible(true);
		
		WindowTask oPrinter = new PrinterSelector();
		oPrinter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		oPrinter.setVisible(true);
		*/	
		GuessTheNumber oGame = new GuessTheNumber();
		oGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		oGame.setLocationRelativeTo(null);
		oGame.setVisible(true);
	}
}

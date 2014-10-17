package Lecture6Exercising;

import javax.swing.JFrame;

public class QuizMain {

	public static void main(String[] args) {		
		/*
		QuizGeneratorFrame mainWindow = new QuizGeneratorFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
		*/
		QuizFrame mainWindow = new QuizFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}
}

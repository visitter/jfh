package Lecture6;

import javax.swing.JFrame;

public class Lecture6Main {

	public static void main(String[] args) {
		MoviesBrowser mainWindow = new MoviesBrowser();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}
}

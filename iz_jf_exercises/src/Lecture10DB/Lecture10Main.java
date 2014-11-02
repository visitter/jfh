package Lecture10DB;

import javax.swing.JFrame;

public class Lecture10Main {
	public static void main(String[] args) {	
		
		Librarian lib = new  Librarian();
		lib.setSize(680, 480);
		lib.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lib.setLocationRelativeTo(null);
		lib.setVisible(true);				
	}
}

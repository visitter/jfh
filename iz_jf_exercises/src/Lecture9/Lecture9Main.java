package Lecture9;

import javax.swing.JFrame;

public class Lecture9Main {
	public static void main(String[] args){
		Balls balls = new Balls();
		balls.setSize(500, 500);
		balls.setLocationRelativeTo(null);
		balls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		balls.setVisible(true);
	}
}
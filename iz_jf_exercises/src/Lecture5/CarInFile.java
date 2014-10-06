package Lecture5;

import javax.swing.JFrame;

public class CarInFile {

	public static void main(String[] args) {
		CarForm carForm = new CarForm();
		carForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		carForm.setSize(300,300);
		carForm.setLocationRelativeTo(null);
		carForm.setVisible(true);
	}

}

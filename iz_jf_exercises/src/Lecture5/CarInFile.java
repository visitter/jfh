package Lecture5;

import java.awt.Color;
import java.util.Scanner;

public class CarInFile {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		TxtFileWriter fileW = new TxtFileWriter();
		fileW.openFile("cars.txt");
		
		while (scan.hasNext()){
			Car car = new Car();
		
			System.out.println("Enter Make");
			car.setMake( scan.nextLine() );
		
			System.out.println("Enter Model");
			car.setModel( scan.nextLine() );
		
			System.out.println("Enter Year");		
			car.setYear( scan.nextInt() );
		
			System.out.println("Enter Color: 1-Red 2-Green 3-Blue: ");
			Color col = null;
			switch (scan.nextInt()){
				case 1:{col = new Color(255, 0, 0); break;}
				case 2:{col = new Color(0, 255, 0); break;}
				case 3:{col = new Color(0, 0, 255); break;}
				default:{col = new Color(0, 0, 255); break;}
			}		
			car.setColor(col );
			fileW.writeToFile(car.toString());
			
		}
		fileW.closeFile();
		scan.close();
	}

}

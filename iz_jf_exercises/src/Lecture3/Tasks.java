package Lecture3;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Tasks{
	public final String cGraphics = "Тестване на графика";
	public Tasks(int taskId){
		switch (taskId){
			case 1:case 2:case 3:case 4:case 5:{runTask12345(taskId);break;}
			case 6:case 7:{runTask67(taskId); break;}
			default:runTask12345(1);
		}			
	}		
	private void runTask12345(int taskId){
		JFrame oFrm = new JFrame();
		oFrm.setTitle(cGraphics);
		oFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		oFrm.add( new MyDrawingArea(taskId));
		oFrm.setSize(300, 300);
		oFrm.setLocationRelativeTo(null);
		oFrm.setVisible(true);
	}
	private void runTask67(int taskId){
		DrawUserCircle oFrm = new DrawUserCircle();
		oFrm.setTitle(cGraphics);
		oFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		oFrm.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		//oFrm.setSize(500,500);
		oFrm.setLocationRelativeTo(null);
		
		if( taskId==6 ){
			oFrm.setVisible(true);
			oFrm.drawUserCircle();//TASK 6
		}else{
			oFrm.setUndecorated(true);						
			oFrm.setVisible(true);
			oFrm.createScreenSaver();//TASK 7
		}		
	}
}

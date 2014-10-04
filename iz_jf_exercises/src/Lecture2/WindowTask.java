package Lecture2;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class WindowTask extends JFrame{
	private static final long serialVersionUID = 1L;

	public void setCentered(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
package Lecture8Applets;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessMain extends JApplet{
	private static final long serialVersionUID = 1L;
	private GridLayout gridLay;
	private JLabel[] figures;
	private JPanel[] blocks;
	private final int blockCount  = 64;
	private final int figureCount = 32;
	private ImageIcon icoPeshka_b; 
	
	public void init(){
		gridLay = new GridLayout(8, 8, 2, 2);
		
		setLayout( gridLay );
		blocks = new JPanel[blockCount];
		
		int k = 0;
		for( int i=0;i<blockCount;i++){
			blocks[i] = new JPanel();
			blocks[i].setBackground((((i+k)%2))>0?Color.BLACK:Color.WHITE);			
			add(blocks[i]);			
			if( i>0 ){
				if((i+1)%8==0){
					if (k>0)
						k=0;
					else
						k=1;
				}
			}
		}		
		
		icoPeshka_b = new ImageIcon(".\\Lecture8Applets\\images\\peshka_b.png");
		
		figures = new JLabel[figureCount];
		for(int i=0;i<figureCount;i++){
			figures[i]=new JLabel();
			figures[i].setBackground(Color.BLUE);
			figures[i].setIcon( icoPeshka_b );
		}
		
		for(int i=0;i<16;i++){		
			blocks[i].add(figures[i]);
		}
		
		for(int i=48;i<64;i++){		
			blocks[i].add(figures[i-32]);
		}
		setSize(600, 600);
	}
}

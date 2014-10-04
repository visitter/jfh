package Lecture2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Calculator extends WindowTask{
	enum Funcs{	none, plus, minus, mul, div, res};
	
	class JCalcButton extends JButton{
		private static final long serialVersionUID = 5596448478178509633L;
		private int nValue = 0;
		private Funcs op = Funcs.none; 
		
		public void setValue(int value){
			this.nValue = value;
		}
		public int getValue(){
			return this.nValue;
		}
		public Funcs getOp() {
			return op;
		}
		public void setOp(Funcs op) {
			this.op = op;
		}
		public JCalcButton(){
			super();
		}
		public JCalcButton(String caption){
			super(caption);
			
			if( caption.trim().equals("+") ){
				this.op = Funcs.plus;
			}
			else if( caption.trim().equals("-") ){
				this.op = Funcs.minus;
			}
			else if( caption.trim().equals("*") ){
				this.op = Funcs.mul;
			}
			else if( caption.trim().equals("/") ){
				this.op = Funcs.div;
			}						
			else if( caption.trim().equals("=") ){
				this.op = Funcs.res;
			};
		}		
	}

	private static final long serialVersionUID = 1L;
	private JTextField txtCalc;
	
	private String[] arrButtonSymbols = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};	
	private double result   = 0;
	private double variable = 0;
	private Funcs  op       = Funcs.none;
	
	private double operation(double res, double var, Funcs func){
		switch(func){
			case plus :{ return res+=var; }
			case minus:{ return res-=var; }
			case mul  :{ return res*=var; }
			case div  :{ return res/=var; }
			
			default:return res;
		}		
	}
	private void doCalc( JCalcButton btn, boolean isNumber, String txt, Funcs fop){
		if( isNumber ){
			if( op == Funcs.res ){
				result=0;
				variable=0;
				txtCalc.setText("");
				op = Funcs.none;
			}
			txtCalc.setText( txtCalc.getText()+txt);							
		}else{																		
			try{
				if( result == 0 )
					result = Double.parseDouble(txtCalc.getText());
				else
					variable = Double.parseDouble(txtCalc.getText());
				
				//if( fop == Funcs.res ){		
				if( (result>0)&&(variable>0)){					
					result = operation(result, variable, op);
					if( fop == Funcs.res ){		
						txtCalc.setText(String.valueOf(result));
					}else txtCalc.setText("");
				}else{									
					txtCalc.setText("");					
				}	
				
				op =fop;
			}catch( NumberFormatException nfe){
				JOptionPane.showMessageDialog( null, "Invalid number", "Invalid number",JOptionPane.ERROR_MESSAGE);
			}				
		}
	}
	private void createButtons( String[] arr){
		JCalcButton oBtn;
		int x=5;
		int y=30;
		for(int i=0;i<arrButtonSymbols.length;i++){
			oBtn = new JCalcButton(arrButtonSymbols[i]);			
			oBtn.setFocusable(false);
			oBtn.setLocation(x, y);
			oBtn.setSize(49, 25);
			if( (i+1)%4!=0 ){
				boolean lNumber = true;
				int number = 0;
				try{
					number = Integer.parseInt(arrButtonSymbols[i]);
				}catch(NumberFormatException nfe){
					lNumber=false;
				}			
				if( lNumber )
					oBtn.setValue(number);
			}else{
				
			}
			add(oBtn);			
			x+=50;
			if((i>2)&&((i+1)%4==0)){
				x=5;
				y=y+30;				
			}
			
			oBtn.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					if( e.getSource() instanceof JCalcButton){
						doCalc( 
								(JCalcButton)e.getSource(), 
								((JCalcButton)e.getSource()).getValue()>0,
								((JCalcButton)e.getSource()).getText(),
								((JCalcButton)e.getSource()).getOp()
								);
					}										
				}
			});
		}		
	}

	public Calculator(){
		setLayout(null);
		
		setSize(220, 195);
		
		txtCalc = new JTextField(10);
		txtCalc.setLocation(5, 5);
		txtCalc.setSize(200, 22);
		txtCalc.setHorizontalAlignment(JTextField.RIGHT);
		txtCalc.setFocusable(false);
		txtCalc.setEditable(false);
		
		add(txtCalc);
		createButtons(arrButtonSymbols);
		setVisible(true);
		setResizable(false);
		setCentered();
		setTitle("Calculator");		
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(e.getKeyCode());
				
				boolean IsEnabled = true;				
				Funcs op = Funcs.none;
				
				String txt = "";
				
				switch( e.getKeyCode() ){					
					case 48:case 96 :{ txt="0"; break;}
					case 49:case 97 :{ txt="1"; break;}
					case 50:case 98 :{ txt="2"; break;}
					case 51:case 99 :{ txt="3"; break;}
					case 52:case 100:{ txt="4"; break;}
					case 53:case 101:{ txt="5"; break;}
					case 54:case 102:{ txt="6"; break;}
					case 55:case 103:{ txt="7"; break;}
					case 56:case 104:{ txt="8"; break;}
					case 57:case 105:{ txt="9"; break;}
					
					case 107:{ op = Funcs.plus; break;}
					case 109:{ op = Funcs.minus; break;}
					case 106:{ op = Funcs.mul; break;}
					case 111:{ op = Funcs.div; break;}
					case 10:case 61 :{ op = Funcs.res; break;}
					
					default:IsEnabled=false;
				}
				
				if( IsEnabled ){
					doCalc( 
							null, 
							txt!="" ? true:false,
									txt,
									op
							);
				}				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
package Lecture5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CarForm extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField make;
	private JTextField model;
	private JComboBox<Integer> year;
	private JComboBox<String> color;
	private JButton btnAdd;
	private JButton btnOpenFile;
	private JButton btnSaveFile;
	private JTextArea txtArea;
	private JScrollPane txtBar;
	private File file = null;	
	
	private void initColor(){
		color  = new JComboBox<String>();		
		for(int i = 0;i<4;i++){
			String cTmp;
			switch(i){
				case 0 :{cTmp = "RED"; break;}
				case 1 :{cTmp = "GREEN"; break;}
				case 2 :{cTmp = "BLUE"; break;}
				case 3 :{cTmp = "YELLOW"; break;}
				default:cTmp="";
			}
			color.addItem( cTmp );		
		}
		color.setSize(120, 22);
		color.setLocation(140, 210);
	}
	
	private void initYear(){
		year  = new JComboBox<Integer>();		
		for(int i = 1981; i<2015;i++){
			year.addItem(i);
		}
		year.setSize(120, 22);
		year.setLocation(10, 210);		
	}
	public CarForm(){
		setLayout(null);
		setResizable(false);
		setTitle("File IO");
		setSize(300,350);
		
		make = new JTextField("Marka");
		make.setSize(120,22);
		make.setLocation(10, 180);
		
		model  = new JTextField("Model");
		model.setSize(120,22);
		model.setLocation(140, 180);
				
		initYear();
		initColor();
		
		txtArea = new JTextArea();	
		txtBar = new JScrollPane(txtArea);
		txtBar.setSize(250,130);
		txtBar.setLocation(10, 40);
				
		btnAdd = new JButton("add");
		btnAdd.setLocation(10, 240);
		btnAdd.setSize(250, 22);		
		btnAdd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {				
				txtArea.append(make.getText()+" "+model.getText()+" "+year.getSelectedItem()+" "+color.getSelectedItem()+"\n");				
			}
		});

		btnOpenFile = new JButton("Load from file");
		btnOpenFile.setLocation(10, 10);
		btnOpenFile.setSize(250, 22);
		btnOpenFile.addActionListener(new ActionListener(){
			/*
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if( jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					file = jfc.getSelectedFile();
					//txt load
					TxtFileReader fileW = new TxtFileReader();
					if( fileW.openFile( file.getAbsolutePath() ) ){
						String cTmp = fileW.readFromFile();
						while( cTmp!="" ){
							txtArea.append(cTmp+"\n");
							cTmp = fileW.readFromFile();
						}												
						fileW.closeFile();
					}
				};					
			}
			*/
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if( jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					file = jfc.getSelectedFile();
					//serializable load
					TxtFileReader fileW = new TxtFileReader();
					
					if( fileW.openSer(file.getAbsolutePath()) ){
						
						ArrayList<Car> cars = fileW.readSer();
						for(int i=0;i<cars.size(); i++){
							txtArea.append(cars.get(i).toString());
						}						
						fileW.closeSer();
					}					
				};					
			}			
		});
	
		btnSaveFile = new JButton("Save to file");
		btnSaveFile.setLocation(10, 270);
		btnSaveFile.setSize(250, 22);		
		btnSaveFile.addActionListener(new ActionListener(){
			/*
			@Override
			public void actionPerformed(ActionEvent e) {				
				//txt save
				TxtFileWriter fileW = new TxtFileWriter();
				if( fileW.openFile("e:\\test.txt") ){
					String[] arr = txtArea.getText().split("\n");
					for(int i=0;i<arr.length;i++){							
						fileW.writeToFile(arr[i]);
					}
					fileW.closeFile();
				}
			}
			*/
			@Override
			public void actionPerformed(ActionEvent e) {				
				//serializable save
				JFileChooser jfc = new JFileChooser();
				if( jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					file = jfc.getSelectedFile();
				}
				try {
					if( file.createNewFile()||file.exists()){
						TxtFileWriter fileW = new TxtFileWriter();
					
						if( fileW.openFileSer(file.getAbsolutePath()) ){					
						
							String[] obj = txtArea.getText().split("\n");
							for(int i=0;i<obj.length;i++){
								String[] params = obj[i].split(" ");					
								Car car = new Car(params[0],params[1],Integer.parseInt(params[2]),params[3]);
								fileW.writeSer(car);
							}
							fileW.closeSer();
						}
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		add(make);
		add(model);
		add(year);
		add(color);
		add(btnAdd);
		add(btnOpenFile);
		add(btnSaveFile);
		add(txtBar);		
	}
}

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
	private JComboBox<Integer> color;
	private JButton btnAdd;
	private JButton btnOpenFile;
	private JButton btnSaveFile;
	private JTextArea txtArea;
	private JScrollPane txtBar;
	private File file = null;	
	
	public CarForm(){		
		setLayout(null);
		setResizable(false);
		
		make = new JTextField("Marka");
		make.setSize(100,22);
		make.setLocation(10, 10);
		
		model  = new JTextField("Model");
		model.setSize(100,20);
		model.setLocation(10, 35);
		
		year  = new JComboBox<Integer>();		
		for(int i = 1981; i<2015;i++){
			year.addItem(i);
		}
		year.setSize(100, 22);
		year.setLocation(10, 60);
		
		color  = new JComboBox<Integer>();		
		for(int i = 0; i<15;i++){			
			color.addItem(i);
		}
		color.setSize(100, 22);
		color.setLocation(10, 85);
		
		txtArea = new JTextArea();
		txtArea.setSize(100, 100);
		
		txtBar = new JScrollPane(txtArea);
		//txtBar.add(txtArea);
		txtBar.setSize(130,130);
		txtBar.setLocation(110, 60);
		
		
		btnAdd = new JButton("add");
		btnAdd.setLocation(10, 110);
		btnAdd.setSize(100, 22);
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				txtArea.append(make.getText()+" "+model.getText()+" "+year.getSelectedItem()+" "+color.getSelectedItem()+"\n");
				
			}
		});

		btnOpenFile = new JButton("Load from file");
		btnOpenFile.setLocation(110, 10);
		btnOpenFile.setSize(150, 22);
		btnOpenFile.addActionListener(new ActionListener() {
			
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
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if( jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					file = jfc.getSelectedFile();
					//ser load
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
		btnSaveFile.setLocation(110, 35);
		btnSaveFile.setSize(150, 22);
		
		btnSaveFile.addActionListener(new ActionListener() {
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
					if( file.createNewFile()){
						TxtFileWriter fileW = new TxtFileWriter();
					
						if( fileW.openFileSer(file.getAbsolutePath()) ){					
						
							String[] obj = txtArea.getText().split("\n");
							for(int i=0;i<obj.length;i++){
								String[] params = obj[i].split(" ");					
								Car car = new Car(params[0],params[1],Integer.parseInt(params[2]),Integer.parseInt(params[3]));
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

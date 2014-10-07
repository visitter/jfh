package Lecture5;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Scanner;


public class TxtFileReader {
	
	private Scanner file = null;
	private InputStream inFile = null;
	private ObjectInput input  = null;
	
	public boolean openFile(String path){
		try {
			file = new Scanner( new File(path) );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( SecurityException se){
			se.printStackTrace();
		}
		return true;
	}
	public String readFromFile(){
		if( file!=null){
			if( file.hasNext())
				return file.nextLine();
			else return "";
		}else return "";		
	}
	public boolean closeFile(){
		if( file!=null ){
			file.close();
		}
		return true;
	}
	
	public boolean openSer(String Path)throws StreamCorruptedException{
		try{
			inFile = new FileInputStream(Path);
			//buffer = new BufferedInputStream(inFile);
			input = new ObjectInputStream(inFile);
		}catch( StreamCorruptedException sce ){
			//sce.printStackTrace();			
			throw sce;
			//return false;
		}catch (FileNotFoundException e) {			
			e.printStackTrace();
			return false;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	
	public ArrayList<Car> readSer(){		
		ArrayList<Car> arr = new ArrayList<Car>();
		try {			
			while(true){				
				arr.add((Car)input.readObject());				
			}			
		}catch( EOFException eof){
			//eof.printStackTrace();
			return arr;//return null;
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void closeSer(){
		if(input!=null)
			try {
				input.close();
				//buffer.close();
				inFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public TxtFileReader(){
		
	}
}

package Lecture5;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Formatter;


public class TxtFileWriter {
	
	private Formatter file = null;
	private OutputStream outFile = null;
	private OutputStream buffer = null;
	private ObjectOutput output = null;
	
	public boolean openFile(String path){
		try {
			file = new Formatter( new File(path) );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( SecurityException se){
			se.printStackTrace();
		}
		return true;
	}
	public boolean writeToFile(String str){
		if( file!=null){
			file.format("%s\n", str);		
			return true;	
		}else return false;		
	}
	public boolean closeFile(){
		if( file!=null ){
			file.close();
		}
		return true;
	}
	
	public boolean openFileSer(String path){
		try {
	    	 outFile =  new FileOutputStream(path);
	    	 buffer = new BufferedOutputStream(outFile);
	    	 output = new ObjectOutputStream(buffer);
		}catch (FileNotFoundException e) {			
			e.printStackTrace();
			return false;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
	    return true;
	}
	
	public void writeSer(Object obj){
		if(output!=null){
			try {
				output.writeObject(obj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void closeSer(){
		if(output!=null)
			try {
				output.close();
				buffer.close();
				outFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public TxtFileWriter(){
		
	}
}

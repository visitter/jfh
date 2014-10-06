package Lecture5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;


public class TxtFileWriter {
	
	private Formatter file = null;
	public boolean openFile(String path){
		try {
			file = new Formatter( new File(path) );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean writeToFile(String str){
		if( file!=null){
			file.format(str);
			return true;	
		}else return false;		
	}
	public boolean closeFile(){
		if( file!=null ){
			file.close();
		}
		return true;
	}
	
	public TxtFileWriter(){
		
	}
}

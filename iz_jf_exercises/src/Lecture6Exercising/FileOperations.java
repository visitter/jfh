package Lecture6Exercising;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileOperations {

	public FileOperations(){
		super();
	}
	public boolean saveToFile(ArrayList<?> list, String filename){
		try {
			FileOutputStream stream = new FileOutputStream(filename);
			ObjectOutputStream obj = new ObjectOutputStream(stream);
			obj.writeObject(list);
			obj.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
	public ArrayList<?> loadFromFile(String filename){
		try 
		{
			FileInputStream stream = new FileInputStream(filename);			
			ObjectInputStream obj = new ObjectInputStream(stream);
			ArrayList<?> rList;
			try {
				rList = (ArrayList<?>)obj.readObject();				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				obj.close();
				return null;
			}
			obj.close();			
			return rList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}

package Lecture10DB;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;



public class Lecture10Main {
	private static void listDrivers() {
		
	    Enumeration<Driver> driverList = DriverManager.getDrivers();
	    if( driverList.hasMoreElements() ){
	    	while (driverList.hasMoreElements()) {
	    		Driver driverClass = (Driver) driverList.nextElement();
	    		System.out.println("   "+driverClass.getClass().getName());
	    	}
	    }
	    else System.out.println("No drivers");
	  }
	public static void main(String[] args) {
		
		try {			
			JdbcConnector con = new JdbcConnector();
			for( Person per:con.getAllPeople()){
				System.out.println(per);
			};
			
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		listDrivers();
	}
}

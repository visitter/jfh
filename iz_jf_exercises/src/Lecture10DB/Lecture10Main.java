package Lecture10DB;

public class Lecture10Main {
	public static void main(String[] args) {
		
		try {			
			JdbcConnector con = new JdbcConnector();
			
			for( Person per:con.getAllPeople()){
				System.out.println(per.getFirstName());
			};
			
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
	}
}

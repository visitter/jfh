package Lecture3JTable;

public class Person{
	private String cFName;
	private String cLName;
	private String cCity;
	
	public String getcFName() {
		return cFName;
	}
	public void setcFName(String cFName) {
		this.cFName = cFName;
	}
	public String getcLName() {
		return cLName;
	}
	public void setcLName(String cLName) {
		this.cLName = cLName;
	}
	public String getcCity() {
		return cCity;
	}
	public void setcCity(String cCity) {
		this.cCity = cCity;
	}
	public String getcYears() {
		return cYears;
	}
	public void setcYears(String cYears) {
		this.cYears = cYears;
	}
	private String cYears;
	
	public Person(){
	}
	
	public Person(String fname, String lname, String city, String years){
		this.cFName	= fname;
		this.cLName	= lname;
		this.cCity	= city;
		this.cYears	= years;
	}
}
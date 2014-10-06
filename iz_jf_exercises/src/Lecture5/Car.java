package Lecture5;

import java.io.Serializable;

public class Car implements Serializable{

	private static final long serialVersionUID = 1L;
	private String make;
	private String model;
	private int year;
	private int color;
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Car(){
	
	}	
	public Car( String make, String model, int year, int color){
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
	}
	public String toString(){
		return getMake()+" "+getModel()+" "+getYear()+" "+getColor()+"\n";
	}
}

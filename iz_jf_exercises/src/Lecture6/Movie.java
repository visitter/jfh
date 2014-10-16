package Lecture6;

import java.io.Serializable;

enum Genre { Drama("Drama"), Action("Action"), Comedy("Comedy"), Fantasy("Fantasy");
	private final String type;
	
	Genre(String type){
		this.type = type;
	}
	public String type(){
		return this.type;
	}	
	public String toString(){
		return this.type;
	}	
};

public class Movie implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String director;
	private Integer year;
	private Genre genre;
	private String actor;
	private Integer rating;
	
	public Movie(String name, String director, Integer year, Genre genre, String actor, Integer rating){
		this.name = name;
		this.director = director;
		this.year = year;
		this.genre = genre;
		this.actor = actor;
		this.rating = rating;		
	}
	
	public String toString(){
		return this.name+" "+this.director+" "+this.year+" "+this.genre+" "+this.actor+" "+this.rating;//etc
	}

	public String getName() {
		return name;
	}

	public String getDirector() {
		return director;
	}

	public Integer getYear() {
		return year;
	}

	public Genre getGenre() {
		return genre;
	}

	public String getActor() {
		return actor;
	}

	public Integer getRating() {
		return rating;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}

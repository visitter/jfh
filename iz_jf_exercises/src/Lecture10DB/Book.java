package Lecture10DB;

public class Book {
	private Integer id;
	private String title;
	private Integer year;
	private String isbn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public Book(Integer id, String title, Integer year, String isbn){
		this.id = id;
		this.title = title;
		this.year = year;
		this.isbn = isbn;
	}
	
	public String toString(){
		return getId()+" "+getTitle()+" "+getYear()+" "+getIsbn();
	}
}

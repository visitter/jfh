package Lecture10DB;

public class Author {
   private int id;
   private String firstName;
   private String lastName;

   public Author(){
   }
   public Author( int id, String fname, String lname ){
      setId( id );
      setFirstName( fname );
      setLastName( lname );
   }
   
   public int getId() {
	return id;
   }
   public void setId(int id) {
	this.id = id;
   }
   public void setFirstName( String first ){
      firstName = first;
   } 
   public String getFirstName(){
      return firstName;
   }
   public void setLastName( String last ){
      lastName = last;
   } 
   public String getLastName(){
      return lastName;
   }
   public String toString(){
	   return getFirstName()+" "+getLastName();
   }
} 



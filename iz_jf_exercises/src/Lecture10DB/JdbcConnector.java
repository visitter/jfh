package Lecture10DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class JdbcConnector {
   
   private Connection connection = null;
   
   private PreparedStatement selectAllAuthors = null; 
   private PreparedStatement selectAuthorsByLastName = null;
   private PreparedStatement selectAuthorsByFirstName = null;
   private PreparedStatement selectAuthorById = null;   
   private PreparedStatement insertAuthor = null;
   private PreparedStatement editAuthor = null;
   
   private PreparedStatement selectAllBooks = null; 
   private PreparedStatement selectBookByTitle = null;//       
   private PreparedStatement selectBookByISBN = null;   
   private PreparedStatement selectBookById = null;
   private PreparedStatement selectBookByAuthor = null;
   private PreparedStatement insertBook = null;
   private PreparedStatement editBook = null;
   
   private PreparedStatement insertBookAuthor = null;
   private PreparedStatement updateBookAuthor = null;
   
   public String driver = "org.apache.derby.jdbc.ClientDriver";
   
   public JdbcConnector() throws ClassNotFoundException{	  
      try{  	  				
         Class.forName(driver);         
         //connection = DriverManager.getConnection("jdbc:derby://localhost:1527/F:\\git\\jfh\\iz_jf_exercises\\src\\Lecture10DB\\MyDB");
         connection = DriverManager.getConnection("jdbc:derby://localhost:1527/C:\\Users\\Admin\\git\\jfh\\iz_jf_exercises\\src\\Lecture10DB\\MyDB");

         selectAllAuthors         = connection.prepareStatement( "SELECT * FROM AUTHORS" );
         selectAuthorsByLastName  = connection.prepareStatement( "SELECT * FROM AUTHORS WHERE AUTHOR_LNAME LIKE ?" );
         selectAuthorsByFirstName = connection.prepareStatement( "SELECT * FROM AUTHORS WHERE AUTHOR_FNAME LIKE ?" );
         selectAuthorById         = connection.prepareStatement( "SELECT * FROM AUTHORS WHERE AUTHOR_ID=?" );         
         insertAuthor             = connection.prepareStatement( "INSERT INTO AUTHORS (AUTHOR_FNAME, AUTHOR_LNAME) VALUES(?,?)");
         editAuthor               = connection.prepareStatement( "UPDATE AUTHORS SET AUTHOR_FNAME=?, AUTHOR_LNAME=? WHERE AUTHOR_ID=?");         
         
         selectAllBooks     = connection.prepareStatement( "SELECT BOOK_ID, BOOK_NAME, BOOK_YEAR, BOOK_ISBN FROM BOOKS" );
         selectBookByTitle  = connection.prepareStatement( "SELECT BOOK_ID, BOOK_NAME, BOOK_YEAR, BOOK_ISBN FROM BOOKS WHERE BOOK_NAME LIKE ?" );
         selectBookByISBN   = connection.prepareStatement( "SELECT BOOK_ID, BOOK_NAME, BOOK_YEAR, BOOK_ISBN FROM BOOKS WHERE BOOK_ISBN LIKE ?" );
         insertBook         = connection.prepareStatement( "INSERT INTO BOOKS (BOOK_NAME, BOOK_YEAR, BOOK_ISBN) VALUES(?, ?, ?)");
         editBook           = connection.prepareStatement( "UPDATE BOOKS SET BOOK_NAME =?, BOOK_YEAR=?, BOOK_ISBN=? WHERE BOOK_ID=?");
         selectBookById     = connection.prepareStatement( "SELECT b.BOOK_ID, b.BOOK_NAME, b.BOOK_YEAR, b.BOOK_ISBN, a.AUTHOR_ID FROM BOOKS b INNER JOIN AUTHOR_ISBN ai ON ai.BOOK_ISBN = b.BOOK_ISBN INNER JOIN AUTHORS a	ON a.AUTHOR_ID = ai.AUTHOR_ID WHERE b.BOOK_ID = ?" );
         selectBookByAuthor = connection.prepareStatement( "SELECT b.BOOK_ID, b.BOOK_NAME, b.BOOK_YEAR, b.BOOK_ISBN, a.AUTHOR_ID FROM BOOKS b INNER JOIN AUTHOR_ISBN ai ON ai.BOOK_ISBN = b.BOOK_ISBN INNER JOIN AUTHORS a  ON a.AUTHOR_ID = ai.AUTHOR_ID WHERE a.AUTHOR_FNAME LIKE ?");         
         
         insertBookAuthor   = connection.prepareStatement( "INSERT INTO AUTHOR_ISBN (BOOK_ISBN, AUTHOR_ID) VALUES(?, ?)");
         updateBookAuthor   = connection.prepareStatement( "UPDATE AUTHOR_ISBN SET BOOK_ISBN=?, AUTHOR_ID=? WHERE BOOK_ISBN=?");

      }
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         JOptionPane.showMessageDialog(null, "Can't connect to DB. Check DB location or if DB apache is running");
         System.exit( 1 );
      }
   }

   public Book getBookById(Integer Id){
	   ResultSet resultSet= null;
	   try {
		   selectBookById.setInt(1, Id);
		   resultSet = selectBookById.executeQuery();
		   if( resultSet.next()){
		   
			   return new Book(
						resultSet.getInt   ("BOOK_ID"  ),
						resultSet.getString("BOOK_NAME"),
						resultSet.getInt   ("BOOK_YEAR"),
						resultSet.getString("BOOK_ISBN"),
						resultSet.getInt   ("AUTHOR_ID")
					 );
		   }
	   } catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	   return null;
   }
   public boolean addBook(String title, Integer year, String ISBN, Integer authorId){
	   ResultSet resultSet= null;
	   try {
		
		   insertBook.setString(1, title);
		   insertBook.setInt(2, year);
		   insertBook.setString(3, ISBN);

		   if(insertBook.executeUpdate()==1){
			   insertBookAuthor.setString(1, ISBN);
			   insertBookAuthor.setInt(2, authorId);
			   if (insertBookAuthor.executeUpdate()==1) {
				   return true;
			   }else{
				   insertBook.executeQuery( "DELETE FROM BOOKS WHERE BOOK_ISBN="+ISBN);
				   return false;
			   }
		   }else {
			   return false;
		   }
		   

	   } catch (SQLException e) {		
		   e.printStackTrace();		   
	   }finally{
		   if( resultSet!=null){ 
			   try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	   }
	  return false;
   }
   public boolean editBook(String title, Integer year, String ISBN, Integer bookid, Integer authorId){	  
	   ResultSet resultSet= null;
	   try {
		
		   editBook.setString(1, title);
		   editBook.setInt(2, year);
		   editBook.setString(3, ISBN);
		   editBook.setInt(4, bookid);

		   if(editBook.executeUpdate()==1){
			   updateBookAuthor.setString(1, ISBN);
			   updateBookAuthor.setInt(2, authorId);
			   updateBookAuthor.setString(3, ISBN);
			   if (updateBookAuthor.executeUpdate()==1) {
				   return true;
			   }else{				   
				   return false;
			   }
		   }else {
			   return false;
		   }
		   

	   } catch (SQLException e) {		
		   e.printStackTrace();		   
	   }finally{
		   if( resultSet!=null){ 
			   try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	   }
	   return false;
   }
   public ArrayList< Book > getBooks(int mode, String authorFName, String title, String isbn){
    	ArrayList< Book > results = null;
        ResultSet resultSet = null;             	
    	
    	try {
    		switch (mode){
    	  		case 0:{
    	   			resultSet = selectAllBooks.executeQuery();
    	   			break;
    	   		}
    	  		case 1:{
    	  			selectBookByTitle.setString( 1, "%"+title+"%" );
	   				resultSet = selectBookByTitle.executeQuery();
	   				break;
    	  		}
    	  		case 2:{
    	  			selectBookByAuthor.setString(1, "%"+authorFName+"%");
	   				resultSet = selectBookByAuthor.executeQuery();
	   				break;
    	  		}
    	  		case 3:{
    	  			selectBookByISBN.setString( 1, "%"+isbn+"%" );
	   				resultSet = selectBookByISBN.executeQuery();
	   				break;
	   		}
    	  		default:{break;}
    		}
			
    		results = new ArrayList< Book >();
					
			while(resultSet.next()){
				results.add( new Book(
										resultSet.getInt("BOOK_ID"),
										resultSet.getString("BOOK_NAME"),
										resultSet.getInt("BOOK_YEAR"),
										resultSet.getString("BOOK_ISBN"),
										null
									 )
							);
				}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
		       	 if( resultSet!=null )
		       		 resultSet.close();
		    }catch ( SQLException sqle ){
		    	sqle.printStackTrace();         
			    //close();
		    }
		}
    	return results;
   }   
   
   public ArrayList< Author > getAllAuthors(){
	   ArrayList< Author > results = null;
	   ResultSet resultSet = null;
      

      try{    	 
    	 resultSet = selectAllAuthors.executeQuery(); 
         results = new ArrayList< Author >();
         
         while ( resultSet.next() )
         {  
            results.add( new Author(
            							resultSet.getInt( "AUTHOR_ID" ),
            							resultSet.getString( "AUTHOR_FNAME" ),
            							resultSet.getString( "AUTHOR_LNAME" )
            						)

            			);            	
         }
      }
      catch ( SQLException sqle ){
    	  sqle.printStackTrace();         
      }
      finally{
         try{
        	 if( resultSet!=null )
        		 resultSet.close();
         }
         catch ( SQLException sqle ){
            sqle.printStackTrace();         
            //close();
         }
      }      
      return results;
   }
   public ArrayList< Author > getAuthorsByLastName( String lname )
   {
	  ArrayList< Author > results = null;
      ResultSet resultSet = null;

      try{
         selectAuthorsByLastName.setString( 1, "%"+lname+"%" );
         
         resultSet = selectAuthorsByLastName.executeQuery(); 

         results = new ArrayList< Author >();

         while ( resultSet.next() ){
        	 results.add( new Author(
        			 					resultSet.getInt( "AUTHOR_ID" ),
        			 					resultSet.getString( "AUTHOR_FNAME" ),
        			 					resultSet.getString( "AUTHOR_LNAME" )
                     				)
        			 	);
         }
      }
      catch ( SQLException sqle ){
    	  sqle.printStackTrace();
      }
      finally{
    	  try{
    		  if( resultSet!=null )
    			  resultSet.close();
         } 
         catch ( SQLException sqle )
         {
        	 sqle.printStackTrace();         
             //close();
         }
      }      
      return results;
   }
   public ArrayList< Author > getAuthorsByFirstName( String fname )
   {
	  ArrayList< Author > results = null;
      ResultSet resultSet = null;

      try{
    	  selectAuthorsByFirstName.setString( 1, "%"+fname+"%" );
         
         resultSet = selectAuthorsByFirstName.executeQuery(); 

         results = new ArrayList< Author >();

         while ( resultSet.next() ){
        	 results.add( new Author(
        			 					resultSet.getInt( "AUTHOR_ID" ),
        			 					resultSet.getString( "AUTHOR_FNAME" ),
        			 					resultSet.getString( "AUTHOR_LNAME" )
                     				)
        			 	);
         }
      }
      catch ( SQLException sqle ){
    	  sqle.printStackTrace();
      }
      finally{
    	  try{
    		  if( resultSet!=null )
    			  resultSet.close();
         } 
         catch ( SQLException sqle )
         {
        	 sqle.printStackTrace();         
            //close();
         }
      }      
      return results;
   }
   public boolean addAuthor( String fname, String lname )
   {      
      try 
      {
         insertAuthor.setString( 1, fname );
         insertAuthor.setString( 2, lname );

         return (insertAuthor.executeUpdate()>0); 
      }
      catch ( SQLException sqle )
      {
    	 sqle.printStackTrace();
         //close();
      }      
      return false;
   }
   public boolean editAuthor( String fname, String lname, Integer authorId){
	   try {
		
		   editAuthor.setString(1, fname);
		   editAuthor.setString(2, lname);
		   editAuthor.setInt   (3, authorId);		   

		   return editAuthor.executeUpdate()>0;
			
	   } catch (SQLException e) {		
		   e.printStackTrace();		   
	   }
	   return false;
   }
   public Author getAuthorById(Integer Id){
	   ResultSet resultSet= null;
	   try {
		   selectAuthorById.setInt(1, Id);
		   resultSet = selectAuthorById.executeQuery();
		   if( resultSet.next()){		   
			   return new Author(
					   				resultSet.getInt   ("AUTHOR_ID"),			
					   				resultSet.getString("AUTHOR_FNAME"),
									resultSet.getString("AUTHOR_LNAME")									
								);
		   }
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	   return null;
   }
   public void close()
   {
      try 
      {
         connection.close();
      }
      catch ( SQLException sqle )
      {
    	  sqle.printStackTrace();
      } // end catch
   }
}

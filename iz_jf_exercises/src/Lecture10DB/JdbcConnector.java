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
   private PreparedStatement insertAuthor = null;
   private PreparedStatement editAuthor = null;
   
   private PreparedStatement selectAllBooks = null; 
   private PreparedStatement selectBookByTitle = null;
   private PreparedStatement selectBookByAuthor = null;    
   private PreparedStatement selectBookByISBN = null;   
   private PreparedStatement insertBook = null;
   private PreparedStatement editBook = null;
   
   public String driver = "org.apache.derby.jdbc.ClientDriver";
   
   public JdbcConnector() throws ClassNotFoundException{	  
      try{  	  				
         Class.forName(driver);         
         connection = DriverManager.getConnection("jdbc:derby://localhost:1527/F:\\git\\jfh\\iz_jf_exercises\\src\\Lecture10DB\\MyDB");
         
         selectAllAuthors         = connection.prepareStatement( "SELECT * FROM AUTHORS" );
         selectAuthorsByLastName  = connection.prepareStatement( "SELECT * FROM AUTHORS WHERE AUTHOR_LNAME LIKE ?" );
         selectAuthorsByFirstName = connection.prepareStatement( "SELECT * FROM AUTHORS WHERE AUTHOR_FNAME LIKE ?" );
         insertAuthor             = connection.prepareStatement( "INSERT INTO AUTHORS (AUTHOR_FNAME, AUTHOR_LNAME) VALUES(?,?)");
         editAuthor               = connection.prepareStatement( "UPDATE AUTHORS SET AUTHOR_FNAME=?, AUTHOR_LNAME=? WHERE AUTHOR_ID=?");
         
         
         selectAllBooks     = connection.prepareStatement( "SELECT BOOK_ID, BOOK_NAME, BOOK_YEAR, BOOK_ISBN FROM BOOKS" );
         selectBookByTitle  = connection.prepareStatement( "SELECT BOOK_ID, BOOK_NAME, BOOK_YEAR, BOOK_ISBN FROM BOOKS WHERE BOOK_NAME LIKE ?" );
         //selectBookByAuthor = connection.prepareStatement( "SELECT BOOK_ID, BOOK_NAME, BOOK_YEAR, BOOK_ISBN FROM BOOKS WHERE BOOK_NAME LIKE ?" );
         selectBookByISBN   = connection.prepareStatement( "SELECT BOOK_ID, BOOK_NAME, BOOK_YEAR, BOOK_ISBN FROM BOOKS WHERE BOOK_ISBN LIKE ?" );
         insertBook         = connection.prepareStatement( "INSERT INTO BOOKS (BOOK_NAME, BOOK_YEAR, BOOK_ISBN) VALUES(?, ?, ?)");
         editBook           = connection.prepareStatement( "UPDATE BOOKS SET BOOK_NAME =?, BOOK_YEAR=?, BOOK_ISBN=? WHERE BOOK_ID=?");
         
      }
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         JOptionPane.showMessageDialog(null, "Can't connect to DB. Check DB location or if DB apache is running");
         System.exit( 1 );
      }
   }
   public ArrayList< Book > getBooks(int mode, Integer id, String title, String isbn){
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
    	  		}/*
    	  		case 2:{
	   				resultSet = selectAllBooks.executeQuery();
	   				break;
    	  		}*/
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
										resultSet.getString("BOOK_ISBN")
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
   public int addAuthor( String fname, String lname )
   {
      int result = 0;
      try 
      {
         insertAuthor.setString( 1, fname );
         insertAuthor.setString( 2, lname );

         result = insertAuthor.executeUpdate(); 
      }
      catch ( SQLException sqle )
      {
    	 sqle.printStackTrace();
         //close();
      }      
      return result;
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
package Lecture10DB;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class JdbcConnector {
   
   private Connection connection = null; // manages connection
   private Statement selectStatement;
   private PreparedStatement selectAllPeople = null; 
   private PreparedStatement selectPeopleByLastName = null; 
   private PreparedStatement insertNewPerson = null; 
    /**
     *
     * @throws ClassNotFoundException
     */
   public String driver = "org.apache.derby.jdbc.EmbeddedDriver";
   // constructor
   public JdbcConnector() throws ClassNotFoundException
   
   {	  
      try 
      {
         Class.forName("org.apache.derby.jdbc.ClientDriver");
    	  				
         //Class.forName(driver);
           //Enum<Driver> drv =  DriverManager.getDrivers();
         //connection = DriverManager.getConnection("jdbc:derby:C:\\Users\\Ivan\\MyDB");
         connection = DriverManager.getConnection("jdbc:derby://localhost:1527/C:\\Users\\Ivan\\MyDB");

         // create query that selects all entries in the AddressBook

         selectStatement = connection.createStatement();                  
         
         selectAllPeople = 
            connection.prepareStatement( "SELECT * FROM PEOPLE" );
         
         // create query that selects entries with a specific last name
         selectPeopleByLastName = connection.prepareStatement( 
            "SELECT * FROM PEOPLE WHERE surname = ?" );
         
         // create insert that adds a new entry into the database
         insertNewPerson = connection.prepareStatement( 
            "INSERT INTO PEOPLE " + 
            "( name, surname ) " + 
            "VALUES ( ?, ? )" );
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         System.exit( 1 );
      } // end catch
   } // end PersonQueries constructor
   
   // select all of the addresses in the database
   public List< Person > getAllPeople()
   {
      List< Person > results = null;
      ResultSet resultSet = null;
      
      try 
      {
         // executeQuery returns ResultSet containing matching entries
    	 String statement = "SELECT * FROM PEOPLE";
    	 resultSet = selectStatement.executeQuery(statement);// selectAllPeople.executeQuery(); 
         results = new ArrayList< Person >();
         
         while ( resultSet.next() )
         {
            results.add( new Person(
               resultSet.getInt( "ID" ),
               resultSet.getString( "name" ),
               resultSet.getString( "surname" )
               /*resultSet.getString( "email" ),
               resultSet.getString( "phoneNumber" )*/ ) );
         } // end while
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();         
      } // end catch
      finally
      {
         try 
         {
            resultSet.close();
         } // end try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();         
            close();
         } // end catch
      } // end finally
      
      return results;
   } // end method getAllPeople
   
   // select person by last name   
   public List< Person > getPeopleByLastName( String name )
   {
      List< Person > results = null;
      ResultSet resultSet = null;

      try 
      {
         selectPeopleByLastName.setString( 1, name ); // specify last name

         // executeQuery returns ResultSet containing matching entries
         resultSet = selectPeopleByLastName.executeQuery(); 

         results = new ArrayList< Person >();

         while ( resultSet.next() )
         {/*
            results.add( new Person( resultSet.getInt( "addressID" ),
               resultSet.getString( "firstName" ),
               resultSet.getString( "lastName" ),
               resultSet.getString( "email" ),
               resultSet.getString( "phoneNumber" ) ) );
               */
        	 results.add( new Person(
                     resultSet.getInt( "ID" ),
                     resultSet.getString( "name" ),
                     resultSet.getString( "surname" )
                     /*resultSet.getString( "email" ),
                     resultSet.getString( "phoneNumber" )*/ ) );
         } // end while
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch
      finally
      {
         try 
         {
            resultSet.close();
         } // end try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();         
            close();
         } // end catch
      } // end finally
      
      return results;
   } // end method getPeopleByName
   
   // add an entry
   public int addPerson( 
      String fname, String lname, String email, String num )
   {
      int result = 0;
      
      // set parameters, then execute insertNewPerson
      try 
      {
         insertNewPerson.setString( 1, fname );
         insertNewPerson.setString( 2, lname );
         insertNewPerson.setString( 3, email );
         insertNewPerson.setString( 4, num );

         // insert the new entry; returns # of rows updated
         result = insertNewPerson.executeUpdate(); 
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch
      
      return result;
   } // end method addPerson
   
   // close the database connection
   public void close()
   {
      try 
      {
         connection.close();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch
   } // end method close
} // end class PersonQueries
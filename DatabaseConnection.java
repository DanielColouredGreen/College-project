import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DatabaseConnection {

    private Connection connection = null;

    //this is the method in which the main connections take place as it handles connecting and if there are any errors, the catch parameters will give out an error message.
    public DatabaseConnection(String dbFile)
    {
        try            
        {         
            Class.forName("org.sqlite.JDBC");                              
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile); 
            System.out.println("Database connection successfully established.");
        } 
        catch (ClassNotFoundException cnfex)    
        {
            System.out.println("Class not found exception: " + cnfex.getMessage());
        }
        catch (SQLException exception)         
        {                        
            System.out.println("Database connection error: " + exception.getMessage());
        }

    }

    //this method will show any error with give out an error message.
    public PreparedStatement newStatement(String query)
    {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
        }
        catch (SQLException resultsexception) 
        {
            System.out.println("Database statement error: " + resultsexception.getMessage());
        }
        return statement;
    }

   
    public ResultSet runQuery(PreparedStatement statement)
    {               
        try {            
            return statement.executeQuery();                       
        }
        catch (SQLException queryexception) 
        {
            System.out.println("Database query error: " + queryexception.getMessage());
            return null;
        }
    }

    //this method will update and changes to the database.
    public void executeUpdate(PreparedStatement statement)
    {               
        try {            
            statement.executeUpdate();                       
        }
        catch (SQLException queryexception) 
        {
            System.out.println("Database update error: " + queryexception.getMessage());
        }
    }

    //this method will end the connection when the application is terminated.
    public void disconnect()
    {
        System.out.println("Disconnecting from database.");
        try {
            if (connection != null) connection.close();                        
        } 
        catch (SQLException finalexception) 
        {
            System.out.println("Database disconnection error: " + finalexception.getMessage());
        }        
    }

}
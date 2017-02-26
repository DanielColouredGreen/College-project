import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.*;
public class games
{
    public String Name;

    public String PublisherName;
    public String PrimaryGenreName;
    public String SecondaryGenreName;
    public String PlatformName;
    
    public games(String Name, String PublisherName, String PrimaryGenreName, String SecondaryGenreName, String PlatformName)
    {
        this.Name = Name;
        this.PublisherName = PublisherName;
        this.PrimaryGenreName = PrimaryGenreName;
        this.SecondaryGenreName = SecondaryGenreName;
        this.PlatformName = PlatformName;
       
    }
    
    @Override public String toString()
    {
        return (Name + "\n" + PublisherName + "\n" + PrimaryGenreName + "\n" + SecondaryGenreName + "\n" + PlatformName);
    }
    // the following method will look in the database and retreave all of the values that have been requested in the prepared statement and I can use this later to display/ populate the scene with values.
    public static void readAll(List<games> list){
        list.clear();
        // the statement below is a statement which will go into SQL and perform some inner joins and retreave all of the data which i have stated.
        PreparedStatement statement = Application.database.newStatement("SELECT Games.Name, Publisher.PublisherName, PrimaryGenre.PrimaryGenreName, SecondaryGenre.SecondaryGenreName, Platform.PlatformName FROM Games INNER JOIN Platform ON Games.PlatformID = Platform.PlatformID INNER JOIN PrimaryGenre ON Games.PrimaryGenreID = PrimaryGenre.PrimaryGenreID INNER JOIN Publisher ON Games.PublisherID = Publisher.PublisherID INNER JOIN SecondaryGenre ON Games.SecondaryGenreID = SecondaryGenre.SecondaryGenreID;");
        
        if(statement != null)
        {
            ResultSet results = Application.database.runQuery(statement);
            if(results != null)
            {
                try{
                    while(results.next()){
                        list.add(new games(results.getString("Name"), results.getString("PublisherName"), results.getString("PrimaryGenreName"), results.getString("SecondaryGenreName"), results.getString("PlatformID")));
                    }
                }
                catch (SQLException reslutsexception)
                {
                    System.out.println("Database result processing error: " + reslutsexception.getMessage());
                }
            }
        }
    }
    // this method will look in the games table and retrieve the gameId of all teh values.
    public static games getByGameId(int GameId)
    {
        games game = null;
        
        PreparedStatement statement = Application.database.newStatement("SELECT Games.Name, Publisher.PublisherName, PrimaryGenre.PrimaryGenreName, SecondaryGenre.SecondaryGenreName, Platform.PlatformName FROM Games INNER JOIN Platform ON Games.PlatformID = Platform.PlatformID INNER JOIN PrimaryGenre ON Games.PrimaryGenreID = PrimaryGenre.PrimaryGenreID INNER JOIN Publisher ON Games.PublisherID = Publisher.PublisherID INNER JOIN SecondaryGenre ON Games.SecondaryGenreID = SecondaryGenre.SecondaryGenreID WHERE= ?");
        
        try{
            if(statement != null)
            {
                statement.setInt(1, GameId);
                ResultSet results = Application.database.runQuery(statement);
                
                if(results != null)
                {
                    game = new games(results.getString("Name"), results.getString("PublisherName"), results.getString("PrimaryGenreName"), results.getString("SecondaryGenreName"), results.getString("PlatfromId"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database results processing error: " + resultsexception.getMessage());
        }
        return game;
    }
    //this method will delete a value by searching for its GameId.
    public static void deleteByGameId(int GameId){
        try{
            PreparedStatement statement = Application.database.newStatement("DELETE FROM Games WHERE GameId = ?");
            statement.setInt(1, GameId);
            
            if(statement != null)
            {
                Application.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }
    //this method will save all of the change that have been made to the database file.
 
    
    //public void save()
    //{
      //  PreparedStatement statement;
       
       // try{
       //     if (GameId == 0)
        //    {
         //       statement = Application.database.newStatement("SELECT GameId FROM Games ORDER BY GameId DESC");
                
          //      if (statement != null)
          //      {
            //       ResultSet results = Application.database.runQuery(statement);
            //        if (results != null)
              //      {
             //          GameId = results.getInt("GameId") + 1;
             //       }
             //   }
                
             //   statement = Application.database.newStatement("INSERT INTO Games (Name, GameId, PublisherName, PrimaryGenreName, SecondaryGenreName, PlatformName) VALUES (?, ?, ?, ?, ?, ?)");
             //   statement.setString(1, Name);
             //   statement.setInt(2, GameId);
             //   statement.setInt(3, PublisherName);
             //   statement.setInt(4, PrimaryGenreName);
              //  statement.setInt(5, SecondaryGenreName);
             //   statement.setInt(6, PlatformName);
          // }
           // else
            //{
                
               // statement = Application.database.newStatement("UPDATE Games SET Name = ?, PublisherName = ?, PrimaryGenreName = ?, SecondaryGenreName = ?, PlatformName = ? WHERE GameId = ?");
              //  statement.setString(1, Name);
              //  statement.setInt(2, PublisherName);
              //  statement.setInt(3, PrimaryGenreName);
              //  statement.setInt(4, SecondaryGenreName);
              //  statement.setInt(5, PlatformName);
              //  statement.setInt(6, GameId);
           // }
            //if (statement != null)
           // {
           //    Application.database.executeUpdate(statement);
           // }
        //}
       // catch (SQLException resultsexception)
        //{
         //  System.out.println("Database results processing error: " + reslutsexception.getMessage());
       // }
    //}
}
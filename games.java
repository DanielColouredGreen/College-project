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
    
    public static void readAll(List<games> list){
        list.clear();
        
        PreparedStatement statement = Application.database.newStatement("SELECT Games.Name, Publisher.PublisherName, PrimaryGenre.PrimaryGenreName, SecondaryGenre.SecondaryGenreName, Platform.PlatformName FROM Games INNER JOIN Platform ON Games.PlatformID = Platform.PlatformId INNER JOIN PrimaryGenre ON Games.PrimaryGenreID = PrimaryGenre.PrimaryGenreID INNER JOIN Publisher ON Games.PublisherID = Publisher.PublisherID INNER JOIN SecondaryGenre ON Games.SecondaryGenreID = SecondaryGenre.SecondaryGenreID;");
        
        if(statement != null)
        {
            ResultSet results = Application.database.runQuery(statement);
            if(results != null)
            {
                try{
                    while(results.next()){
                        list.add(new games(results.getString("Name"), results.getString("PublisherName"), results.getString("PrimaryGenreName"), results.getString("SecondaryGenreName"), results.getString("PlatfromId")));
                    }
                }
                catch (SQLException reslutsexception)
                {
                    System.out.println("Database result processing error: " + reslutsexception.getMessage());
                }
            }
        }
    }
    
    public static games getByGameId(int GameId)
    {
        games game = null;
        
        PreparedStatement statement = Application.database.newStatement("SELECT Games.Name, Publisher.PublisherName, PrimaryGenre.PrimaryGenreName, SecondaryGenre.SecondaryGenreName, Platform.PlatformName FROM Games INNER JOIN Platform ON Games.PlatformID = Platform.PlatformId INNER JOIN PrimaryGenre ON Games.PrimaryGenreID = PrimaryGenre.PrimaryGenreID INNER JOIN Publisher ON Games.PublisherID = Publisher.PublisherID INNER JOIN SecondaryGenre ON Games.SecondaryGenreID = SecondaryGenre.SecondaryGenreID WHERE= ?");
        
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
    
   // public void save()
    //{
      //  PreparedStatement statement;
        
        //try{
          //  if (GameID == 0)
            //{
              //  statement = Application.database.newStatement("SELECT GameId FROM Games ORDER BY GameId DESC");
                //
                //if (statement != null)
                //{
                  //  ResultSet results = Application.database.runQuery(statement);
                    //if (results != null)
                    //{
                      //  GameId = results.getInt("GameId") + 1;
                    //}
                //}
                
                //statement = Application.database.newStatement("INSERT INTO Games (Name, GameId, PublisherName, PrimaryGenreName, SecondaryGenreName, PlatformName) VALUES (?, ?, ?, ?, ?, ?)");
                //statement.setString(1, Name);
                //statement.setInt(2, GameId);
                //statement.setInt(3, PublisherName);
                //statement.setInt(4, PrimaryGenreName);
              //  /statement.setInt(5, SecondaryGenreName);
                //statement.setInt(6, PlatformName);
           // }
            //else
            //{
                
              //  statement = Application.database.newStatement("UPDATE Games SET Name = ?, PublisherName = ?, PrimaryGenreName = ?, SecondaryGenreName = ?, PlatformName = ? WHERE GameId = ?");
                //statement.setString(1, Name);
                //statement.setInt(2, PublisherName);
                //statement.setInt(3, PrimaryGenreName);
                //statement.setInt(4, SecondaryGenreName);
                //statement.setInt(5, PlatformName);
                //statement.setInt(6, GameId);
            //}
            //if (statement != null)
            //{
          //      Application.database.executeUpdate(statement);
            //}
        //}
        //catch (SQLException resultsexception)
        //{
          //  System.out.println("Database results processing error: " + reslutsexception.getMessage());
        //}
   // }
}
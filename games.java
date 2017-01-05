import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.*;
public class games
{
    public String Name;
    public int GameId;
    public int PublisherId;
    public int PrimaryGenreId;
    public int SecondaryGenreId;
    public int PlatformId;
    
    public games(String Name, int GameId, int PublisherId, int PrimaryGenreId, int SecondaryGenreId, int PlatfromId)
    {
        this.Name = Name;
        this.GameId = GameId;
        this.PublisherId = PublisherId;
        this.PrimaryGenreId = PrimaryGenreId;
        this.SecondaryGenreId = SecondaryGenreId;
        this.PlatformId = PlatformId;
       
    }
    
    @Override public String toString()
    {
        return Name;
    }
    
    public static void readAll(List<games> list){
        list.clear();
        
        PreparedStatement statement = Application.database.newStatement("SELECT Name, PublisherId, PrimaryGenreId, SecondaryGenreId, PlatformId FROM Games ORDER BY GameId");
        
        if(statement != null)
        {
            ResultSet results = Application.database.runQuery(statement);
            if(results != null)
            {
                try{
                    while(results.next()){
                        list.add(new game(results.getString("Name"), resluts.getInt("GameId"), resluts.getInt("PublisherId"), resluts.getInt("PrimaryGenreId"), resluts.getInt("SecondaryGenreId"), results.getInt("PlatfromId")));
                    }
                }
                catch (SQLException reslutsexception)
                {
                    System.out.prinltln("Database result processing error: " + reslutsexception.getMessage());
                }
            }
        }
    }
    
    public static games getByGameId(int GameId)
    {
        game game = null;
        
        PreparedStatement statement = Application.database.newStatement("SELECT Name, PublisherId, PrimaryGenreId, SecondaryGenreId, PlatformId FROM Games WHERE GameId = ?");
        
        try{
            if(statement != null)
            {
                statement.setInt(1, GameId);
                ResultSet results = Applicatio.database.runQuery(statement);
                
                if(resluts != null)
                {
                    game = new game(results.getString("Name"), resluts.getInt("GameId"), resluts.getInt("PublisherId"), resluts.getInt("PrimaryGenreId"), resluts.getInt("SecondaryGenreId"), results.getInt("PlatfromId"));
                }
            }
        }
        catch (SQLException reslutsexception)
        {
            System.out.println("Database results processing error: " + resultsexception.getMessage());
        }
        return game;
    }
    
    public static void deleteByGameId(int GameId){
        try{
            preparedStatement statement = Application.database.newStatement("DELETE FROM Games WHERE GameId = ?");
            statement.setInt(1, GameId);
            
            if(statement != null)
            {
                Application.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + reslutsexception.getMessage());
        }
    }
    
    public void save()
    {
        PreparedStatement statement;
        
        try{
            if (GameId == 0)
            {
                statement = Application.database.newStatement("SELECT GameId FROM Games ORDER BY GameId DESC");
                
                if (statement != null)
                {
                    ResultSet results = Application.database.runQuery(statement);
                    if (results != null)
                    {
                        GameId = results.getInt("GameId") + 1;
                    }
                }
                
                statement = Application.database.newStatement("INSERT INTO Games (Name, GameId, PublisherId, PrimaryGenreId, SecondaryGenreId, PlatformId) VALUES (?, ?, ?, ?, ?, ?)");
                statement.setString(1, Name);
                statement.setInt(2, GameId);
                statement.setInt(3, PublisherId);
                statement.setInt(4, PrimaryGenreId);
                statement.setInt(5, SecondaryGenreId);
                statement.setInt(6, PlatformId);
            }
            else
            {
                
                statement = Application.database.newStatement("UPDATE Games SET Name = ?, PublisherId = ?, PrimaryGenreId = ?, SecondaryGenreId = ?, PlatformId = ? WHERE GameId = ?");
                statement.setString(1, Name);
                statement.setInt(2, PublisherId);
                statement.setInt(3, PrimaryGenreId);
                statement.setInt(4, SecondaryGenreId);
                statement.setInt(5, PlatformId);
                statement.setInt(6, GameId);
            }
            if (statement != null)
            {
                Application.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database results processing error: " + reslutsexception.getMessage());
        }
    }
}
package Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

public class Publisher
{
   public int PublisherId;
   public String PublisherName;
   public int StartYear;
   
   public Publisher(int PublisherId, String PublisherName, int StartYear)
   {
       this.PublisherId = PublisherId;
       this.PublisherName = PublisherName;
       this.StartYear = StartYear;
    }
    
    @Override public String toString()
    {
        return PublisherName;
    }
    
    public static void readAll(List<Publisher> list)
    {
        list.clear();
        
        prepareStatement statement = Application.database.newStatement("SELECT PublisherId, PublisherName, StartYear FROM Publisher OREDER BY PublisherId");
        
        if (statement != null)
        {
            ResultSet results = Application.database.runQuery(statement);
            
            if(results != null)
            {
                try{
                    
                while (results.next()) {
                    list.add( new Publisher(results.getInt("PublisherId"), results.getString("PublisherName"), results.getInt("StartYear")));
                }
            }
            
            catch (SQLException resultsexception)
            {
                System.out.println("Database result processing error: " + resultsexception.getMessage());
            }
        }
    }
}
}
                
            
            
            


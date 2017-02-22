import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
//above is where i have iported the javaFX controls.
public class Application
{
    public static DatabaseConnection database;
   
//the main method creates a panel and the opens the first scene.    
    public static void main(String args[]){
        JFXPanel panel = new JFXPanel();
        Platform.runLater(() -> start());
    }


//this method start, which is called upon by the above main method it the method which does a few things, it states the database file to connect to and then it creates the stage for the first scene.
private static void start() 
    {
        try
        {  
            database = new DatabaseConnection("Inventory.db"); 


            FXMLLoader loader = new FXMLLoader(Application.class.getResource("SteamUI.fxml"));


            Stage stage = new Stage();
            stage.setTitle("SteamUI");
            stage.setScene(new Scene(loader.load()));
            stage.show();           

            SceneController controller = loader.getController();
            controller.prepareStageEvents(stage);
        }
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
            terminate();
        }
    }

  //this method os one which will be called through out the code as it will close the program. 
    public static void terminate()
    {
        System.out.println("Closing database connection and terminating application..."); 
         if (database != null) database.disconnect();


       
        System.exit(0);
    }

}
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//above is where i have imported multiple things including the controls for javaFX.


public class SceneController
{
    private static Stage stage;
// below is where i take the controls from inside the javaFX and then give them a name which i can call on later.
    @FXML   private SplitPane pane;
    @FXML   private ListView list;
    @FXML   private Button searchButton;
    @FXML   private Button addButton;
    @FXML   private Button deleteButton;
    @FXML   private Button editButton;
    @FXML   private TextField search;
    @FXML   private Button exitButton;
    
    
    public SceneController(){
        System.out.println("Loading Scene...");
        if (stage  != null)
        {
            System.out.println("ERROR --- Exiting Programme");
            System.exit(-1);
        }
    }
    
    //this method will assert the controls and if there are no controls matching the name, it will come back with an error message.
    @FXML   void initialize(){
        try{
            
        assert pane != null : "Pane not found";
        assert searchButton != null : "searchButton not found";
        assert addButton != null : "addButton not found";
        assert deleteButton != null : "deleteButton not found";
        assert editButton != null : "editButton not found";
        assert search != null : "search not found";
        assert exitButton != null : "Exit button not found";
        assert list != null : "table not found";
    }
    catch (AssertionError ae){
        System.out.println("FXML assertion failure: " + ae.getMessage());
        Application.terminate();
    }
    
    System.out.println("Populating list with items from table...");
    @SuppressWarnings("unchecked")
    List<games> targetList = list.getItems();
    games.readAll(targetList);
    }
   
// this method is called to prepare all of the events which will happen when the stage is created.
    public void prepareStageEvents(Stage stage)
    {
        System.out.println("Preparing stage events...");

        this.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() 
        {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");

                }
            });
    }       
      //the following are different methods that will run when you interact with the scene.
    @FXML   void searchClicked(){
        System.out.println("search button was clicked");
    }

    @FXML   void addClicked() 
    {
       System.out.println("add was clicked");
       openNewScene();
    }
    @FXML   void exitClicked()
    {
        System.out.println("Closing program");
        Application.terminate();
    }
        
    

    @FXML   void deleteClicked(){
       System.out.println("delete button was clicked");
    }

    @FXML   void editClicked(){
        System.out.println("edit button was clicked");

       
    }
  
    
    
    // this is the method which will open the second scene from with in the first scene
    void openNewScene(){
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("EditUI.fxml"));
        
        try
        {  
            
            Stage stage2 = new Stage();
            stage2.setTitle("Info");
            stage2.setScene(new Scene(loader.load()));
            stage2.show();           

            Scene2Controller controller2 = loader.getController();
            controller2.prepareStageEvents(stage2);
            
            controller2.setParent(this);
        
            
        }
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
            Application.terminate();
        }

    

    }
}
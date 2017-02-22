import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;
import java.util.*;

public class Scene2Controller
{
    private static Stage stage;
    private SceneController parent;
    
    //like in the first scene controller, the following statements will take the tyoe of controls used in the javaFX and state their FXid's. if the id's do not match up then it will throw an error later on.
    @FXML   private Pane pane2;
    @FXML   private TextField Name;
    @FXML   private Button confirmNew;
    @FXML   private Button exit;
    @FXML   private Label name;
    @FXML   private Label publisher;
    @FXML   private Label platform;
    @FXML   private Label pgenre;
    @FXML   private Label sgenre;
    @FXML   private ChoiceBox PublisherChoiceBox;
    @FXML   private ChoiceBox PlatformChoiceBox;
    @FXML   private ChoiceBox PrimaryGenreChoiceBox;
    @FXML   private ChoiceBox SecondaryGenreChoiceBox;
    
    private games game;
    //the following method will check for any errors when preparing the stage.
        public Scene2Controller(){
            System.out.println("Loading Scene...");
            if (stage != null)
            {
                System.out.println("ERROR --- Exiting Programme");
                System.exit(-1);
            }
        }
        //this method is what will use a try-catch block to check that there are no errors and if there are some errors then it will come back with an error message.
        @FXML   void initialize(){
            try{
                assert pane2 != null : "pane2 not found";
                assert confirmNew != null : "confirmNew not found";
                assert exit != null: "exit not found";
                assert name != null: "name not found";
                assert Name != null: "Name not found";
                assert publisher != null: "publisher not found";
                
                
            }
            catch (AssertionError ae){
                System.out.println("FXML assertion failure: " + ae.getMessage());
                Application.terminate();
            }
            
          System.out.println("Loading scene with the items from the database...");

          
            
        }
        
        public void setParent(SceneController parent){
            this.parent = parent;
            
        }
        
       
        
        //this method is the method which will prepare the stage with all of the things that are needed to create the scene.
        public void prepareStageEvents(Stage stage){
            System.out.println("Preparing stage events...");

        this.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");
                    stage.close();

                }
            });
    }       
    //the following method simply calls for the terminate method in the Application class.
    @FXML   void extClicked(){
          System.out.println("Closing program");
        System.exit(1);
    }
    
            

}

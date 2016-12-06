import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;

public class Scene2Controller
{
    private static Stage stage;
    
    @FXML   private Pane pane2;
    @FXML   private TextField newName;
    @FXML   private TextField newGenre;
    @FXML   private TextField newPublisher;
    @FXML   private Button confirmNew;
    @FXML   private TextField newPlatform;
    @FXML   private Button exit;
    
        public Scene2Controller(){
            System.out.println("Loading Scene...");
            if (stage != null)
            {
                System.out.println("ERROR --- Exiting Programme");
                System.exit(-1);
            }
        }
        
        @FXML   void initialize(){
            try{
                assert pane2 != null : "pane2 not found";
                assert newName != null : "newName not found";
                assert newGenre != null : "newGenre not found";
                assert newPublisher != null : "newPublisher not found";
                assert confirmNew != null : "confirmNew not found";
                assert newPlatform != null : "newPlatform not found";
                assert exit != null: "exit not found";
                
            }
            catch (AssertionError ae){
                System.out.println("FXML assertion failure: " + ae.getMessage());
                Application.terminate();
            }
        }
        
        public void prepareStageEvents(Stage stage){
            System.out.println("Preparing stage events...");

        this.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");

                }
            });
    }       
    
    @FXML   void confirmNewClicked(){
        System.out.println("confirm button was clicked");
    }
    
    @FXML   void extClicked(){
        Application.terminate();
    }
    
            

}

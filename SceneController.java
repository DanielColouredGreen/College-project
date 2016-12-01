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

public class SceneController
{
    private static Stage stage;

    @FXML   private SplitPane pane;
    @FXML   private TableView table;
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

    @FXML   void initialize(){
        try{
            
        assert pane != null : "Pane not found";
        assert searchButton != null : "searchButton not found";
        assert addButton != null : "addButton not found";
        assert deleteButton != null : "deleteButton not found";
        assert editButton != null : "editButton not found";
        assert search != null : "search not found";
        assert exitButton != null : "Exit button not found";
        assert table != null : "table not found";
    }
    catch (AssertionError ae){
        System.out.println("FXML assertion failure: " + ae.getMessage());
        Application.terminate();
    }
    }
   

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

    @FXML   void searchClicked(){
        System.out.println("search button was clicked");
    }

    @FXML   void addClicked() 
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("EditUI.fxml"));
            Stage stage2 = new Stage();
            stage2.setTitle("EditUI");
            stage2.setScene(new Scene(loader.load()));
            stage2.show();           
            Scene2Controller controller2 = loader.getController();
            controller2.prepareStageEvents(stage2);

   


        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
        
    

    @FXML   void deleteClicked(){
        System.out.println("delete button was clicked");
    }

    @FXML   void editClicked(){
        System.out.println("edit button was clicked");
    }

    @FXML   void tableClicked(){
        System.out.println("table item was clicked");
    }

    @FXML   void exitClicked(){
        Application.terminate();
    }
}
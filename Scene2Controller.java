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
    private SceneController parent;
    
    
    @FXML   private Pane pane2;
    @FXML   private TextField newName;
    @FXML   private TextField newGenre;
    @FXML   private TextField newPublisher;
    @FXML   private Button confirmNew;
    @FXML   private TextField newPlatform;
    @FXML   private Button exit;
    
    private game game;
    
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
            
            System.out.println("Loading scene with the items from the database...");
            @Suppresswarnings("unchecked")
            List<Category> targetList = categoryChoiceBox.getItems();
            Category.readAll(targetList);
            categoryChoiceBox.getSeletionModel().selectFirst();
            
        }
        
        public void setParent(SceneController parent){
            this.parent = parent;
            
        }
        
        public void loadItem(int id)
        {
            game = game.getById(id);
            nameTextField.setText(game.name);
            
            List<Category> targetList = categoryChoiceBox.getItems();
            for(category c : targetList){
                if (c.id == game.categoryId){
                    categoryChoiceBox.getSelectionModel().select(c);
                }
            }
        }
        
        @FXML   void saveButtonClicked(){
            System.out.println("Save button was clicked");
            
            if (game == null){
                game = new game(0, "", 0);
                       
            }
            
            game.name = nameTextField.getText();
            
            Category selectedCategory = (category) categoryChoiceBox.getSelectionModel().getSelectedItem();
            game.categoryId = selectedCategory.id;
            
            game.save();
            
            parent.initialize();
            
            stage.close();
        }
        
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
    
    @FXML   void extClicked(){
        Application.terminate();
    }
    
            

}

package Project;

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

          List<Publisher> targetList = .getItems();
          Publisher.readAll(targetList);
          categoryChoiceBox.getSeletionModel().selectFirst();
            
        }
        
        public void setParent(SceneController parent){
            this.parent = parent;
            
        }
        
        public void loadItem(int GameId)
        {
           game = game.getByGameId(GameId);
            newName.setText(game.Name);
           
           List<Publisher> targetList = newPublisher.getItems();
           for(category c : targetList){
               if (c.id == game.categoryId){
                   categoryChoiceBox.getSelectionModel().select(c);
               }
            }
       }
        
        @FXML   void saveButtonClicked(){
            System.out.println("Save button was clicked");
            
            if (game == null){
                game = new games("", 0, 0, 0, 0, 0);
                       
            }
            
            game.Name = newName.getText();
            
            Publisher selectedPublish = (category) categoryChoiceBox.getSelectionModel().getSelectedItem();
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

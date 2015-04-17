package game;


import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import view.gameController;
import view.menuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class SISEGame extends Application {
	private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane pane;
    private Player bluePlayer;
    private Player redPlayer;
    private static final int GAME_FIELD_WIDTH_HEIGHT_SIZE = 5;
	private static final int INITIAL_SOLDIERS_QUANTITY = 50;
	
	private ArrayList<FieldUnit> gameField;
	@Override
	public void start(Stage primaryStage) {

		bluePlayer = new Player(null, null, PlayerType.PlayerA);
		redPlayer = new Player(null, null, PlayerType.PlayerB);
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SISEGame");
		
		initRoot();
		showMenu();
	}
    public SISEGame(){}

    public Stage getPrimaryStage() {
        return primaryStage;
    }
 
    public void initRoot(){
    	try{
			FXMLLoader load = new FXMLLoader(SISEGame.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) load.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(IOException e){
			e.printStackTrace();
		}
    }

    public void showMenu() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader load = new FXMLLoader(SISEGame.class.getResource("../view/MenuPanel.fxml"));
            AnchorPane menuPanel = (AnchorPane) load.load();
            rootLayout.setCenter(menuPanel);
            
            menuController controller = load.getController();
            controller.setSISEMenu(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showGame(){
    	try{
    		FXMLLoader load = new FXMLLoader(SISEGame.class.getResource("../view/GameContent.fxml"));
    		AnchorPane gamePanel = (AnchorPane) load.load();
    		rootLayout.setCenter(gamePanel);
    		
    		gameController gc = new gameController();
    		gc.setSISEGame(this);
    	} catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
	public static void main(String[] args) {
		launch(args);
		//Base base = new Base(null, 0, null, null);
	}
}
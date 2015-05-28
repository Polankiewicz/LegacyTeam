package game;


import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import FuzzyLogic.FuzzyLogicControl;
import view.gameController;
import view.menuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class SISEGame extends Application {
	
	private static final int GAME_FIELD_WIDTH_HEIGHT_SIZE = 4;
	private static final int INITIAL_SOLDIERS_QUANTITY = 50;
	private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane pane;
    private Player bluePlayer;
    private Player redPlayer;
    private Player actualPlayer;
    public FuzzyLogicControl fuzzyLogicControlForBluePlayer;
    public FuzzyLogicControl fuzzyLogicControlForRedPlayer;
   
    boolean autoPlay; // jesli prawda, to gra testController
	private MoveDataStructure moveDataStructure;
	private ArrayList<FieldUnit> gameField;
	private gameController gc;
	private TestController testController;
	private int turn;
	
	@Override
	public void start(Stage primaryStage) 
	{
		this.gameField = new ArrayList<FieldUnit>();
		createGameField();
		//tutaj zestaw fcl-ek niebieskiego gracza
		this.fuzzyLogicControlForBluePlayer = new FuzzyLogicControl(
				"src/FuzzyLogic/fightChances.fcl", 
				"src/FuzzyLogic/fieldsControled.fcl", 
				"src/FuzzyLogic/unitsPerField.fcl", 
				"src/FuzzyLogic/unitsRatioToBase.fcl");
		//tutaj zestaw fcl-ek czerwonego gracza
		this.fuzzyLogicControlForRedPlayer = new FuzzyLogicControl(
				"src/FuzzyLogic/fightChances.fcl", 
				"src/FuzzyLogic/fieldsControled.fcl", 
				"src/FuzzyLogic/unitsPerField.fcl", 
				"src/FuzzyLogic/unitsRatioToBase.fcl");
		
		moveDataStructure = new MoveDataStructure();
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HEXABATTLE");
		actualPlayer = bluePlayer;
		turn = 1;
		autoPlay = true;
		initRoot();
		showMenu();
		testController = new TestController(gameField, bluePlayer, redPlayer,actualPlayer, moveDataStructure,this);
	}

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    private void createGameField() 
	{
    	
		for (int i = 0; i <= GAME_FIELD_WIDTH_HEIGHT_SIZE; i++) 
		{
			for (int j = 0; j <= GAME_FIELD_WIDTH_HEIGHT_SIZE; j++) 
			{
				if (i == 0 && j == 0) 
				{
					Base baseA = new Base(new Point(i, j), INITIAL_SOLDIERS_QUANTITY , PlayerType.PlayerA, BonusType.NONE);
					this.gameField.add(baseA);
					this.bluePlayer = new Player(baseA,gameField,PlayerType.PlayerA,"Gracz Niebieski","#523bff");
				}
				else if (i == GAME_FIELD_WIDTH_HEIGHT_SIZE && j == GAME_FIELD_WIDTH_HEIGHT_SIZE) 
				{
					Base baseB = new Base(new Point(i, j), INITIAL_SOLDIERS_QUANTITY , PlayerType.PlayerB, BonusType.NONE);
					this.gameField.add(baseB);
					//this.gameField.add(new Base(new Point(i, j), INITIAL_SOLDIERS_QUANTITY , PlayerType.PlayerB, BonusType.NONE));
					this.redPlayer = new Player(baseB,gameField,PlayerType.PlayerB,"Gracz Czerwony", "#f84f45");
				}
				else 
					this.gameField.add(new FieldUnit(new Point(i, j), 0, PlayerType.NoOne, BonusType.NONE));
			}
		}
	}
   

    //je�eli zamek pierwszy zosta� zaj�ty przez wroga - wygrywa gracz 2

  	//w przeciwnym razie wygrywa gracz 1
  	public boolean checkWhoWins()
  	{
  		if(gameField.get(0).getSoldiersType() != PlayerType.PlayerA)
  			return true;
  		return false;
  	}
  	
	public static void main(String[] args) 
	{
		launch(args);
		//Base base = new Base(null, 0, null, null);
	}
	
	public boolean isGameFinished()
	{
		PlayerType firstBaseOwner = gameField.get(0).getSoldiersType();
		PlayerType secondBaseOwner = gameField.get(gameField.size()-1).getSoldiersType();
		if(firstBaseOwner == secondBaseOwner)
			return true;
		else
			return false;
	}
	
	public void updatePlayer()
	{
		actualPlayer.increaseUnitsAmount();
		if(actualPlayer.getPlayerType()==PlayerType.PlayerA)
		{
			System.out.println("gracz a zmienia sie na gracza b");
			actualPlayer=redPlayer;
		}
		else if(actualPlayer.getPlayerType()==PlayerType.PlayerB) 
		{
			System.out.println("gracz b zmienia sie na gracza a");
			actualPlayer=bluePlayer;
		}
	}
	
	public void makeMove()
	{
		//tutaj chyba trzeba bedzie załadować w podobny sposób reszte fcl-ek ale juz nie ogarniam co się skad bierze,
		//takze Polan rob według uznania :D
		double fuzzyFieldControlled;
		if(actualPlayer.getPlayerType() == PlayerType.PlayerA){
			fuzzyFieldControlled = fuzzyLogicControlForBluePlayer.getFuzzyFieldsControled(bluePlayer.getControlledFields());
		}
		else{
			fuzzyFieldControlled = fuzzyLogicControlForRedPlayer.getFuzzyFieldsControled(redPlayer.getControlledFields());
		}
		
		if(actualPlayer.move(moveDataStructure.howMany,moveDataStructure.targetIndex,moveDataStructure.sourceIndex))
    	{
    		System.out.println(moveDataStructure.sourceIndex + " sie ruszyl na " + moveDataStructure.targetIndex);
    		actualPlayer.increaseUnitsAmount();
    		updatePlayer();
    		turn++;
    		gc.refreshView(actualPlayer,turn);
    	}	
	 }

    public void initRoot()
    {
    	try {

			FXMLLoader load = new FXMLLoader(SISEGame.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) load.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(IOException e) {
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
   
    public void showGame()
    {
    	try {
    		gc = new gameController(gameField, bluePlayer, redPlayer,actualPlayer, moveDataStructure,this);
    		
    		FXMLLoader load = new FXMLLoader(SISEGame.class.getResource("../view/GameContent.fxml"));
    		load.setController(gc);
    		AnchorPane gamePanel = (AnchorPane) load.load();
    		rootLayout.setCenter(gamePanel);
    		if(autoPlay) 
    			testController.gameMainLoop(); //wywolanie gracza testowego
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    
}
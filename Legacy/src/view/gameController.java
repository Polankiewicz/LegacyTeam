package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.Button;
import java.awt.Point;

import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxResult;
import se.mbaeumer.fxmessagebox.MessageBoxType;
import model.hexModel;

public class gameController {
	//Hexy
	@FXML
	Rectangle hex0_0;
	@FXML
	Rectangle hex0_1;
	@FXML
	Rectangle hex0_2;
	@FXML
	Rectangle hex0_3;
	@FXML
	Rectangle hex0_4;
	@FXML
	Rectangle hex1_0;
	@FXML
	Rectangle hex1_1;
	@FXML
	Rectangle hex1_2;
	@FXML
	Rectangle hex1_3;
	@FXML
	Rectangle hex1_4;
	@FXML
	Rectangle hex2_0;
	@FXML
	Rectangle hex2_1;
	@FXML
	Rectangle hex2_2;
	@FXML
	Rectangle hex2_3;
	@FXML
	Rectangle hex2_4;
	@FXML
	Rectangle hex3_0;
	@FXML
	Rectangle hex3_1;
	@FXML
	Rectangle hex3_2;
	@FXML
	Rectangle hex3_3;
	@FXML
	Rectangle hex3_4;
	@FXML
	Rectangle hex4_0;
	@FXML
	Rectangle hex4_1;
	@FXML
	Rectangle hex4_2;
	@FXML
	Rectangle hex4_3;
	@FXML
	Rectangle hex4_4;
	
	@FXML
	Label hexLabel0_0;
	@FXML
	Label hexLabel0_1;
	@FXML
	Label hexLabel0_2;
	@FXML
	Label hexLabel0_3;
	@FXML
	Label hexLabel0_4;
	@FXML
	Label hexLabel1_0;
	@FXML
	Label hexLabel1_1;
	@FXML
	Label hexLabel1_2;
	@FXML
	Label hexLabel1_3;
	@FXML
	Label hexLabel1_4;
	@FXML
	Label hexLabel2_0;
	@FXML
	Label hexLabel2_1;
	@FXML
	Label hexLabel2_2;
	@FXML
	Label hexLabel2_3;
	@FXML
	Label hexLabel2_4;
	@FXML
	Label hexLabel3_0;
	@FXML
	Label hexLabel3_1;
	@FXML
	Label hexLabel3_2;
	@FXML
	Label hexLabel3_3;
	@FXML
	Label hexLabel3_4;
	@FXML
	Label hexLabel4_0;
	@FXML
	Label hexLabel4_1;
	@FXML
	Label hexLabel4_2;
	@FXML
	Label hexLabel4_3;
	@FXML
	Label hexLabel4_4;
	
	@FXML
	Label finishRoundLabel;
	
	@FXML
	AnchorPane bottomPane;
	
	Image hexHover;
	Image hexDefault;
	Stage stage;
	SISEGame game;
	int turnNumber = 1;
	int redTroops = 20;
	int blueTroops = 20;
	
	@FXML
	private Label playerID = new Label();
	@FXML
	private Label turnID = new Label();
	@FXML
	private Label troopsSize = new Label();
	private static final int GAME_FIELD_WIDTH_HEIGHT_SIZE = 4;
	private static final int INITIAL_SOLDIERS_QUANTITY = 50;
	private Player playerA;
	private Player playerB;
	private ArrayList<FieldUnit> gameField;
	
	private Player actualPlayer;
	private int sourceIndex,targetIndex;
	private boolean isTargetSelected;
	
	//Je�eli tura jest true - kolejka nale�y do gracza 1
	//Je�eli false - do przeciwnika
	private boolean turn;
	
	
	List<hexModel> hexModelArray;
	
	private boolean isSourceSelected;
	
	public gameController(){
		
		
		this.gameField = new ArrayList<FieldUnit>();
		this.playerA = new Player(null,gameField,PlayerType.PlayerA);
		this.playerB = new Player(null,gameField,PlayerType.PlayerB);
		this.createGameField();
	}

	public void setSISEGame(SISEGame game){
		this.game = game;
	}

	@FXML
	private void initialize() {
		turn = true;
		updateTurn();
		updateTroops();
		playerID.setText("Gracz niebieski");
		playerID.setTextFill(Color.web("#523bff"));
		
		sourceIndex=1;
		targetIndex=0;
		isTargetSelected=false;
		actualPlayer = playerA;
		hexModelArray = new ArrayList<hexModel>();
		try{
			for(int i=0; i<5; i++){
				for(int j=0; j<5; j++){
					String hexString = "hex"+(i)+"_"+(j);
					String hexLabelString = "hexLabel"+(i)+"_"+(j);
					Object hexInstance = getClass().getDeclaredField(hexString).get(this);
					Object hexLabelInstance = getClass().getDeclaredField(hexLabelString).get(this);
					hexModel hexModel = new hexModel(i,j,(i+j),false, (Rectangle)hexInstance, (Label)hexLabelInstance);
					hexModelArray.add(hexModel);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}		
		
		for(int i=0; i<hexModelArray.size(); i++){
				if(gameField.get(i).getSoldiersType() == PlayerType.PlayerA)
			{
				switchColor(hexModelArray.get(i).getHex(), "#523bff");
			}
			else if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB)
			{
				switchColor(hexModelArray.get(i).getHex(), "#f84f45");
			}
			else 
				switchColor(hexModelArray.get(i).getHex(), "0xffffff00");
			String soldiersOnUnitCount = Integer.toString(gameField.get(i).getSoldiers());
			
				selectArmy(hexModelArray.get(i).getHexLabel(), "000000", soldiersOnUnitCount);

		}

			
		for(hexModel hM : hexModelArray){
			hM.setEnemy(true);
		}
		
		
		
	}
	

	
	private void createGameField() 
	{
			for (int i = 0; i <= GAME_FIELD_WIDTH_HEIGHT_SIZE; i++) {
				for (int j = 0; j <= GAME_FIELD_WIDTH_HEIGHT_SIZE; j++) {
					if (i == 0 && j == 0) {
						this.gameField.add(new Base(new Point(i, j), INITIAL_SOLDIERS_QUANTITY , PlayerType.PlayerA, BonusType.NONE));
					}
					else if (i == GAME_FIELD_WIDTH_HEIGHT_SIZE && j == GAME_FIELD_WIDTH_HEIGHT_SIZE) {
						this.gameField.add(new Base(new Point(i, j), INITIAL_SOLDIERS_QUANTITY , PlayerType.PlayerB, BonusType.NONE));
					}
					else {
						this.gameField.add(new FieldUnit(new Point(i, j), 0, PlayerType.NoOne, BonusType.NONE));
					}
				}
			}
		}
		
	
	private void updatePlayer()
	{
//		if(playerID.getText() == "Gracz czerwony")
//		{
//		playerID.setText("Gracz niebieski");
//		playerID.setTextFill(Color.web("#523bff"));
//		}
//		else
//		{
//			playerID.setText("Gracz czerwony");
//			playerID.setTextFill(Color.web("#f84f45"));
//		}
		if(actualPlayer.getPlayerType()==PlayerType.PlayerA)
			{
			System.out.println("gracz a zmienia sie na gracza b");
			actualPlayer=playerB;
			playerID.setText("Gracz czerwony");
			playerID.setTextFill(Color.web("#f84f45"));
			
			}
		else if(actualPlayer.getPlayerType()==PlayerType.PlayerB) {
			System.out.println("gracz b zmienia sie na gracza a");
			actualPlayer=playerA;
			playerID.setText("Gracz niebieski");
			playerID.setTextFill(Color.web("#523bff"));
		}
		
		
	}
	
	private void updateTurn()
	{
		turnID.setText("#" + turnNumber);
		turnNumber++;
		turn = !turn;
	}
	void actualizeHexLabels()
	{
		for(int i=0;i<hexModelArray.size();i++)
		{
			
			String soldiersOnUnitCount = Integer.toString(gameField.get(i).getSoldiers());
			
			selectArmy(hexModelArray.get(i).getHexLabel(), "000000", soldiersOnUnitCount);
			
		}
	}
	private void updateTroops()
	{
		troopsSize.setText(redTroops + " jednostek");
	}
	private void move(int armyCount, int targetIndex, int index)
	{
		//sprawdzamy, czy ruch chce sie wykonac z pola nalezacego do aktualnego gracza
		if(actualPlayer.getPlayerType()==gameField.get(index).getSoldiersType()) 
        {
			
			actualPlayer.move(armyCount,hexModelArray.get(targetIndex).getPoint(),hexModelArray.get(index).getPoint());
       
			actualizeHexLabels();
			updatePlayer(); //zmiana tury
        }
	}
	private void showContextMenu(int index, int targetIndex)
	{
		Rectangle singleHex = hexModelArray.get(index).getHex();
		final ContextMenu contextMenu = new ContextMenu();
		MenuItem option1 = new MenuItem("Rusz wszystkie jednostki z ��tego na zielone");
		MenuItem option2 = new MenuItem("Wybierz kilka jednostek z pola");
		MenuItem option3 = new MenuItem("Kapituluj");
		
		contextMenu.getItems().addAll(option1, option2, option3);
		
		option1.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        System.out.println("moveAll");
		       	int armyCount = gameField.get(index).getSoldiers();
		     
		       	
		        move(armyCount,targetIndex,index);
		    	
		    }
		});
		
		option2.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        System.out.println("opt2...");
		        
		        
		    }
		});
		
		option3.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        System.out.println("opt3...");
		    }
		});
		
		singleHex.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isSecondaryButtonDown()) {
		            contextMenu.show(singleHex, event.getScreenX(), event.getScreenY());
		        }
		    }
		});
	}
	
	//Akcja
	@SuppressWarnings("deprecation")
	public void clickHex(MouseEvent event){
		String text = null; 
		
		for(int i=0; i<hexModelArray.size(); i++){
			
			
			
			//ustawianie �r�d�a i celu
			if(hexModelArray.get(i).getHex().isHover() && event.getButton() == MouseButton.SECONDARY){
				if(this.isSourceSelected==true)
				{
					if(playerA.isFieldNeighbour(sourceIndex, i))
					{
						switchColor(hexModelArray.get(this.targetIndex).getHex(), "ffffff");
						this.targetIndex=i;
						showContextMenu(sourceIndex,targetIndex);
					}
					else
					{
						// <-- DIALOG BOX informuj�cy gracza o niemo�liwym do wykonania ruchu -->
						MessageBox mb = new MessageBox("Mo�liwy jest tylko wyb�r s�siaduj�cego pola", MessageBoxType.OK_ONLY);
						mb.showAndWait();
						if (mb.getMessageBoxResult() == MessageBoxResult.OK)
						{
							System.out.println("OK");
						}

					}
					
				}
				else
				{
//					System.out.println("zrodlo wybrane " + hexRepresentation.get(i).getId());
//					
//					//switchColor(hexRepresentation.get(sourceIndex), "0xffffff00");
//					sourceIndex=i;
//					isSourceSelected = true;
				
					
				}
				
			}
			
			else if(hexModelArray.get(i).getHex().isHover() && event.getButton() == MouseButton.PRIMARY){
				System.out.println(hexModelArray.get(i).getHex().getId());
				switchColor(hexModelArray.get(this.sourceIndex).getHex(), "ffffff");
				this.sourceIndex=i;
				isSourceSelected = true;
				
			}
			
			
			//koloryzacja osobnym ifem
			
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerA)
			{
				switchColor(hexModelArray.get(i).getHex(), "#523bff");
			}
			else if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB)
			{
				switchColor(hexModelArray.get(i).getHex(), "#f84f45");
			}
			else if(i==targetIndex)
			{
				switchColor(hexModelArray.get(i).getHex(), "00ff00");
			}
			else if(i==sourceIndex)
			{
				switchColor(hexModelArray.get(i).getHex(), "fff000");
			}
			else
			{
				switchColor(hexModelArray.get(i).getHex(), "0xffffff00");
			}
			
			
			
			
		}
	}
	
	public ArrayList<FieldUnit> getGameField() {
		return new ArrayList<FieldUnit>(gameField);
	}

	public static int getGameFieldWidthHeightSize() {
		return GAME_FIELD_WIDTH_HEIGHT_SIZE;
	}
	public void switchColor(Rectangle hex, String color){
		
			hex.fillProperty().set(Color.web(color));
		
	}
	
	//kolory to czerwony i niebieski
	//"0xff0000" oraz "0x0000ff"
	public void selectArmy(Label label, String color, String count){
		label.setText(count);
		label.setTextFill(Paint.valueOf(color));
	}
	
	//Do uzupe�nienie growej logiki
	public void calculateRound(){
		if(isGameFinished()){
			if(checkWhoWins())
				finishRoundLabel.setText("Wygra� gracz 2");
			else
				finishRoundLabel.setText("Wygra� gracz 1");
		}
		else{
			updateTurn(); // Inkrementacja licznika kolejek, je�eli gra dalej trwa, oraz potwierdzono zako�czenie ruchu
			updatePlayer(); // Zmiana prezentacji etykiety Gracza, po zako�czeniu tury, je�eli gra dalej trwa
			
			if(turn)
				finishRoundLabel.setText("Gracz 1 - zako�cz rund�");
			else
				finishRoundLabel.setText("Gracz 2 - zako�cz rund�");
		}
	}
	
	//je�eli zamek pierwszy zosta� zaj�ty przez wroga - wygrywa gracz 2
	//w przeciwnym razie wygrywa gracz 1
	public boolean checkWhoWins(){
		if(!hexModelArray.get(0).isEnemy())
			return true;
		return false;
	}
	
	//Je�eli nie ma na li�cie pola, kt�re jest przeciwnikiem gra si� ko�czy
	public boolean isGameFinished(){
		return false;
	}
	
//Chyba, �e gramy tylko do zdobycia zamku, to zakomentowa� powy�sz� funkcj� i odkomentowa� ni�ej
	
//	public boolean isGameFinished(){
//		if(hexModelArray.get(0).isEnemy())
//			return true;
//		else if(!hexModelArray.get(hexModelArray.size()-1).isEnemy())
//			return true;
//		return false;
//	}
}

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
import javafx.scene.control.Slider;
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
	
	@FXML
	Slider troopsSlider;
	
	@FXML
	Label amountOfTroopsL;
	
	Image hexHover;
	Image hexDefault;
	Stage stage;
	SISEGame game;
	int turnNumber = 1;
	int redTroops = 20;
	int blueTroops = 20;
	private int armyCount;
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
	private MoveDataStructure moveDataStructure;
	private Player actualPlayer;
	private boolean isTargetSelected;
	
	//Je�eli tura jest true - kolejka nale�y do gracza 1
	//Je�eli false - do przeciwnika
	private boolean turn;
	
	//liczba woja do przesuni�cia ze slidera
	private int armyToMove;
	
	List<hexModel> hexModelArray;
	
	private boolean isSourceSelected;
	
	public gameController(ArrayList<FieldUnit> gameField, Player playerA, 
		Player playerB, Player actualPlayer,MoveDataStructure moveDataStructure,
		SISEGame game)
	{
		this.gameField = gameField;
		this.playerA = playerA;
		this.playerB = playerB;
		this.moveDataStructure = moveDataStructure;
		this.actualPlayer = actualPlayer;
		this.game = game;
	}

	@FXML
	private void initialize() 
	{
		playerID.setText("Gracz niebieski");
		playerID.setTextFill(Color.web("#523bff"));
		finishRoundLabel.setText("Gracz Niebieski - kliknij, by zako�czy� tur�");
		turnID.setText("#" + 1);
		troopsSize.setText("Kupa jednostek");
		isTargetSelected=false;
		
		hexModelArray = new ArrayList<hexModel>();
		try {
			for(int i=0; i<5; i++)
			{
				for(int j=0; j<5; j++)
				{
					String hexString = "hex"+(i)+"_"+(j);
					String hexLabelString = "hexLabel"+(i)+"_"+(j);
					Object hexInstance = getClass().getDeclaredField(hexString).get(this);
					Object hexLabelInstance = getClass().getDeclaredField(hexLabelString).get(this);
					hexModel hexModel = new hexModel(i,j,(i+j),false, (Rectangle)hexInstance, (Label)hexLabelInstance);
					hexModelArray.add(hexModel);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		
		for(int i=0; i<hexModelArray.size(); i++)
		{
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerA)
				switchColor(hexModelArray.get(i).getHex(),  playerA.getPlayerColorString());
			else if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB)
				switchColor(hexModelArray.get(i).getHex(), playerB.getPlayerColorString());
			else 
				switchColor(hexModelArray.get(i).getHex(), "0xffffff00");
			
			String soldiersOnUnitCount = Integer.toString(gameField.get(i).getSoldiers());
			selectArmy(hexModelArray.get(i).getHexLabel(), "000000", soldiersOnUnitCount);
		}

		for(hexModel hM : hexModelArray)
			hM.setEnemy(true);
		
		troopsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("Ilo�� armii do przesuni�cia: "+((gameField.get(moveDataStructure.sourceIndex).getSoldiers()*newValue.intValue())/100));
		    armyToMove = (gameField.get(moveDataStructure.sourceIndex).getSoldiers()*newValue.intValue())/100;
		    amountOfTroopsL.setText(String.valueOf(armyToMove));
		    moveDataStructure.howMany=armyToMove;
		});	
	}
	
	public void refreshView(Player actualPlayer, int turn)
	{
		System.out.println("GC refresh: " + actualPlayer.getPlayerNameString() + actualPlayer.getPlayerColorString());
		playerID.setText(actualPlayer.getPlayerNameString());
		playerID.setTextFill(Color.web(actualPlayer.getPlayerColorString()));
		turnID.setText("#" + turn);
		finishRoundLabel.setText(actualPlayer.getPlayerNameString() + " - kliknij, by zako�czy� tur�");
		updateHexLabels();
		updateHexColors();
	}
	
	public void updateHexColors()
	{
		for(int i=0; i<gameField.size(); i++)
		{
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerA)
				switchColor(hexModelArray.get(i).getHex(), "#523bff");
			else if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB)
				switchColor(hexModelArray.get(i).getHex(), "#f84f45");
			else if(i==moveDataStructure.targetIndex)
				switchColor(hexModelArray.get(i).getHex(), "00ff00");
			else if(i==moveDataStructure.sourceIndex)
				switchColor(hexModelArray.get(i).getHex(), "fff000");
			else
				switchColor(hexModelArray.get(i).getHex(), "0xffffff00");
		}
	}
	
	void updateHexLabels()
	{
		for(int i=0;i<hexModelArray.size();i++)
		{
			String soldiersOnUnitCount = Integer.toString(gameField.get(i).getSoldiers());
			
			selectArmy(hexModelArray.get(i).getHexLabel(), "000000", soldiersOnUnitCount);
		}
	}
	
	//Akcja
	@SuppressWarnings("deprecation")
	public void clickHex(MouseEvent event)
	{
		String text = null; 
		

		for(int i=0; i<hexModelArray.size(); i++)
		{
			//ustawianie �r�d�a i celu
			if(hexModelArray.get(i).getHex().isHover() && event.getButton() == MouseButton.SECONDARY)
			{

				if(this.isSourceSelected==true)
				{
					if(playerA.isFieldNeighbour(moveDataStructure.sourceIndex, i))
						moveDataStructure.targetIndex=i;
					else
					{
						// <-- DIALOG BOX informuj�cy gracza o niemo�liwym do wykonania ruchu -->
						MessageBox mb = new MessageBox("Mo�liwy jest tylko wyb�r s�siaduj�cego pola", MessageBoxType.OK_ONLY);
						mb.showAndWait();
						if (mb.getMessageBoxResult() == MessageBoxResult.OK)
							System.out.println("OK");
					}
				}
			}
			
			if(hexModelArray.get(i).getHex().isHover() && event.getButton() == MouseButton.PRIMARY)
			{
				System.out.println(hexModelArray.get(i).getHex().getId());
			
				moveDataStructure.sourceIndex=i;
				
				troopsSlider.setValue(50);
				isSourceSelected = true;
			}
			
			updateHexColors();
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
	public void selectArmy(Label label, String color, String count)
	{
		label.setText(count);
		label.setTextFill(Paint.valueOf(color));
	}
	
	//Do uzupe�nienie growej logiki
	public void calculateRound(){
		if(game.isGameFinished()){
			if(game.checkWhoWins())
				finishRoundLabel.setText("Wygra� gracz 2");
			// <-- DIALOG BOX informuj�cy o zwyciestwie gracza 2 -->
			MessageBox mb = new MessageBox("Hexabattle zdominowa� gracz nr 2 (Czerwony)!", MessageBoxType.OK_ONLY);
			mb.showAndWait();
			if (mb.getMessageBoxResult() == MessageBoxResult.OK)
			{
				System.out.println("Winner: 2");
			}
			else
				finishRoundLabel.setText("Wygra� gracz 1");
			// <-- DIALOG BOX informuj�cy o zwyciestwie gracza 2 -->
			MessageBox mb2 = new MessageBox("Hexabattle zdominowa� gracz nr 1 (Niebieski)!", MessageBoxType.OK_ONLY);
			mb2.showAndWait();
			if (mb2.getMessageBoxResult() == MessageBoxResult.OK)
			{
				System.out.println("Winner: 1");
			}
		}
		else
			game.makeMove();
	}
	
	public void arrangeArmy(){
	//to juz nie jest uzywane, ale nie moge usunac, bo fxml wymaga #KubaUsu�To
		
	}
	
}

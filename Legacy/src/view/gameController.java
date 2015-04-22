package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.Point;

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
	
	//Czêœæ clickable danego hexa
	List<Rectangle> hexRepresentation;
	
	//Co dane pole zawiera
	List values ;
	/*
	 * 0 - nieklikniête
	 * 1 - klikniête
	 * 2 - zamek do zaatakowania
	*/
	
	//Jaki gracz jest na danym polu
	List players;
	/*
	 * True - gracz
	 * False - przeciwnik
	 * */
	
	//Liczebnoœæ woja na danym polu
	List<Label> armyCount;
	
	//NOWE, DO DALSZEGO OBROBIENIA
	List<hexModel> bindingHexModel;
	
	public gameController(){
		
		this.playerA = new Player(null,null,PlayerType.PlayerA);
		this.playerB = new Player(null,null,PlayerType.PlayerB);
		this.gameField = new ArrayList<FieldUnit>();
		this.createGameField();
	}

	public void setSISEGame(SISEGame game){
		this.game = game;
	}

	@FXML
	private void initialize() {
		updateTurn();
		updateTroops();
		playerID.setText("Gracz niebieski");
		playerID.setTextFill(Color.web("#523bff"));
		
		hexRepresentation = new ArrayList();
		hexRepresentation.add(hex0_0);
		hexRepresentation.add(hex0_1);
		hexRepresentation.add(hex0_2);
		hexRepresentation.add(hex0_3);
		hexRepresentation.add(hex0_4);
		hexRepresentation.add(hex1_0);
		hexRepresentation.add(hex1_1);
		hexRepresentation.add(hex1_2);
		hexRepresentation.add(hex1_3);
		hexRepresentation.add(hex1_4);
		hexRepresentation.add(hex2_0);
		hexRepresentation.add(hex2_1);
		hexRepresentation.add(hex2_2);
		hexRepresentation.add(hex2_3);
		hexRepresentation.add(hex2_4);
		hexRepresentation.add(hex3_0);
		hexRepresentation.add(hex3_1);
		hexRepresentation.add(hex3_2);
		hexRepresentation.add(hex3_3);
		hexRepresentation.add(hex3_4);
		hexRepresentation.add(hex4_0);
		hexRepresentation.add(hex4_1);
		hexRepresentation.add(hex4_2);
		hexRepresentation.add(hex4_3);
		hexRepresentation.add(hex4_4);
		
		armyCount = new ArrayList();
		armyCount.add(hexLabel0_0);
		armyCount.add(hexLabel0_1);
		armyCount.add(hexLabel0_2);
		armyCount.add(hexLabel0_3);
		armyCount.add(hexLabel0_4);
		armyCount.add(hexLabel1_0);
		armyCount.add(hexLabel1_1);
		armyCount.add(hexLabel1_2);
		armyCount.add(hexLabel1_3);
		armyCount.add(hexLabel1_4);
		armyCount.add(hexLabel2_0);
		armyCount.add(hexLabel2_1);
		armyCount.add(hexLabel2_2);
		armyCount.add(hexLabel2_3);
		armyCount.add(hexLabel2_4);
		armyCount.add(hexLabel3_0);
		armyCount.add(hexLabel3_1);
		armyCount.add(hexLabel3_2);
		armyCount.add(hexLabel3_3);
		armyCount.add(hexLabel3_4);
		armyCount.add(hexLabel4_0);
		armyCount.add(hexLabel4_1);
		armyCount.add(hexLabel4_2);
		armyCount.add(hexLabel4_3);
		armyCount.add(hexLabel4_4);
		
		//NOWE, DO DALSZEGO OBROBIENIA, PRZYK£AD DODAWANIA
		//PRZYK£AD
		//teraz resztê trzeba tak pododawaæ, najlepiej w magicznej pêtli robi¹cej 
		//ze stringa obiekt :v
		bindingHexModel = new ArrayList<hexModel>();
		bindingHexModel.add(new hexModel(0,0,1,true,hex0_0, hexLabel0_0));
		
		for(int i=0; i<armyCount.size(); i++){
			armyCount.get(i).setText("");
		}
		
		for(int i=0; i<hexRepresentation.size(); i++){
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerA)
			{
				switchColor(hexRepresentation.get(i), "#523bff");
			}
			else if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB)
			{
				switchColor(hexRepresentation.get(i), "#f84f45");
			}
			else 
				switchColor(hexRepresentation.get(i), "ffffff");
			String soldiersOnUnitCount = Integer.toString(gameField.get(i).getSoldiers());
			
				selectArmy(armyCount.get(i), "000000", soldiersOnUnitCount);

		}
		//zerowanie listy, bo na razie ¿adne pole nie jest zaznaczone
		values = new ArrayList();
		for(int i=0; i<hexRepresentation.size(); i++){
			values.add(0);
		}
		
		Random randomGenerator = new Random();
		//Wygenerowaæ jakoœ rozmieszczenie graczy
		players = new ArrayList();
		for(int i=0; i<hexRepresentation.size(); i++){
			//Generowanie true/false
			players.add(randomGenerator.nextInt()%2);
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
		if(playerID.getText() == "Gracz czerwony")
		{
		playerID.setText("Gracz niebieski");
		playerID.setTextFill(Color.web("#523bff"));
		}
		else
		{
			playerID.setText("Gracz czerwony");
			playerID.setTextFill(Color.web("#f84f45"));
		}
	}
	
	private void updateTurn()
	{
		turnID.setText("#" + turnNumber);
		turnNumber++;
	}
	
	private void updateTroops()
	{
		troopsSize.setText(redTroops + " jednostek");
	}
	
	//Akcja
	public void clickHex(MouseEvent event){
		String text = null; 
		for(int i=0; i<hexRepresentation.size(); i++){
			if(hexRepresentation.get(i).isHover() && event.getButton() == MouseButton.SECONDARY){
				System.out.println(hexRepresentation.get(i).getId());
				switchColor(hexRepresentation.get(i), "00ff00");

				//selectArmy(army.get(i), "0xff0000", "150");
			}
			else if(hexRepresentation.get(i).isHover() && event.getButton() == MouseButton.PRIMARY){
				System.out.println(hexRepresentation.get(i).getId());
				switchColor(hexRepresentation.get(i), "fff000");
			}
			else
			{
				switchColor(hexRepresentation.get(i), "0xFFFFFF");
			}
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerA)
			{
				switchColor(hexRepresentation.get(i), "#523bff");
			}
			else if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB)
			{
				switchColor(hexRepresentation.get(i), "#f84f45");
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
	//"0xff0000" oraz "0x00ff00"
	public void selectArmy(Label label, String color, String count){
		label.setText(count);
		label.setTextFill(Paint.valueOf(color));
	}
	
	//Do uzupe³nienie growej logiki
	public void calculateRound(){
		if(isGameFinished()){
			
		}
		else{
			updateTurn(); // Inkrementacja licznika kolejek, je¿eli gra dalej trwa, oraz potwierdzono zakoñczenie ruchu
			updatePlayer(); // Zmiana prezentacji etykiety Gracza, po zakoñczeniu tury, je¿eli gra dalej trwa
			
			if(players.contains(true))
				finishRoundLabel.setText("Wygra³ gracz 1");
			else
				finishRoundLabel.setText("Wygra³ gracz 2");
		}
	}
	
	//Je¿eli nie ma na liœcie pola nale¿¹cego do gracza, to game over.
	public boolean isGameFinished(){
		if(players.contains(true) && players.contains(false))
			return true;
		else
			return false;
	}
}

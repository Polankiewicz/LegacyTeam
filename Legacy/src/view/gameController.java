package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.Point;

public class gameController {
	//Hexy
	@FXML
	Rectangle hex1;
	@FXML
	Rectangle hex2;
	@FXML
	Rectangle hex3;
	@FXML
	Rectangle hex4;
	@FXML
	Rectangle hex5;
	@FXML
	Rectangle hex6;
	@FXML
	Rectangle hex7;
	@FXML
	Rectangle hex8;
	@FXML
	Rectangle hex9;
	@FXML
	Rectangle hex10;
	@FXML
	Rectangle hex11;
	@FXML
	Rectangle hex12;
	@FXML
	Rectangle hex13;
	@FXML
	Rectangle hex14;
	@FXML
	Rectangle hex15;
	@FXML
	Rectangle hex16;
	@FXML
	Rectangle hex17;
	@FXML
	Rectangle hex18;
	@FXML
	Rectangle hex19;
	@FXML
	Rectangle hex20;
	@FXML
	Rectangle hex21;
	@FXML
	Rectangle hex22;
	@FXML
	Rectangle hex23;
	@FXML
	Rectangle hex24;
	@FXML
	Rectangle hex25;
	
	@FXML
	Label hexLabel1;
	@FXML
	Label hexLabel2;
	@FXML
	Label hexLabel3;
	@FXML
	Label hexLabel4;
	@FXML
	Label hexLabel5;
	@FXML
	Label hexLabel6;
	@FXML
	Label hexLabel7;
	@FXML
	Label hexLabel8;
	@FXML
	Label hexLabel9;
	@FXML
	Label hexLabel10;
	@FXML
	Label hexLabel11;
	@FXML
	Label hexLabel12;
	@FXML
	Label hexLabel13;
	@FXML
	Label hexLabel14;
	@FXML
	Label hexLabel15;
	@FXML
	Label hexLabel16;
	@FXML
	Label hexLabel17;
	@FXML
	Label hexLabel18;
	@FXML
	Label hexLabel19;
	@FXML
	Label hexLabel20;
	@FXML
	Label hexLabel21;
	@FXML
	Label hexLabel22;
	@FXML
	Label hexLabel23;
	@FXML
	Label hexLabel24;
	@FXML
	Label hexLabel25;

	Image hexHover;
	Image hexDefault;
	Stage stage;
	SISEGame game;
	int turnNumber = 0;
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
	List<Rectangle> hexy;
	List values ;
	/*
	 * 0 - nieklikniête
	 * 1 - klikniête
	 * 2 - zamek do zaatakowania
	*/
	List players;
	/*
	 * True - gracz
	 * False - przeciwnik
	 * 
	 * */
	
	List<Label> army;
	
	public gameController(){
		
		this.playerA = new Player(null,null,PlayerType.PlayerA);
		this.playerB = new Player(null,null,PlayerType.PlayerB);
		this.gameField = new ArrayList<FieldUnit>();
		this.createGameField();
		
		
		
	}
	@FXML
	 

    private void initialize() {
		updatePlayer();
		updateTurn();
		updateTroops();
		
		hexy = new ArrayList();
		hexy.add(hex1);
		hexy.add(hex2);
		hexy.add(hex3);
		hexy.add(hex4);
		hexy.add(hex5);
		hexy.add(hex6);
		hexy.add(hex7);
		hexy.add(hex8);
		hexy.add(hex9);
		hexy.add(hex10);
		hexy.add(hex11);
		hexy.add(hex12);
		hexy.add(hex13);
		hexy.add(hex14);
		hexy.add(hex15);
		hexy.add(hex16);
		hexy.add(hex17);
		hexy.add(hex18);
		hexy.add(hex19);
		hexy.add(hex20);
		hexy.add(hex21);
		hexy.add(hex22);
		hexy.add(hex23);
		hexy.add(hex24);
		hexy.add(hex25);
		
		army = new ArrayList();
		army.add(hexLabel1);
		army.add(hexLabel2);
		army.add(hexLabel3);
		army.add(hexLabel4);
		army.add(hexLabel5);
		army.add(hexLabel6);
		army.add(hexLabel7);
		army.add(hexLabel8);
		army.add(hexLabel9);
		army.add(hexLabel10);
		army.add(hexLabel11);
		army.add(hexLabel12);
		army.add(hexLabel13);
		army.add(hexLabel14);
		army.add(hexLabel15);
		army.add(hexLabel16);
		army.add(hexLabel17);
		army.add(hexLabel18);
		army.add(hexLabel19);
		army.add(hexLabel20);
		army.add(hexLabel21);
		army.add(hexLabel22);
		army.add(hexLabel23);
		army.add(hexLabel24);
		army.add(hexLabel25);
		
		for(int i=0; i<army.size(); i++){
			army.get(i).setText("");
		}
		for(int i=0; i<hexy.size(); i++){
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerA)
			{
				switchColor(hexy.get(i), "0x0000FF");
			}
			else if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB)
			{
				switchColor(hexy.get(i), "0xFF00FF");
			}
			else 
				switchColor(hexy.get(i), "0xFFFFFF");
			String soldiersOnUnitCount = Integer.toString(gameField.get(i).getSoldiers());
			
				selectArmy(army.get(i), "0xff0000", soldiersOnUnitCount);
			
		}
		//zerowanie listy, bo na razie ¿adne pole nie jest zaznaczone
		values = new ArrayList();
		for(int i=0; i<hexy.size(); i++){
			values.add(0);
		}
		
		Random randomGenerator = new Random();
		//Wygenerowaæ jakoœ rozmieszczenie graczy
		players = new ArrayList();
		for(int i=0; i<hexy.size(); i++){
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
		
	public void setSISEGame(SISEGame game){
		this.game = game;
	}
	
	//jeden warunek takich zabaw - wszystkie hexy musz¹ mieæ t¹ metodê podpiêt¹
	private void updatePlayer()
	{
		playerID.setText("Gracz niebieski");
	}
	
	private void updateTurn()
	{
		turnID.setText("#" + turnNumber+1);
	}
	
	private void updateTroops()
	{
		troopsSize.setText(redTroops + " jednostek");
	}
	
	//Akcja nastêpuj¹ca po najechaniu poza hoverem
	public void hoverHex(){
		String text = null; 
		for(int i=0; i<hexy.size(); i++){
			if(hexy.get(i).isHover()){
				System.out.println(hexy.get(i).getId());
				switchColor(hexy.get(i), "0xFFF000");
				//selectArmy(army.get(i), "0xff0000", "150");
			}
			else
			{
				switchColor(hexy.get(i), "0xFFFFFF");
			}
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerA)
			{
				switchColor(hexy.get(i), "0x0000FF");
			}
			else if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB)
			{
				switchColor(hexy.get(i), "0xFF00FF");
			}
			//if()
		}
	}
	public ArrayList<FieldUnit> getGameField() {
		return new ArrayList<FieldUnit>(gameField);
	}

	public static int getGameFieldWidthHeightSize() {
		return GAME_FIELD_WIDTH_HEIGHT_SIZE;
	}
	public void switchColor(Rectangle hex, String color){
		
			hex.fillProperty().set(Paint.valueOf(color));
		
	}
	
	//kolory to czerwony i niebieski
	//"0xff0000" oraz "0x00ff00"
	public void selectArmy(Label label, String color, String count){
		label.setText(count);
		label.setTextFill(Paint.valueOf(color));
	}
}

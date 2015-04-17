package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.SISEGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
	
	public gameController(){}
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
			}
		}
	}
	
	//Akcja "resetuj¹ca" hover
	public void defaultHex(){
		System.out.println("Wyszed³em ze starego");
	}
	
	//Tutaj umieszczacie funkcje niezbêdne do dzia³ania gry
	
	//Akcja nastêpuj¹ca po klikniêciu na hexa
	public void clickHex(){
		
	}
}

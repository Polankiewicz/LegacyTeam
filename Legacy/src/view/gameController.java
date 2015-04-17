package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.SISEGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
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
		hexy.add(hex24);
		hexy.add(hex25);
		
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
				switchColor(hexy.get(i), true);
			}
		}
	}
	
	public void switchColor(Rectangle hex, boolean isActive){
		if(isActive)
			hex.fillProperty().set(Paint.valueOf("0xfff000"));
		else
			hex.fillProperty().set(Paint.valueOf("0x000000"));
	}
	
	//kolory to czerwony i niebieski
	//"0xff0000" oraz "0x00ff00"
	public void selectArmy(Label label, String color, String count){
		label.setText(count);
		label.setTextFill(Paint.valueOf(color));
	}
}

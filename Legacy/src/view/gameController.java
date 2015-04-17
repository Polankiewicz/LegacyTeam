package view;

import game.SISEGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class gameController {
	//Hexy
	@FXML
	ImageView[] images; 
	
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

	
	public gameController(){}
	@FXML
    private void initialize() {
		images = new ImageView[12];
		updatePlayer();
		updateTurn();
		updateTroops();
	}

	public void setSISEGame(SISEGame game){
		this.game = game;
	}
	
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
	private void hoverHex(){
		
	}
	
	//Akcja "resetuj¹ca" hover
	private void defaultHex(){
		
	}
	
	//Tutaj umieszczacie funkcje niezbêdne do dzia³ania gry
	
	//Akcja nastêpuj¹ca po klikniêciu na hexa
	private void clickHex(){
		
	}
}

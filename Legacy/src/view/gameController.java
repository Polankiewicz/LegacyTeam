package view;

import game.SISEGame;
import javafx.fxml.FXML;
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
	
	public gameController(){}
	@FXML
    private void initialize() {
		images = new ImageView[12];
	}

	public void setSISEGame(SISEGame game){
		this.game = game;
	}
	
	//Akcja nast�puj�ca po najechaniu poza hoverem
	private void hoverHex(){
		
	}
	
	//Akcja "resetuj�ca" hover
	private void defaultHex(){
		
	}
	
	//Tutaj umieszczacie funkcje niezb�dne do dzia�ania gry
	
	//Akcja nast�puj�ca po klikni�ciu na hexa
	private void clickHex(){
		
	}
}

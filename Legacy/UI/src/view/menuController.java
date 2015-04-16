package view;

import game.SISEGame;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class menuController {
	@FXML
	private ImageView start;
	@FXML
	private ImageView exit;
	@FXML
	private ImageView background;
	
	private SISEGame menu;
	
	public menuController(){}
	
	@FXML
    private void initialize() {}
	
	public void setSISEMenu(SISEGame menu){
		this.menu = menu;
	}
	
	public void startGame(){
		menu.showGame();
	}
	
	public void changeStartImage(){
		System.out.println("I'm hovered");
		Image img = new Image("resources/startHover.png");
		start.imageProperty().set(img);
	}
	
	public void defaultStartImage(){
		System.out.println("I'm out");
		Image img = new Image("resources/start.png");
		start.imageProperty().set(img);
	}
	
	public void changeExitImage(){
		System.out.println("I'm hovered");
		Image img = new Image("resources/exitHover.png");
		exit.imageProperty().set(img);
	}
	
	public void defaultExitImage(){
		System.out.println("I'm out");
		Image img = new Image("resources/exit.png");
		exit.imageProperty().set(img);
	}
	
	public void setSISEGame(SISEGame game){
		this.menu = game;
	}
	
	public void exitGame(){
		System.out.println("Exit clicked");
	    Stage stage = (Stage) exit.getScene().getWindow();
	    stage.close();
	}
}
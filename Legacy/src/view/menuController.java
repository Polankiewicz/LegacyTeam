package view;

import CLIPSJNI.*;
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
	
	@FXML
    private void initialize() {}
	
	public void setSISEMenu(SISEGame menu){
		this.menu = menu;
	}
	
	public void startGame(){
		menu.showGame();
	}
	
	public void changeStartImage()
	{
		Image img = new Image("resources/startHover.png");
		start.imageProperty().set(img);
	}
	
	public void defaultStartImage()
	{
		Image img = new Image("resources/start.png");
		start.imageProperty().set(img);
	}
	public void changeExitImage()
	{
		Image img = new Image("resources/exitHover.png");
		exit.imageProperty().set(img);
	}
	
	public void defaultExitImage()
	{
		Image img = new Image("resources/exit.png");
		exit.imageProperty().set(img);
	}
	
	public void setSISEGame(SISEGame game){
		this.menu = game;
	}
	
	public void exitGame()
	{
	    Stage stage = (Stage) exit.getScene().getWindow();
	    stage.close();
	}
	
	public void PrintClipsText(){
		
	}
}

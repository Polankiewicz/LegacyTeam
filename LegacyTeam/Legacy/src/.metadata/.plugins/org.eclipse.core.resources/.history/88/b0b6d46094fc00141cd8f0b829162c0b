package view;

import CLIPSJNI.*;
import game.SISEGame;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class menuController {
	private static Environment clips;

	
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
		InitializeClips();
		this.menu = menu;
	}
	
	public void startGame(){
		menu.showGame();
	}
	
<<<<<<< HEAD
	public void changeStartImage(){
//		System.out.println("I'm hovered");
=======
	public void changeStartImage()
	{
		System.out.println("I'm hovered");
>>>>>>> 92b087bbd75f70d793785fd9cf75286c58976555
		Image img = new Image("resources/startHover.png");
		start.imageProperty().set(img);
	}
	
<<<<<<< HEAD
	public void defaultStartImage(){
//		System.out.println("I'm out");
=======
	public void defaultStartImage()
	{
		System.out.println("I'm out");
>>>>>>> 92b087bbd75f70d793785fd9cf75286c58976555
		Image img = new Image("resources/start.png");
		start.imageProperty().set(img);
	}
	
<<<<<<< HEAD
	public void changeExitImage(){
//		System.out.println("I'm hovered");
=======
	public void changeExitImage()
	{
		System.out.println("I'm hovered");
>>>>>>> 92b087bbd75f70d793785fd9cf75286c58976555
		Image img = new Image("resources/exitHover.png");
		exit.imageProperty().set(img);
	}
	
<<<<<<< HEAD
	public void defaultExitImage(){
//		System.out.println("I'm out");
=======
	public void defaultExitImage()
	{
		System.out.println("I'm out");
>>>>>>> 92b087bbd75f70d793785fd9cf75286c58976555
		Image img = new Image("resources/exit.png");
		exit.imageProperty().set(img);
	}
	
	public void setSISEGame(SISEGame game){
		this.menu = game;
	}
	
<<<<<<< HEAD
	public void exitGame(){
//		System.out.println("Exit clicked");
=======
	public void exitGame()
	{
		System.out.println("Exit clicked");
>>>>>>> 92b087bbd75f70d793785fd9cf75286c58976555
	    Stage stage = (Stage) exit.getScene().getWindow();
	    stage.close();
	}
	
	public void InitializeClips(){
		clips = new Environment();
		/* Najpierw ³adujemy odpowiednie wzorce, wg których bêdziemy zapisywaæ fakty
		 * Te wzorce musz¹ byæ skonstruowane "slotowo", bo inaczej nic cholery nie 
		 * wyci¹gniesz danych, o czym ni¿ej
		 */
		clips.load("templates.clp");
		/*
		 * Tutaj podajemy przyk³adowe fakty. Niby mo¿na przez deffacts, ale to jest wiedza pocz¹tkowa, niezmienna.
		 * Nasza baza wiedzy zmienia siê co rundê, wiêc trzeba j¹ resetowaæ, odœwie¿aæ itp,
		 * co ³atwiej zrobiæ przez sparametryzowanie stringów poni¿ej i w loopie. To ni¿ej jest tylko przyk³adem
		 */
		clips.eval("(assert (graczInfo(coordX 5)(coordY 7)(czyAI no)(iloscWoja 500)))");
		clips.eval("(assert (graczInfo(coordX 4)(coordY 8)(czyAI no)(iloscWoja 300)))");
		clips.eval("(assert (graczInfo(coordX 2)(coordY 2)(czyAI no)(iloscWoja 400)))");
		clips.eval("(assert (graczInfo(coordX 8)(coordY 4)(czyAI no)(iloscWoja 800)))");
		/*
		 * Tuaj w tym ai ³adujemy nasze wszystkie defrule - czyli zasady wg których ai ma podejmowaæ decyzje
		 * One id¹ w sposób programistyczny, czyli jedna za drugim, chyba, ¿e siê odwo³asz w CLIPSie do poprzedniej regu³y
		 * i znowu leci po kolei. Coœ jak w assemblerze, ¿e przeskakujecie miêdzy odpowiednimi zasadami.
		 */
		clips.load("ai.clp");
		//No i odpalamy ten szajs.
		clips.run();
		
		/* Jak ju¿ odpalimy, CLIPS sobie pokalkuluje co potrzeba i...
		 * Tutaj ¿¹damy od CLIPSa by zwróci³ wszystkie fakty zwi¹zane z nag³ówkiem "kogoZaatakowaæ"
		 * i jest to zwracane w formie listy. MultifieldValue oznacza, ¿e fakt "kogoZaatakowaæ" ma wiêcej
		 * ni¿ jedno pole/zmienn¹ do oddania, ¿e tak powiem.
		*/
		MultifieldValue attack = (MultifieldValue) clips.eval("(find-all-facts ((?f kogoZaatakowac)) TRUE)");
		if(attack.listValue().size() != 0){
			for(int i=0; i< attack.listValue().size(); i++){
				FactAddressValue attackFacts = (FactAddressValue) attack.listValue().get(i);
				/* Tutaj wyci¹gamy z listy odpowiednie sloty zdefiniowane w templates dla nag³ówka
				 * "kogoZaatakowaæ"
				 */
				int coordX = Integer.parseInt(attackFacts.getFactSlot("coordY").toString());
				int coordY = Integer.parseInt(attackFacts.getFactSlot("coordY").toString());
				System.out.println("gracz spe³niaj¹cy warunek jest na polu X: "+coordX+" Y: "+coordY);
			}
		}
		else{
			System.out.println("Brak faktów do zwrócenia");
		}
		//Reszta komentarzy w wy¿ej wymienionych plikach ai.clp oraz templates.clp
	}
	
	public void PrintClipsText(){
		
	}
}

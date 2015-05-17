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
		/* Najpierw �adujemy odpowiednie wzorce, wg kt�rych b�dziemy zapisywa� fakty
		 * Te wzorce musz� by� skonstruowane "slotowo", bo inaczej nic cholery nie 
		 * wyci�gniesz danych, o czym ni�ej
		 */
		clips.load("templates.clp");
		/*
		 * Tutaj podajemy przyk�adowe fakty. Niby mo�na przez deffacts, ale to jest wiedza pocz�tkowa, niezmienna.
		 * Nasza baza wiedzy zmienia si� co rund�, wi�c trzeba j� resetowa�, od�wie�a� itp,
		 * co �atwiej zrobi� przez sparametryzowanie string�w poni�ej i w loopie. To ni�ej jest tylko przyk�adem
		 */
		clips.eval("(assert (graczInfo(coordX 5)(coordY 7)(czyAI no)(iloscWoja 500)))");
		clips.eval("(assert (graczInfo(coordX 4)(coordY 8)(czyAI no)(iloscWoja 300)))");
		clips.eval("(assert (graczInfo(coordX 2)(coordY 2)(czyAI no)(iloscWoja 400)))");
		clips.eval("(assert (graczInfo(coordX 8)(coordY 4)(czyAI no)(iloscWoja 800)))");
		/*
		 * Tuaj w tym ai �adujemy nasze wszystkie defrule - czyli zasady wg kt�rych ai ma podejmowa� decyzje
		 * One id� w spos�b programistyczny, czyli jedna za drugim, chyba, �e si� odwo�asz w CLIPSie do poprzedniej regu�y
		 * i znowu leci po kolei. Co� jak w assemblerze, �e przeskakujecie mi�dzy odpowiednimi zasadami.
		 */
		clips.load("ai.clp");
		//No i odpalamy ten szajs.
		clips.run();
		
		/* Jak ju� odpalimy, CLIPS sobie pokalkuluje co potrzeba i...
		 * Tutaj ��damy od CLIPSa by zwr�ci� wszystkie fakty zwi�zane z nag��wkiem "kogoZaatakowa�"
		 * i jest to zwracane w formie listy. MultifieldValue oznacza, �e fakt "kogoZaatakowa�" ma wi�cej
		 * ni� jedno pole/zmienn� do oddania, �e tak powiem.
		*/
		MultifieldValue attack = (MultifieldValue) clips.eval("(find-all-facts ((?f kogoZaatakowac)) TRUE)");
		if(attack.listValue().size() != 0){
			for(int i=0; i< attack.listValue().size(); i++){
				FactAddressValue attackFacts = (FactAddressValue) attack.listValue().get(i);
				/* Tutaj wyci�gamy z listy odpowiednie sloty zdefiniowane w templates dla nag��wka
				 * "kogoZaatakowa�"
				 */
				int coordX = Integer.parseInt(attackFacts.getFactSlot("coordY").toString());
				int coordY = Integer.parseInt(attackFacts.getFactSlot("coordY").toString());
				System.out.println("gracz spe�niaj�cy warunek jest na polu X: "+coordX+" Y: "+coordY);
			}
		}
		else{
			System.out.println("Brak fakt�w do zwr�cenia");
		}
		//Reszta komentarzy w wy�ej wymienionych plikach ai.clp oraz templates.clp
	}
	
	public void PrintClipsText(){
		
	}
}

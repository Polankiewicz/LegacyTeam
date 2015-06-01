package firstOrderLogic;

import java.util.ArrayList;

import com.sun.prism.image.Coords;

import game.FieldUnit;
import game.MoveDataStructure;
import game.PlayerType;
import game.SISEGame;
import CLIPSJNI.Environment;
import CLIPSJNI.FactAddressValue;
import CLIPSJNI.MultifieldValue;

public class FOLController {
	private Environment clips;
	private Boolean useClips;
	private MoveDataStructure moveAI;
	
	public void runAI(ArrayList<FieldUnit> gameField){
		int x, y, soldiers;
		Boolean enemy;
		
		moveAI = new MoveDataStructure();
		clips = new Environment();
		
		/* Najpierw �adujemy odpowiednie wzorce, wg kt�rych b�dziemy zapisywa� fakty
		 * Te wzorce musz� by� skonstruowane "slotowo", bo inaczej nic cholery nie 
		 * wyci�gniesz danych, o czym ni�ej
		 */
		for(int i=0; i<gameField.size(); i++){
			x = gameField.get(i).getCoordinates().x;
			y = gameField.get(i).getCoordinates().y;
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB){
				enemy = true;
			}
			else{
				enemy = false;
			}
		}
		
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
		int coordX = 1, coordY = 1;
		if(attack.listValue().size() != 0){
			for(int i=0; i< attack.listValue().size(); i++){
				FactAddressValue attackFacts = (FactAddressValue) attack.listValue().get(i);
				/* Tutaj wyci�gamy z listy odpowiednie sloty zdefiniowane w templates dla nag��wka
				 * "kogoZaatakowa�"
				 */
				coordX = Integer.parseInt(attackFacts.getFactSlot("coordY").toString());
				coordY = Integer.parseInt(attackFacts.getFactSlot("coordY").toString());
				System.out.println("gracz spe�niaj�cy warunek jest na polu X: "+coordX+" Y: "+coordY);
			}
			
			//Zadanie Dopek dla Ciebie by sprawdzi� czy to wszystko jest ok
			moveAI.sourceIndex = coordX + coordY; //Suma daje numer indeksu pola
			moveAI.targetIndex = coordX + coordY+1; //Testowo wybieramy nast�pne pole
			moveAI.howMany = 1; //te� testowo, potem do podstawienia
			//nie wiem tylko jak to si� rusza
		}
		else{
			System.out.println("Brak fakt�w do zwr�cenia");
		}
		//Reszta komentarzy w wy�ej wymienionych plikach ai.clp oraz templates.clp
	}

}

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
		
		/* Najpierw ³adujemy odpowiednie wzorce, wg których bêdziemy zapisywaæ fakty
		 * Te wzorce musz¹ byæ skonstruowane "slotowo", bo inaczej nic cholery nie 
		 * wyci¹gniesz danych, o czym ni¿ej
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
		int coordX = 1, coordY = 1;
		if(attack.listValue().size() != 0){
			for(int i=0; i< attack.listValue().size(); i++){
				FactAddressValue attackFacts = (FactAddressValue) attack.listValue().get(i);
				/* Tutaj wyci¹gamy z listy odpowiednie sloty zdefiniowane w templates dla nag³ówka
				 * "kogoZaatakowaæ"
				 */
				coordX = Integer.parseInt(attackFacts.getFactSlot("coordY").toString());
				coordY = Integer.parseInt(attackFacts.getFactSlot("coordY").toString());
				System.out.println("gracz spe³niaj¹cy warunek jest na polu X: "+coordX+" Y: "+coordY);
			}
			
			//Zadanie Dopek dla Ciebie by sprawdziæ czy to wszystko jest ok
			moveAI.sourceIndex = coordX + coordY; //Suma daje numer indeksu pola
			moveAI.targetIndex = coordX + coordY+1; //Testowo wybieramy nastêpne pole
			moveAI.howMany = 1; //te¿ testowo, potem do podstawienia
			//nie wiem tylko jak to siê rusza
		}
		else{
			System.out.println("Brak faktów do zwrócenia");
		}
		//Reszta komentarzy w wy¿ej wymienionych plikach ai.clp oraz templates.clp
	}

}

package firstOrderLogic;

import java.awt.Point;
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
		int castleX, castleY, x, y, soldiers, index, playerType;
		Boolean enemy;
		
		moveAI = new MoveDataStructure();
		clips = new Environment();
		
		clips.load("templates.clp");
		//£adowanie ca³ej planszy
		for(int i=0; i<gameField.size(); i++){
			String isMyCastle = gameField.get(i).getIndex() == 24  ? "yes" : "no";
			String isCastleEnemy = gameField.get(i).getIndex() == 0 ? "yes" : "no";
			String isEnemy = (gameField.get(i).getSoldiersType() == PlayerType.PlayerA) ? "yes" : "no";
			//Je¿eli AI, to zapisujemy swoich
			String assertion = "(assert(boardInfo(coordX "+gameField.get(i).getCoordinates().x+")("+
						"coordY "+gameField.get(i).getCoordinates().y+")("+
						"index "+gameField.get(i).getIndex()+")("+
						"isCastleAI " + isMyCastle + ")("+
						"isCastleEnemy " + isCastleEnemy + ")("+
						"isEnemy " + isEnemy + ")("+
						"iloscWoja "+gameField.get(i).getSoldiers()+")))";
			clips.eval(assertion);
			System.out.println(assertion);
		}
		
		//£adowanie pól AI
		for(int i=0; i<gameField.size(); i++){
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB){
				String assertion = "(assert(AIFields(coordX "+gameField.get(i).getCoordinates().x + ")("+
						"coordY "+gameField.get(i).getCoordinates().y + ")(" +
						"index "+gameField.get(i).getIndex()+")("+
						"iloscWoja "+gameField.get(i).getSoldiers()+")))";
				clips.eval(assertion);
				System.out.println(assertion);
			}
		}
		//£adowanie s¹siadów sztucznej inteligencji
		for(int i=0; i<gameField.size(); i++){
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB){
				ArrayList<Point> neighbour = gameField.get(i).getNeighbours();
				for(int j=0; j<neighbour.size(); j++){
					String isEnemy =((gameField.get(i).getSoldiersType() == PlayerType.PlayerB) ? "yes" : "no" );
					String assertion = "(assert(neighbour(coordX "+gameField.get(i).getCoordinates().x + ")("+
							"coordY "+gameField.get(i).getCoordinates().y + ")(" +
							"index "+gameField.get(i).getIndex()+")("+
							"isEnemy " + isEnemy + ")("+
							"iloscWoja "+gameField.get(i).getSoldiers()+")))";
					clips.eval(assertion);
					System.out.println(assertion);
				}
			}
		}
		clips.load("ai.clp");
		clips.run();
		
		MultifieldValue attack = (MultifieldValue) clips.eval("(find-all-facts ((?f kogoZaatakowac)) TRUE)");
		if(attack.listValue().size() != 0){
			int indexAI = 0, indexEnemy = 0, iloscWoja = 0;
			//Ma zwróciæ tylko jedn¹ opcjê
			for(int i=0; i< 1; i++){
				FactAddressValue attackFacts = (FactAddressValue) attack.listValue().get(i);
				indexAI = Integer.parseInt(attackFacts.getFactSlot("indexAI").toString());
				indexEnemy = Integer.parseInt(attackFacts.getFactSlot("indexEnemy").toString());
				iloscWoja = Integer.parseInt(attackFacts.getFactSlot("iloscWoja").toString());
			}
			
			moveAI.sourceIndex = indexAI;
			moveAI.targetIndex = indexEnemy; //Testowo wybieramy nastêpne pole
			moveAI.howMany = iloscWoja; //te¿ testowo, potem do podstawienia
		}
		else{
			System.out.println("Brak faktów do zwrócenia");
		}
		
	}

}

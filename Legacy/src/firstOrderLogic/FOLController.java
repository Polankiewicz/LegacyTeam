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
			String isCastleEnemy = ((gameField.get(i).getCoordinates() == new Point(0, 0)) ? "Yes" : "No" );
			String isMyCastle = (gameField.get(i).getCoordinates() == new Point(4, 4) ? "Yes" : "No" );
			String isEnemy =((gameField.get(i).getSoldiersType() == PlayerType.PlayerB) ? "Yes" : "No" );
			//Je¿eli AI, to zapisujemy swoich
			String assertion = "(assert(boardInfo(coordX "+gameField.get(i).getCoordinates().x+")("+
						"coordY "+gameField.get(i).getCoordinates().y+")("+
						"index "+gameField.get(i).getCoordinates().x*gameField.get(i).getCoordinates().y+")("+
						"(isCastleEnemy " + isCastleEnemy + ")("+
						"(isMyCastle " + isMyCastle + ")("+
						"(isEnemy " + isEnemy + ")("+
						"(iloscWoja "+gameField.get(i).getSoldiers()+")))";
			clips.eval(assertion);
			System.out.println(assertion);
		}

		//£adowanie s¹siadów sztucznej inteligencji
		for(int i=0; i<gameField.size(); i++){
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB){
				ArrayList<Point> neighbour = gameField.get(i).getNeighbours();
				for(int j=0; j<neighbour.size(); j++){
					String isEnemy =((gameField.get(i).getSoldiersType() == PlayerType.PlayerB) ? "Yes" : "No" );
					String assertion = "(assert(neighbour(coordX "+gameField.get(i).getCoordinates().x + ")("+
							"coordY "+gameField.get(i).getCoordinates().y + ")(" +
							"index "+gameField.get(i).getCoordinates().x*gameField.get(i).getCoordinates().y+")("+
							"(isEnemy " + isEnemy + ")("+
							"(iloscWoja "+gameField.get(i).getSoldiers()+")))";
					clips.eval(assertion);
					System.out.println(assertion);
				}
			}
		}
		clips.load("ai.clp");
		clips.run();
		
		MultifieldValue attack = (MultifieldValue) clips.eval("(find-all-facts ((?f kogoZaatakowac)) TRUE)");
		if(attack.listValue().size() != 0){
			int coordXAI = 0, coordXEnemy = 0, coordYAI = 0, coordYEnemy = 0, iloscWoja = 0;
			//Ma zwróciæ tylko jedn¹ opcjê
			for(int i=0; i< 1; i++){
				FactAddressValue attackFacts = (FactAddressValue) attack.listValue().get(i);
				coordXAI = Integer.parseInt(attackFacts.getFactSlot("coordXAI").toString());
				coordYAI = Integer.parseInt(attackFacts.getFactSlot("coordYAI").toString());
				coordXEnemy = Integer.parseInt(attackFacts.getFactSlot("coordXEnemy").toString());
				coordYEnemy = Integer.parseInt(attackFacts.getFactSlot("coordYEnemy").toString());
				iloscWoja = Integer.parseInt(attackFacts.getFactSlot("iloscWoja").toString());
			}
			
//			moveAI.sourceIndex = coordXAI*coordYAI+1;
//			moveAI.targetIndex = coordXEnemy*coordYEnemy+1; //Testowo wybieramy nastêpne pole
//			moveAI.howMany = iloscWoja; //te¿ testowo, potem do podstawienia
		}
		else{
			System.out.println("Brak faktów do zwrócenia");
		}
		
	}

}

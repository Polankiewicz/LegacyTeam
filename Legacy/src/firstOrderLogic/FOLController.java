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
//			System.out.println(assertion);
		}
		
		//Player B - AI
		//Player A - normalny gracz
		
		//£adowanie pól AI
		for(int i=0; i<gameField.size(); i++){
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB){
				String assertion = "(assert(AIFields(coordX "+gameField.get(i).getCoordinates().x + ")("+
						"coordY "+gameField.get(i).getCoordinates().y + ")(" +
						"index "+gameField.get(i).getIndex()+")("+
						"iloscWoja "+gameField.get(i).getSoldiers()+")))";
				clips.eval(assertion);
				System.out.println("Dodawanie pól AI: "+assertion);
			}
		}
		
		//£adowanie s¹siadów sztucznej inteligencji 
		for(int i=0; i<gameField.size(); i++){
			if(gameField.get(i).getSoldiersType() == PlayerType.PlayerB){
				System.out.println("Szukanie s¹siadów pierwszego stopnia");
				ArrayList<Point> neighbour = gameField.get(i).getNeighbours();
				for(int j=0; j<neighbour.size(); j++){
					for(int k=0; k<gameField.size(); k++){
						if(neighbour.get(j).x == gameField.get(k).getCoordinates().x &&
							neighbour.get(j).y == gameField.get(k).getCoordinates().y){
							//Tutaj ustawiæ zapis s¹siada pierwszego poziomu
							String isEnemy = (gameField.get(k).getSoldiersType() == PlayerType.PlayerA)? "yes" : "no";
							
							String assertion = "(assert(neighbour(coordX "+gameField.get(k).getCoordinates().x+")("+
							"coordY "+gameField.get(k).getCoordinates().y+")("+
							"index "+gameField.get(k).getIndex()+
							"isEnemy "+isEnemy+")(iloscWoja "+gameField.get(k).getSoldiers()+")))";
							System.out.println("Coordy s¹siada x "+gameField.get(k).getCoordinates().x + 
									"y "+gameField.get(k).getCoordinates().y+")("+
									"indeks "+ gameField.get(k).getIndex()+")("+
									"isEnemy "+isEnemy+")(iloscWoja "+gameField.get(k).getSoldiers()
							);
							clips.eval(assertion);
//							£ADUJEMY S¹SIADÓW DRUGIEGO POZIOMU, czyli s¹siad s¹siadów.
							ArrayList<Point> secondNeighbour = gameField.get(k).getNeighbours();
							for(int l=0; l<secondNeighbour.size(); l++){
								for(int m=0; m<gameField.size(); m++){
									if(secondNeighbour.get(l).x == gameField.get(m).getCoordinates().x &&
											secondNeighbour.get(l).y == gameField.get(m).getCoordinates().y){
										
										String isEnemy2 = (gameField.get(m).getSoldiersType() == PlayerType.PlayerA)? "yes" : "no";
										
										String assertion2 = "(assert(neighbour(coordX "+gameField.get(m).getCoordinates().x+")("+
										"coordY "+gameField.get(m).getCoordinates().y+")("+
										"index "+gameField.get(m).getIndex()+
										"isEnemy "+isEnemy+")(iloscWoja "+gameField.get(m).getSoldiers()+")))";
										System.out.println("Coordy s¹siada drugiego poziomu x "+gameField.get(m).getCoordinates().x + 
												" y "+gameField.get(m).getCoordinates().y+ 
												" indeks "+ gameField.get(m).getIndex());
									}
								}
							}
						}
					}
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

	
	public int returnIndex(Point coords){
		return coords.x+coords.y*5;
	}
	
	public int returnIndexXY(int x, int y){
		return x+y*5;
	}

}
//	loadNeighbour(gameField, neighbour);
//	for(int j=0; j<neighbour.size(); j++){
//		System.out.println("Szukanie s¹siadów drugiego stopnia dla pola: "+neighbour.get(j).x+ " "+neighbour.get(j).y);
//		ArrayList<Point> secondNeighbours = gameField.get(returnIndexXY(neighbour.get(j).x, neighbour.get(j).y)).getNeighbours();
//		loadNeighbour(gameField, secondNeighbours);
//	}

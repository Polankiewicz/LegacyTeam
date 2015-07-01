package firstOrderLogic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import view.gameController;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.prism.image.Coords;

import game.FieldUnit;
import game.MoveDataStructure;
import game.Player;
import game.PlayerType;
import game.SISEGame;
import CLIPSJNI.Environment;
import CLIPSJNI.FactAddressValue;
import CLIPSJNI.MultifieldValue;

public class FOLController {
	private Environment clips;
	
	private MoveDataStructure moveAI;
	String isMyCastle;
	String isCastleEnemy;
	String isEnemy;
	String category;
	String playerTypeString;
	
	private Player bluePlayer;
    private Player redPlayer;
    private Player actualPlayer;
   
	private ArrayList<FieldUnit> gameField;
	private gameController gc;
	SISEGame game;
	
	public FOLController(ArrayList<FieldUnit> gameField, Player bluePlayer,
			Player redPlayer, Player actualPlayer,
			MoveDataStructure moveDataStructure, SISEGame siseGame) 
	{
		this.gameField = gameField;
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.moveAI = moveDataStructure;
		this.actualPlayer = actualPlayer;
		this.game = siseGame;
	}
	
	public void runAI(String ai){
		int castleX, castleY, x, y, soldiers, index, playerType;
		Boolean enemy;
		
		PlayerType type = actualPlayer.getPlayerType();
		clips = new Environment();
		
		clips.load("templates.clp");
		//£adowanie ca³ej planszy
		for(int i=0; i<gameField.size(); i++){
			if (type == PlayerType.PlayerB)
			{
				isMyCastle = gameField.get(i).getIndex() == 0  ? "yes" : "no";
				isCastleEnemy = gameField.get(i).getIndex() == 24 ? "yes" : "no";
				isEnemy = (gameField.get(i).getSoldiersType() == PlayerType.PlayerA) ? "yes" : "no";
				//System.out.println("gracz to: " + type.toString() + " a typ pola to: " + gameField.get(i).getSoldiersType().toString());
				
				 
				//System.out.println(category);
			}
			else
			{
				isMyCastle = gameField.get(i).getIndex() == 0  ? "yes" : "no";
				isCastleEnemy = gameField.get(i).getIndex() == 24 ? "yes" : "no";
				isEnemy = (gameField.get(i).getSoldiersType() == PlayerType.PlayerB) ? "yes" : "no";
				
				//System.out.println("gracz to: " + type.toString() + " a typ pola to: " + gameField.get(i).getSoldiersType().toString());
			}
			
			if(gameField.get(i).getSoldiersType().toString() == "PlayerA")
			{
				category = "playera";
			}
			else if(gameField.get(i).getSoldiersType().toString() == "PlayerB")
			{
				category = "playerb";
			}
			else
			{
				category = "noone";
			}
			
			if(type.toString() == "PlayerA")
			{
				playerTypeString = "playera";
			}
			else if(type.toString() == "PlayerB")
			{
				playerTypeString = "playerb";
			}
			
			String assertField = "(assert(fieldType(coordX " + gameField.get(i).getCoordinates().x + ")(" +
								 "coordY " + gameField.get(i).getCoordinates().y + ")(" +
								 "index " + gameField.get(i).getIndex() + ")(" +
								 "category " + category + ")(" +
								 "playerType " + playerTypeString +")))";
			
			clips.eval(assertField);
			
			
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
		
	
//		for(int i=0; i<gameField.size(); i++){
//			if(gameField.get(i).getSoldiersType() == type){
//				String assertion = "(assert(AIFields(coordX "+gameField.get(i).getCoordinates().x + ")("+
//						"coordY "+gameField.get(i).getCoordinates().y + ")(" +
//						"index "+gameField.get(i).getIndex()+")("+
//						"iloscWoja "+gameField.get(i).getSoldiers()+")))";
//				clips.eval(assertion);
//				//System.out.println("Dodawanie pól AI: "+assertion);
//			}
//		}
		
		//£adowanie s¹siadów sztucznej inteligencji 
		String assertion = null;
		for(int i = 0; i <gameField.size(); i++)
			if(gameField.get(i).getSoldiersType() == type){
				ArrayList<Point> neighbour = gameField.get(i).getNeighbours();
				
				assertion = "(assert(AIFields(coordX "+gameField.get(i).getCoordinates().x + ")("+
						"coordY "+gameField.get(i).getCoordinates().y + ")(" +
						"index "+gameField.get(i).getIndex()+")("+
						"iloscWoja "+gameField.get(i).getSoldiers()+")";
				
				for(int j=0; j<neighbour.size(); j++){
					assertion += "(neighbour"+j+"coordX "+gameField.get(i).getCoordinates().x + ")("+
							"neighbour"+j+"coordY "+gameField.get(i).getCoordinates().y + ")(" +
							"neighbour"+j+"index "+gameField.get(i).getIndex()+")("+
							"neighbour"+j+"iloscWoja "+gameField.get(i).getSoldiers()+")";
				}
				assertion += ")))";
				System.out.println(assertion);
//				clips.eval(assertion);
			}
//		for(int i=0; i<gameField.size(); i++)
//		{
//			
//			if(gameField.get(i).getSoldiersType() == type){
//				//System.out.println("Szukanie s¹siadów pierwszego stopnia");
//				ArrayList<Point> neighbour = gameField.get(i).getNeighbours();
//				
//				for(int j=0; j<neighbour.size(); j++)
//				{
//					for(int k=0; k<gameField.size(); k++)
//					{
//						if(neighbour.get(j).x == gameField.get(k).getCoordinates().x &&
//							neighbour.get(j).y == gameField.get(k).getCoordinates().y)
//						{
//							//Tutaj ustawiæ zapis s¹siada pierwszego poziomu
//							String isEnemy = (gameField.get(k).getSoldiersType() == PlayerType.PlayerA)? "yes" : "no";
//							
//							String assertion = "(assert(neighbour(coordX "+gameField.get(k).getCoordinates().x+")("+
//							"coordY "+gameField.get(k).getCoordinates().y+")("+
//							"index "+gameField.get(k).getIndex()+")("+
//							"isEnemy "+isEnemy+")(iloscWoja "+gameField.get(k).getSoldiers()+")))";
//							//System.out.println("Coordy s¹siada x "+gameField.get(k).getCoordinates().x + 
//							//		"y "+gameField.get(k).getCoordinates().y+")("+
//								//	"indeks "+ gameField.get(k).getIndex()+")("+
//								//	"isEnemy "+isEnemy+")(iloscWoja "+gameField.get(k).getSoldiers()
//						//	);
//							clips.eval(assertion);
////							£ADUJEMY S¹SIADÓW DRUGIEGO POZIOMU, czyli s¹siad s¹siadów.
//							ArrayList<Point> secondNeighbour = gameField.get(k).getNeighbours();
//							
//							for(int l=0; l<secondNeighbour.size(); l++)
//							{
//								for(int m=0; m<gameField.size(); m++)
//								{
//									if(secondNeighbour.get(l).x == gameField.get(m).getCoordinates().x &&
//
//											secondNeighbour.get(l).y == gameField.get(m).getCoordinates().y){
//										
//										String isEnemy2 = (gameField.get(m).getSoldiersType() == PlayerType.PlayerA)? "yes" : "no";
//										
//										String assertion2 = "(assert(neighbour(coordX "+gameField.get(m).getCoordinates().x+")("+
//										"coordY "+gameField.get(m).getCoordinates().y+")("+
//										"index "+gameField.get(m).getIndex()+")("+
//										"isEnemy "+isEnemy2+")(iloscWoja "+gameField.get(m).getSoldiers()+")))";
//
//										//System.out.println("Coordy s¹siada drugiego poziomu x "+gameField.get(m).getCoordinates().x + 
//		//										" y "+gameField.get(m).getCoordinates().y+ 
//		//										" indeks "+ gameField.get(m).getIndex());
//
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		
		clips.load(ai);

		clips.run();
		
//		MultifieldValue attac = (MultifieldValue) clips.eval("(find-all-facts ((?f pI)) TRUE)");
//		for(int i=0; i<attac.listValue().size(); i++){
//			FactAddressValue attackFact = (FactAddressValue) attac.listValue().get(i);
//			int indexToMove = Integer.parseInt(attackFact.getFactSlot("indexField").toString());
//			if(indexToMove == 0){
//				moveAI.sourceIndex = indexToMove;
//				moveAI.targetIndex = 1;
//				moveAI.howMany = 10;
//			}
//			else if(indexToMove == 24){
//				moveAI.sourceIndex = indexToMove;
//				moveAI.targetIndex = 23;
//				moveAI.howMany = 10;
//			}
//			System.out.println("Wartosc: " + (attackFact.getFactSlot("indexField").toString()));
//		}
		
		boolean randomizeSelection = false;
		MultifieldValue attack = (MultifieldValue) clips.eval("(find-all-facts ((?f kogoZaatakowac)) TRUE)");
		MultifieldValue random = (MultifieldValue) clips.eval("(find-all-facts ((?f randomizeSelection)) TRUE)");
		if(random.listValue().size() != 0){
			for(int i=0; i<1; i++){
				FactAddressValue isRandomize = (FactAddressValue) random.listValue().get(i);
				randomizeSelection = (isRandomize.toString() == "yes");
			}
		}
		
		Random randomNumber = new Random();
		int choosenOne = 0;
		
		if(randomizeSelection)
			choosenOne = randomNumber.nextInt(random.listValue().size());
		
		if(attack.listValue().size() != 0){
			int indexAI = 0, indexEnemy = 0, iloscWoja = 0;
			//Ma zwróciæ tylko jedn¹ opcjê
			for(int i = choosenOne; i < choosenOne + 1; i++){
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
	public void gameMainLoop()
	{
		for (int i=0;i<100;i++)
		{
			//System.out.println("tura "+i);
			actualPlayer = bluePlayer;
			runAI("ai.clp");
			game.makeMove();
			if (game.isGameFinished())
				break;
			actualPlayer = redPlayer; 
			runAI("ai.clp");
			game.makeMove();
			if (game.isGameFinished())
				break;
		}
		
	}
}


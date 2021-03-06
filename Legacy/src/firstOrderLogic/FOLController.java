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
    
    private Random randomNumber;
	int indexAI = 0, indexEnemy = 0, iloscWoja = 0;
   
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
	
	public void runAI(String ai, String templates){
		int castleX, castleY, x, y, soldiers, index, playerType;
		Boolean enemy;
		
		PlayerType type = actualPlayer.getPlayerType();
		clips = new Environment();
		
		clips.load(templates);
		//�adowanie ca�ej planszy
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
				isMyCastle = gameField.get(i).getIndex() == 24  ? "yes" : "no";
				isCastleEnemy = gameField.get(i).getIndex() == 0 ? "yes" : "no";
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
		
		//�adowanie pola AI z s�siadami 
		String assertion = null;
		for(int i = 0; i <gameField.size(); i++)
			if(gameField.get(i).getSoldiersType() == type){
				ArrayList<Point> neighbour = gameField.get(i).getNeighbours();
				for(int j=0; j<neighbour.size(); j++){
					assertion = "(assert(AIFields(coordX "+gameField.get(i).getCoordinates().x + ")("+
							"coordY "+gameField.get(i).getCoordinates().y + ")(" +
							"index "+gameField.get(i).getIndex()+")("+
							"iloscWoja "+gameField.get(i).getSoldiers()+")";
					
					int field = returnIndex(neighbour.get(j));
					String fieldType = null;
					System.out.println("Pole do obliczenia " + field);
					if(gameField.get(field).getSoldiersType() == PlayerType.NoOne)
						fieldType = "none";
					else if(gameField.get(field).getSoldiersType() == type)
						fieldType = "player";
					else
						fieldType = "enemy";
					
					assertion += "(neighbour0coordX "+gameField.get(field).getCoordinates().x + ")("+
							"neighbour0coordY "+gameField.get(field).getCoordinates().y + ")(" +
							"neighbour0index "+gameField.get(field).getIndex()+")("+
							"neighbour0field "+fieldType+")("+
							"neighbour0iloscWoja "+gameField.get(field).getSoldiers()+
							")))";
//					System.out.println(assertion);
					clips.eval(assertion);
					}
			}
		
		clips.eval("(assert (randomizeSelection (random yes)))");
		clips.load(ai);
		clips.run();
		
		boolean randomizeSelection = false;
		MultifieldValue random = (MultifieldValue) clips.eval("(find-all-facts ((?f randomizeSelection)) TRUE)");
		if(random.listValue().size() != 0){
			FactAddressValue isRandomize = (FactAddressValue) random.listValue().get(0);
			if(isRandomize.getFactSlot("random").toString().equals("yes"))
				randomizeSelection = true;
			else
				randomizeSelection = false;
		}
		
		int choosenOne = 0;
		
		

		MultifieldValue attack = (MultifieldValue) clips.eval("(find-all-facts ((?f kogoZaatakowac)) TRUE)");
		if(attack.listValue().size() != 0 && randomizeSelection){
//			System.out.println("Ilo�� mo�liwo�ci "+attack.listValue().size());
			choosenOne = randomNumber.nextInt(attack.listValue().size());
//			System.out.println(choosenOne);
			
			//Ma zwr�ci� tylko jedn� opcj�
				FactAddressValue attackFacts = (FactAddressValue) attack.listValue().get(choosenOne);
				indexAI = Integer.parseInt(attackFacts.getFactSlot("indexAI").toString());
				indexEnemy = Integer.parseInt(attackFacts.getFactSlot("indexEnemy").toString());
				iloscWoja = Integer.parseInt(attackFacts.getFactSlot("iloscWoja").toString());
				System.out.println("Wylosowano " + choosenOne + " i idzie z "+indexAI + " do " + indexEnemy);
				
			moveAI.sourceIndex = indexAI;
			moveAI.targetIndex = indexEnemy; //Testowo wybieramy nast�pne pole
			moveAI.howMany = iloscWoja-1; //te� testowo, potem do podstawienia
		}
		else{
			System.out.println("Brak fakt�w do zwr�cenia");
		}
		
		clips.clear();
	}

	
	public int returnIndex(Point coords){
		return coords.x+coords.y*5 - 1;
	}
	
	public int returnIndexXY(int x, int y){
		return x+y*5 - 1;
	}
	public void gameMainLoop()
	{
		randomNumber = new Random();
		for (int i=0;i<98 ;i++)
		{
//			System.out.println("tura "+i);
			actualPlayer = bluePlayer;
			runAI("ai.clp", "templates.clp");
			game.makeMove();
			if (game.isGameFinished())
				break;
			actualPlayer = redPlayer; 
			runAI("ai.clp", "templates.clp");
			game.makeMove();
			if (game.isGameFinished())
				break;
		}
		
	}
}


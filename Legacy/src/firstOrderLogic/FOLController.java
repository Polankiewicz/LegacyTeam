package firstOrderLogic;

import java.awt.Point;
import java.util.ArrayList;

import view.gameController;

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
	
	
	private Player bluePlayer;
    private Player redPlayer;
    private Player actualPlayer;
    PlayerType opponent;
    PlayerType type ;
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
		
		type = actualPlayer.getPlayerType();
	
		clips = new Environment();
		
		clips.load("templates.clp");
		//�adowanie ca�ej planszy
		for(int i=0; i<gameField.size(); i++){
			if (type == PlayerType.PlayerB)
			{
				isMyCastle = gameField.get(i).getIndex() == 0  ? "yes" : "no";
				isCastleEnemy = gameField.get(i).getIndex() == 24 ? "yes" : "no";
				isEnemy = (gameField.get(i).getSoldiersType() == PlayerType.PlayerA) ? "yes" : "no";
			}
			else
			{
				isMyCastle = gameField.get(i).getIndex() == 0  ? "yes" : "no";
				isCastleEnemy = gameField.get(i).getIndex() == 24 ? "yes" : "no";
				isEnemy = (gameField.get(i).getSoldiersType() == PlayerType.PlayerB) ? "yes" : "no";
			}
			
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
		
	
		for(int i=0; i<gameField.size(); i++){
			if(gameField.get(i).getSoldiersType() == type){
				String assertion = "(assert(AIFields(coordX "+gameField.get(i).getCoordinates().x + ")("+
						"coordY "+gameField.get(i).getCoordinates().y + ")(" +
						"index "+gameField.get(i).getIndex()+")("+
						"iloscWoja "+gameField.get(i).getSoldiers()+")))";
				clips.eval(assertion);
				System.out.println("Dodawanie p�l AI: "+assertion);
			}
		}
		
		//�adowanie s�siad�w sztucznej inteligencji 
		for(int i=0; i<gameField.size(); i++)
		{
			
			if(gameField.get(i).getSoldiersType() == type){
				System.out.println("Szukanie s�siad�w pierwszego stopnia");
				ArrayList<Point> neighbour = gameField.get(i).getNeighbours();
				
				for(int j=0; j<neighbour.size(); j++)
				{
					for(int k=0; k<gameField.size(); k++)
					{
						if(neighbour.get(j).x == gameField.get(k).getCoordinates().x &&
							neighbour.get(j).y == gameField.get(k).getCoordinates().y)
						{
							//Tutaj ustawi� zapis s�siada pierwszego poziomu
							String isEnemy = (gameField.get(k).getSoldiersType() == opponent)? "yes" : "no";
							
							String assertion = "(assert(neighbour(coordX "+gameField.get(k).getCoordinates().x+")("+
							"coordY "+gameField.get(k).getCoordinates().y+")("+
							"index "+gameField.get(k).getIndex()+
							"isEnemy "+isEnemy+")(iloscWoja "+gameField.get(k).getSoldiers()+")))";
							System.out.println("Coordy s�siada x "+gameField.get(k).getCoordinates().x + 
									"y "+gameField.get(k).getCoordinates().y+")("+
									"indeks "+ gameField.get(k).getIndex()+")("+
									"isEnemy "+isEnemy+")(iloscWoja "+gameField.get(k).getSoldiers()
							);
							clips.eval(assertion);
//							�ADUJEMY S�SIAD�W DRUGIEGO POZIOMU, czyli s�siad s�siad�w.
							ArrayList<Point> secondNeighbour = gameField.get(k).getNeighbours();
							
							for(int l=0; l<secondNeighbour.size(); l++)
							{
								for(int m=0; m<gameField.size(); m++)
								{
									if(secondNeighbour.get(l).x == gameField.get(m).getCoordinates().x &&

											secondNeighbour.get(l).y == gameField.get(m).getCoordinates().y){
										
										String isEnemy2 = (gameField.get(m).getSoldiersType() == opponent)? "yes" : "no";
										
										String assertion2 = "(assert(neighbour(coordX "+gameField.get(m).getCoordinates().x+")("+
										"coordY "+gameField.get(m).getCoordinates().y+")("+
										"index "+gameField.get(m).getIndex()+
										"isEnemy "+isEnemy2+")(iloscWoja "+gameField.get(m).getSoldiers()+")))";
										clips.eval(assertion2);
										System.out.println("Coordy s�siada drugiego poziomu x "+gameField.get(m).getCoordinates().x + 
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
		
		clips.load(ai);

		clips.run();
		
		MultifieldValue attack = (MultifieldValue) clips.eval("(find-all-facts ((?f kogoZaatakowac)) TRUE)");
		if(attack.listValue().size() != 0){
			int indexAI = 0, indexEnemy = 0, iloscWoja = 0;
			//Ma zwr�ci� tylko jedn� opcj�
			for(int i=0; i< 1; i++){
				FactAddressValue attackFacts = (FactAddressValue) attack.listValue().get(i);
				indexAI = Integer.parseInt(attackFacts.getFactSlot("indexAI").toString());
				indexEnemy = Integer.parseInt(attackFacts.getFactSlot("indexEnemy").toString());
				iloscWoja = Integer.parseInt(attackFacts.getFactSlot("iloscWoja").toString());
			}
			
			moveAI.sourceIndex = indexAI;
			moveAI.targetIndex = indexEnemy; //Testowo wybieramy nast�pne pole
			moveAI.howMany = iloscWoja; //te� testowo, potem do podstawienia
		}
		else{
			System.out.println("Brak fakt�w do zwr�cenia");
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
			System.out.println("tura "+i);
			actualPlayer = bluePlayer;
			opponent = redPlayer.getPlayerType();
			runAI("ai.clp");
			game.makeMove();
			if (game.isGameFinished())
				break;
			actualPlayer = redPlayer;
			opponent = bluePlayer.getPlayerType();
			runAI("ai.clp");
			game.makeMove();
			if (game.isGameFinished())
				break;
		}
		
	}
}


package FuzzyLogic;

import game.FieldUnit;
import game.MoveDataStructure;
import game.Player;
import game.PlayerType;
import game.SISEGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import view.gameController;

public class FuzzyLogicAutoPlay {

	private Player bluePlayer;
    private Player redPlayer;
    private Player actualPlayer;
    private Player otherPlayer;
   
	private MoveDataStructure moveDataStructure;
	private ArrayList<FieldUnit> gameField;
	//private gameController gc;
	private SISEGame game;
	
	private FuzzyLogicControl fuzzyLogicControlForBluePlayer; 
	private FuzzyLogicControl fuzzyLogicControlForRedPlayer;
	private FuzzyLogicControl actualFuzzyLogicControl;
	
	private String autoBluePlayer;
	private String autoRedPlayer;
	
	private int actualPlayerIteration, otherPlayerIteration;
	
	private double fuzzyFieldControlled, fuzzyFightChances, fuzzyUnitsPerField, fuzzyUnitsRatioToBase;
		
	
	public FuzzyLogicAutoPlay(ArrayList<FieldUnit> gameField, Player bluePlayer, Player redPlayer, Player actualPlayer,
			MoveDataStructure moveDataStructure, SISEGame game) 
	{
		this.gameField = gameField;
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.actualPlayer = actualPlayer;
		this.moveDataStructure = moveDataStructure;
		this.game = game;
		
		// Auto Players: Damian, Dopek, Polka, Grzesio, Dziedzic, Dobrotek
		autoBluePlayer = new String("Damian");
		autoRedPlayer = new String("Dopek");
		
		// Blue Player FCL files
		this.fuzzyLogicControlForBluePlayer = new FuzzyLogicControl(
				new StringBuilder("src/FuzzyLogic/").append(autoBluePlayer).append("/fightChances.fcl").toString(),
				new StringBuilder("src/FuzzyLogic/").append(autoBluePlayer).append("/fieldsControled.fcl").toString(),
				new StringBuilder("src/FuzzyLogic/").append(autoBluePlayer).append("/unitsPerField.fcl").toString(),
				new StringBuilder("src/FuzzyLogic/").append(autoBluePlayer).append("/unitsRatioToBase.fcl").toString());
		// Red Player FCL files
		this.fuzzyLogicControlForRedPlayer = new FuzzyLogicControl(
				new StringBuilder("src/FuzzyLogic/").append(autoRedPlayer).append("/fightChances.fcl").toString(),
				new StringBuilder("src/FuzzyLogic/").append(autoRedPlayer).append("/fieldsControled.fcl").toString(),
				new StringBuilder("src/FuzzyLogic/").append(autoRedPlayer).append("/unitsPerField.fcl").toString(),
				new StringBuilder("src/FuzzyLogic/").append(autoRedPlayer).append("/unitsRatioToBase.fcl").toString());
	}

	public void gameMainLoop()
	{
		List<FuzzyLogicFieldData> fieldsData = new ArrayList<FuzzyLogicFieldData>();
		
		// BARDZO TYMCZASOWA WERSJA DLATEGO JEST TU BURDEL JESZCZE :P
		
		actualPlayerIteration = 0;
		otherPlayerIteration = 0;
		
		//for(;;)
		for(int iteration=0; iteration<100; iteration++)
		{
			if(actualPlayer.getPlayerType() == PlayerType.PlayerA) {
				otherPlayer = redPlayer;
				actualFuzzyLogicControl = fuzzyLogicControlForBluePlayer;
			}
			else {
				otherPlayer = bluePlayer;
				actualFuzzyLogicControl = fuzzyLogicControlForRedPlayer;
			}
			
			
			// check ours controlled fields  
			fuzzyFieldControlled = actualFuzzyLogicControl.getFuzzyFieldsControled(
					actualPlayer.getControlledFields());
			// check if we can fight and chances to win in each situation
			fuzzyFightChances = actualFuzzyLogicControl.getFuzzyFightChances(
					actualPlayer.getGameField().get(0).getSoldiers(), 
					otherPlayer.getGameField().get(1).getSoldiers());
			// check if we have huge amount of units in base so we can place them on next fields 
			fuzzyUnitsRatioToBase = actualFuzzyLogicControl.getFuzzyUnitsRatioToBase(
					actualPlayer.getBase().getSoldiers(), 
					actualPlayer.countAllSoldiers());
			// check amount of units in base to other our units and decide witch fields need help
			fuzzyUnitsPerField = actualFuzzyLogicControl.getFuzzyUnitsPerField(
					actualPlayer.countAllSoldiers(), 
					actualPlayer.getGameField().get(0).getSoldiers()); 
			// decide: move units
			
			
			System.out.println("--------------- ROZMYTA - START -------------------------------------------------------");
			
			
			for(int i=0; i< actualPlayer.getGameField().size(); i++)
			{
				// znalezienie pol z jednostkami gracza
				if(actualPlayer.getGameField().get(i).getSoldiersType() == actualPlayer.getPlayerType())
				{
//					System.out.println("Gracz ma jednostki na obecnym polu: " + i + " ---------------");
					
					// nowy element listy dla naszego aktualnego pola 
					fieldsData.add(new FuzzyLogicFieldData(i));
					
					// sprawdzic sasiadow dla naszego pola
					for(int j=0; j < actualPlayer.getGameField().get(i).getNeighbours().size(); j++)
					{
						// Punkt na id pola
						int fieldNumber = translateCoordinates(actualPlayer.getGameField().get(i).getNeighbours().get(j));
						
//						System.out.println("Id sasiada: " + fieldNumber 
//								+ " X: " + actualPlayer.getGameField().get(i).getNeighbours().get(j).x
//								+ " Y: " + actualPlayer.getGameField().get(i).getNeighbours().get(j).y);
						
						// dodawanie do specjalnych list numery pola sasiadow w zaleznosci od typu ich pola
						if(gameField.get(fieldNumber).getSoldiersType() == PlayerType.NoOne)
						{
							fieldsData.get(fieldsData.size()-1).ListEmptyFields.add(fieldNumber);
						}
						else if(gameField.get(fieldNumber).getSoldiersType() == otherPlayer.getPlayerType())
						{
							fieldsData.get(fieldsData.size()-1).ListEnemyFields.add(fieldNumber);
						}
						else if(gameField.get(fieldNumber).getSoldiersType() == actualPlayer.getPlayerType())
						{
							fieldsData.get(fieldsData.size()-1).ListPlayerFields.add(fieldNumber);
						}
						
						
					}
					
				}
			}
			
			
			
			
			
			// check ours controlled fields  
				fuzzyFieldControlled = actualFuzzyLogicControl.getFuzzyFieldsControled(
						actualPlayer.getControlledFields());
				fuzzyFieldControlled = 2; // tymczasowo wstawiam sta�� warto�� !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			boolean czyBylaEkspansja = false;
			
			List<FuzzyLogicFieldData> fieldsData2 = copyList(fieldsData);
			
			System.out.println("-------------------------- RUCH - START ---------------------------------");
			// FCL - jesli jakas zmienna mniejsza od X to ekspansja
			// okreslamy jak czesto mamy zdobywac nowe pola, a jak czesto uzupelniac obecne
			
			//////////////////////////////////// Zdobycie nowego pola lub walka //////////////////////////////////
			if(actualPlayerIteration < fuzzyFieldControlled)
			{				
				while(true)
				{
					if(fieldsData.isEmpty())
						break;
					
					// losujemy element z listy fieldsData, ktora zawiera nasze pola
					int losowyElementZFieldsData = (1+ (int)(Math.random()*fieldsData.size())) -1;
					// id pola dla losowego elementu
					int idLosowegoElementu = fieldsData.get( losowyElementZFieldsData ).id;
					
					// musza byc przynajmniej 2 jednostki na polu zeby mozna bylo przeniesc przynajmniej jedna
					if(gameField.get(idLosowegoElementu).getSoldiers() <= 1) {
						fieldsData.remove(losowyElementZFieldsData);
						continue;
					}
						
					
					// po sasiadach pola - je�li nie ma pustych s�siad�w to walka albo uzupelnienie
					// wybor miedzy pustymi jednostkami a walka !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					if(!fieldsData.get(losowyElementZFieldsData).ListEmptyFields.isEmpty())
					{
						int iloscPustychSasiadow = fieldsData.get(losowyElementZFieldsData).ListEmptyFields.size();
						
						int idLosowegoSasiada = (1+ (int)(Math.random()*iloscPustychSasiadow)) -1;
						int idPolaLosowegoSasiada = fieldsData.get(losowyElementZFieldsData).ListEmptyFields.get(idLosowegoSasiada);
						
						// przenosimy dalej losowa ilosc jednostek !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						int howManySoldiers = (1+ (int)(Math.random()*(gameField.get(idLosowegoElementu).getSoldiers()-1)) );
						
						moveDataStructure.sourceIndex = idLosowegoElementu;
						moveDataStructure.targetIndex = idPolaLosowegoSasiada;
						moveDataStructure.howMany = howManySoldiers;
						game.makeMove();
						
						czyBylaEkspansja = true;
						
						System.out.println("Ruch na puste pole! Z: " + idLosowegoElementu + " Na: " + idPolaLosowegoSasiada
								+ " Ile: " + moveDataStructure.howMany);
						
						break;
					}
					else if(!fieldsData.get(losowyElementZFieldsData).ListEnemyFields.isEmpty())
					{
						// walka :)
						
						// wywalenie wrogow z ktorymi nie mamy szansy na wygrana
						for(int i=0; i < fieldsData.get(losowyElementZFieldsData).ListEnemyFields.size(); i++)
						{
							int idPolaWroga = fieldsData.get(losowyElementZFieldsData).ListEnemyFields.get(i);
							
							if( actualPlayer.getGameField().get(idLosowegoElementu).getSoldiers() <= 
									gameField.get(idPolaWroga).getSoldiers()) {
								fieldsData.get(losowyElementZFieldsData).ListEnemyFields.remove(i);
								i--;
								
								if(fieldsData.get(losowyElementZFieldsData).ListEnemyFields.isEmpty())
									break;
							}
						}
						
						
						// wywalenie obiektu bo i tak z nikim nie wygra
						if(fieldsData.get(losowyElementZFieldsData).ListEnemyFields.isEmpty())
						{
							fieldsData.remove(losowyElementZFieldsData);
							continue;
						}
						
						
						int iloscWrogichSasiadow = fieldsData.get(losowyElementZFieldsData).ListEnemyFields.size();
						
						int losowyWrog = ((int)(Math.random()*iloscWrogichSasiadow));
						int idPolaLosowegoWroga = fieldsData.get(losowyElementZFieldsData).ListEnemyFields.get(losowyWrog);
						
						// wyliczenie ile jednostek przeniesc na pole wroga zeby go pokonac !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						int roznicaJednostek = gameField.get(idLosowegoElementu).getSoldiers() 
								- gameField.get(idPolaLosowegoWroga).getSoldiers();
						int howManyUnitsToMove = (1+(int)(Math.random()*roznicaJednostek-1))
								+ gameField.get(idPolaLosowegoWroga).getSoldiers();
						
						moveDataStructure.sourceIndex = idLosowegoElementu;
						moveDataStructure.targetIndex = idPolaLosowegoWroga; 
						moveDataStructure.howMany = howManyUnitsToMove;
						
						System.out.println("Ruch na wrogie pole! Z: " + idLosowegoElementu + " Na: " + idPolaLosowegoWroga
								+ " Ile: " + moveDataStructure.howMany 
								+ " Moje jednostki: " + gameField.get(idLosowegoElementu).getSoldiers()
								+ " Jednostki wroga: " + gameField.get(idPolaLosowegoWroga).getSoldiers());
						
						game.makeMove();
						
						czyBylaEkspansja = true;
						
						break;
					}
					else
					{
						// jesli nie ma sasiadow z pustymi polami albo z przeciwnikami
						fieldsData.remove(losowyElementZFieldsData);
					}
					
				}
				
				if(czyBylaEkspansja)
					actualPlayerIteration++;
			}
			
			//////////////////////////////////// Do�adowanie jednostek //////////////////////////////////
			if (!czyBylaEkspansja)
			{
				
				while(true)
				{
					if(fieldsData2.isEmpty())
						break;
					
					
					
					// losujemy element z listy fieldsData, ktora zawiera nasze pola
					int losowyElementZFieldsData = ((int)(Math.random()*fieldsData2.size()));
					// id pola dla losowego elementu
					int idLosowegoElementu = fieldsData2.get( losowyElementZFieldsData ).id;
					
					// musza byc przynajmniej 2 jednostki na polu zeby mozna bylo przeniesc przynajmniej jedna
					if(gameField.get(idLosowegoElementu).getSoldiers() <= 1) {
						fieldsData2.remove(losowyElementZFieldsData);
						continue;
					}
					
					
					// szukamy sasiadow z naszymi jednostkami zeby moc im przekazac wsparcie :)
					if(!fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.isEmpty())
					{
						int iloscNaszychSasiadow = fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.size();
						
						int idLosowegoSasiada = ((int)(Math.random()*iloscNaszychSasiadow));
						int idPolaLosowegoSasiada = fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.get(idLosowegoSasiada);
						int howManySoldiers = (1+(int)(Math.random()*actualPlayer.getGameField().get(idLosowegoElementu).getSoldiers()-1));
						
						moveDataStructure.sourceIndex = idLosowegoElementu;
						moveDataStructure.targetIndex = idPolaLosowegoSasiada;
						moveDataStructure.howMany = howManySoldiers;
						game.makeMove();
						
						czyBylaEkspansja = true;
						
						System.out.println("Ruch na nasze pole! Z: " + idLosowegoElementu + " Na: " + idPolaLosowegoSasiada
								+ " Ile: " + moveDataStructure.howMany);
						
						break;
						
					}
					else
					{
						fieldsData2.remove(losowyElementZFieldsData);
					}
					
					
				}
				actualPlayerIteration = 0;
			}
			
			
			// domyslny ruch jesli nie zostanie wykonany ruch
			if(!czyBylaEkspansja)
			{
				if(actualPlayer.getPlayerType() == PlayerType.PlayerA) {
					moveDataStructure.sourceIndex = 0;
					moveDataStructure.targetIndex = 1;
					moveDataStructure.howMany = 1;
					game.makeMove();
				}
				else {
					moveDataStructure.sourceIndex = 24;
					moveDataStructure.targetIndex = 24;
					moveDataStructure.howMany = 1;
					game.makeMove();
				}
			}
			

			
			// zamiana aktualnej iteracji dla stosunku zajetych pol i taktyki od tego
			int temp = actualPlayerIteration;
			actualPlayerIteration = otherPlayerIteration;
			otherPlayerIteration = temp;
			
			
			// update player
			if(actualPlayer.getPlayerType() == PlayerType.PlayerA) {
				actualPlayer = redPlayer;
			}
			else {
				actualPlayer = bluePlayer;
			}
			
			// clear list for next player
			fieldsData.clear();
			fieldsData2.clear();
			
			//if( WYGRANA )
			//	break;
			
			System.out.println("-------------------------- RUCH - KONIEC ---------------------------------");
			System.out.println("--------------- ROZMYTA - KONIEC -------------------------------------------------------");
		}
		
	}
	
	public int translateCoordinates(Point point)
	{
		return (point.x*5) + point.y;
	}
	
	public List<FuzzyLogicFieldData> copyList(List<FuzzyLogicFieldData> base)
	{
		List<FuzzyLogicFieldData> destination = new ArrayList<FuzzyLogicFieldData>();
		
		for(int k=0; k<base.size(); k++)
		{
			destination.add(new FuzzyLogicFieldData(base.get(k).id));
			
			for(int h=0; h<base.get(k).ListEmptyFields.size(); h++)
			{
				destination.get(k).ListEmptyFields.add(base.get(k).ListEmptyFields.get(h));
			}
			
			for(int h=0; h<base.get(k).ListEnemyFields.size(); h++)
			{
				destination.get(k).ListEnemyFields.add(base.get(k).ListEnemyFields.get(h));
			}
			
			for(int h=0; h<base.get(k).ListPlayerFields.size(); h++)
			{
				destination.get(k).ListPlayerFields.add(base.get(k).ListPlayerFields.get(h));
			}
		}
		
		return destination;
	}
	
}

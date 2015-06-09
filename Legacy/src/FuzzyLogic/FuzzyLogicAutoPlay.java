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
		
		//for(;;)
		for(int iteration=0; iteration<20; iteration++)
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
					System.out.println("Gracz ma jednostki na obecnym polu: " + i + " ---------------");
					
					// nowy element listy dla naszego aktualnego pola 
					fieldsData.add(new FuzzyLogicFieldData(i));
					
					// sprawdzic sasiadow dla naszego pola
					for(int j=0; j < actualPlayer.getGameField().get(i).getNeighbours().size(); j++)
					{
						// Punkt na id pola
						int fieldNumber = translateCoordinates(actualPlayer.getGameField().get(i).getNeighbours().get(j));
						
						System.out.println("Id sasiada: " + fieldNumber 
								+ " X: " + actualPlayer.getGameField().get(i).getNeighbours().get(j).x
								+ " Y: " + actualPlayer.getGameField().get(i).getNeighbours().get(j).y);
						
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
			
			
			// algorytm bedzie decydowal kiedy ekspansja/przekazanie jednostek a kiedy atak
			
			int zmiennaKtoraBedzieZFcla = 8;
			int licznikDlaTejZmiennej = 0; // dwa zrobic osobno dla graczy
			boolean czyBylaEkspansja = false;
			
			
			List<FuzzyLogicFieldData> fieldsData2 = copyList(fieldsData);
			
			System.out.println("-------------------------- RUCH - START ---------------------------------");
			////////////////////////////////////Ekspansja //////////////////////////////////
			// FCL - jesli jakas zmienna mniejsza od X to ekspansja
			if(licznikDlaTejZmiennej < zmiennaKtoraBedzieZFcla)
			{
				// ekspansja
				
				while(true) // po polach
				{
					if(fieldsData.isEmpty())
						break;
					
					// losujemy element z listy fieldsData, ktora zawiera nasze pola
					int losowyElementZFieldsData = (1+ (int)(Math.random()*fieldsData.size())) -1;
					// id pola dla losowego elementu
					int idLosowegoElementu = fieldsData.get( losowyElementZFieldsData ).id;
					
					
					
					// po sasiadach pola
					if(fieldsData.get(losowyElementZFieldsData).ListEmptyFields.size()!=0)
					{
						int iloscPustychSasiadow = fieldsData.get(losowyElementZFieldsData).ListEmptyFields.size();
						
						int idLosowegoSasiada = (1+ (int)(Math.random()*iloscPustychSasiadow)) -1;
						int idPolaLosowegoSasiada = fieldsData.get(losowyElementZFieldsData).ListEmptyFields.get(idLosowegoSasiada);
						
						moveDataStructure.sourceIndex = idLosowegoElementu;
						moveDataStructure.targetIndex = idPolaLosowegoSasiada;
						moveDataStructure.howMany = (gameField.get(idLosowegoElementu).getSoldiers()-1);
						game.makeMove();
						
						czyBylaEkspansja = true;
						
						System.out.println("Ruch na puste pole! Z: " + idLosowegoElementu + " Na: " + idPolaLosowegoSasiada
								+ " Ile: " + (actualPlayer.getGameField().get(idLosowegoElementu).getSoldiers()-1));
						
						break;
					}
					else if(!fieldsData.get(losowyElementZFieldsData).ListEnemyFields.isEmpty())
					{
						// walka :)
						
						// jesli nikt nie mial szansy na wygrana to wywalic element tez
						
						int iloscPustychSasiadow = fieldsData.get(losowyElementZFieldsData).ListEnemyFields.size();
						
						int idLosowegoSasiada = ((int)(Math.random()*iloscPustychSasiadow));
						int idPolaLosowegoSasiada = fieldsData.get(losowyElementZFieldsData).ListEnemyFields.get(idLosowegoSasiada);
						
						moveDataStructure.sourceIndex = idLosowegoElementu;
						moveDataStructure.targetIndex = idPolaLosowegoSasiada;
						moveDataStructure.howMany = actualPlayer.getGameField().get(idLosowegoElementu).getSoldiers()-1;
						game.makeMove();
						
						czyBylaEkspansja = true;
						
						System.out.println("Ruch na wrogie pole! Z: " + idLosowegoElementu + " Na: " + idPolaLosowegoSasiada
								+ " Ile: " + (actualPlayer.getGameField().get(idLosowegoElementu).getSoldiers()-1));
						
						break;
					}
					else
					{
						fieldsData.remove(losowyElementZFieldsData);
					}
					
					
				}
				
				
				licznikDlaTejZmiennej++;
			}
			
			//////////////////////////////////// Do³adowanie jednostek //////////////////////////////////
			if(licznikDlaTejZmiennej >= zmiennaKtoraBedzieZFcla || !czyBylaEkspansja)
			{
				// do³adowanie jednostek
				
				
				while(true)
				{
					// losujemy element z listy fieldsData, ktora zawiera nasze pola
					int losowyElementZFieldsData = ((int)(Math.random()*fieldsData2.size()));
					// id pola dla losowego elementu
					int idLosowegoElementu = fieldsData2.get( losowyElementZFieldsData ).id;
					
					
					// na razie zakladam ze ma sasiadow jako puste pola
					
					if(!fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.isEmpty())
					{
						int iloscPustychSasiadow = fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.size();
						
						int idLosowegoSasiada = ((int)(Math.random()*iloscPustychSasiadow));
						int idPolaLosowegoSasiada = fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.get(idLosowegoSasiada);
						
						moveDataStructure.sourceIndex = idLosowegoElementu;
						moveDataStructure.targetIndex = idPolaLosowegoSasiada;
						moveDataStructure.howMany = actualPlayer.getGameField().get(idLosowegoElementu).getSoldiers()-1;
						game.makeMove();
						
						czyBylaEkspansja = true;
						
						System.out.println("Ruch na nasze pole! Z: " + idLosowegoElementu + " Na: " + idPolaLosowegoSasiada
								+ " Ile: " + (actualPlayer.getGameField().get(idLosowegoElementu).getSoldiers()-1));
						
						break;
						
					}
					else
					{
						fieldsData2.remove(losowyElementZFieldsData);
					}
					
					
				}
				licznikDlaTejZmiennej = 0;
			}
			System.out.println("-------------------------- RUCH - KONIEC ---------------------------------");
			
			
			System.out.println("--------------- ROZMYTA - KONIEC -------------------------------------------------------");
			
			
			// example move
			//moveDataStructure.sourceIndex = 0;
			//moveDataStructure.targetIndex = 1;
			//moveDataStructure.howMany = 20;
			//game.makeMove();
			
			
			
			// update player
			if(actualPlayer.getPlayerType() == PlayerType.PlayerA) {
				actualPlayer = redPlayer;
			}
			else {
				actualPlayer = bluePlayer;
			}
			
			// clear list for next player
			fieldsData.removeAll(fieldsData);
			
			
			//if( WYGRANA )
			//	break;
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

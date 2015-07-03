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
	private gameController gc;
	private SISEGame game;
	
	private FuzzyLogicControl fuzzyLogicControlForBluePlayer; 
	private FuzzyLogicControl fuzzyLogicControlForRedPlayer;
	private FuzzyLogicControl actualFuzzyLogicControl;
	
	private String autoBluePlayer;
	private String autoRedPlayer;
	
	private int actualPlayerIteration, otherPlayerIteration;
	private int actualPlayerMaxSoldiers, otherPlayerMaxSoldiers, maxSoldiersInCurrentRound;
	
	private double fuzzyFieldControlled;
		
	
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
		autoBluePlayer = new String("Dobrotek");
		autoRedPlayer = new String("Polka");
		
		// Blue Player FCL files
		this.fuzzyLogicControlForBluePlayer = new FuzzyLogicControl(
				new StringBuilder("src/FuzzyLogic/").append(autoBluePlayer).append("/fieldsControled.fcl").toString());
		// Red Player FCL files
		this.fuzzyLogicControlForRedPlayer = new FuzzyLogicControl(
				new StringBuilder("src/FuzzyLogic/").append(autoRedPlayer).append("/fieldsControled.fcl").toString());
	}

	public void gameMainLoop()
	{
		List<FuzzyLogicFieldData> fieldsData = new ArrayList<FuzzyLogicFieldData>();
		
		// BARDZO TYMCZASOWA WERSJA DLATEGO JEST TU BURDEL JESZCZE :P
		// udaje ze cos robie, ale kiedys zrobie :v
		
		actualPlayerIteration = 0;
		otherPlayerIteration = 0;
		
		for(;;)
		{
			if(actualPlayer.getPlayerType() == PlayerType.PlayerA) {
				otherPlayer = redPlayer;
				actualFuzzyLogicControl = fuzzyLogicControlForBluePlayer;
			}
			else {
				otherPlayer = bluePlayer;
				actualFuzzyLogicControl = fuzzyLogicControlForRedPlayer;
			}
			
			actualPlayerMaxSoldiers = 0;
			otherPlayerMaxSoldiers = 0;
			maxSoldiersInCurrentRound = 0;
			
			// zapisanie id naszych pol i informacji o ich sasiadach do specjalnego typu: FuzzyLogicFieldData
			for(int i=0; i< actualPlayer.getGameField().size(); i++)
			{
				// znalezienie pol z jednostkami gracza
				if(actualPlayer.getGameField().get(i).getSoldiersType() == actualPlayer.getPlayerType())
				{
					// nowy element listy dla naszego aktualnego pola 
					fieldsData.add(new FuzzyLogicFieldData(i));
					
					// sprawdzic sasiadow dla naszego pola
					for(int j=0; j < actualPlayer.getGameField().get(i).getNeighbours().size(); j++)
					{
						// Punkt na id pola
						int fieldNumber = translateCoordinates(actualPlayer.getGameField().get(i).getNeighbours().get(j));
						
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
			
			// get max soldiers for players in current round
			for(int s=0; s < gameField.size(); s++)
			{
				if(gameField.get(s).getSoldiersType() == actualPlayer.getPlayerType())
					actualPlayerMaxSoldiers += gameField.get(s).getSoldiers();
				else if(gameField.get(s).getSoldiersType() == otherPlayer.getPlayerType())
					otherPlayerMaxSoldiers += gameField.get(s).getSoldiers();
			}
			maxSoldiersInCurrentRound = actualPlayerMaxSoldiers + otherPlayerMaxSoldiers;
			
			////////////////////////////////////// FCL  //////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////////////
			// okreslamy jak czesto mamy zdobywac nowe pola, a jak czesto uzupelniac obecne
			fuzzyFieldControlled = actualFuzzyLogicControl.getFuzzyFieldsControled(
					actualPlayer.getControlledFields(),		// my Fields
					(25 - (actualPlayer.getControlledFields() + otherPlayer.getControlledFields())),	// empty fields
					otherPlayer.getControlledFields(),	// enemy fields
					((actualPlayerMaxSoldiers * 100)/maxSoldiersInCurrentRound), // my soldieres &
					((otherPlayerMaxSoldiers * 100)/maxSoldiersInCurrentRound) // enemy soldiers %	
			);	
			//////////////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			boolean czyBylaEkspansja = false;
			
			List<FuzzyLogicFieldData> fieldsData2 = copyList(fieldsData);
			
			System.out.println("-------------------------- RUCH - START ---------------------------------");
			
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
					int idLosowegoElementu = fieldsData.get(losowyElementZFieldsData).id;
					
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
						int idLosowegoSasiada = ((int)(Math.random()*iloscPustychSasiadow));
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
					else if(!fieldsData.get(losowyElementZFieldsData).ListEnemyFields.isEmpty()) // walka
					{
						// wywalenie wrogow z ktorymi nie mamy szansy na wygrana
						for(int i=0; i < fieldsData.get(losowyElementZFieldsData).ListEnemyFields.size(); i++)
						{
							int idPolaWroga = fieldsData.get(losowyElementZFieldsData).ListEnemyFields.get(i);
							
							if( actualPlayer.getGameField().get(idLosowegoElementu).getSoldiers() <= 
									gameField.get(idPolaWroga).getSoldiers()) 
							{
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
					else // jesli nie ma sasiadow z pustymi polami albo z przeciwnikami
					{
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

					// szukam pola z najwieksza iloscia jednostek
					for(int i=0, soldiers = 0; i<fieldsData2.size(); i++)
					{
//						System.out.println("Ilosc jednostek na  polu: " + fieldsData2.get(i).id + " wynosi:" 
//								+ gameField.get(fieldsData2.get(i).id).getSoldiers());
						
						if(gameField.get(fieldsData2.get(i).id).getSoldiers() > soldiers) {
							losowyElementZFieldsData = i;
							idLosowegoElementu = fieldsData2.get(i).id;
							soldiers = gameField.get(fieldsData2.get(i).id).getSoldiers();
						}	
					}

					// szukamy sasiadow z naszymi jednostkami zeby moc im przekazac wsparcie :)
					if(!fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.isEmpty())
					{
						int iloscNaszychSasiadow = fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.size();
						
						// tymczasowa lista do przechowywania id sasiadow
						List<Integer> idKolejnychSasiadow = new ArrayList<Integer>();
						
						// zbieram sasiadow na kolejnych polach, ktorym moge przekazac jednostki
						for(int i=0; i<iloscNaszychSasiadow; i++)
						{
							if(actualPlayer.getPlayerType() == PlayerType.PlayerA) {
								if(fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.get(i) > fieldsData2.get(losowyElementZFieldsData).id)
									idKolejnychSasiadow.add(fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.get(i));
							}
							else {
								if(fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.get(i) < fieldsData2.get(losowyElementZFieldsData).id)
									idKolejnychSasiadow.add(fieldsData2.get(losowyElementZFieldsData).ListPlayerFields.get(i));
							}
						}
						
						// jesli nie ma komu przekazac to nie atakuje tylko szukamy kolejnego pola, ktore moze sie podzielic
						if(idKolejnychSasiadow.isEmpty()) {
							fieldsData2.remove(losowyElementZFieldsData);
							continue;
						}
							
						int idLosowegoSasiada = ((int)(Math.random()*idKolejnychSasiadow.size()));
						int idPolaLosowegoSasiada = idKolejnychSasiadow.get(idLosowegoSasiada);
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
				
				if(fuzzyFieldControlled > 0)
					actualPlayerIteration = 0;
				else 
				{
					actualPlayerIteration--;
				}
			}
			
			// domyslny ruch jesli nie zostanie wykonany jakis konkretny wczesniej
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
			

			// AKTUALIZACJE DANYCH PO RUNDZIE
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
			
			// check the winner
			if(gameField.get(24).getSoldiersType() == PlayerType.PlayerA) 
			{
				gc = new gameController(gameField, bluePlayer, redPlayer, actualPlayer, moveDataStructure, game);
				gc.winnerFuzzyLogic("Niebieski");
				break;
			}
			else if (gameField.get(0).getSoldiersType() == PlayerType.PlayerB)
			{
				gc = new gameController(gameField, bluePlayer, redPlayer, actualPlayer, moveDataStructure, game);
				gc.winnerFuzzyLogic("Czerwony");
				break;
			}
		}
		
//		for(int i=1; i<25; i++)
//		{
//			System.out.println("Nr: " + i + " Value:" 
//					+ fuzzyLogicControlForBluePlayer.getFuzzyFieldsControled(i));
//		}
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

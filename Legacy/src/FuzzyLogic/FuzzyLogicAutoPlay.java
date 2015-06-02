package FuzzyLogic;

import game.FieldUnit;
import game.MoveDataStructure;
import game.Player;
import game.PlayerType;
import game.SISEGame;

import java.util.ArrayList;

import view.gameController;

public class FuzzyLogicAutoPlay {

	private Player bluePlayer;
    private Player redPlayer;
    private Player actualPlayer;
   
	private MoveDataStructure moveDataStructure;
	private ArrayList<FieldUnit> gameField;
	private gameController gc;
	SISEGame game;
	
	FuzzyLogicControl fuzzyLogicControlForBluePlayer; 
	FuzzyLogicControl fuzzyLogicControlForRedPlayer;
		
	public FuzzyLogicAutoPlay(ArrayList<FieldUnit> gameField, Player bluePlayer,
			Player redPlayer, Player actualPlayer,
			MoveDataStructure moveDataStructure, SISEGame siseGame,
			FuzzyLogicControl fuzzyLogicControlForBluePlayer, FuzzyLogicControl fuzzyLogicControlForRedPlayer) 
	{
		this.gameField = gameField;
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.moveDataStructure = moveDataStructure;
		this.actualPlayer = actualPlayer;
		this.game = siseGame;
		
		this.fuzzyLogicControlForBluePlayer = fuzzyLogicControlForBluePlayer;
		this.fuzzyLogicControlForRedPlayer = fuzzyLogicControlForRedPlayer;
	}

	public void gameMainLoop()
	{
		double fuzzyFieldControlled;
		double fuzzyFightChances;
		double fuzzyUnitsPerField;
		double fuzzyUnitsRatioToBase;
		if(actualPlayer.getPlayerType() == PlayerType.PlayerA){
			fuzzyFieldControlled = fuzzyLogicControlForBluePlayer.getFuzzyFieldsControled(bluePlayer.getControlledFields());
			fuzzyFightChances = fuzzyLogicControlForBluePlayer.getFuzzyFightChances(
					bluePlayer.countAllSoldiers(), 
					redPlayer.countAllSoldiers());
			fuzzyUnitsPerField = fuzzyLogicControlForBluePlayer.getFuzzyUnitsPerField(
					bluePlayer.countAllSoldiers(), 
					bluePlayer.getGameField().get(0).getSoldiers()); 
			fuzzyUnitsRatioToBase = fuzzyLogicControlForBluePlayer.getFuzzyUnitsRatioToBase(
					bluePlayer.getBase().getSoldiers(), 
					bluePlayer.countAllSoldiers());
		}
		else{
			fuzzyFieldControlled = fuzzyLogicControlForRedPlayer.getFuzzyFieldsControled(redPlayer.getControlledFields());
			fuzzyFightChances = fuzzyLogicControlForRedPlayer.getFuzzyFightChances(
					redPlayer.countAllSoldiers(), 
					bluePlayer.countAllSoldiers());
			fuzzyUnitsPerField = fuzzyLogicControlForRedPlayer.getFuzzyUnitsPerField(
					redPlayer.countAllSoldiers(), 
					redPlayer.getGameField().get(24).getSoldiers() );
			fuzzyUnitsRatioToBase = fuzzyLogicControlForRedPlayer.getFuzzyUnitsRatioToBase(
					redPlayer.getBase().getSoldiers(), 
					redPlayer.countAllSoldiers());
		}
		
		
		
		// tu sie b�dzie bralo  dane o gamefieldzie i wysylalo do ai
		
		//zwrocone rzeczy wygladaja mniej wiecej tak
		moveDataStructure.sourceIndex = 0; //zrodlo ruchu
		moveDataStructure.targetIndex = 1; //cel ruchu
		moveDataStructure.howMany = 20; //ile woja;brak zabezpieczenia przed podaniem za duzej wartosci poki co
		
		game.makeMove();//wykonanie ruchu dla actualPlayera i wszystkie inne rzeczy z automatu tu;
		//to konczy ture i zaczyna ture nastepnego gracza
		moveDataStructure.sourceIndex =24; //zrodlo ruchu
		moveDataStructure.targetIndex = 23; //cel ruchu
		moveDataStructure.howMany = 20; //ile woja
		
		game.makeMove();//wykonanie ruchu dla actualPlayera i wszystkie inne rzeczy z automatu tu;
		
		moveDataStructure.sourceIndex =0; //zrodlo ruchu
		moveDataStructure.targetIndex = 5; //cel ruchu
		moveDataStructure.howMany = 5; //ile woja
		
		game.makeMove();//wykonanie ruchu dla actualPlayera i wszystkie inne rzeczy z automatu tu;	
	}
	
}

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
    private Player otherPlayer;
   
	private MoveDataStructure moveDataStructure;
	//private ArrayList<FieldUnit> gameField;
	//private gameController gc;
	private SISEGame game;
	
	private FuzzyLogicControl fuzzyLogicControlForBluePlayer; 
	private FuzzyLogicControl fuzzyLogicControlForRedPlayer;
	private FuzzyLogicControl actualFuzzyLogicControl;
	
	private double fuzzyFieldControlled, fuzzyFightChances, fuzzyUnitsPerField, fuzzyUnitsRatioToBase;
		
	
	public FuzzyLogicAutoPlay(Player bluePlayer, Player redPlayer, Player actualPlayer,
			MoveDataStructure moveDataStructure, SISEGame game,
			FuzzyLogicControl fuzzyLogicControlForBluePlayer, FuzzyLogicControl fuzzyLogicControlForRedPlayer) 
	{
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.actualPlayer = actualPlayer;
		this.moveDataStructure = moveDataStructure;
		this.game = game;
		this.fuzzyLogicControlForBluePlayer = fuzzyLogicControlForBluePlayer;
		this.fuzzyLogicControlForRedPlayer = fuzzyLogicControlForRedPlayer;
	}

	public void gameMainLoop()
	{
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
			// example move
			moveDataStructure.sourceIndex = 0;
			moveDataStructure.targetIndex = 1;
			moveDataStructure.howMany = 20;
			
			game.makeMove();
			
			//if( WYGRANA )
				break;
		}
		
	}
	
}

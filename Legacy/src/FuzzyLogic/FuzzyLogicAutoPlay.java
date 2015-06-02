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
			// next steps...
			fuzzyFightChances = actualFuzzyLogicControl.getFuzzyFightChances(
					actualPlayer.countAllSoldiers(), 
					otherPlayer.countAllSoldiers());
			fuzzyUnitsPerField = actualFuzzyLogicControl.getFuzzyUnitsPerField(
					actualPlayer.countAllSoldiers(), 
					actualPlayer.getGameField().get(0).getSoldiers()); 
			fuzzyUnitsRatioToBase = actualFuzzyLogicControl.getFuzzyUnitsRatioToBase(
					actualPlayer.getBase().getSoldiers(), 
					actualPlayer.countAllSoldiers());
			
			
			
			
			// temp move
			moveDataStructure.sourceIndex = 0;
			moveDataStructure.targetIndex = 1;
			moveDataStructure.howMany = 20;
			
			game.makeMove();
			
			
			//if( WYGRANA )
				break;
		}
		
	}
	
}

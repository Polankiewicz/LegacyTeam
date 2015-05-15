package FuzzyLogic;

import game.FieldUnit;
import game.MoveDataStructure;
import game.Player;

import java.util.ArrayList;

public class FuzzyLogicController {
	
	private Player bluePlayer;
    private Player redPlayer;
    
	public FuzzyLogicController(Player bluePlayer, Player redPlayer) {
		super();
		this.bluePlayer = bluePlayer;
		this.redPlayer = redPlayer;
	}

	public Player getBluePlayer() {
		return bluePlayer;
	}

	public Player getRedPlayer() {
		return redPlayer;
	}
	
	public boolean evaluateMove(MoveDataStructure moveDataStructure){
		
		//wczytywanie fcli, na ich podstawie ocenianie czy przesunac sie z danego pola na inne i zwrocic decyzje
		return true;
	}
}

package game;

import java.util.ArrayList;

import view.gameController;

public class TestController {
	 private Player bluePlayer;
	    private Player redPlayer;
	    private Player actualPlayer;
	   
		private MoveDataStructure moveDataStructure;
		private ArrayList<FieldUnit> gameField;
		private gameController gc;
		SISEGame game;
		
	public TestController(ArrayList<FieldUnit> gameField, Player bluePlayer,
			Player redPlayer, Player actualPlayer,
			MoveDataStructure moveDataStructure, SISEGame siseGame) {
		
		this.gameField = gameField;
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.moveDataStructure = moveDataStructure;
		this.actualPlayer = actualPlayer;
		this.game = siseGame;
		
		
	}

	
	public void gameMainLoop()
	{
		
		// tu sie b�dzie bralo  dane o gamefieldzie i wysylalo do ai
		
		//zwrocone rzeczy wygladaja mniej wiecej tak
			moveDataStructure.sourceIndex = 0; //zrodlo ruchu
			moveDataStructure.targetIndex = 1; //cel ruchu
			moveDataStructure.howMany = 20; //ile woja;brak zabezpieczenia przed podaniem za duzej wartosci poki co
			
			game.makeMove();//wykonanie ruchu dla actualPlayera i wszystkie inne rzeczy z automatu tu;
			//to konczy ture i zaczyna ture nastepnego gracza
			moveDataStructure.sourceIndex = 24; //zrodlo ruchu
			moveDataStructure.targetIndex = 23; //cel ruchu
			moveDataStructure.howMany = 20; //ile woja
			
			game.makeMove();//wykonanie ruchu dla actualPlayera i wszystkie inne rzeczy z automatu tu;
			
			moveDataStructure.sourceIndex = 0; //zrodlo ruchu
			moveDataStructure.targetIndex = 5; //cel ruchu
			moveDataStructure.howMany = 5; //ile woja
			
			game.makeMove();//wykonanie ruchu dla actualPlayera i wszystkie inne rzeczy z automatu tu;
			
	}
	
	
	
}

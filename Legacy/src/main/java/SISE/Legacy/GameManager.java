package SISE.Legacy;

import java.util.Collection;

public class GameManager {
	
	Collection<FieldUnit> gameField;
	Player playerA;
	Player playerB;
	public GameManager(Collection<FieldUnit> gameField, Player playerA,
			Player playerB) {
		this.gameField = gameField;
		this.playerA = playerA;
		this.playerB = playerB;
	}
	
	public void start() {
		
	}
}

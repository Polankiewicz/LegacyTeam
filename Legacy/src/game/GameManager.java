package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

public class GameManager {
	
	private ArrayList<FieldUnit> gameField;
	private static final int GAME_FIELD_WIDTH_HEIGHT_SIZE = 5;
	private static final int INITIAL_SOLDIERS_QUANTITY = 50;
	private Player playerA;
	private Player playerB;
	
	public GameManager(Player playerA, Player playerB) {
		this.gameField = new ArrayList<FieldUnit>();
		this.playerA = playerA;
		this.playerB = playerB;
		this.createGameField();
	}
	

	private void createGameField() {
		for (int i = 0; i <= GAME_FIELD_WIDTH_HEIGHT_SIZE; i++) {
			for (int j = 0; j <= GAME_FIELD_WIDTH_HEIGHT_SIZE; j++) {
				if (i == 0 && j == 0) {
					this.gameField.add(new Base(new Point(i, j), INITIAL_SOLDIERS_QUANTITY , PlayerType.PlayerA, BonusType.NONE));
				}
				else if (i == GAME_FIELD_WIDTH_HEIGHT_SIZE && j == GAME_FIELD_WIDTH_HEIGHT_SIZE) {
					this.gameField.add(new Base(new Point(i, j), INITIAL_SOLDIERS_QUANTITY , PlayerType.PlayerB, BonusType.NONE));
				}
				else {
					this.gameField.add(new FieldUnit(new Point(i, j), 0, PlayerType.NoOne, BonusType.NONE));
				}
			}
		}
	}

	public void start() {
		
	}
	
	public ArrayList<FieldUnit> getGameField() {
		return new ArrayList<FieldUnit>(gameField);
	}

	public static int getGameFieldWidthHeightSize() {
		return GAME_FIELD_WIDTH_HEIGHT_SIZE;
	}
}

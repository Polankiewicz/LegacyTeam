package SISE.Legacy;

import java.awt.Point;
import java.util.Collection;

public class Player {

	Base base;
	Collection<FieldUnit> gameField;
	PlayerType playerType;
	
	public Player(Base base, Collection<FieldUnit> gameField,
			PlayerType playerType) {
		super();
		this.base = base;
		this.gameField = gameField;
		this.playerType = playerType;
	}
	
	public FieldUnit fieldUnitStatus(Point fu) {
		return null;
	}
	
	public boolean move(int howMany, Point fu) {
		return false;
	}
}

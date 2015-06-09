package game;

import java.awt.Point;
import java.util.Collection;

public class Base extends FieldUnit {

	private float multiplier;
		
	public Base(Point coordinates, int soldiers, PlayerType soldiersType, BonusType bonusType, int index) 
	{
		super(coordinates, soldiers, soldiersType, bonusType, index);
		// TODO Auto-generated constructor stub
	}

	private void setMultiplayer(Collection<FieldUnit> gf) {
		
	}
}

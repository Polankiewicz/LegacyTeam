package SISE.Legacy;

import java.awt.Point;
import java.util.Collection;

public class FieldUnit {
	
	Point coordinates;
	Collection<Point> neighbours;
	int soldiers;
	char soldiersType;
	BonusType bonusType;
	
	public FieldUnit(Point coordinates, int soldiers, char soldiersType,
			BonusType bonusType) {
		this.coordinates = coordinates;
		this.soldiers = soldiers;
		this.soldiersType = soldiersType;
		this.bonusType = bonusType;
		this.neighbours = calculateNeighbours();
	}
	
	private Collection<Point> calculateNeighbours() {
		return null;
	}
}

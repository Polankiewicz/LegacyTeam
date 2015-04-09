package SISE.Legacy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

public class FieldUnit {
	
	private Point coordinates;
	private ArrayList<Point> neighbours;
	private int soldiers;
	private char soldiersType;
	private BonusType bonusType;
	
	public int getSoldiers() {
		return soldiers;
	}

	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}

	public char getSoldiersType() {
		return soldiersType;
	}

	public void setSoldiersType(char soldiersType) {
		this.soldiersType = soldiersType;
	}

	public BonusType getBonusType() {
		return bonusType;
	}

	public void setBonusType(BonusType bonusType) {
		this.bonusType = bonusType;
	}

	public Point getCoordinates() {
		return coordinates;
	}

	public ArrayList<Point> getNeighbours() {
		return new ArrayList<Point>(neighbours);
	}

	public FieldUnit(Point coordinates, int soldiers, char soldiersType,
			BonusType bonusType) {
		this.coordinates = coordinates;
		this.soldiers = soldiers;
		this.soldiersType = soldiersType;
		this.bonusType = bonusType;
		this.neighbours = new ArrayList<Point>();
		this.calculateNeighbours();
	}
	
	private void calculateNeighbours() {
		if (!neighbours.isEmpty()) {
			neighbours.clear();
		}
		
		neighbours.add(new Point(coordinates.x, coordinates.y + 1));
		neighbours.add(new Point(coordinates.x, coordinates.y -1));
		neighbours.add(new Point(coordinates.x + 1, coordinates.y - 1));
		neighbours.add(new Point(coordinates.x + 1, coordinates.y));
		neighbours.add(new Point(coordinates.x - 1, coordinates.y + 1));
		neighbours.add(new Point(coordinates.x - 1, coordinates.y));
	}
}

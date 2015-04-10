package SISE.Legacy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FieldUnit {
	
	private Point coordinates;
	private ArrayList<Point> neighbours;
	private int soldiers;
	private PlayerType soldiersType;
	private BonusType bonusType;
	

	public FieldUnit(Point coordinates, int soldiers, PlayerType soldiersType,
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
		if (isNotFieldUnitAtTheEdge()) {
			neighbours.add(new Point(coordinates.x, coordinates.y + 1));
			neighbours.add(new Point(coordinates.x, coordinates.y -1));
			neighbours.add(new Point(coordinates.x + 1, coordinates.y - 1));
			neighbours.add(new Point(coordinates.x + 1, coordinates.y));
			neighbours.add(new Point(coordinates.x - 1, coordinates.y + 1));
			neighbours.add(new Point(coordinates.x - 1, coordinates.y));
		}
		else {
			addNeighboursOfFieldUnitsFromTheEdges();
		}
		
	}
	
	private void addNeighboursOfFieldUnitsFromTheEdges() {
		if (coordinates.x > 0 && coordinates.x < GameManager.getGameFieldWidthHeightSize() && coordinates.y == 0) {
			neighbours.add(new Point(coordinates.x + 1, coordinates.y));
			neighbours.add(new Point(coordinates.x - 1, coordinates.y + 1));
			neighbours.add(new Point(coordinates.x - 1, coordinates.y));
			neighbours.add(new Point(coordinates.x, coordinates.y + 1));
		}
		else if (coordinates.x > 0 && coordinates.x < GameManager.getGameFieldWidthHeightSize()
				&& coordinates.y == GameManager.getGameFieldWidthHeightSize()) {
			neighbours.add(new Point(coordinates.x - 1, coordinates.y));
			neighbours.add(new Point(coordinates.x + 1, coordinates.y));
			neighbours.add(new Point(coordinates.x, coordinates.y -1));
			neighbours.add(new Point(coordinates.x + 1, coordinates.y - 1));
		}
		else if (coordinates.x == 0 && coordinates.y < GameManager.getGameFieldWidthHeightSize()
				&& coordinates.y > 0) {
			neighbours.add(new Point(coordinates.x, coordinates.y + 1));
			neighbours.add(new Point(coordinates.x, coordinates.y -1));
			neighbours.add(new Point(coordinates.x + 1, coordinates.y - 1));
			neighbours.add(new Point(coordinates.x + 1, coordinates.y));
		}
		else if (coordinates.x == GameManager.getGameFieldWidthHeightSize() 
				&& coordinates.y < GameManager.getGameFieldWidthHeightSize() && coordinates.y > 0) {
			neighbours.add(new Point(coordinates.x, coordinates.y + 1));
			neighbours.add(new Point(coordinates.x, coordinates.y -1));
			neighbours.add(new Point(coordinates.x - 1, coordinates.y + 1));
			neighbours.add(new Point(coordinates.x - 1, coordinates.y));
		}
		else if (coordinates.x == GameManager.getGameFieldWidthHeightSize()
				&& coordinates.y == GameManager.getGameFieldWidthHeightSize()) {
			neighbours.add(new Point(coordinates.x - 1, coordinates.y));
			neighbours.add(new Point(coordinates.x, coordinates.y -1));
		}
		else if (coordinates.x == 0 && coordinates.y == 0) {
			neighbours.add(new Point(coordinates.x + 1, coordinates.y));
			neighbours.add(new Point(coordinates.x, coordinates.y + 1));
		}
		else if (coordinates.x == 0 && coordinates.y == GameManager.getGameFieldWidthHeightSize()) {
			neighbours.add(new Point(coordinates.x, coordinates.y -1));
			neighbours.add(new Point(coordinates.x + 1, coordinates.y - 1));
			neighbours.add(new Point(coordinates.x + 1, coordinates.y));
		}
		else if (coordinates.x == GameManager.getGameFieldWidthHeightSize() && coordinates.y == 0) {
			neighbours.add(new Point(coordinates.x - 1, coordinates.y + 1));
			neighbours.add(new Point(coordinates.x - 1, coordinates.y));
			neighbours.add(new Point(coordinates.x, coordinates.y + 1));
		}
		
	}

	private boolean isNotFieldUnitAtTheEdge() {
		if (coordinates.x == 0 || coordinates.y == 0
				|| coordinates.x == GameManager.getGameFieldWidthHeightSize()
				|| coordinates.y == GameManager.getGameFieldWidthHeightSize())
			return false;
		else
			return true;
	}
	
	
	public int getSoldiers() {
		return soldiers;
	}
	
	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}
	
	public PlayerType getSoldiersType() {
		return soldiersType;
	}
	
	public void setSoldiersType(PlayerType soldiersType) {
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
}

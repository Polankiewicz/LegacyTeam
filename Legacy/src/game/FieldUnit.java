package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import view.gameController;

public class FieldUnit {
	
	private Point coordinates;
	private ArrayList<Point> neighbours;
	private int soldiers;
	private PlayerType soldiersType;
	private BonusType bonusType;
	

	public FieldUnit(Point coordinates, int soldiers, PlayerType soldiersType, BonusType bonusType) 
	{
		this.coordinates = coordinates;
		this.soldiers = soldiers;
		this.soldiersType = soldiersType;
		this.bonusType = bonusType;
		this.neighbours = new ArrayList<Point>();
		this.calculateNeighbours();
	}
	
	private void calculateNeighbours() 
	{
		if (!neighbours.isEmpty()) 
			neighbours.clear();
		
		if (isNotFieldUnitAtTheEdge()) 
		{
			neighbours.add(new Point(coordinates.x, coordinates.y + 1));
			neighbours.add(new Point(coordinates.x, coordinates.y -1));
			neighbours.add(new Point(coordinates.x + 1, coordinates.y - 1));
			neighbours.add(new Point(coordinates.x + 1, coordinates.y));
			neighbours.add(new Point(coordinates.x - 1, coordinates.y + 1));
			neighbours.add(new Point(coordinates.x - 1, coordinates.y));
		}
		else 
			addNeighboursOfFieldUnitsFromTheEdges();
	}
	
	// ula la - takie przed zmiana
	private void addNeighboursOfFieldUnitsFromTheEdges() 
	{
		if (coordinates.y > 0 && coordinates.y < gameController.getGameFieldWidthHeightSize() && coordinates.x == 0) 
		{
			neighbours.add(new Point(coordinates.y + 1, coordinates.x));
			neighbours.add(new Point(coordinates.y - 1, coordinates.x + 1));
			neighbours.add(new Point(coordinates.y - 1, coordinates.x));
			neighbours.add(new Point(coordinates.y, coordinates.x + 1));
		}
		else if (coordinates.y > 0 && coordinates.y < gameController.getGameFieldWidthHeightSize()
				&& coordinates.x == gameController.getGameFieldWidthHeightSize()) 
		{
			neighbours.add(new Point(coordinates.y - 1, coordinates.x));
			neighbours.add(new Point(coordinates.y + 1, coordinates.x));
			neighbours.add(new Point(coordinates.y, coordinates.x -1));
			neighbours.add(new Point(coordinates.y + 1, coordinates.x - 1));
		}
		else if (coordinates.y == 0 && coordinates.x < gameController.getGameFieldWidthHeightSize()
				&& coordinates.x > 0) 
		{
			neighbours.add(new Point(coordinates.y, coordinates.x + 1));
			neighbours.add(new Point(coordinates.y, coordinates.x -1));
			neighbours.add(new Point(coordinates.y + 1, coordinates.x - 1));
			neighbours.add(new Point(coordinates.y + 1, coordinates.x));
		}
		else if (coordinates.y == gameController.getGameFieldWidthHeightSize() 
				&& coordinates.x < gameController.getGameFieldWidthHeightSize() && coordinates.x > 0) 
		{
			neighbours.add(new Point(coordinates.y, coordinates.x + 1));
			neighbours.add(new Point(coordinates.y, coordinates.x -1));
			neighbours.add(new Point(coordinates.y - 1, coordinates.x + 1));
			neighbours.add(new Point(coordinates.y - 1, coordinates.x));
		}
		else if (coordinates.y == gameController.getGameFieldWidthHeightSize()
				&& coordinates.x == gameController.getGameFieldWidthHeightSize()) 
		{
			neighbours.add(new Point(coordinates.y - 1, coordinates.x));
			neighbours.add(new Point(coordinates.y, coordinates.x -1));
		}
		else if (coordinates.y == 0 && coordinates.x == 0) 
		{
			neighbours.add(new Point(coordinates.y + 1, coordinates.x));
			neighbours.add(new Point(coordinates.y, coordinates.x + 1));
		}
		else if (coordinates.y == 0 && coordinates.x == gameController.getGameFieldWidthHeightSize()) 
		{
			neighbours.add(new Point(coordinates.y, coordinates.x -1));
			neighbours.add(new Point(coordinates.y + 1, coordinates.x - 1));
			neighbours.add(new Point(coordinates.y + 1, coordinates.x));
		}
		else if (coordinates.y == gameController.getGameFieldWidthHeightSize() && coordinates.x == 0) 
		{
			neighbours.add(new Point(coordinates.y - 1, coordinates.x + 1));
			neighbours.add(new Point(coordinates.y - 1, coordinates.x));
			neighbours.add(new Point(coordinates.y, coordinates.x + 1));
		}
	}

	private boolean isNotFieldUnitAtTheEdge() 
	{
		if (coordinates.x == 0 || coordinates.y == 0
				|| coordinates.x == gameController.getGameFieldWidthHeightSize()
				|| coordinates.y == gameController.getGameFieldWidthHeightSize())
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bonusType == null) ? 0 : bonusType.hashCode());
		result = prime * result
				+ ((coordinates == null) ? 0 : coordinates.hashCode());
		result = prime * result
				+ ((neighbours == null) ? 0 : neighbours.hashCode());
		result = prime * result + soldiers;
		result = prime * result
				+ ((soldiersType == null) ? 0 : soldiersType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldUnit other = (FieldUnit) obj;
		if (bonusType != other.bonusType)
			return false;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		if (neighbours == null) {
			if (other.neighbours != null)
				return false;
		} else if (!neighbours.equals(other.neighbours))
			return false;
		if (soldiers != other.soldiers)
			return false;
		if (soldiersType != other.soldiersType)
			return false;
		return true;
	}
}

package game;

import java.awt.Point;
import java.util.ArrayList;

public class Player {

	private Base base;
	private ArrayList<FieldUnit> gameField;
	private PlayerType playerType;
	private int howMany;
	private int controlledFields;
	private String playerNameString;
	private String playerColorString;
	
	public Player(Base base, ArrayList<FieldUnit> gameField, PlayerType playerType, String name, String color) 
	{
		super();
		this.base = base;
		this.gameField = gameField;
		this.playerType = playerType;
		this.controlledFields = 1; //bo baza
		this.setPlayerColorString(color);
		this.setPlayerNameString(name);
	}
	
	public boolean move(int howMany,int destinationPointIndex,int currentPointIndex) //Point destinationPoint, Point currentPoint
	{
		this.howMany=howMany;

		boolean moved=false;
		if(this.playerType==gameField.get(currentPointIndex).getSoldiersType()) 
        {
			FieldUnit source = gameField.get(currentPointIndex);
			if(isFieldNeighbour(destinationPointIndex, currentPointIndex))
			{
				moved=isSoldiersMoved(destinationPointIndex);
				source.setSoldiers(source.getSoldiers()-howMany);
				if(source.getSoldiers()==0 && source != this.base) 
					source.setSoldiersType(PlayerType.NoOne);
			}
        }
		return moved;
	}
	
	public int fieldUnitStatus(Point searchedPoint) 
	{
		int fieldIndex;
		for(fieldIndex=0; fieldIndex<gameField.size(); fieldIndex++)
		{
			if(gameField.get(fieldIndex).getCoordinates().equals(searchedPoint))
				return fieldIndex;
		}
		return fieldIndex;
	}
	
	public void increaseUnitsAmount()
	{
		this.controlledFields=0;
		
		for (FieldUnit e : gameField)
		{
			if(e.getSoldiersType()==this.playerType) 
				this.controlledFields++;
		}
		base.setSoldiers(base.getSoldiers()+this.controlledFields);
	}
	
	public boolean isFieldNeighbour(int destination, int current)
	{ 
		for(Point e : gameField.get(current).getNeighbours()){
			if(e.equals(gameField.get(destination).getCoordinates()))
				return true;
		}
		return false;
	}
	
	private boolean isSoldiersMoved(int destination)
	{
		if(gameField.get(destination).getSoldiersType()==playerType)
		{
			howMany+=gameField.get(destination).getSoldiers();
			gameField.get(destination).setSoldiers(howMany);
			return true;
		} 
		else if(isFieldPlayerChanged(destination))
		{
			gameField.get(destination).setSoldiers(howMany);
			return true;
		}
		return false;
	}
	
	private boolean isFieldPlayerChanged(int destination)
	{
		if(gameField.get(destination).getSoldiersType()==PlayerType.NoOne)
		{
			gameField.get(destination).setSoldiersType(playerType);
			return true;
		}
		else if(gameField.get(destination).getSoldiersType()!=playerType)
		{
			if(isFightWon(destination))
			{
				gameField.get(destination).setSoldiersType(playerType);
				return true;
			}
		}
		return false;
	}
	
	public PlayerType getPlayerType()
	{
		return this.playerType;
	}
	
	private boolean isFightWon(int destination)
	{
		if(howMany>gameField.get(destination).getSoldiers())
		{
			howMany-=gameField.get(destination).getSoldiers();
			return true;
		}
		else
		{
			int newNumberOfSoldiers=gameField.get(destination).getSoldiers()-howMany;
			howMany=0;
			gameField.get(destination).setSoldiers(newNumberOfSoldiers);
		}
		return false;
	}

	public String getPlayerColorString() {
		return playerColorString;
	}

	public void setPlayerColorString(String playerColorString) {
		this.playerColorString = playerColorString;
	}

	public String getPlayerNameString() {
		return playerNameString;
	}

	public void setPlayerNameString(String playerNameString) {
		this.playerNameString = playerNameString;
	}
}

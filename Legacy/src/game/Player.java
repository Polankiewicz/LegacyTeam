package game;

import java.awt.Point;
import java.util.ArrayList;

public class Player {

	Base base;
	ArrayList<FieldUnit> gameField;
	PlayerType playerType;
	
	public Player(Base base, ArrayList<FieldUnit> gameField,
			PlayerType playerType) {
		super();
		this.base = base;
		this.gameField = gameField;
		this.playerType = playerType;
	}
	
	public boolean move(int howMany, Point destinationPoint, Point currentPoint) {
		int destinationPointIndex= fieldUnitStatus(destinationPoint);
		int currentPointIndex= fieldUnitStatus(destinationPoint);
		boolean moved=false;
		if(isFieldNeighbour(destinationPointIndex, currentPointIndex)){
			moved=isSoldiersMoved(howMany, destinationPointIndex);
		}
		return moved;
	}
	
	public int fieldUnitStatus(Point searchedPoint) {
		int fieldIndex;
		for(fieldIndex=0; fieldIndex<gameField.size(); fieldIndex++){
			if(gameField.get(fieldIndex).getCoordinates().equals(searchedPoint)){
				return fieldIndex;
			}
		}
		return fieldIndex;
	}
	
	private boolean isFieldNeighbour(int destination, int current){
		for(Point e : gameField.get(current).getNeighbours()){
			if(e.equals(gameField.get(destination).getCoordinates())){
				return true;
			}
		}
		return false;
	}
	
	private boolean isSoldiersMoved(int howMany, int destination){
		if(gameField.get(destination).getSoldiersType()==playerType){
			gameField.get(destination).setSoldiers(howMany);
			//sprawdzanie czy sa wojska zeby dodac
			return true;
		} 
		else if(isFieldPlayerChanged(howMany, destination)){
			gameField.get(destination).setSoldiers(howMany);
			return true;
		}
		else return false;
	}
	
	private boolean isFieldPlayerChanged(int howMany, int destination){
		if(gameField.get(destination).getSoldiersType()==PlayerType.NoOne){
			gameField.get(destination).setSoldiersType(playerType);
			return true;
		}
		else if(gameField.get(destination).getSoldiersType()!=playerType){
			if(isFightWon(howMany, destination)){
				gameField.get(destination).setSoldiersType(playerType);
				return true;
			}
		}
		return false;
	}
	
	private boolean isFightWon(int howMany, int destination){
		if(howMany>gameField.get(destination).getSoldiers()){
			return true;
		}
		return false;
	}
}

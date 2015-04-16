package SISE.Legacy;

import java.awt.Point;
import java.util.ArrayList;

public class Player {

	private Base base;
	private ArrayList<FieldUnit> gameField;
	private PlayerType playerType;
	private int howMany;
	
	public Player(Base base, ArrayList<FieldUnit> gameField,
			PlayerType playerType) {
		super();
		this.base = base;
		this.gameField = gameField;
		this.playerType = playerType;
	}
	
	public boolean move(int howMany, Point destinationPoint, Point currentPoint) {
		this.howMany=howMany;
		int destinationPointIndex= fieldUnitStatus(destinationPoint);
		int currentPointIndex= fieldUnitStatus(destinationPoint);
		boolean moved=false;
		if(isFieldNeighbour(destinationPointIndex, currentPointIndex)){
			moved=isSoldiersMoved(destinationPointIndex);
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
	
	private boolean isSoldiersMoved(int destination){
		if(gameField.get(destination).getSoldiersType()==playerType){
			howMany+=gameField.get(destination).getSoldiers();
			gameField.get(destination).setSoldiers(howMany);
			return true;
		} 
		else if(isFieldPlayerChanged(destination)){
			gameField.get(destination).setSoldiers(howMany);
			return true;
		}
		return false;
	}
	
	private boolean isFieldPlayerChanged(int destination){
		if(gameField.get(destination).getSoldiersType()==PlayerType.NoOne){
			gameField.get(destination).setSoldiersType(playerType);
			return true;
		}
		else if(gameField.get(destination).getSoldiersType()!=playerType){
			if(isFightWon(destination)){
				gameField.get(destination).setSoldiersType(playerType);
				return true;
			}
		}
		return false;
	}
	
	private boolean isFightWon(int destination){
		if(howMany>gameField.get(destination).getSoldiers()){
			howMany-=gameField.get(destination).getSoldiers();
			return true;
		}
		else{
			int newNumberOfSoldiers=gameField.get(destination).getSoldiers()-howMany;
			howMany=0;
			gameField.get(destination).setSoldiers(newNumberOfSoldiers);
		}
		return false;
	}
}

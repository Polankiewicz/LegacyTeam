package FuzzyLogic;

import java.util.ArrayList;
import java.util.List;

public class FuzzyLogicFieldData implements Cloneable
{
	// nie chcialo mi sie juz bawic w enkapsulacje :P
	public int id;
	public List<Integer> ListEmptyFields = new ArrayList<Integer>();
	public List<Integer> ListEnemyFields = new ArrayList<Integer>();
	public List<Integer> ListPlayerFields = new ArrayList<Integer>();
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public FuzzyLogicFieldData(int id)
	{
		this.id = id;
	}
}

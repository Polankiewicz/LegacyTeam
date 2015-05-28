package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;


public class FuzzyLogicControl {

	private FIS fightChancesFCL;
	private FIS fieldsControledFCL;
	private FIS unitsPerFieldFCL;
	private FIS unitsRatioToBaseFCL;
	
	/*public static void main(String[] args) throws Exception // temp method :P
	{
		FuzzyLogicControl fuzzyLogicControl = new FuzzyLogicControl();
		fuzzyLogicControl.loadFclFiles();
		
		System.out.println(fuzzyLogicControl.getFuzzyFightChances(5,3));
    }*/
	public FuzzyLogicControl(String fightChancesFCL, String fieldsControledFCL,
			String unitsPerFieldFCL, String unitsRatioToBaseFCL) {
		super();
		loadFclFiles(fightChancesFCL, fieldsControledFCL, unitsPerFieldFCL, unitsRatioToBaseFCL);
	}
	
	private void loadFclFiles(String fightChancesFCL, String fieldsControledFCL,
			String unitsPerFieldFCL, String unitsRatioToBaseFCL) 
	{
		// values <0,100> - % my units to enemy units
		this.fightChancesFCL = FIS.load(fightChancesFCL, true);
		// values <0,25> - number of fields
		this.fieldsControledFCL = FIS.load(fieldsControledFCL, true);
		// values <0,100> - % my all units to units on my one field
		this.unitsPerFieldFCL = FIS.load(unitsPerFieldFCL, true);
		// values <0,100> - % units in my base to all my units
		this.unitsRatioToBaseFCL = FIS.load(unitsRatioToBaseFCL, true);
	}
	

	public double getFuzzyFightChances(double myUnits, double enemyUnits)
	{
		//JFuzzyChart.get().chart(fightChancesFCL);

        fightChancesFCL.setVariable("myUnits", myUnits);
        fightChancesFCL.setVariable("enemyUnits", enemyUnits);

        fightChancesFCL.evaluate();
		
		return fightChancesFCL.getVariable("fightResult").getValue();
	}
	
	public double getFuzzyFieldsControled(double myFields)
	{
		//JFuzzyChart.get().chart(fieldsControledFCL);

		fieldsControledFCL.setVariable("myFields", myFields);

		fieldsControledFCL.evaluate();
		
		return fieldsControledFCL.getVariable("fieldsRatio").getValue();
	}
	
	public double getFuzzyUnitsPerField(double allUnits, double unitsOnField)
	{
		//JFuzzyChart.get().chart(unitsPerFieldFCL);

		unitsPerFieldFCL.setVariable("allUnits", allUnits);
		unitsPerFieldFCL.setVariable("unitsOnField", unitsOnField);

		unitsPerFieldFCL.evaluate();
		
		return unitsPerFieldFCL.getVariable("unitsRatio").getValue();
	}
	
	public double getFuzzyUnitsRatioToBase(double unitsInBase, double otherOurUnits)
	{
		//JFuzzyChart.get().chart(unitsRatioToBaseFCL);

		unitsRatioToBaseFCL.setVariable("unitsInBase", unitsInBase);
		unitsRatioToBaseFCL.setVariable("otherOurUnits", otherOurUnits);

		unitsRatioToBaseFCL.evaluate();
		
		return unitsRatioToBaseFCL.getVariable("result").getValue();
	}
	
}

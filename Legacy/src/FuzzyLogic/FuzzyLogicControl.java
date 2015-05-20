package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;


public class FuzzyLogicControl {

	
	public static void main(String[] args) throws Exception // temp method :P
	{
		FuzzyLogicControl fuzzyLogicControl = new FuzzyLogicControl();
		fuzzyLogicControl.loadFclFiles();
    }
	
	public void loadFclFiles() 
	{
		// values <0,100> - % my units to enemy units
		FIS fightChancesFCL = FIS.load("src/FuzzyLogic/fightChances.fcl", true);
		// values <0,25> - number of fields
		FIS fieldsControledFCL = FIS.load("src/FuzzyLogic/fieldsControled.fcl", true);
		// values <0,100> - % my all units to units on my one field
		FIS unitsPerFieldFCL = FIS.load("src/FuzzyLogic/unitsPerField.fcl", true);
		// values <0,100> - % units in my base to all my units
		FIS unitsRatioToBaseFCL = FIS.load("src/FuzzyLogic/unitsRatioToBase.fcl", true);

        if( fightChancesFCL == null || fieldsControledFCL == null || unitsPerFieldFCL == null || unitsRatioToBaseFCL == null) 
        { 
            System.err.println("Can't load fcl file");
            return;
        }

        // example
        JFuzzyChart.get().chart(fightChancesFCL);

        fightChancesFCL.setVariable("myUnits", 8);
        fightChancesFCL.setVariable("enemyUnits", 2);

        fightChancesFCL.evaluate();

        
        System.out.println("Magiczna wartosc: \n" + fightChancesFCL.getVariable("fightResult").getValue() );
        Variable fightResult =  fightChancesFCL.getVariable("fightResult");
        JFuzzyChart.get().chart(fightResult, fightResult.getDefuzzifier(), true);
        
        //System.out.println(fightChancesFCL);
	}
	
}

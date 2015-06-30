package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;


public class FuzzyLogicControl {

	private FIS fieldsControledFCL;

	public FuzzyLogicControl(String fieldsControledFCL) 
	{
		// values <0,25> - number of fields
		this.fieldsControledFCL = FIS.load(fieldsControledFCL, true);
	}
	
	public double getFuzzyFieldsControled(double myFields, double emptyFields, double enemyFields, 
			double mySoldiers, double enemySoldiers)
	{
		fieldsControledFCL.setVariable("myFields", myFields);
		fieldsControledFCL.setVariable("emptyFields", emptyFields);
		fieldsControledFCL.setVariable("enemyFields", enemyFields);
		fieldsControledFCL.setVariable("mySoldiers", mySoldiers);
		fieldsControledFCL.setVariable("enemySoldiers", enemySoldiers);

		fieldsControledFCL.evaluate();
		
		return fieldsControledFCL.getVariable("result").getValue();
	}
	
}

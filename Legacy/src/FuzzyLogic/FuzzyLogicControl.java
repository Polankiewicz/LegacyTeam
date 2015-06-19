package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;


public class FuzzyLogicControl {

	private FIS fieldsControledFCL;

	public FuzzyLogicControl(String fieldsControledFCL) 
	{
		loadFclFiles(fieldsControledFCL);
	}
	
	private void loadFclFiles(String fieldsControledFCL) 
	{
		// values <0,25> - number of fields
		this.fieldsControledFCL = FIS.load(fieldsControledFCL, true);
	}
	
	
	public double getFuzzyFieldsControled(double myFields)
	{
		fieldsControledFCL.setVariable("myFields", myFields);

		fieldsControledFCL.evaluate();
		
		return fieldsControledFCL.getVariable("fieldsRatio").getValue();
	}
	
}

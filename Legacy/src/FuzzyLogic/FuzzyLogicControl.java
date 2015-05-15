package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;


public class FuzzyLogicControl {

	
	public static void main(String[] args) throws Exception {
		
        
        String fileName = "src/FuzzyLogic/fightChances.fcl";
        FIS fis = FIS.load(fileName,true);

        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        JFuzzyChart.get().chart(fis);
        //FunctionBlock functionBlock = fis.getFunctionBlock(null);
        //functionBlock.chart();

        fis.setVariable("myUnits", 8);
        fis.setVariable("enemyUnits", 2);

        fis.evaluate();

        
        System.out.println("Magiczna wartosc: \n" + fis.getVariable("fightResult").getValue() );
        Variable fightResult =  fis.getVariable("fightResult");
        JFuzzyChart.get().chart(fightResult, fightResult.getDefuzzifier(), true);
        
        //System.out.println(fis);
    }
	
}

package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;


public class FuzzyLogicControl {

	
	public static void main(String[] args) throws Exception {
        
        String fileName = "src/FuzzyLogic/fightChances.fcl";
        FIS fis = FIS.load(fileName,true);

        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }


        fis.setVariable("myUnits", 8);
        fis.setVariable("enemyUnits", 2);

        fis.evaluate();

        
        System.out.println("Magiczna wartosc: \n" + fis.getVariable("fightResult").getLatestDefuzzifiedValue() );
        
        //System.out.println(fis);
    }
	
}

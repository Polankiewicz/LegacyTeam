package meteorolog;

import java.util.Vector;

import CLIPSJNI.*;

import javax.swing.*;

public class Meteorolog {

	private static Environment clips;
	
	private static boolean askQuestion(JFrame parent) {
		MultifieldValue questionHandle = (MultifieldValue) clips.eval("(find-all-facts ((?f pytanie)) TRUE)");
		
		if(questionHandle.listValue().size() == 0)
			return false;
		
		FactAddressValue questionRecord = (FactAddressValue) questionHandle.listValue().get(0);
		int questionId = Integer.parseInt(questionRecord.getFactSlot("id").toString());
		String questionTxt = questionRecord.getFactSlot("tekst").toString();
		
		questionTxt = questionTxt.substring(1, questionTxt.length()-1);
		
		MultifieldValue answerHandle = (MultifieldValue) clips.eval("(find-all-facts ((?f odpowiedz)) TRUE)");
		Vector<String> possibleAnswers = new Vector<String>();
		Vector<String> possibleTokens = new Vector<String>();
		
		for(int i=0; i<answerHandle.listValue().size(); i++){
			FactAddressValue answerRecord = (FactAddressValue) answerHandle.listValue().get(i);
			int answerId = Integer.parseInt(answerRecord.getFactSlot("id").toString());
			String answerTxt = answerRecord.getFactSlot("tekst").toString();
			answerTxt = answerTxt.substring(1, answerTxt.length()-1);
			String answerToken = answerRecord.getFactSlot("token").toString();
			answerToken = answerToken.substring(1, answerToken.length()-1);
			
			if(answerId == questionId){
				possibleAnswers.add(answerTxt);
				possibleTokens.add(answerToken);
			}
		}
		
		String userAnswer = (String) JOptionPane.showInputDialog(parent, questionTxt, "Meteorolog", JOptionPane.QUESTION_MESSAGE, null, possibleAnswers.toArray(), null);
		if(userAnswer==null){
			Runtime.getRuntime().halt(0);
		}
		
		
		for(int i=0; i<possibleAnswers.size(); i++){
			if(possibleAnswers.get(i) == userAnswer){
				System.out.println("("+possibleTokens.get(i)+")");
				clips.assertString("(answered "+Integer.toString(questionId)+")");
				clips.assertString("("+possibleTokens.get(i)+")");
				clips.run();
				break;
			}
		}
		
		return true;
	}
	
	private static void showConclusion(JFrame parent){
		MultifieldValue conclusionHandle = (MultifieldValue) clips.eval("(find-all-facts ((?f konkluzja)) TRUE)");
		
		String story = new String("Prognoza:\n");
		
		for(int i=0; i<conclusionHandle.listValue().size(); i++){
			FactAddressValue conclusionRecord = (FactAddressValue) conclusionHandle.listValue().get(i);
			String conclusionTxt = conclusionRecord.getFactSlot("tekst").toString();
			story += conclusionTxt.substring(1, conclusionTxt.length()-1) + "\n";
		}
		
		JOptionPane.showMessageDialog(parent, story, "Meteorolog", JOptionPane.INFORMATION_MESSAGE);
	}	
	
	public static void main(String[] args) {
		JFrame parent = new JFrame();
		clips = new Environment();
		clips.load("meteorolog.clp");
		clips.reset();
		UIManager.put("OptionPane.cancelButtonText", "Wyjœcie");
		UIManager.put("OptionPane.okButtonText", "Dalej");
		while(askQuestion(parent));
		UIManager.put("OptionPane.okButtonText", "Koniec");
		showConclusion(parent);
		Runtime.getRuntime().halt(0);
	}


}

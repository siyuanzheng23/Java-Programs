import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel{

	
	public MarkovOne() {
		myRandom = new Random();
	}
	
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int idx = myRandom.nextInt(myText.length() - 1);
		String key = myText.substring(idx, idx+1);
		sb.append(key);
		
		//already append one character before the for loop
		for(int  i = 0 ; i < numChars-1 ; i ++){
			ArrayList<String> follows = getFollows(key);
			idx = myRandom.nextInt(follows.size());
			String temp = follows.get(idx);
			sb.append(temp);
			key = temp;
		}
		
		return sb.toString();
	
	}
	
	public String toString(){
		return "MarkovModel of order " + 1;
	}
	
	
	
	
}
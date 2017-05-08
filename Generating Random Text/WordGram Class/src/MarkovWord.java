import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel {
	
	private String[] myText;
	private Random myRandom;
	private int myOrder;
	
	public MarkovWord(int n) {
        myRandom = new Random();
        myOrder = n;
    }
	
	

	@Override
	public void setTraining(String text) {
		myText = text.split("\\s+");
	}

	@Override
	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}

	@Override
	//OK, one attempt only
	public String getRandomText(int numWords) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
		WordGram key = new WordGram(myText,index,myOrder);
		//should use for loop to append all the words
		for(int i = 0; i < key.length(); i ++){
			sb.append(key.wordAt(i));
			sb.append(" ");
		}
//		sb.append(key.myWords);
//		sb.append(" ");
		for(int k=0; k < numWords-myOrder; k++){
		    ArrayList<String> follows = getFollows(key);
		    //System.out.println("The key is " + key + " and the follows are : "+ follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			//shiftAdd generates a new WordGram instead of altering itself
			key = key.shiftAdd(next);
		}
		
		return sb.toString().trim();
	}
	
	private int indexOf(String words[], WordGram target, int start){
		int targetLength = target.length();
		for(int i = start ; i < words.length - targetLength + 1 ; i ++){
			WordGram temp = new WordGram(words,i,targetLength);
			if(temp.equals(target)){
				return i;
			}
		}
		return -1;
	}
	
	private ArrayList<String> getFollows(WordGram kGram) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int start = 0;
	    while(start < myText.length){
	    	int keyIdx = indexOf(myText, kGram, start);
	    	//not found 
	    	if(keyIdx == -1){
	    		break;
	    	}
	    	//There is no word following the WordGram parameter
	    	if(keyIdx + kGram.length() >= myText.length){
	    		break;
	    	}
	    	follows.add(myText[keyIdx + kGram.length()]);
	    	start = keyIdx + 1;
	    }
	    return follows;
    }
	

}

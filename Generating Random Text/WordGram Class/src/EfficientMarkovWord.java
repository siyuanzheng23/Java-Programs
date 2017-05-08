import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

//one attempt
public class EfficientMarkovWord implements IMarkovModel{
	private String[] myText;
	private Random myRandom;
	private int myOrder;
	private HashMap<WordGram, ArrayList<String>> hashMap;
	
	public EfficientMarkovWord(int n) {
        myRandom = new Random();
        myOrder = n;
        hashMap = new HashMap<WordGram,ArrayList<String>>();
    }
	
	public void buildMap(){
		//don't forget the + 1 here
		for(int i = 0; i < myText.length-myOrder + 1; i ++ ){
			WordGram key = new WordGram(myText,i,myOrder);
			//int hc = key.hashCode();
			//the last WordGram of training text
			if(i + myOrder == myText.length){
				if(!hashMap.containsKey(key)){
					ArrayList<String> follows = new ArrayList<String>();
					hashMap.put(key, follows);
				}
				break;
			}
			
			//not the last WordGram of the training text
			if(!hashMap.containsKey(key)){
				//create an empty array list of string 
				ArrayList<String> follows = new ArrayList<String>();
				//NOTE: do not use i + myOrder + 1 as index
				follows.add(myText[i + myOrder]);
				hashMap.put(key, follows);
			}else{
				hashMap.get(key).add(myText[i + myOrder]);
			}	
		}
		
		//printHashMapInfo();
	}
	
	

	@Override
	public void setTraining(String text) {
		myText = text.split("\\s+");
		//should call buildMap here
		buildMap();
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
		    if(follows == null || follows.size() == 0){
				break;
			}
		    //System.out.println("The key is " + key + " and the follows are : "+ follows);
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

		return hashMap.get(kGram);

    }
	
	public void printHashMapInfo(){
		if(hashMap.size() < 20){
			for(WordGram wg : hashMap.keySet()){
				System.out.println("Hash Code(WordGram) " + wg  + " : " + hashMap.get(wg));
			}
		}
		
		System.out.println("It has "+hashMap.size() +" keys in the HashMap"  );
		
		int max = -1;
		for(WordGram wg : hashMap.keySet()){
			if(hashMap.get(wg).size() > max){
				max = hashMap.get(wg).size();
			}
		}
		System.out.println("The maximum number of elements following a key is " + max);
		
		System.out.println("The key(s) that has largest values : ");
		for(WordGram wg: hashMap.keySet()){
			if(hashMap.get(wg).size() == max){
				System.out.println(wg + " : " + hashMap.get(wg));
			}
		}
		
		
	}
	
}

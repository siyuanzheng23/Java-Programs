import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
	private ArrayList<String> people;
	private ArrayList<Integer> count;
	
	public CharactersInPlay(){
		people = new ArrayList<String>();
		count = new ArrayList<Integer>();
	}
	
	public void update(String person){ 
		int idx = people.indexOf(person);
		if(idx == -1){ //first time recording
			people.add(person); 
			count.add(1); //should matches the index of people
		}else{
			count.set(people.indexOf(person), count.get(people.indexOf(person)) + 1);
		}
	}
	
	public void findAllCharacters(){
		people.clear(); //refresh
		count.clear(); //refresh
		FileResource fr = new FileResource();
		for(String line:fr.lines()){
			int idx = line.indexOf(".");
			if(idx != -1){
				String temp = line.substring(0,idx); //excluding ,
				update(temp);
			}
		}
	}
	
	public void tester(){
		//only print main character ( estimate it by yourself) 
		System.out.println("Testing findAllCharacters ");
		findAllCharacters();
		System.out.println("The main characters are: ");
		for(int i = 0 ; i < people.size(); i ++){
			if(count.get(i) >= 3 && (people.get(i).length() <= 28)){ //main character
				System.out.println(people.get(i) + "\t" + count.get(i));
			}
		}
		System.out.println("Testing charactersWithNumParts ");
		charactersWithNumParts(10,15);
		int tempMax = -1;
		int tempMaxIdx = -1;
		for(int i = 0; i < people.size(); i ++){
			if(count.get(i) > tempMax){
				tempMax = count.get(i);
				tempMaxIdx = i;
			}
		}
		System.out.println("The person who spoke the most is " + people.get(tempMaxIdx) + ", " + count.get(tempMaxIdx));
	}
	
	
	/* This method should print out the names of all those characters 
	 * that have exactly number speaking parts, where number is greater than 
	 * or equal to num1 and less than or equal to num2 */
	//contract: num1 <= num2
	public void charactersWithNumParts(int num1, int num2){
		for(int i = 0 ; i< people.size(); i ++){
			if( num1 <= count.get(i)  && count.get(i) <= num2 ){
				if(people.get(i).length() <= 25){
					System.out.println(people.get(i) + "\t" + count.get(i));
				}
			}
		}
	}
	
}
import java.util.ArrayList;

import edu.duke.FileResource;

public class Tester {

	
	public void testGetFollows(){
		MarkovOne mOne = new MarkovOne();
		mOne.setTraining("this is a test yes this is a test.");
		ArrayList<String> follows = mOne.getFollows("o");
		System.out.println(follows.size());
		System.out.println(follows);
	}
	
	public void testGetFollowsWithFile(){
		//FileResource fr = new FileResource();
	    FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setTraining(st);
		ArrayList<String> follows = markov.getFollows("he");
		System.out.println(follows.size());
		System.out.println(follows);
	}
	
}

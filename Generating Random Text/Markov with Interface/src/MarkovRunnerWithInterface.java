
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        markov.setRandom(seed);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
        MarkovZero mz = new MarkovZero();
        int seed1 = 1;
        runModel(mz, st, size,seed1);
    
        MarkovOne mOne = new MarkovOne();
        int seed2 = 1;
        runModel(mOne, st, size,seed2);
        
        MarkovModel mThree = new MarkovModel(3);
        int seed3 = 1;
        runModel(mThree, st, size,seed3);
        
        MarkovFour mFour = new MarkovFour();
        int seed4 = 1;
        runModel(mFour, st, size,seed4);

    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
	public void testHashMap(){
		EfficientMarkovModel emm2 = new EfficientMarkovModel(2);
		emm2.setRandom(42);
		emm2.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
		emm2.getRandomText(50);
		emm2.printHashMapInfo();
	}
	
	public void compareMethods(){
		MarkovModel mm2 = new MarkovModel(2);
		EfficientMarkovModel emm2 = new EfficientMarkovModel(2);
		//API..
		//runModel(IMarkovModel markov, String text, int size, int seed) 
		FileResource fr = new FileResource("data/hawthorne.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		long before = System.nanoTime();
		//runModel(mm2,st,1000,42);
		runModel(emm2,st,1000,42);
		System.out.println("The nano time the markov model take is " + (System.nanoTime() - before));
	}
	
	public void testMarkov6(){
		EfficientMarkovModel emm6 = new EfficientMarkovModel(6);
		FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		//runModel(IMarkovModel markov, String text, int size, int seed)
		runModel(emm6,st,500,792);
		
	}
	
	public void testMarkov5(){
		EfficientMarkovModel emm5 = new EfficientMarkovModel(5);
		FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		//runModel(IMarkovModel markov, String text, int size, int seed)
		runModel(emm5,st,500,531);
	}
}

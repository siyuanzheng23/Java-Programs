
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(3); 
        
        //while choosing confucius.txt as training text, first line of output should be 
        //failure. The sense of his wasted powers may well have tempted
        runModel(markovWord, st, 300,643); 
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
    	EfficientMarkovWord emm2 = new EfficientMarkovWord(2);
    	//String text = "this is a test yes this is really a test";
    	String text = "this is a test yes this is really a test yes a test this is wow";
    	runModel(emm2,text,50,42);
    }
    
    public void compareMethods(){
    	EfficientMarkovWord emm2 = new EfficientMarkovWord(2);
    	MarkovWord mm2 = new MarkovWord(2);
    	FileResource fr = new FileResource("data/hawthorne.txt");
    	//String s = fr.toString();
    	String s = fr.asString();
    	//System.out.println(s);
    	long cur1 = System.nanoTime();
    	runModel(mm2,s,100,42);
    	long cur1After = System.nanoTime();
    	long cur2 = System.nanoTime();
    	runModel(emm2,s,100,42);
    	long cur2After = System.nanoTime();
    	System.out.println("It takes " + (cur1After-cur1) + " for normal Markov and it takes " + 
    	(cur2After - cur2) + " for Efficient Markov and the difference of "
    			+ " time consumption is " + ((cur1After-cur1) - (cur2After - cur2)));
    	
    }

}

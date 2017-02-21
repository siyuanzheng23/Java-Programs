import edu.duke.*;

public class Part3 {
	Part2 temp2 = new Part2();
	Part1 temp1 = new Part1();
	//part1.getAllGenes(dna) will return a storage
	public void  processGenes(StorageResource sr){
		StorageResource sr2 = sr;
		int longerThan9 = 0;
		int highCgRatio = 0;
		String l = "";
		System.out.print("The strings that with length no longer than 9 : \n");
		for(String s: sr.data()){
			if(s.length() <= 9){
				System.out.print(s + "\n");
			}else{
				longerThan9 ++;
			}
			if(s.length() >= l.length()){
				l = s;
			}
		}
		
		System.out.print("There are " + longerThan9 + " strings that have length greater than 9 \n");
		
		for(String s:sr2.data() ){
			System.out.print("\n The strings that have c-g-ratios greater than 0.35 : \n");
			if(temp2.cgRatio(s) > 0.35){
				System.out.print(s + "\n");
				highCgRatio ++;
			}
			System.out.print("The number of strings that have c-g-ratios higher than 0.35 is " + highCgRatio +"\n");
		}
		
		System.out.print("The longest string is " + l);
	}
	
	public void testProcessGenes(){
		FileResource fr = new FileResource("brca1line.fa");
		String dna = fr.asString();
		//System.out.print(dna);
		StorageResource hello = temp1.getAllGenes(dna);
		System.out.print("Below is the result for calling findGene() \n");
		String test = temp1.findGene(dna);
		int count = 0;
		System.out.print(test + "\n");
		System.out.print("Below is the result for testing ProcessGenes \n");
		for(String s: hello.data()){
			System.out.print(s + "\n");
			System.out.print(count + "\n");
			count ++;
		}
		//processGenes(storage);
	}

}
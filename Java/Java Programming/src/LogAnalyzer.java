
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;


public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     //should not do the following, since WebLogParser is statis
     //WebLogParser wlp = new WebLogParser();
     
     public LogAnalyzer() {
         // complete constructor
    	 records = new ArrayList<LogEntry>();
     }
     
     //empty the ArrayList of LogEntry records
     public void clearRecords(){
    	 records.clear();
     }
        
     public void readFile(String filename) {
         // complete method
    	 //File f = new File(filename);
    	 FileResource fr = new FileResource("data/" + filename);
    	 for(String l: fr.lines()){
    		 records.add(WebLogParser.parseEntry(l));
    	 }   	 
     }
        
     public void printAll() {
    	 System.out.println("Printing all LogEntry");
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
    	 ArrayList<String> uniqueIPs = new ArrayList<String>();
    	 for(int i = 0 ; i < records.size(); i ++){
    		 String currentIP = records.get(i).getIpAddress();
    		 //contains means 'equal' method..
    		 if(!(uniqueIPs.contains(currentIP))){
    			 uniqueIPs.add(currentIP);
    		 }
    	 }
    	 return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
    	 System.out.println("Printing LogEntry with status code higher than " + num);
    	 for(int i = 0; i < records.size(); i++){
    		 int statusCode = records.get(i).getStatusCode();
    		 if(statusCode > num){
    			 System.out.println(records.get(i));
    		 }
    	 }
     }
     
     //someday has the Format Mmm dd
     public ArrayList<String>  uniqueIPVisitsOnDay(String someday){
    	 //System.out.println(someday);
    	 ArrayList<String> someDay = new ArrayList<String>();
    	 for(int i = 0; i < records.size(); i ++){
    		 String ip = records.get(i).getIpAddress();
    		 String d = records.get(i).getAccessTime().toString().substring(4,10);
    		 if(someday.equals(d)){
    			 if(!someDay.contains(ip)){
    				 someDay.add(records.get(i).getIpAddress());
    			 }
    		 }
    	 }
    	 return someDay;
     }
     
     public int countUniqueIPsInRange(int low, int high){
    	 int count = 0;
    	 ArrayList<String> seen = new ArrayList<String>();
    	 for(int i = 0; i < records.size(); i ++){
    		 int statusCode = records.get(i).getStatusCode();
    		 if(low <= statusCode && statusCode <= high){
    			 String ip = records.get(i).getIpAddress();
    			 if(! seen.contains(ip)){
    				 count ++; 
    				 seen.add(ip);
    			 }
    		 } 
    	 }
    	 return count;
     }
}
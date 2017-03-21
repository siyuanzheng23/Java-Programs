
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
    	LogAnalyzer la = new LogAnalyzer();
//    	la.readFile("short-test_log");
//    	la.printAll();
//    	System.out.println("There are "+ la.countUniqueIPs() + " unique IPs");
//    	if using weblog-short_log, return ArrayList of 2 items
//    	la.uniqueIPVisitsOnDay("Sep 14");
//    	if using weblog-short_log, return ArrayList of 3 items
//    	la.uniqueIPVisitsOnDay("Sep 30");
//    	if using short-test_log, should return 4
//    	la.countUniqueIPsInRange(200,299);
//    	if using short-test_log, should return 2
//    	la.countUniqueIPsInRange(300,399);
    	
    	la.clearRecords();
    	la.readFile("weblog1_log");
    	la.printAllHigherThanNum(400);
    	
    	la.clearRecords();
    	la.readFile("weblog1_log");
    	System.out.println(la.uniqueIPVisitsOnDay("Mar 17").size());
    	System.out.println(la.countUniqueIPsInRange(200,299));
    }
}
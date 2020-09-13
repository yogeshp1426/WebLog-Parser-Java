
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
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
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile();
        //logan.printAll();
        int uniqueIPs = logan.countUniqueIPs();
        System.out.println("No of unique IP's are: "+uniqueIPs);
        //logan.printAllHigherThanNum(400);
        logan.uniqueIPVisitsOnDay("Sep 24");
        logan.countUniqueIPsInRange(200,299);
        HashMap<String,Integer> counts = logan.countVisitsPerIp();
        //System.out.println(counts);
        int max = logan.mostNumberVisitsByIP(counts);
        System.out.println("max visits by ip: "+max);
        ArrayList<String> ipMap = logan.iPsMostVisits(counts);
        System.out.println("Ips with most visits: "+ipMap);
        HashMap<String,ArrayList<String>> dayIp = logan.iPsForDays();
        logan.dayWithMostIPVisits(dayIp);
        logan.iPsWithMostVisitsOnDay(dayIp,"Sep 29");
    }
}

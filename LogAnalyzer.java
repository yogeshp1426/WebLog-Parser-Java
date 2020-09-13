
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
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile() {
         // complete method
         FileResource fr = new FileResource();
         for(String s : fr.lines()){
             WebLogParser w = new WebLogParser();
             records.add(w.parseEntry(s));
         }
     }
        
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le: records){
             String ipAddr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
               }
          }
          return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
         ArrayList<LogEntry> higherThan = new ArrayList<LogEntry>();
         for(LogEntry le: records){
             int currNum = le.getStatusCode();
             if(currNum > num){
                 higherThan.add(le);
                 System.out.println("Log with status > num: "+le);
              }
         }
     }
     
     public  void  uniqueIPVisitsOnDay(String someday ){
         ArrayList<String> ipDam = new ArrayList<String>();
         ArrayList<LogEntry> visitsOnDay = new ArrayList<LogEntry>();
         for(LogEntry le: records){
             String date = le.getAccessTime().toString();
             String ipAddr = le.getIpAddress();
             if((!ipDam.contains(ipAddr))&& (date.indexOf(someday) != -1)){
                 ipDam.add(ipAddr);
                 visitsOnDay.add(le);
              }
         }
         System.out.println(visitsOnDay.size());
     }
     
     public void countUniqueIPsInRange(int low, int high){
         ArrayList<LogEntry> uAndR = new ArrayList<LogEntry>();
         ArrayList<String> ipDam = new ArrayList<String>();
         for(LogEntry le:records){
            int currNum = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if((!ipDam.contains(ipAddr))&&(currNum >= low && currNum <= high)){
             ipDam.add(ipAddr);   
             uAndR.add(le);
             }
          }
         System.out.println("Range Unique IP logs: "+uAndR.size()); 
     }
     
     public HashMap<String,Integer> countVisitsPerIp(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for(LogEntry le:records){
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)){
                 counts.put(ip,1);
             }
             else{
                 counts.put(ip,counts.get(ip)+1);
             }
          }
         System.out.println("count visits per ip "+counts);
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int max = 0;
         for(int currCount:counts.values()){
             if(currCount > max){
                 max = currCount;
             }
         }
         return max;
     }
     
     public ArrayList<String>  iPsMostVisits(HashMap<String,Integer> counts){
         ArrayList<String> mostIp = new ArrayList<String>();
         int max  = mostNumberVisitsByIP(counts);
         for(String  curr: counts.keySet()){
             if(counts.get(curr) == max){
                mostIp.add(curr);
             }
         }
         return mostIp;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> dayIp = new HashMap<String,ArrayList<String>>();
         for(LogEntry le: records){
             String ip = le.getIpAddress();
             String date = le.getAccessTime().toString().substring(4,10);
             if(!dayIp.containsKey(date)){
                 ArrayList<String> ipMap = new ArrayList<String>();
                 ipMap.add(ip);
                 dayIp.put(date,ipMap);
             }
             //else if(dayIp.containsKey(date) && !(dayIp.get(date)).contains(ip)){
             //    dayIp.get(date).add(ip);
             //}
              else{
                 dayIp.get(date).add(ip);
             }
         }
         System.out.println(dayIp);
         return dayIp;
     }
     
     public void dayWithMostIPVisits(HashMap<String,ArrayList<String>> dayIp){
         int max = 0;
         String day = null;
         for(String date: dayIp.keySet()){
             int curr = dayIp.get(date).size();
             if(curr > max){
                 max = curr;
                 day = date;
             }
         }
         System.out.println(day);
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> dayIp, String day){
         ArrayList<String> ipDam = new ArrayList<String>() ;
         HashMap<String,Integer> ipMap = new HashMap<String,Integer>();
         for(String date: dayIp.keySet()){
             if(date.equals(day)){
                 //System.out.println(dayIp.get(date));
                 for(String ip: dayIp.get(date)){
                     if(!ipMap.containsKey(ip)){
                         ipMap.put(ip,1);
                     }
                     else{
                         ipMap.put(ip,ipMap.get(ip)+1);
                     }
                 }
                 int max  = mostNumberVisitsByIP(ipMap);
                 for(String ipHigh :ipMap.keySet()){
                    if(max == ipMap.get(ipHigh)){
                        ipDam.add(ipHigh);
                    }
                 }
              }
         }
         System.out.println(ipDam);
         return ipDam;
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}

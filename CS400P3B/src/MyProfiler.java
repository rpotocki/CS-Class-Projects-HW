/**
 * Filename:   MyProfiler.java
 * Project:    p3b-201901     
 * Authors:    Ryan Potocki 
 *
 * Semester:   Spring 2019
 * Course:     CS400
 * 
 * Due Date:   3/28 10pm
 * Version:    1.0
 * 
 * Credits:    
 * 
 * Bugs:       
 */

// Used as the data structure to test our hash table against the java tree map
import java.util.Hashtable;
import java.util.TreeMap;


public class MyProfiler<K extends Comparable<K>, V> {

    HashTableADT<K, V> hashtable;
    TreeMap<K, V> treemap;
    
    //initial constructor
    public MyProfiler() {
      hashtable = new HashTable();
      treemap = new TreeMap();
    }
    
    //inserts items into both hash table and tree map
    public void insert(K key, V value) {
      try {
        hashtable.insert(key, value);
        treemap.put(key, value);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    //retrives values from a key in both hash table and tree map
    public void retrieve(K key) {
        try {
          treemap.get(key);
          hashtable.get(key);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try { //insert and retrieve the numbner of elements into both the hash table and tree map
            int numElements = Integer.parseInt(args[0]);
            MyProfiler<Integer, Integer> profile = new MyProfiler<Integer, Integer>();
            for (int i = 0; i < numElements; i++) {
              profile.insert(i, i);
            }
            for (int i = 0; i < numElements; i++) {
              profile.retrieve(i);
            }
 
        
            String msg = String.format("Inserted and retreived %d (key,value) pairs", numElements);
            System.out.println(msg);
        }
        catch (Exception e) {
            System.out.println("Usage: java MyProfiler <number_of_elements>");
            System.exit(1);
        }
    }
}

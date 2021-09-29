//Title:           HashTableTest.java
//Files:           HashTable.java
//Course:          CS 400 Spring 2019
//
//Author:          Ryan Potocki
//Email:           rpotocki@wisc.edu
//Lecturer's Name: Deb Deppler
//

import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*; 
import org.junit.jupiter.api.Assertions;
 
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
 
import java.util.Random;

/** 
 * This class tests the HashTable.java class to ensure
 * the datastructure is working correctly
 */

public class HashTableTest{

    @Before
    public void setUp() throws Exception {
      HashTable hashTableTest = new HashTable();
    }

    @After
    public void tearDown() throws Exception {
    }
    
    /** 
     * Tests that a HashTable returns an integer code
     * indicating which collision resolution strategy 
     * is used.
     * REFER TO HashTableADT for valid collision scheme codes.
     */
    @Test
    public void test000_collision_scheme() {
        HashTableADT htIntegerKey = new HashTable<Integer,String>();
        int scheme = htIntegerKey.getCollisionResolution();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }
        
    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that insert(null,null) throws IllegalNullKeyException
     */
    @Test
    public void test001_IllegalNullKey() {
        try {
            HashTableADT htIntegerKey = new HashTable<Integer,String>();
            htIntegerKey.insert(null, null);
            fail("should not be able to insert null key");
        } 
        catch (IllegalNullKeyException e) { /* expected */ } 
        catch (Exception e) {
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    /** 
     * Tests if a KeyNotFoundException is thrown from get method
     */
    @Test
    public void test002_TestKeyNotFound() {
      HashTableADT htIntegerKey = new HashTable<Integer,String>();
      try {
          htIntegerKey.get(1);
          fail("should have thrown an exception");
      } 
      catch (KeyNotFoundException e) { /* expected */ } 
      catch (Exception e) {
          fail("insert null key should not throw exception "+e.getClass().getName());
      }
    }
    
    /** 
     * Tests if the insert method works
     */
    @Test
    public void test003_Insert() {
      HashTableADT htIntegerKey = new HashTable<Integer,String>();
      try {
        htIntegerKey.insert(1, "one");
        if (htIntegerKey.numKeys() != 1) {
          fail("numKeys should return 1 but instead returned " + htIntegerKey.numKeys());
        }
      } catch (IllegalNullKeyException | DuplicateKeyException e) {
        fail("threw an exception");

      }
      
    }
    
    /** 
     * Tests if the remove method works
     */
    @Test
    public void test004_remove() {
      HashTableADT htIntegerKey = new HashTable<Integer,String>();
      try {
        htIntegerKey.insert(1, "one");
        htIntegerKey.insert(2, "two");
        htIntegerKey.insert(3, "three");
        htIntegerKey.insert(4, "four");
        htIntegerKey.remove(2);
        if (htIntegerKey.numKeys() != 3) {
          fail("numKeys should return 3 but instead returned " + htIntegerKey.numKeys());
        }
      } catch (IllegalNullKeyException | DuplicateKeyException e) {
        fail("threw an exception");

      }
      
    }
    

    /** 
     * Tests if the get method works
     */
    @Test
    public void test005_get() {
      HashTableADT htIntegerKey = new HashTable<Integer,String>();
      try {
        htIntegerKey.insert(1, "one");
        htIntegerKey.insert(2, "two");
        htIntegerKey.insert(3, "three");
        htIntegerKey.insert(4, "four");
        if (!htIntegerKey.get(3).equals("three")) {
          fail("numKeys should return 3 but instead returned " + htIntegerKey.numKeys());
        }
      } catch (IllegalNullKeyException | DuplicateKeyException e) {
        fail("threw an exception");

      } catch (KeyNotFoundException e) {
        fail("threw an exception");
      }
      
    }
    
    /** 
     * Tests several insert/removes
     */
    @Test
    public void test006_multipleInsertAndRemove() {
      HashTableADT htIntegerKey = new HashTable<Integer,String>();
      try {
        htIntegerKey.insert(1, "one");
        htIntegerKey.insert(2, "two");
        htIntegerKey.insert(3, "three");
        htIntegerKey.insert(4, "four");
        htIntegerKey.remove(2);
        htIntegerKey.remove(4);
        htIntegerKey.remove(1);
        htIntegerKey.insert(6, "six");
        htIntegerKey.insert(7, "seven");
        
        if (htIntegerKey.numKeys() != 3) {
          fail("numKeys should return 3 but instead returned " + htIntegerKey.numKeys());
        }
      } catch (IllegalNullKeyException | DuplicateKeyException e) {
        fail("threw an exception");

      }
      
    }
    
}

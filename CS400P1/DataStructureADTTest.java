import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Title:           DataStructureADDTTest..java
//Files:           DataStructureADT.java
//Course:          CS 400 Spring 2019
//
//Author:          Ryan Potocki
//Email:           rpotocki@wisc.edu
//Lecturer's Name: Deb Deppler
//


/**
 * This class runs tests using the JUnit framework on instances of different
 * data structures created by TA's/myself. It contains all the methods necessary
 * to test the different methods found within DataStructureADT interface
 * 
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
	
	private T dataStructureInstance;
	
	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
	}

    /**
     * Tests whether a dataStructureInstance has size 0 when created
     */
	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0)
		fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
    /**
     * Tests if the size is 1 after inserting a node
     */
	@Test
	void test01_after_insert_one_size_is_one() {
	    dataStructureInstance.insert("1", "Blue");
	    if (dataStructureInstance.size() != 1) {
	      fail("data structure should have size 1, but instead is size="+dataStructureInstance.size());
	    }
	}
	
    /**
     * Tests if the size is 0 after inserting and then removing a node
     */
	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
	  dataStructureInstance.insert("1", "Blue");
	  dataStructureInstance.remove("1");
	  if (dataStructureInstance.size() != 0)
        fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
    /**
     * Tests if an exception is thrown when a duplicate key is attempted
     * to be inserted into the list
     */
	@Test
	void test03_duplicate_exception_is_thrown() {
	  try {
	    dataStructureInstance.insert("1", "Blue");
	    dataStructureInstance.insert("1", "Red");
	  }
	  catch (RuntimeException e) {
	    return;
	  }
	  fail("Runtime (duplicate exception) should of been thrown but wasn't");
	  
	}
	
    /**
     * Tests whether remove returns false when a key that is not present
     * in the list is attempted to be removed from the list
     */
	@Test
	void test04_remove_returns_false_when_key_not_present() {
	  dataStructureInstance.insert("1", "Blue");
	  dataStructureInstance.insert("2", "Red");
	  dataStructureInstance.insert("3", "Green");
	  if (dataStructureInstance.remove("4")) {
	    fail("data structure should return false when removing a key that is not present, but it returned true");
	  }
	}
	
    /**
     * Tests whether the contains method works while inserting and deleting
     * different nodes
     */
    @Test
    void test05_insert_and_remove_contains_check() {
      dataStructureInstance.insert("1", "Blue");
      dataStructureInstance.insert("2", "Red");
      dataStructureInstance.insert("3", "Green");
      dataStructureInstance.remove("2");
      if (dataStructureInstance.contains("2")) {
        fail("data structure should not cotain key 2, as it was removed.");
      }
    }
    
    /**
     * Tests if the size method works after inserting several items
     * into the list and removing all the items
     */
    @Test
    void test06_insert_and_remove_to_clear_list() {
      dataStructureInstance.insert("1", "Blue");
      dataStructureInstance.insert("2", "Red");
      dataStructureInstance.insert("3", "Green");
      dataStructureInstance.remove("2");
      dataStructureInstance.remove("1");
      dataStructureInstance.remove("3");
      if (dataStructureInstance.size() != 0) {
        fail("the size should be 0 but instead was " + dataStructureInstance.size());
      }
    }
    
    /**
     * Tests if adding items and then removing them all works, 
     * and then adding items back into the empty list
     */
    @Test
    void test07_insert_and_remove_to_clear_list_and_add_more() {
      dataStructureInstance.insert("1", "Blue");
      dataStructureInstance.insert("2", "Red");
      dataStructureInstance.insert("3", "Green");
      dataStructureInstance.remove("2");
      dataStructureInstance.remove("1");
      dataStructureInstance.remove("3");
      dataStructureInstance.insert("7", "Orange");
      dataStructureInstance.insert("98", "Purple");
      if (dataStructureInstance.size() != 2) {
        fail("the size should be 2 but instead was " + dataStructureInstance.size());
      }
      if (!dataStructureInstance.contains("98")) {
        fail("the data structure should contain the value 98 but it was not found.");
      }
    }

    /**
     * Tests the get method to see if it is able to retrieve a value
     * from an associated key
     */
    @Test
    void test08_check_get() {
      dataStructureInstance.insert("1", "Blue");
      dataStructureInstance.insert("2", "Red");
      dataStructureInstance.insert("3", "Green");
      if (dataStructureInstance.get("2") != "Red") {
        fail("get should return the value Red but instead returned " + dataStructureInstance.get("2"));
      }
    }
    
    /**
     * Tests if the get command returns null when trying to retrieve 
     * a value from a key that doesn't exist
     */
    @Test
    void test08_check_get_null() {
      dataStructureInstance.insert("1", "Blue");
      dataStructureInstance.insert("2", "Red");
      dataStructureInstance.insert("3", "Green");
      if (dataStructureInstance.get("98") != null) {
        fail("should return null as the key was not present in the list");
      }
    }
    

	
	
	
	// Tip: consider different numbers of inserts and removes and how different combinations of insert and removes


}

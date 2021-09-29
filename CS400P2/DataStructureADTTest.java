import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: add imports as needed for your JUnit tests

/**
 * ABSTRACT super-class with DataStructureADT JUnit tests.
 * 
 * This class contains methods for testing the basic functionality of a DataStructureADT
 * implementation.   Such a d.s. type was designed and tested in Program 1.
 * 
 * This class will now be the super-class of SearchTreeADTTest.  This means that 
 * SearchTreeADTTest inherit all of tests (public and protect methods) from 
 * DataStructureADTTest.  
 * 
 * For Program 2, almost all tests from your p1 DataStructureADTTest class 
 * can be copied and run here without changes.  There are some required changes.
 * 
 * TODO: 1. Copy your DataStructureADTTest methods to this class 
 *       2. Edit your tests to handle the changed names, types, and exception handling requirements
 *          for insert, remove, and get methods.
 *          
 * See @DataStructureADT for more details 
 *          
 * NOTE: this class has changed the visibility of dataStructureInstance
 * and added dataStructureInstance2, and dataStructureInstance3.
 * 
 * dataStructureInstance is still a DataStructure<String,String>.
 * dataStructureInstance2 is a DataStructure<Integer,String>.
 * dataStructureInstance3 is a DataStructure<Integer,Integer>.
 * DO NOT CHANGE THE TYPES, NAMES, OR VISIBLITY OF THOSE FIELDS
 * 
 * @author Debra Deppeler (deppeler@cs.wisc.edu)
 */
abstract class DataStructureADTTest {
	
	// CHANGED FROM P1: fields are protected (so they may be accessed from sub-classes)
	protected DataStructureADT<String,String> dataStructureInstance;
	
	// ADDED FROM P1: added another dataStructureInstance type <Integer,String>
	protected DataStructureADT<Integer,String> dataStructureInstance2;

	
	// CHANGED FROM P1: methods are protected (so they may be accessed from sub-classes)
	protected abstract DataStructureADT<String,String> createInstance();

	// ADDED FROM P1: added method to create another dataStructureInstance type <Integer,String> 
	protected abstract DataStructureADT<Integer,String> createInstance2();

	@BeforeAll
	static void setUpBeforeClass() {
		// UNUSED - may be removed if not using
	}

	@AfterAll
	static void tearDownAfterClass() {
		// UNUSED - may be removed if not using
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
		dataStructureInstance2 = createInstance2();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
		dataStructureInstance2 = null;
	}

	/**
	 * Provided Utility Method for comparing List<K> with other List<K>
	 * 
	 * Helper assert method for comparing lists of various element types.
	 * List must have the same number of elements, 
	 * be of the same type, and have elements that are the same
	 * in the same order.
	 * 
	 * @param list1<?> 
	 * @param list2<?>
	 */
	public void assertEquals(List<?> list1, List<?> list2) {
		assertTrue(list1!=null);
		assertTrue(list2!=null);
		assertTrue(list1.size()==list2.size());
		for (int i=0; i < list1.size(); i++ ) {
			assertTrue(list1.get(i).equals(list2.get(i)));			
		}
	}

	@Test
	void testDS00_empty_ds_size() {
		// It it works for one test, should work for all
		assertTrue(dataStructureInstance.numKeys()==0);
		assertTrue(dataStructureInstance2.numKeys()==0);
	}

	@Test
	void testDS01_insert_one_ds_size() throws IllegalNullKeyException {
		try {
			// It it works for one test, should work for all
			dataStructureInstance.insert("mykey1", "myvalue1");
			dataStructureInstance2.insert(2, "myvalue2");

			assertTrue(dataStructureInstance.numKeys()==1);
			assertTrue(dataStructureInstance2.numKeys()==1);

		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception DS01: "+e.getMessage());
		}
	}

	// [OPTIONAL]: you can add basic data structure tests here or test all in BSTTest
	void test00_empty_ds_size() {
      if (dataStructureInstance.numKeys() != 0)
      fail("data structure should be empty, with size=0, but size="+dataStructureInstance.numKeys());
  }
  
    /**
     * Tests if the size is 1 after inserting a node
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     */
    @Test
    void test01_after_insert_one_size_is_one() throws IllegalNullKeyException, DuplicateKeyException {
        dataStructureInstance.insert("1", "Blue");
        if (dataStructureInstance.numKeys() != 1) {
          fail("data structure should have size 1, but instead is size="+dataStructureInstance.numKeys());
        }
    }
    
    /**
     * Tests if the size is 0 after inserting and then removing a node
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     * @throws KeyNotFoundException 
     */
    @Test
    void test02_after_insert_one_remove_one_size_is_0() throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
      dataStructureInstance.insert("1", "Blue");
      dataStructureInstance.remove("1");
      if (dataStructureInstance.numKeys() != 0)
        fail("data structure should be empty, with size=0, but size="+dataStructureInstance.numKeys());
    }
    
    /**
     * Tests if an exception is thrown when a duplicate key is attempted
     * to be inserted into the list
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     */
    @Test
    void test03_duplicate_exception_is_thrown() throws IllegalNullKeyException, DuplicateKeyException {
      try {
        dataStructureInstance.insert("1", "Blue");
        dataStructureInstance.insert("1", "Red");
      }
      catch (DuplicateKeyException e) {
        return;
      }
      fail("Runtime (duplicate exception) should of been thrown but wasn't");
      
    }
    
    /**
     * Tests whether remove returns false when a key that is not present
     * in the list is attempted to be removed from the list
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     * @throws KeyNotFoundException 
     */
    @Test
    void test04_remove_throws_exception_when_key_not_present() throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
      try {
        dataStructureInstance.insert("1", "Blue");
        dataStructureInstance.insert("2", "Red");
        dataStructureInstance.insert("3", "Green");
        dataStructureInstance.remove("4");
      }
      catch (KeyNotFoundException e) {
        return;
      }
        fail("data structure should thrown a KeyNotFoundException");
      }

    
    /**
     * Tests whether the contains method works while inserting and deleting
     * different nodes
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException  
     */
    @Test
    void test05_insert_and_remove_contains_check() throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
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
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     * @throws KeyNotFoundException 
     */
    @Test
    void test06_insert_and_remove_to_clear_list() throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
      dataStructureInstance.insert("1", "Blue");
      dataStructureInstance.insert("2", "Red");
      dataStructureInstance.insert("3", "Green");
      dataStructureInstance.remove("2");
      dataStructureInstance.remove("1");
      dataStructureInstance.remove("3");
      if (dataStructureInstance.numKeys() != 0) {
        fail("the size should be 0 but instead was " + dataStructureInstance.numKeys());
      }
    }
    
    /**
     * Tests if adding items and then removing them all works, 
     * and then adding items back into the empty list
     * @throws KeyNotFoundException 
     * @throws IllegalNullKeyException 
     * @throws DuplicateKeyException 
     */
    @Test
    void test07_insert_and_remove_to_clear_list_and_add_more() throws IllegalNullKeyException, KeyNotFoundException, DuplicateKeyException {
      dataStructureInstance.insert("1", "Blue");
      dataStructureInstance.insert("2", "Red");
      dataStructureInstance.insert("3", "Green");
      dataStructureInstance.remove("2");
      dataStructureInstance.remove("1");
      dataStructureInstance.remove("3");
      dataStructureInstance.insert("7", "Orange");
      dataStructureInstance.insert("98", "Purple");
      if (dataStructureInstance.numKeys() != 2) {
        fail("the size should be 2 but instead was " + dataStructureInstance.numKeys());
      }
      if (!dataStructureInstance.contains("98")) {
        fail("the data structure should contain the value 98 but it was not found.");
      }
    }
  
    /**
     * Tests the get method to see if it is able to retrieve a value
     * from an associated key
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     * @throws KeyNotFoundException 
     */
    @Test
    void test08_check_get() throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
      dataStructureInstance.insert("1", "Blue");
      dataStructureInstance.insert("2", "Red");
      dataStructureInstance.insert("3", "Green");
      if (dataStructureInstance.get("2") != "Red") {
        fail("get should return the value Red but instead returned " + dataStructureInstance.get("2"));
      }
    }

	
}

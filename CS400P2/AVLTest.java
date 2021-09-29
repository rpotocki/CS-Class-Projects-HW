import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
//Title:           AVLTest.java
//Files:           
//Course:          CS 400 Spring 2019
//
//Author:          Ryan Potocki
//Email:           rpotocki@wisc.edu
//Lecturer's Name: Deb Deppler
//
//NOTE: Some tests that are inherited by the BSTTest class may fail as a result of incorrect
//traversal through the AVL as the expected value in the test is for an UNBALANCED tree

//@SuppressWarnings("rawtypes")
public class AVLTest extends BSTTest {

	AVL<String,String> avl;
	AVL<Integer,String> avl2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = bst = avl = createInstance();
		dataStructureInstance2 = bst2 = avl2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		avl = null;
		avl2 = null;
	}


	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance()
	 */
	@Override
	protected AVL<String, String> createInstance() {
		return new AVL<String,String>();
	}


	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance2()
	 */
	@Override
	protected AVL<Integer, String> createInstance2() {
		return new AVL<Integer,String>();
	}

	/** 
	 * Insert three values in sorted order and then check 
	 * the root, left, and right keys to see if rebalancing 
	 * occurred.
	 */
	@Test
	void testAVL_001_insert_sorted_order_simple() {
		try {
			avl2.insert(10, "10");
			if (!avl2.getKeyAtRoot().equals(10)) 
				fail("avl insert at root does not work");
			
			avl2.insert(20, "20");
			if (!avl2.getKeyOfRightChildOf(10).equals(20)) 
				fail("avl insert to right child of root does not work");
			
			avl2.insert(30, "30");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 000: "+e.getMessage() );
		}
	}

	/** 
	 * Insert three values in reverse sorted order and then check 
	 * the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_002_insert_reversed_sorted_order_simple() {
      try {
        avl2.insert(30, "30");
        if (!avl2.getKeyAtRoot().equals(30)) 
            fail("avl insert at root does not work");
        
        avl2.insert(20, "20");
        
        avl2.insert(10, "10");
        Integer k = avl2.getKeyAtRoot();
        if (!k.equals(20)) 
            fail("avl rotate does not work");
        
        // IF rebalancing is working,
        // the tree should have 20 at the root
        // and 10 as its left child and 30 as its right child

        Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
        Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
        Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
        
    } catch (Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception AVL 000: "+e.getMessage() );
    }
		
	}

	/** 
	 * Insert three values so that a right-left rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 10-30-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_003_insert_smallest_largest_middle_order_simple() {
	     try {
	        avl2.insert(10, "10");
	        if (!avl2.getKeyAtRoot().equals(10)) 
	            fail("avl insert at root does not work");
	        
	        avl2.insert(30, "30");
	        
	        avl2.insert(20, "20");
	        Integer k = avl2.getKeyAtRoot();
	        if (!k.equals(20)) 
	            fail("avl rotate does not work" + k);
	        
	        // IF rebalancing is working,
	        // the tree should have 20 at the root
	        // and 10 as its left child and 30 as its right child

	        Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
	        Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
	        Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        fail( "Unexpected exception AVL 000: "+e.getMessage() );
	    }
		
	}

	/** 
	 * Insert three values so that a left-right rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_004_insert_largest_smallest_middle_order_simple() {
		
	     try {
	        avl2.insert(30, "30");
	        if (!avl2.getKeyAtRoot().equals(30)) 
	            fail("avl insert at root does not work");
	        
	        avl2.insert(10, "10");
	        
	        avl2.insert(20, "20");
	        Integer k = avl2.getKeyAtRoot();
	        if (!k.equals(20)) 
	            fail("avl rotate does not work");
	       
	        Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        fail( "Unexpected exception AVL 000: "+e.getMessage() );
	    }
		
	}
	
	   /** 
     * Insert several values into the tree and 
     * makes sure the rebalancing of the tree
     * works correctly
     */
    @Test
    void testAVL_005_insert_and_test_rebalance() {
         try {
            avl2.insert(20, "20");
      
            avl2.insert(40, "40");
            
            avl2.insert(10, "10");
            avl2.insert(15, "15");
            avl2.insert(9, "9");
            avl2.insert(12, "12");
            Integer k = avl2.getKeyAtRoot();
            Integer kL = avl2.getKeyOfLeftChildOf(k);
            Integer kR = avl2.getKeyOfRightChildOf(k);
            if (!k.equals(15)) 
                fail("avl rotate does not work");
            if (!kL.equals(10)) {
              fail("avl rotate does not work");
            }
            if (!kR.equals(20)) {
              fail("avl rotate does not work");  
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
        
    }
    
    /** 
  * Insert several values into the tree and 
  * makes sure the rebalancing of the tree
  * works correctly
  */
 @Test
 void testAVL_006_insert_and_test_rebalance() {
      try {
         avl2.insert(20, "20");
   
         avl2.insert(40, "40");
         
         avl2.insert(45, "10");
         avl2.insert(37, "15");
         avl2.insert(38, "9");
         avl2.insert(46, "12");
         Integer k = avl2.getKeyAtRoot();
         Integer kL = avl2.getKeyOfLeftChildOf(k);
         Integer kR = avl2.getKeyOfRightChildOf(k);

         if (!k.equals(40)) 
             fail("avl rotate does not work");
         if (!kL.equals(37)) {
           fail("avl rotate does not work");
         }
         if (!kR.equals(45)) {
           fail("avl rotate does not work");  
         }
         
     } catch (Exception e) {
         e.printStackTrace();
         fail( "Unexpected exception AVL 000: "+e.getMessage() );
     }
     
 }
	


}

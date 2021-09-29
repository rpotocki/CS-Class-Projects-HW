//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P09 Badgers Settle Down
// Files:           Badger.java, BadgersSettleDown.java, Sett.java,
//					P9Tests.java
// Course:          CS300, Fall 2018, LEC01
//
// Author:          Ryan Potocki
// Email:           rpotocki@wisc.edu	
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Max Richter
// Partner Email:   mhrichter@wisc.edu
// Partner Lecturer's Name: Gary Dahl
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds all of the test methods that test whether or not the Sett and 
 * Badger classes are implemented correctly
 *
 */
public class P9Tests {

  /**
   * This methods runs the two methods that run all of the remaining test methods
   * @param args
   */
  public static void main(String[] args) {
	// run the tests
	if (!runAllBadgerTests()) {
	  System.out.println("Badger tests failed!");
	}
	if (!runAllSettTests()) {
	  System.out.println("Sett tests failed!");
	} else {
	  System.out.println("Badger and Sett tests passed!");
	}
  }

  /**
   * Runs all of the Badger test methods
   * @return true when all Badger tests pass, false otherwise
   */
  public static boolean runAllBadgerTests() { 
	if (testBadgerConstructor() && testSetLowerNeighbors()) {
	  return true;
	}
	return false;
  }

  /**
   * Runs all of the Sett test methods
   * @return true when all Sett tests pass, false otherwise
   */
  public static boolean runAllSettTests() { 
	if (testSettConstructor() && testClear() && testCountBadger() && testFindBadger()
		&& testGetHeight() && testGetLargestBadger() && testSettleBadger() && testIsEmpty()
		&& testGetAllBadgers() && testGetTopBadger()) {
	  return true;
	}
	return false;
  }

  /**
   * Tests if the Badger constructor is implemented correctly
   * @return true if constructor is implemented correctly, false otherwise
   */
  public static boolean testBadgerConstructor() {
	// badger w/ size 23
	Badger test = new Badger(23); 

	if (test.getSize() == 23) {
	  return true;
	}
	return false;
  }

  /**
   * Tests if the set methods for the right and left neighbors settle a Badger correctly
   * @return true if implemented correctly, false otherwise
   */
  public static boolean testSetLowerNeighbors() {
	// new Badgers
	Badger test = new Badger(62);
	Badger testLeftLower = new Badger(54);
	Badger testRightLower = new Badger(65);
	// settle badgers into left and right lower neighbor
	test.setLeftLowerNeighbor(testLeftLower);
	test.setRightLowerNeighbor(testRightLower);

	// check if badgers were settled correctly
	if (test.getLeftLowerNeighbor().equals(testLeftLower) && 
		test.getRightLowerNeighbor().equals(testRightLower)) {
	  return true;
	}
	return false;
  }

  /**
   * Tests if the Sett constructor is implemented correctly
   * @return true if implemented correctly, false otherwise
   */
  public static boolean testSettConstructor() {
	// empty Sett created
	Sett test = new Sett();

	if (test.isEmpty()) {
	  return true;
	}
	return false;
  }

  /**
   * Tests if the the clear method empties the sett
   * @return true if sett is empty, false otherwise
   */
  public static boolean testClear() {
	// empty Sett
	Sett test = new Sett();
	// settle three badgers into the sett
	test.settleBadger(45);
	test.settleBadger(37);
	test.settleBadger(78);

	test.clear();

	// checks if sett was cleared
	if (test.isEmpty()) {
	  return true;
	}
	return false;
  }

  /**
   * Tests if the countBadger method returns correct number of badgers
   * in the sett
   * @return true if correct amount of badgers, false otherwise
   */
  public static boolean testCountBadger() {
	// empty Sett
	Sett test = new Sett();
	// settle three badgers into the sett
	test.settleBadger(45);
	test.settleBadger(21);
	test.settleBadger(76);
	boolean passed = false;

	if (test.countBadger() == 3) {
	  passed = true;
	}

	// clear sett
	test.clear();

	// checks if sett contains zero badgers
	if (test.countBadger() == 0 && test.isEmpty()) {
	  passed = true;
	} else {
	  passed = false;
	}
	return passed;
  }

  /**
   * Tests if the findBadger method is implemented correctly
   * @return true if implemented correctly, false otherwise
   */
  public static boolean testFindBadger() {
	// empty Sett
	Sett test = new Sett();
	// settle five badgers
	test.settleBadger(34);
	test.settleBadger(12);
	test.settleBadger(78);
	test.settleBadger(64);
	test.settleBadger(14);

	// checks if badger is found
	if (test.findBadger(64).getSize() == 64) {
	  return true;
	}
	return false;
  }

  /**
   * Tests if the getAllBadgers method correctly inserts badgers into a list
   * in ascending order
   * @return true if lists match, false otherwise
   */
  public static boolean testGetAllBadgers() {
	Sett test = new Sett();
	// list that holds manual copy of sett
	List<Badger> manualCopy = new ArrayList<Badger>();
	// seven badgers created
	Badger zero = new Badger(13);
	Badger one = new Badger(23);
	Badger two = new Badger(27);
	Badger three = new Badger(45);
	Badger four = new Badger(62);
	Badger five = new Badger(78);
	Badger six = new Badger(85);
	// add badgers to the list
	manualCopy.add(zero);
	manualCopy.add(one);
	manualCopy.add(two);
	manualCopy.add(three);
	manualCopy.add(four);
	manualCopy.add(five);
	manualCopy.add(six);
	// settle badgers into sett
	test.settleBadger(45); // top badger
	test.settleBadger(23);
	test.settleBadger(78);
	test.settleBadger(13);
	test.settleBadger(27);
	test.settleBadger(62);
	test.settleBadger(85);

	List<Badger> copy = test.getAllBadgers();

	// checks if both lists contain all the badgers in correct order
	for (int i = 0; i < manualCopy.size(); i++) {
	  if (copy.get(i).getSize() != manualCopy.get(i).getSize()) {
		return false;
	  }
	}
	return true;		
  }

  /**
   * Tests if the getHeight method returns the correct height of the tree
   * @return true if correct height, false otherwise
   */
  public static boolean testGetHeight() {
	Sett test = new Sett();
	test.settleBadger(45);
	test.settleBadger(32);
	test.settleBadger(98);
	test.settleBadger(13);
	test.settleBadger(87);

	// checks if correct height is returned
	if (test.getHeight() == 3) {
	  return true;
	}
	return false;
  }

  /**
   * Tests if the getLargestBadger method returns the largest badger in the sett
   * @return true if largest badger is returned, false otherwise
   */
  public static boolean testGetLargestBadger() {
	Sett test = new Sett();
	test.settleBadger(89); // largest badger
	test.settleBadger(46);
	test.settleBadger(23);
	test.settleBadger(76);
	test.settleBadger(10);

	// checks if largest badger is found in the sett
	if (test.getLargestBadger().equals(test.findBadger(89))) {
	  return true;
	}
	return false;
  }

  /**
   * Tests if the getTopBadger method returns the badger at the top of the tree
   * @return true if top badger is correctly settled, false otherwise
   */
  public static boolean testGetTopBadger() {
	Sett test = new Sett();
	test.settleBadger(23); // top badger
	test.settleBadger(78);
	test.settleBadger(7);

	// checks if top badger is found in the sett
	if (test.getTopBadger().equals(test.findBadger(23))) {
	  return true;
	}
	return false;
  }

  /**
   * Tests if the isEmpty method is correctly implemented
   * @return true if implemented correctly, false otherwise
   */
  public static boolean testIsEmpty() {
	Sett test = new Sett();
	test.settleBadger(23);
	test.settleBadger(89);
	test.settleBadger(76);

	// tests if badgers were added to the sett
	if (test.isEmpty()) {
	  return false;
	}

	// clears sett
	test.clear();

	// checks if sett is empty after being cleared
	if (test.isEmpty()) {
	  return true;
	}

	return false;
  }

  /**
   * Tests if the settleBadger method is correctly inserted into the Sett (BST)
   * @return true if badgers are settled correctly, false otherwise
   */
  public static boolean testSettleBadger() {
	Sett test = new Sett();
	test.settleBadger(50);
	test.settleBadger(65);

	// checks if badgers were correctly settled
	if (test.getTopBadger().getRightLowerNeighbor().getSize() == 65) {
	  return true;
	}
	return false;
  }
}

import java.util.Random;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: GameOperator.java
// Files: (a list of all source files used by that program)
// Course: CS300 Fall 2018
//
// Author: Ryan Potocki
// Email: rpotocki@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class contains 4 test methods to ensure all the classes involved in running the
 * GameApplication are working correctly.
 */

public class GameTests {

  /**
   * This method ensures the setNext method in the GameNode class works correctly
   */
  private static boolean testSetNext() {
    Random rng = new Random();
    boolean pass = false;
    GameNode one = new GameNode(rng);
    GameNode two = new GameNode(rng);
    one.setNext(two); // sets the next pointer of node one to the second node
    int testInt = one.getNext().getNumber();
    if (testInt == two.getNumber()) {
      pass = true;
    }
    return pass;
  }

  /**
   * This method ensures creating the node object in the GameNode class works
   */
  private static boolean testCreateNode() {
    Random rng = new Random();
    boolean pass = false;
    GameNode one = new GameNode(rng);
    if (one.getNumber() >= 1 && one.getNumber() <= 9) { // makes sure a number was set on creation
      if (one.getNext() == null) { // makes sure next is defaulted as null
        pass = true;
      }
    }
    return pass;
  }

  /**
   * This method ensures the toString method in the GameList class works correctly
   */
  private static boolean testToString() {
    Random rng = new Random();
    boolean pass = false;
    GameList testList = new GameList();
    GameNode one = new GameNode(rng);
    GameNode two = new GameNode(rng);
    testList.addNode(one);
    testList.addNode(two);
    String testString = testList.toString();
    if (testString.equals(one.getNumber() + " -> " + two.getNumber() + " -> END")) {
      pass = true;
    }
    return pass;
  }

  /**
   * This method ensures the contains method in the GameList class works correctly
   */
  private static boolean testContains() {
    Random rng = new Random();
    boolean pass = false;
    GameList testList = new GameList();
    GameNode one = new GameNode(rng);
    GameNode two = new GameNode(rng);
    testList.addNode(one);
    testList.addNode(two);
    if (testList.contains(one.getNumber()) && testList.contains(two.getNumber())) {
      pass = true;
    }
    return pass;
  }

  public static void main(String[] args) {
    int passedTests = 0;
    if (testSetNext()) {
      passedTests++;
    } else {
      System.out.println("testSetNext() has failed.");
    }
    if (testCreateNode()) {
      passedTests++;
    } else {
      System.out.println("testCreateNode() has failed.");
    }
    if (testToString()) {
      passedTests++;
    } else {
      System.out.println("testToString() has failed.");
    }
    if (testContains()) {
      passedTests++;
    } else {
      System.out.println("testContains() has failed.");
    }
    System.out.println("You passed " + passedTests + " out of 4 tests.");


  }
}

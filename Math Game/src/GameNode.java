import java.util.Random;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: GameNode.java
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
 * 
 * This class is the blueprint to a the GameNode object that is used in a GameList.
 * 
 */

public class GameNode {
  private int number; // the number held within this node
  private GameNode next; // the next GameNode in the list, or null for the last node

  public GameNode(Random rng) { // initializes number to random 1-9 value, and next to null
    number = rng.nextInt(8) + 1;
    next = null;
  }

  public int getNumber() { // accessor for the number field
    return this.number;
  }

  public GameNode getNext() { // accessor for the next field
    return this.next;
  }

  public void setNext(GameNode next) { // mutator for the next fiel
    this.next = next;
  }


  /**
   * This method applies an operator to a node to perform an operation on it to combine two nodes
   * and then remove the second node used in the operation
   */
  public void applyOperator(GameOperator operator) {
    GameNode tempNode = this.getNext();
    this.number = operator.apply(this.getNumber(), tempNode.getNumber());
    this.setNext(tempNode.getNext());
  }

}


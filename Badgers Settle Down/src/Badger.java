//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P09 Badgers Settle Down
// Files:           Badger.java, BadgersSettleDown.java, Sett.java,
//                  P9Tests.java
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

/**
 * This class represents a Badger which is designed to live in a Sett. Each Badger 
 * object represents a single node within a BST (known as a Sett)
 *
 */
public class Badger {
  // lower left Badger
  private Badger leftLowerNeighbor = null;
  // lower right Badger
  private Badger rightLowerNeighbor = null;
  // used to determine size of new badgers
  private int size;

  /**
   * Creates a new Badger with specified size
   * @param size of the new Badger
   */
  Badger(int size) {
    this.size = size;
  }

  /**
   * Retrieves neighboring badger that is smaller than this one
   * @return the left lower neighbor of current badger
   */
  public Badger getLeftLowerNeighbor() {
    if (this.leftLowerNeighbor != null) {
      return this.leftLowerNeighbor;
    }
    return null;
  }

  /**
   * Retrieves neighboring badger that is larger than this one
   * @return the right lower neighbor of current badger
   */
  public Badger getRightLowerNeighbor() {
    if (this.rightLowerNeighbor != null) {
      return this.rightLowerNeighbor;
    }
    return null;   
  }

  /**
   * Retrieves the size of this badger
   * @return the size of current badger
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Changes this badger's lower left neighbor
   * @param badger - new left lower neighbor of current badger
   */
  public void setLeftLowerNeighbor(Badger badger) {
    this.leftLowerNeighbor = badger;
  }

  /**
   * Changes this badger's lower right neighbor
   * @param badger - new right lower neighbor of current badger
   */
  public void setRightLowerNeighbor(Badger badger) {
    this.rightLowerNeighbor = badger;
  }
}
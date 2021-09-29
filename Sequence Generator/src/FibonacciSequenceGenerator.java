import java.util.Iterator;
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           FibonacciSequenceGenerator.java
// Files:           
// Course:          CS300 Fall 2018
//
// Author:          Ryan Potocki
// Email:           r.potocki@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
 
/**
 * This class represents a generator for an fibonacci progression
 * This class implements the Iterator<Integer> interface
 *
 */
public class FibonacciSequenceGenerator implements Iterator<Integer> {
  private final int SIZE; // number of elements in this sequence
  private int prev; // previous item in the sequence with respect to the current iteration
  private int next; // next item in the sequence with respect to the current iteration
  private int generatedCount; // number of items generated so far
  
 
  // constructor
  public FibonacciSequenceGenerator(int size) {
    // check for the precondition: size > 0, throws an IllegalArgumentException if this precondition
    // is not satisfied
    if (size <= 0) 
      throw new IllegalArgumentException("WARNING: "
          + "CANNOT create a sequence with size <= zero.");
    
    this.SIZE = size;
    this.prev = 0;
    this.next = 1; // initializes next to the first element in this arithmetic progression
    generatedCount = 0;
  }
 
 
  /** 
   * Checks if the iteration has a next element in this sequence
   * @return true if the current element in the iteration has a next element in this sequence,
   * false otherwise 
   */
  @Override
  public boolean hasNext() {
    // Time Complexity: O(1)
    return generatedCount < SIZE;
  }
 
  
  /** 
   * Returns the next element in this fibonacci sequence iteration with respect to the numbers
   * generated so far
   * @return the next element in this iteration
   */
  @Override
  public Integer next() {
     // Time Complexity: O(N)
    if (!hasNext()) // check if the current element has a next element in this sequence
      return null;
    if (generatedCount == 0) {
      generatedCount++;
      return 0;
    }
    int current = next; // set the current element to next
    generatedCount++; // increment the number of generated elements so far
    next = prev + current;
    prev = current;
    return current; // return the current number as the generated one
  }
 
  // You CAN add public accessor and mutator methods as needed here in order to implement your test methods
 
}

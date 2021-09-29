//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           SequenceGeneratorTests.java
// Files:           GeometricSequenceGenerator.java, DigitProductSequenceGenerator.java
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
   * This class tests to ensure the Geometric and Fibonacci sequencegenerator classes work correctly
   *
   */
public class SequenceGeneratorTests {
  
  /**
   * This method tests whether the next() and hasNext() methods work for the 
   * geometricSequenceGenerator class
   *
   */
  public static boolean geometricSequenceGeneratorTest() {
    boolean passed = true;
    GeometricSequenceGenerator test = new GeometricSequenceGenerator(5,2,6);
  //the if statements compare if the next() method returns the correct value in the sequence
    if (test.next() != 5) {
      passed = false;
    }
    if (test.next() != 10) {
      passed = false;
    }
    if (test.next() != 20) {
      passed = false;
    }
    if (test.next() != 40) {
      passed = false;
    }
    if (test.next() != 80) {
      passed = false;
    }
    if (test.next() != 160) {
      passed = false;
    }
    if (test.hasNext() == true) {
      passed = false;
    }
    return passed;
  }
  
  /**
   * This method tests whether the next() and hasNext() methods work for the 
   * fibonacciSequenceGenerator class
   *
   */
  public static boolean fibonacciSequenceGeneratorTest() {
    boolean passed = true;
    FibonacciSequenceGenerator test = new FibonacciSequenceGenerator(10);
    //the if statements compare if the next() method returns the correct value in the sequence
    if (test.next() != 0) {
      passed = false;
    }
    if (test.next() != 1) {
      passed = false;
    }
    if (test.next() != 1) {
      passed = false;
    }
    if (test.next() != 2) {
      passed = false;
    }
    if (test.next() != 3) {
      passed = false;
    }
    if (test.next() != 5) {
      passed = false;
    }
    if (test.next() != 8) {
      passed = false;
    }
    if (test.next() != 13) {
      passed = false;
    }
    if (test.next() != 21) {
      passed = false;
    }
    if (test.next() != 34) {
      passed = false;
    }
    if (test.hasNext() == true) { //tests to ensure the last number in the sequence 
      passed = false;
    }
    return passed;
  }
  
  
  public static void main(String[] args) {
    geometricSequenceGeneratorTest();
    fibonacciSequenceGeneratorTest();
  }



}


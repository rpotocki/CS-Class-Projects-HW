import java.util.ArrayList;
import java.util.Iterator;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           DigitProductSequenceGenerator.java
//Files:           
//Course:          CS300 Fall 2018
//
//Author:          Ryan Potocki
//Email:           r.potocki@wisc.edu
//Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons:         (identify each person and describe their help in detail)
//Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class represents a generator for an DigitProduct sequence progression
 *
 */
public class DigitProductSequenceGenerator {
  private final int INIT; // initial number
  private final int SIZE; // size of sequence
  private ArrayList<Integer> sequence; // ArrayList object storing the sequence
  
  /**
   * This method intializes the DigitProductSequenceGenerator object
   *
   */
  public DigitProductSequenceGenerator(int init, int size) {
    // check for the precondition: size > 0, throws an IllegalArgumentException if this precondition
    // is not satisfied
    if (size <= 0) 
      throw new IllegalArgumentException("WARNING: "
          + "CANNOT create a sequence with size <= zero.");
    if (init <= 0 ) { //checks if the starting calue is 0 or less
      throw new IllegalArgumentException("WARNING: "
          + "The starting element for digit product sequence cannot be less than or equal to zero.");
    }
    //initializes the variables for the DigitProductSequence object
    this.INIT = init;
    this.SIZE = size;
    sequence = new ArrayList<Integer>();
    
  }
  
  /**
   * This method creates the sequence arrayList based on the initial value and size
   *
   */
  public void generateSequence() {
    int current = 0;
    sequence.clear(); //clears the list to get rid of any previous numbers
    sequence.add(this.INIT); //adds the start of the list
    while (current < this.SIZE - 1) { //loops until the list is the correct size
      int next = 1;
      String number = String.valueOf(sequence.get(current));
      for (int i = 0; i < number.length(); i++) { //loops through each character in the number
        int j = Character.digit(number.charAt(i), 10); //converts the character to an int
        if (j == 0) {
          next *= 1;
        }
        else {
          next *= j;
        }
      }
      sequence.add(next + sequence.get(current)); //adds the bumber of the sequence
      current++;
    }
  }
  
  /** 
   * Creates an iterator over the sequence generated for the digitproductsequence
   * @return the iterator
   */
    public Iterator<Integer> getIterator() {  
       return this.sequence.iterator();
     }
 

}

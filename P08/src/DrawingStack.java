import java.util.Iterator;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           DrawingStack.java
//Files:           
//Course:          CS300 Fall 2018
//
//Author:          Ryan Potocki
//Email:           rpotocki@wisc.edu
//Lecturer's Name: Gary Dahl
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:    Max Richter
//Partner Email:   mhrichter@wisc.edu
//Partner Lecturer's Name: Gary Dahl
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//__x_ Write-up states that pair programming is allowed for this assignment.
//__x_ We have both read and understand the course Pair Programming Policy.
//__x_ We have registered our team prior to the team registration deadline.
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
 * This class creates a new DrawingStack object and 
 * implements the StackADT<T> class for the type DrawingChange
 */
public class DrawingStack implements StackADT<DrawingChange> {
  
  Node<DrawingChange> top; //top  of the stack
  int size; // size of the stack
  
  
  /**
   * Initializes the DrawingStack object
   */
  public DrawingStack() {
    this.top = null;
    this.size = 0;
  }
  
  /**
   * Pushes a new DrawingChange object to the top of the DrawingStack
   * @param element you wish to push
   */
  @Override
  public void push(DrawingChange element) throws IllegalArgumentException {
    Node<DrawingChange> newNode = new Node<>(element, top);
    top = newNode;
    size++;
  }
  
  /**
   * Removes the top object in the DrawingStack
   */
  @Override
  public DrawingChange pop() {
    if (isEmpty()) {
      throw new NullPointerException("The stack is empty.");
    }
    
    DrawingChange remove = top.getData();
    top = top.getNext();
    size--;
    return remove;
  }  
  
  /**
   * Returns the data in the DrawingChange element at the top of the stack
   */
  @Override
  public DrawingChange peek() {
    return top.getData(); //returns the current stack
  }
  
  /**
   * Returns the size of the DrawingStack
   * @return DrawingStack size
   */
  @Override
  public int size() {
    return this.size;
  }
  
  /**
   * Applies an iterator over the top of the DrawingStack
   * @return Top of DrawingStack with an iterator over it
   */
  @Override
  public Iterator<DrawingChange> iterator() {
    return new DrawingStackIterator(top);
  }
  
  /**
   * Checks to see if the DrawingStack is empty
   * @return boolean value (true) if the stack is empty
   */
  @Override
  public boolean isEmpty() {
    return this.top == null; //checks if top of the stack is null or not, indicating if it's empty
  }
  }

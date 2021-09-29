import java.util.Iterator;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           DrawingStackIterator.java
//Files:           Iterator<T>
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
 * DrawingStackIterator class holds the methods used to implement an iterator
 * over the DrawingStack
 *
 */
public class DrawingStackIterator implements Iterator<DrawingChange> {
 
    private Node<DrawingChange> next; // next element in the drawing
 
    /**
     * Constructor that creates a new iterator
     * @param top starts at the top of the stack of DrawingChanges
     */
    public DrawingStackIterator(Node<DrawingChange>top) {
        this.next = top;
    }
 
    /**
     * Returns true if the stack has another element, false if otherwise
     */
    @Override
    public boolean hasNext() {
        // check if stack is null
        if (next == null) {
            return false;
        } else {
            return true;
        }
    }
 
    /**
     * Gets the next element in the stack
     */
    @Override
    public DrawingChange next() {
        //check if stack has another element
        if (!hasNext()) {
            return null;
        }
        Node<DrawingChange> curr = next;
        next = next.getNext();
        return curr.getData();
    }
}
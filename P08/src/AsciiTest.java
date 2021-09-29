import java.util.Iterator;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           AsciiTest.java
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
 * Contains methods that tests each StackADT implementation
 *
 */
public class AsciiTest {
 
    /**
     * Runs a test to determine whether or not all StackADT implementation's
     * are functioning correctly
     * @param args
     */
    public static void main(String[] args) {
        // number of fails
        int failCount = 0;
        // runs method that runs all tests
        if (!runStackTestSuite()) {
            failCount++;
            System.out.println("runStackTestSuite() failed");
        }
        // checks if any fails occurred
        if (failCount == 0) {
            System.out.println("All tests have passed");
        }      
    }
 
    /**
     * Tests whether or not the last added element is at the top of the stack
     * using peek()
     * @return true if correct element is at top of stack, false otherwise
     */
    public static boolean testStackPushPeek() {
        // creates a new stack
        DrawingStack topOfStack = new DrawingStack();
        // creates two new elements
        DrawingChange firstElement = new DrawingChange(0, 0, 'X', 'M');
        DrawingChange secondElement = new DrawingChange(0, 3, 'M', 'L');
        // default boolean value set to false
        boolean passed = false;
        // adds two elements to the stack
        topOfStack.push(firstElement);
        topOfStack.push(secondElement);
        // checks if the correct value returns at top of the stack
        if (topOfStack.peek().toString().equals(secondElement.toString())) {
            passed = true;
        }
        return passed;     
    }
 
    /**
     * Tests if the correct element is returned when pop() is called on the stack
     * @return true if correct element at top of stack, false if otherwise
     */
    public static boolean testStackPop() {
        // creates a new stack
        DrawingStack topOfStack = new DrawingStack();
        // creates two new elements
        DrawingChange firstElement = new DrawingChange(0, 0, 'J', 'L');
        DrawingChange secondElement = new DrawingChange(0, 3, 'L', 'W');
        // default boolean value set to false
        boolean passed = false;
        // add two elements to the stack
        topOfStack.push(firstElement);
        topOfStack.push(secondElement);
        // remove element at top of stack
        topOfStack.pop();
        // check if correct element was popped
        if (topOfStack.peek().toString().equals(firstElement.toString())) {
            passed = true;
        }      
        return passed;     
    }
 
    /**
     * Tests if the DrawingStack's isEmpty() method is implemented correctly
     * @return true if correctly implemented, false if otherwise
     */
    public static boolean testStackIsEmpty() {
        // creates a new stack
        DrawingStack topOfStack = new DrawingStack();
        // default boolean value set to true
        boolean passed = true;
        // checks if stack is empty
        if (!topOfStack.isEmpty()) {
            passed = false;
        }
        // creates a new element
        DrawingChange firstElement = new DrawingChange(0, 0, 'K', 'G');
        topOfStack.push(firstElement);
        // checks if stack is empty after element is added
        if (topOfStack.isEmpty()) {
            passed = false;
        }
        return passed;
    }
 
    /**
     * Tests if the size() method in DrawingStack is implemented correctly
     * @return
     */
    public static boolean testStackSize() {
        // creates a new stack
        DrawingStack topOfStack = new DrawingStack();
        // creates two new elements
        DrawingChange firstElement = new DrawingChange(0, 0, 'J', 'H');
        DrawingChange secondElement = new DrawingChange(0, 3, 'P', 'T');
        // default boolean value set to false
        boolean passed = false;
        // add two elements to the stack
        topOfStack.push(firstElement);
        topOfStack.push(secondElement);
        // check if size() returns the correct value
        if (topOfStack.size() == 2) {
            passed = true;
        }
        return passed;
    }
   
    /**
     * Tests if the iterator() method in DrawingStack is returning the correct elements
     * @return true if correct elements returned, false if otherwise
     */
    public static boolean testStackIterator() {
        // creates a new stack
        DrawingStack topOfStack = new DrawingStack();
        // creates four new elements
        DrawingChange elementOne = new DrawingChange(0, 0, 'h', 'j');
        DrawingChange elementTwo = new DrawingChange(1, 0, 'f', 'e');
        DrawingChange elementThree = new DrawingChange(2, 0, 'l', 'p');
        DrawingChange elementFour = new DrawingChange(3, 0, 'q', 'n');
        // default boolean value set to true
        boolean passed = true;
        // add four elements to the stack
        topOfStack.push(elementOne);
        topOfStack.push(elementTwo);
        topOfStack.push(elementThree);
        topOfStack.push(elementFour);
        // create a new iterator of type DrawingChange
        Iterator<DrawingChange> stackIterator = topOfStack.iterator();
        // test string that will hold element history (coords & values)
        String test = "";
        // correct output iterator() should add to the String test
        String correctOutput = "30qn20lp10fe00hj";
        String incorrectOutput = "00hj10fe20lp30qn";
        // loop runs while there are elements on the stack
        while (stackIterator.hasNext()) {
            // creates a new DrawingChange object that holds the next element
            DrawingChange curr = stackIterator.next();
            // add the DrawingChange instructions to the String test
            test += curr.x + "" + curr.y + "" + curr.prevChar + "" + curr.newChar;
        }
        // returns false if test != correct output
        if (!test.equals(correctOutput)) {
            passed = false;
        }
        // returns false if test != correct output
        if (test.equals(incorrectOutput)) {
            passed = false;
        }
        return passed;
    }
   
    /**
     * Runs all of the test methods described in AsciiTest
     * @return true if all implemented correctly, false if otherwise
     */
    public static boolean runStackTestSuite() {
        // keeps track of number of fails encountered
        int failCount = 0;
 
        if (!testStackPushPeek()) {
            failCount++;
            System.out.println("testStackPushPeek() failed");
        }
        if (!testStackPop()) {
            failCount++;
            System.out.println("testStackPop() failed");
        }
        if (!testStackIsEmpty()) {
            failCount++;
            System.out.println("testStackIsEmpty() failed");
        }
        if (!testStackSize()) {
            failCount++;
            System.out.println("testStackSize() failed");
        }
        if (!testStackIterator()) {
            failCount++;
            System.out.println("testStackIterator() failed");
        }  
        // checks if no fails were encountered
        if (failCount == 0) {
            return true;
        }
        return false;
    }
}
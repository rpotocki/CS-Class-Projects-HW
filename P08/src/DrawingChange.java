////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           DrawingChange.java
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
 * This class records the details of a single change
 * made to a canvas
*/
public class DrawingChange {

    public final int x; // x coordinate for a change
    public final int y; // y coordinate for a change
    public final char prevChar; // previous character in the (x,y)
    public final char newChar; // new character in the (x,y)

    /**
     * Initializes the DrawingChange object
     */
    public DrawingChange(int x, int y, char prevChar, char newChar) { 

        this.x = x;
        this.y = y;
        this.prevChar = prevChar;
        this.newChar = newChar;

    }

}

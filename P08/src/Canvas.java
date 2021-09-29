////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Canvas.java
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
     * This class creates a canvas object in which the drawings for AsciiArt are placed on
     * and has methods to modify the canvas in different ways.
    */

public class Canvas {
    private final int width;  // width of the canvas
    private final int height; // height of the canvas

    private char [][] drawingArray; // 2D character array to store the drawing

    private final DrawingStack undoStack; // store previous changes for undo
    private final DrawingStack redoStack; // store undone changes for redo

    /**
     * Constructor. Throws IllegalArgumentException if width or height is 0 or negative
     * A Canvas is initially blank (use the space ' ' character)
    */
    
    public Canvas(int width, int height) { 

        if (width <= 0 || height <= 0) { //ensures canvas has positive values for height/width
            throw new IllegalArgumentException("Height or width cannot be negative or zero");
        }
         
        //initializes the height and width
        this.width = width;
        this.height = height;
        
        //creates a new array to store the drawing
        drawingArray = new char[height][width];
        
        //fills the array with empty spaces until characters are drawn
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                drawingArray[i][j] = ' ';
            }
        }
        //creates new stacks for undo/redo commands in AsciiArt
        this.undoStack = new DrawingStack();
        this.redoStack = new DrawingStack();

    }

    /**
     * Draw a character at the given position.
     * If that position is already marked with a different character, overwrite it.
     * After making a new change, add a matching DrawingChange to the undoStack so that we can undo if needed.
     * After making a new change, the redoStack should be empty.
     * @param row, column, and character you wish to place on the canvas
    */
    
    public void draw(int row, int col, char c) { 

        if ((row < 0 || row > height) || (col < 0 || col > width)) {
            throw new IllegalArgumentException("Position is outside of the canvas");
        }

        char oldArr = drawingArray[row][col];
        DrawingChange drawingChange = new DrawingChange(row, col, oldArr, c);
        undoStack.push(drawingChange);

        if (!redoStack.isEmpty()) { // if redoStack isn't empty, pop
            redoStack.pop();
        }
        drawingArray[row][col] = c;

    } 

    /**
     * Undo the most recent drawing change. Return true if successful. False otherwise.
     * An undone DrawingChange should be added to the redoStack so that we can redo if needed.
    */
    public boolean undo() { 

        DrawingChange recentChange = undoStack.pop();
        draw(recentChange.x, recentChange.y, recentChange.prevChar);
        redoStack.push(recentChange);
        return true; // will return false if unable to undo change
    } 

    /**
     * Redo the most recent undone drawing change. Return true if successful. False otherwise.
     * A redone DrawingChange should be added (back) to the undoStack so that we can undo again if needed.
    */
    public boolean redo() { 

        DrawingChange recentUndoneChange = redoStack.pop();
        draw(recentUndoneChange.x, recentUndoneChange.y, recentUndoneChange.newChar);
        undoStack.push(recentUndoneChange);
        return true; // will return false if unable to redo change

    } 
    
    /**
     * Return a printable string version of the Canvas.
     * @return string version of the canvas
    */
    public String toString() { 
        String displayCanvas = "";

        //loops through the entire array and adds the elements to a string
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                displayCanvas += drawingArray[i][j];
            }
            if (i != width - 1) { //draws to a new line in the canvas
                displayCanvas += System.lineSeparator();
            }
        }
        return displayCanvas;
    }
}
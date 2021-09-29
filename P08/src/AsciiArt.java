import java.util.ArrayList;
import java.util.Scanner;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           AsciiArt.java
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
 * This class is the main driver for the AsciiArt program
 * that handles the user text interface
 */
public class AsciiArt {
  
  static Canvas userCanvas;
  private static ArrayList<String> history;
  private static String historyRecent = "";
  
  /**
   * The main method runs the program until the user would like to quit
   * @param args
   */
  public static void main(String [ ] args) {
    history = new ArrayList<String>();
    boolean continueDrawing = true;
    Scanner scnr = new Scanner(System.in);
    while (continueDrawing) { //while the user wants to keep playing
      printMenu();
      String userInput = scnr.nextLine(); //gathers input from the user
      userInput = userInput.trim();
      if (userInput.equals("7")) { //if the user wants to quit
        System.out.println("Bye.");
        continueDrawing = false;
      }
      else {
        userOption(userInput);
      }
    }
  }

  /**
   * This method handles the user input from the menu and calls the correct
   * methods/creates objects to successfully execute the desired command
   * @param userOption
   */
  private static void userOption(String userInput) {
    Scanner scnr = new Scanner(System.in);
    if (userInput.equals("1")) { //if the user wishes to create a new canvas
      System.out.println("Width: ");
      int canvasWidth = scnr.nextInt();
      System.out.println("Height: ");
      int canvasHeight = scnr.nextInt();
      try {
        userCanvas = new Canvas(canvasWidth, canvasHeight);
      }
      catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
    else if (userInput.equals("2")) { //if the user wants to draw a character to a canvas
      System.out.println("Row: ");
      int drawRow = scnr.nextInt();
      System.out.println("Column: ");
      int drawCol = scnr.nextInt();
      System.out.println("Character: ");
      char drawChar = scnr.next().charAt(0);
      try {
        userCanvas.draw(drawRow, drawCol, drawChar);
        //below adds the drawing to the drawing history array
        history.add("" + (history.size() + 1) + ". draw '" + drawChar +"' on (" + drawRow + "," + drawCol + ")");
        historyRecent = "" + (history.size()) + ". draw '" + drawChar +"' on (" + drawRow + "," + drawCol + ")";
      }
      catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
    else if (userInput.equals("3")) { //if the user wants to undo their last move
      Boolean undoVar = userCanvas.undo();
      if (undoVar) { //removes the drawing history from the array
        history.remove(history.size() - 1);
      }
    }
    else if (userInput.equals("4")) { //if the user wants to redo their last move
      Boolean redoVar = userCanvas.redo();
      if (redoVar) { //adds the redo drawing to the drawing  history array
        history.add(historyRecent);
      }
    }
    else if (userInput.equals("5")) { //if the user wants to display the canvas
      System.out.println(userCanvas.toString());
    }
    else if (userInput.equals("6")) { //if the user wishes to show the drawing history
      for (int i = history.size() - 1 ; i >= 0; i--) { //loops through the array keeping track of past drawings
        System.out.println(history.get(i));
      }
    }
    else { //user entered an invalid input
      System.out.println("Invalid input. Please try again.");
    }
  }
  
  /**
   * This method prints the text-menu for the user
   */
  private static void printMenu() {
    System.out.println("======== MENU ========");
    System.out.println("[1] Create a new canvas");
    System.out.println("[2] Draw a character");
    System.out.println("[3] Undo drawing");
    System.out.println("[4] Redo drawing");
    System.out.println("[5] Show current canvas");
    System.out.println("[6] Show drawing history");
    System.out.println("[7] Exit");
  }
}
    
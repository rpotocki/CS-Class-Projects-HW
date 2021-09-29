import java.util.Random;
import java.util.Scanner;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: GameApplication.java
// Files: (a list of all source files used by that program)
// Course: CS300 Fall 2018
//
// Author: Ryan Potocki
// Email: rpotocki@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class is the main driver for the math game as it handles all the logic and text-based
 * interface the user sees.
 */

public class GameApplication {

  public static void main(String[] args) {
    Random rng = new Random();
    GameList mainList = new GameList();
    Scanner scnr = new Scanner(System.in);
    int goal = rng.nextInt(89) + 10; // creates the target number
    int userMoves = 0;
    boolean notWinner = true;
    // creates the intial 7 nodes
    for (int i = 0; i < 7; i++) {
      GameNode newNode = new GameNode(rng);
      mainList.addNode(newNode);
    }
    while (notWinner) {
      System.out.println("Goal: " + goal + " Moves Taken: " + userMoves);
      System.out.println("Puzzle: " + mainList.toString());
      System.out.println("Number and Operation " + GameOperator.ALL_OPERATORS + " to Apply: ");
      String userInput = scnr.nextLine();
      userInput = userInput.trim();
      if (userInput.toLowerCase().equals("quit")) { // if the user wants to quit
        System.out.println("Goodbye.");
        break;
      }
      int inputLen = userInput.length();
      char inputOperator = userInput.charAt(inputLen - 1);
      GameOperator userOperator = GameOperator.getFromChar(inputOperator);
      if (userInput.substring(0, inputLen - 1).equals("")) { // user only inputs number not operator
        System.out.println("WARNING: Input not accepted");
        System.out.println("Please try again.");
      } else {
        int inputInt = Integer.parseInt(userInput.substring(0, inputLen - 1));
        if (mainList.contains(inputInt)) {
          try {
            mainList.applyOperatorToNumber(inputInt, userOperator); // applies operator to first
                                                                    // appearance of number in list
            userMoves++;
            GameNode extraNode = new GameNode(rng);
            mainList.addNode(extraNode); // adds new node at end of the list
          } catch (NullPointerException e) {
            System.out.println("WARNING: Cannot call operator on last node.");
            System.out.println("Please try again.");
          }
        } else { // invalid input
          System.out.println("WARNING: Input not accepted");
          System.out.println("Please try again.");
        }
        if (mainList.contains(goal)) { // if the target number is in the list
          System.out.println("Congratulations !! You hit the goal !!");
          break;
        }
      }
    }

  }
}

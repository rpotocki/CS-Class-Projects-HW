import java.util.Iterator;
import java.util.Scanner;
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Sequence.java
// Files:           ArithmeticSequenceGenerator, GeometricSequenceGenerator, FibonacciSequenceGenerator, DigitProductSequenceGenerator
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
 * This class represents a sequence generator for a numerical progression
 * This sequence generator can generate arithmetic, geometric, Fibonacci, 
 * and digit product sequences
 * @author mouna & Ryan Potocki
 *
 */

public class Sequence implements Iterable<Integer> {
  // set of constants that represent the sequence types supported by this sequence generator
  public enum SequenceType {
    ARITHMETIC, GEOMETRIC, FIBONACCI, DIGIT_PRODUCT
  };

  private Iterator<Integer> sequenceIterator; // iterator to iterate over this sequence
  private SequenceType sequenceType;  // type of this sequence


  /**
   * Creates a Sequence with respect to a user command line
   * 
   * @param command array of integers that represents the user command line to generate a sequence
   * 
   */
  public Sequence(int[] command) {
    // Note This constructor does not catch any exception
    sequenceType = SequenceType.values()[command[0]]; // set the sequence type
    switch (sequenceType) {
      case ARITHMETIC: // call the constructor of the ArithmeticSequenceGenerator class to create
        // an iterator over an arithmetic sequence with initial element command[1], common
        // difference command[2], and size stored at command[3]
        sequenceIterator = new ArithmeticSequenceGenerator(command[1], command[2], command[3]);
        break;
      case GEOMETRIC: // call the constructor of the GeometriccSequenceGenerator class to create
        // an iterator over an geometric sequence with initial element command[1], common
        // difference command[2], and size stored at command[3]
        sequenceIterator = new GeometricSequenceGenerator(command[1], command[2], command[3]);
        break;
      case FIBONACCI: //calls the constructor of the FibonacciSequenceGenerator class to create
        //an iterator over a fibonacci sequence
        sequenceIterator = new FibonacciSequenceGenerator(command[1]); 
        break;
      case DIGIT_PRODUCT:
        //creates a new DigitProductSequenceGenerator list
        DigitProductSequenceGenerator list = new DigitProductSequenceGenerator(command[1], command[2]);
        //Generates the sequence based on the object created above
        list.generateSequence();
        //creates an iterator over the digit product sequence
        sequenceIterator = list.getIterator();
        break;
    }
  }


  
  /**
   * Returns a String representation of the generated sequence
   * @return String that includes the sequence name and the different numbers of 
   * the sequence separated by a single space
   */
  @Override
  public String toString() {
    // Time Complexity: O(N)
    String seq = sequenceType.name() + " sequence: "; // initialize the String seq to return
    // Use a for-each loop to traverse the sequence and add the different numbers of the sequence 
    // to its string representation, separated by a single space
    for (Integer i : this)
      seq += i + " ";
    return seq;
  }

  /**
   * This helper method checks for the correctness of the syntax of the user command with respect to
   * the sequence type
   * @param userCommand command entered by the user
   * @return true if the userCommand contains a syntax error with respect to the program specification,
   * false otherwise
   */
  private static boolean checkCommandSyntax(String[] userCommand) {

    //Time Complexity: O(1)
    
    boolean syntaxError = false;
    switch (userCommand[0].trim()) {
      case "0": // Arithmetic progression
      case "1": // Geometric progression
        if (userCommand.length != 4)
          syntaxError = true;
        break;
      case "2": // Fibonacci progression
        if (userCommand.length != 2)
          syntaxError = true;
        break;
      case "3":
        if (userCommand.length != 3) // Digit Product progression
          syntaxError = true;
        break;
      default:
        syntaxError = true;
    }
    return syntaxError;
  }
  
  @Override
  public Iterator<Integer> iterator() {
    return sequenceIterator;
  }
  /**
   * main method that represents the driver application: starts the program, displays the command 
   * menu, prompt the user for input, and displays the expected output
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("=================   Welcome to the Sequence Generator App   =================\r\n");
    // Set the menu, prompt the user command, error messages, and goodbye strings
    final String menu = "COMMAND MENU: \r\n" + " [Sequence_Code] [Sequence_Parameters]\r\n"
        + "   [0 (for ARITHMETIC)  ] [First_Number Common_Difference Sequence_Size]\r\n"
        + "   [1 (for GEOMETRIC)   ] [First_Number Common_Ratio Sequence_Size]\r\n"
        + "   [2 (for FIBONACCI)   ] [Sequence_Size]\r\n"
        + "   [3 (for DIGIT PRODUCT SEQUENCE)] [First_Number Sequence_Size]\r\n " + " \r\n"
        + " [Q]uit Program\r\n";
    final String promptUser = "\nENTER COMMAND: ";
    final String errorMsg = "SYNTAX ERROR. Please refer to the above COMMAND MENU for details.";
    final String formatErrMsg = "ERROR: COMMAND must contain ONLY space separated integer values";
    final String goodBye = "===================   Thank you for using this App!!!!   ===================";

    boolean syntaxError = false; // boolean variable that tells if the syntax of the user command is
                                 // correct
    Sequence sequence; // sequence object
    Scanner input = new Scanner(System.in); // Scanner object to read the user input 
                                            // from the keyboard

    int[] seqCommand; // array of integers that represents the command line parts

    // Display menu and prompt the user
    System.out.println(menu);
    System.out.print(promptUser);

    // read the user command line
    String line = input.nextLine().trim();
    char firstChar = line.charAt(0);// first character in the user command
    // process the user command line
    while (Character.toUpperCase(firstChar) != 'Q') {
      // Parse the user command
      String[] userCommand = line.trim().split(" "); // Array of Strings representing the command
      // Checks for the correctness of the userCommand Syntax
      syntaxError = checkCommandSyntax(userCommand);

      if (!syntaxError) { 
        // process the user command
        seqCommand = new int[userCommand.length];
        try {
          // convert the user command to integers
          for (int i = 0; i < userCommand.length; i++)
            seqCommand[i] = Integer.parseInt(userCommand[i]);
          // generate the sequence with respect to the processed user command line
          sequence = new Sequence(seqCommand);
          // display the sequence
          System.out.println(sequence);
          
        } catch (NumberFormatException e) {
          System.out.println(formatErrMsg);
        } catch (IllegalArgumentException bug) {
          System.out.println(bug.getMessage());
        }
      } else {// Syntax error
        System.out.println(errorMsg);
      }
      // prompt the user
      System.out.print(promptUser);
      line = input.nextLine().trim();
      firstChar = line.charAt(0);

    }
    input.close(); // free the Scanner resource
    System.out.println(goodBye); // display goodbye message
  }

}
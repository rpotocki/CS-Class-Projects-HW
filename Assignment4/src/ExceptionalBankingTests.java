import java.io.File;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           ExceptionalBankingTests
//Files:           Account.java, TransactionGroup.java
//Course:          CS 300 Fall 2018
//
//Author:          Ryan Potocki
//Email:           rpotocki@wisc.edu
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
//Persons:         NONE
//Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class ExceptionalBankingTests {

  public static boolean testAccountBawlance() {
    return false;
  }
  
  public static boolean testOverdraftCount() { 
    return false;
    
  }
  
  /**
   * This method checks whether the TransactionGroup constructor throws an exception with an
   * appropriate message, when it is passed an empty int[].
   * @return true when test verifies   correct functionality, and false otherwise.
   */
  public static boolean testTransactionGroupEmpty() { 
    int[] groupEncoding = new int[0]; //creates empty array
    try {
      TransactionGroup test = new TransactionGroup(groupEncoding);
    }
    catch (DataFormatException e) {
      //checks if the message is for the right exception that was thrown/caught
      if (e.getMessage().equals("transaction group encoding cannot be null or empty")) {
        return true;
      } 
    }
    return false; 
  }
   
  /**
   * This method checks whether the TransactionGroup constructor throws an exception with an
   * appropriate message, when then first int in the provided array is not 0, 1, or 2.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testTransactionGroupInvalidEncoding() {
    int[] groupEncoding = new int[] {5,0,1,1,3};
    try {
      TransactionGroup test = new TransactionGroup(groupEncoding);
    }
    catch (DataFormatException e) {
    //checks if the message is for the right exception that was thrown/caught
      if (e.getMessage().equals("the first element within a transaction group must be 0, 1, or 2")) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * This method checks whether the Account.addTransactionGroup method throws an exception with an
   * appropriate message, when it is passed a quick withdraw encoded group that contains negative
   * numbers of withdraws.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountAddNegativeQuickWithdraw() {
    Account testAcc = new Account("test");
    try {
      testAcc.addTransactionGroup("2 -5 2 3 3"); //tries to call method with a negative number in the string
    }
    catch (DataFormatException e) {
    //checks if the message is for the right exception that was thrown/caught
      if (e.getMessage().equals("quick withdraw transaction groups may not contain negative numbers")) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * This method checks whether the Account.addTransactionGroup method throws an exception with an
   * appropriate message, when it is passed a string with space separated English words (non-int).
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountBadTransactionGroup() {
      Account testAcc = new Account("test");
      try {
        testAcc.addTransactionGroup("crazy test methods"); //tries to add a transaction group consisting of strings (that are not ints)
      }
      catch (DataFormatException e) { 
        //checks if the message is for the right exception that was thrown/caught
        if (e.getMessage().equals("addTransactionGroup requires string commands that contain only space separated integer values")) {
          return true;
        }
      }
      return false;
  }


  /**
   * This method checks whether the Account.getTransactionAmount method throws an exception with an
   * appropriate message, when it is passed an index that is out of bounds.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountIndexOutOfBounds() { 
    Account testAcc = new Account("test");
    try {
      testAcc.getTransactionAmount(10001); //tries to use an index that not in the array
    }
    catch (IndexOutOfBoundsException e) {
      return true;
    }
    return false;
  }
  
  /**
   * This method checks whether the Account constructor that takes a File parameter throws an 
   * exception with an appropriate message, when it is passed a File object that does not correspond
   * to an actual file within the file system.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountMissingFile() { 
    File testFile = new File("testFile.txt"); //creates a file that doesn't exist
    try {
      Account testAcc = new Account(testFile); //tried to make an account with the file that doesn't exist
    }
    catch (FileNotFoundException e ) {
      return true;
    }
    return false;
  }
}

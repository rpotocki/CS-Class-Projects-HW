import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.zip.DataFormatException;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           ExceptionalBanking
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
public class Account {
  
  private static final int MAX_GROUPS = 10000;
  private static int nextUniqueId = 1000; 
  
  private String name;
  private final int UNIQUE_ID; 
  private TransactionGroup[] transactionGroups;
  private int transactionGroupsCount; 
  
  public Account(String name) {
   this.name = name;
   this.UNIQUE_ID = Account.nextUniqueId;
   Account.nextUniqueId++;
   this.transactionGroups = new TransactionGroup[MAX_GROUPS];
   this.transactionGroupsCount = 0;
  }
  
  /**
   * This method creates a new TransactionGroup object from an array of integers.
   *
   * @throws FileNotFoundException if the file input is non-existant
   */
  public Account(File file) throws FileNotFoundException {
    // NOTE: THIS METHOD SHOULD NOT BE CALLED MORE THAN ONCE, BECAUSE THE
    // RESULTING BEHAVIOR IS NOT DEFINED WITHIN THE JAVA SPECIFICATION ...
      Scanner in = new Scanner(file);
    // ... THIS WILL BE UPDATED TO READ FROM A FILE INSTEAD OF SYSTEM.IN.
    this.name = in.nextLine();
    this.UNIQUE_ID = Integer.parseInt( in.nextLine() );
    Account.nextUniqueId = this.UNIQUE_ID + 1;
    this.transactionGroups = new TransactionGroup[MAX_GROUPS];
    this.transactionGroupsCount = 0;    
    String nextLine="";
      while(in.hasNextLine()) {
        try { //tries to add the line as a transaction group
          this.addTransactionGroup(in.nextLine());
        }
        catch (DataFormatException e) {
            System.out.println(e.getMessage());
        }
    }
      in.close();
  }
  
  /**
   * This method returns the accounts UNIQUE_ID
   *
   * @returns UNIQUE_ID
   */
  public int getId() { 
    return this.UNIQUE_ID;
  }
  
  /**
   * This method creates adds a string input command to the transaction group
   *
   * @throws DataFormatException in some circumstances
   * @throws OutOfMemoryError is the account is at its maximum
   */
  public void addTransactionGroup(String command) throws DataFormatException{
    if (this.transactionGroupsCount >= MAX_GROUPS) {
      throw new OutOfMemoryError("The capacity of this account is at the maximum of " + MAX_GROUPS + ".");
    }
    try {
      String[] parts = command.split(" ");
      int[] newTransactions = new int[parts.length];
      for (int i = 0; i < parts.length; i++)
        newTransactions[i] = Integer.parseInt(parts[i]);
      TransactionGroup t = new TransactionGroup(newTransactions); //creates a new transaction group with the transactions read in
      this.transactionGroups[this.transactionGroupsCount] = t;
      this.transactionGroupsCount++;
    }
    catch (RuntimeException e) {
      throw new DataFormatException("addTransactionGroup requires string commands that contain only space separated integer values");
    }
  }

  /**
   * This method gets the transaction count for an account
   *
   * @returns transactionCount
   */
  public int getTransactionCount() {
    int transactionCount = 0;
    for(int i=0;i<this.transactionGroupsCount;i++)
      transactionCount += this.transactionGroups[i].getTransactionCount(); //gets transaction count for each group
    return transactionCount;
  }
  
  /**
   * This method returns the value for a transaction within an account
   *
   *@returns transactionAmount if worked correctly, otherwise -1
   * @throws DataFormatException in some circumstances
   */
  public int getTransactionAmount(int index) {    
    int transactionCount = 0;
    if (index >= this.transactionGroups.length) { //checks if the index is in bounds
      throw new IndexOutOfBoundsException("The index " + index + "is larger than the array size " + this.transactionGroups.length + ".");
    }
    for(int i=0;i<this.transactionGroupsCount;i++) { //loops through all transaction groups
      int prevTransactionCount = transactionCount;
      transactionCount += this.transactionGroups[i].getTransactionCount();
      if(transactionCount > index) {
        index -= prevTransactionCount;
        return this.transactionGroups[i].getTransactionAmount(index);
      }
    }
    return -1;
  }
  
  /**
   * This method calculates the current balance of an account
   *
   *@returns balance of an account
   */
  public int getCurrentBalance() {
    int balance = 0;
    int size = this.getTransactionCount();
    for(int i=0;i<size; i++)
      balance += this.getTransactionAmount(i);
    return balance;
  }
  
  /**
   * This method calculates the number of overdrafts for an account
   *
   *@returns overdraftCount
   */
  public int getNumberOfOverdrafts() {
    int balance = 0;
    int overdraftCount = 0;
    int size = this.getTransactionCount();
    for(int i=0;i<size; i++) {
      int amount = this.getTransactionAmount(i); //gets the amount from the transaction
      balance += amount;
      if(balance < 0 && amount < 0) //checks if it was an overdraft
        overdraftCount++;
    }
    return overdraftCount;
  }
    
}
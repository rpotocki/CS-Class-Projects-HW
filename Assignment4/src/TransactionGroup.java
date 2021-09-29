import java.util.zip.DataFormatException;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           ExceptionalBanking
//Files:           TransactionGroup.java
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

public class TransactionGroup {
  
  private enum EncodingType { BINARY_AMOUNT, INTEGER_AMOUNT, QUICK_WITHDRAW };
  private EncodingType type;
  private int[] values;

  /**
   * This method creates a new TransactionGroup object from an array of integers.
   *
   * @throws DataFormatException in some circumstances
   */
  
  public TransactionGroup(int[] groupEncoding) throws DataFormatException {
    if (groupEncoding == null || groupEncoding.length == 0 || groupEncoding.length == 1) {
      throw new DataFormatException("transaction group encoding cannot be null or empty");
    }
    if (groupEncoding[0] != 0 && groupEncoding[0] != 1 && groupEncoding[0] != 2) { //makes sure the first element matches a valid transaction
      throw new DataFormatException("the first element within a transaction group must be 0, 1, or 2");
    }
    this.type = EncodingType.values()[groupEncoding[0]]; //determines the type of transaciton
    if (this.type.equals(EncodingType.values()[0])) {
      for (int i = 0; i < groupEncoding.length; i++) {
        if (groupEncoding[i] != 0 && groupEncoding[i] != 1) {
          throw new DataFormatException("binary amount transaction groups may only contain 0s and 1s");
        }
      }
    }
    else if (this.type.equals(EncodingType.values()[1])) {
      for (int i = 1; i < groupEncoding.length; i++) {
        if (groupEncoding[i] == 0) {
          throw new DataFormatException("integer amount transaction groups may not contain 0s");
        }
      }
    }
    else if (this.type.equals(EncodingType.values()[2])) {
      if (groupEncoding.length != 5) {
        throw new DataFormatException("quick withdraw transaction groups must contain 5 elements");
      }
      for (int i = 1; i < groupEncoding.length; i++) {
        if (groupEncoding[i] < 0) {
          throw new DataFormatException("quick withdraw transaction groups may not contain negative numbers");
        }
      }
    }
    this.values = new int[groupEncoding.length-1];
    for(int i=0;i<values.length;i++)
      this.values[i] = groupEncoding[i+1];
  }
  
  /**
   * This method calculates the amount of transactions within a group. There are
   * 3 types of transactions possible 1) BINARY 2) INTEGER 3) WITHDRAW -- Each is handled differently.
   * 
   * @return transactionCount
   */
  
  public int getTransactionCount() {
    int transactionCount = 0;
      switch(this.type) { //determines what type the TransactionGroup is
        case BINARY_AMOUNT:
          int lastAmount = -1;
          for(int i=0;i<this.values.length;i++) { //loops through all items in the array
            if(this.values[i] != lastAmount) { //if the binary number is different then the last it is treated as a new transactionCount
              transactionCount++; 
              lastAmount = this.values[i];            
            }
          }
          break;
        case INTEGER_AMOUNT:
          transactionCount = values.length;
          break;
        case QUICK_WITHDRAW:
          for(int i=0;i<this.values.length;i++)
            transactionCount += this.values[i]; //counts how many withdraws occur
      }
    return transactionCount;
  }
  
  /**
   * This method calculates the the value of a transaction group. There are
   * 3 types of transactions possible 1) BINARY 2) INTEGER 3) WITHDRAW -- Each is handled differently.
   * 
   * @throws IndexOutOfBoundsException
   * @return transactionAmount if works correctly, else -1
   */
  public int getTransactionAmount(int transactionIndex) throws IndexOutOfBoundsException {
    int transactionCount = 0;
    if (transactionIndex >= this.values.length) { //checks for if the index given is out of bounds
      throw new IndexOutOfBoundsException("The index " + transactionIndex + "is larger than the array size " + this.values.length +".");
    }
      switch(this.type) { //determines which type of transaction it is
        case BINARY_AMOUNT:
          int lastAmount = -1;
          int amountCount = 0;
          for(int i=0;i<=this.values.length;i++) {
            if(i == this.values.length || this.values[i] != lastAmount)  { //Checks to see if it's the end or the array
              if(transactionCount-1 == transactionIndex) { //checks how long the transaction array is
                if(lastAmount == 0)
                  return -1 * amountCount; //the transactionGroup withdraws the money
                else
                  return +1 * amountCount; //the transactionGroup deposits the money
              }
              transactionCount++;       
              lastAmount = this.values[i];
              amountCount = 1;
            } else
              amountCount++;
            lastAmount = this.values[i];
          } 
          break;
        case INTEGER_AMOUNT:
          return this.values[transactionIndex];
        case QUICK_WITHDRAW:
          final int[] QW_AMOUNTS = new int[] {-20, -40, -80, -100};
          for(int i=0;i<this.values.length;i++) 
            for(int j=0;j<this.values[i];j++)
              if(transactionCount == transactionIndex) 
                return QW_AMOUNTS[i]; 
              else 
                transactionCount++; //increments transactionCount to see how many withdrawls occured
      }
    return -1;
  }  
}
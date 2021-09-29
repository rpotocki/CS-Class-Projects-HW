import java.util.Scanner;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           AuditableBanking
//Files:           
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


/**
This class runs the AuditableBanking simulation that allows users to input transactions in 1 of 3 ways, check the balance or their account, the amount of overdrafts,
and finally quit the application.
 */
public class AuditableBanking {
	
	
	public static void main(String [ ] args){
		Scanner scnr = new Scanner(System.in);
		int allTransactionsCount = 0;
		int[][] allTransactions = new int[200][];
		System.out.println("========== Welcome to the Auditable Banking App ==========");
		boolean repeater = true;
		while (repeater) {
			System.out.println("COMMAND MENU:");
			System.out.println("  Submit a Transaction (enter sequence of integers separated by spaces)");
			System.out.println("  Show Current [B]alance");
			System.out.println("  Show Number of [O]verdrafts");
			System.out.println("  [Q]uit Program");
			System.out.print("ENTER COMMAND: ");
			String command = scnr.nextLine();
			command = command.toUpperCase();
			if (command.equals("Q")) {
				System.out.println("============ Thank you for using this App!!!! ============");
				repeater = false;
			}
			else {
				allTransactionsCount = processCommand(command, allTransactions, allTransactionsCount);
			}
			System.out.println("");
		}
		
	}

	/**
	 * Adds a transaction group to an array of transaction groups. If the allTransactions array is
	 * already full then this method will do nothing other than return allTransactionCount.
	 * 
	 * @param newTransactions is the new transaction group being added (perfect size).
	 * @param allTransactions is the collection that newTransactions is being added to (oversize).
	 * @param allTransactionsCount is the number of transaction groups within allTransactions 
	 *        (before newTransactions is added.
	 * @return the number of transaction groups within allTransactions after newTransactions is added.
	 */
	public static int submitTransactions(int[] newTransactions, int[][] allTransactions,
	    int allTransactionsCount) {
	  if (allTransactionsCount > allTransactions.length) {
		  return allTransactionsCount;
	  }
	  else {
		  allTransactions[allTransactionsCount] = newTransactions;
		  allTransactionsCount += 1;
	  }
	  return allTransactionsCount;
	}
	
	
	/**
	 * This method takes process transactions by adding them to the allTranactions array with the help of the submitTransactions method
	 * It also processes whether the users' input was a transaction or a request to check their balance/overdrafts
	 * 
	 * @param command The user input for the transaction or balance/overdraft check
	 * @param allTransactions is the collection that newTransactions is being added to (oversize).
	 * @param allTransactionsCount is the number of transaction groups within allTransactions 
	 *        (before newTransactions is added.
	 * @return the number of transaction groups within allTransactions after newTransactions is added.
	 */
	
	static int processCommand(String command, int[][] allTransactions, int allTransactionsCount) {
		//if allTransactions is full
		command = command.toUpperCase();
		if (command.equals("B")) {
			int balance = calculateCurrentBalance(allTransactions, allTransactionsCount);
			System.out.println("Current Balance: " + balance);
			return allTransactionsCount;
		}
		if (command.equals("O")) {
			int overdrafts = calculateNumberOfOverdrafts(allTransactions, allTransactionsCount);
			System.out.println("Number of Overdrafts: " + overdrafts);
			return allTransactionsCount;
		}
		else {
			String[] user_input = command.split(" "); 
			//Checks to see if the command can even be processed as a transaction
			if (user_input[0].equals("0") || user_input[0].equals("1") || user_input[0].equals("2")){
				//creates an array of integers to be added to the allTransactions array
				int[] transactions= new int[user_input.length];
				for (int n = 0; n < user_input.length; n++) {
					transactions[n] = Integer.parseInt(user_input[n]);
				}
				allTransactionsCount = submitTransactions(transactions, allTransactions, allTransactionsCount);
				return allTransactionsCount;
			}
			else {
				return allTransactionsCount;
			}
		}
	}
	
	
	/**
	 * Calculates the balance of all the transactions (all 3 types of encoding)
	 * 
	 * @param allTransactions is the collection that newTransactions is being added to (oversize).
	 * @param allTransactionsCount is the number of transaction groups within allTransactions 
	 *       
	 * @return the balance for all the transactions
	 */
	
	public static int calculateCurrentBalance(int[][] allTransactions, int allTransactionsCount) {
		int balance = 0;
		for (int n = 0; n < allTransactionsCount; n++) {
			//runs if the transaction is in binary
			if (allTransactions[n][0] == 0) {
			  for (int l = 1; l < allTransactions[n].length; l++) {
				  if (allTransactions[n][l] == 1) {
					  balance += 1;
				  }
				  if (allTransactions[n][l] == 0) {
					  balance -= 1;
				  }
			  }
			}
			//runs if the transaction is in whole number (Ex. +10 -100)
			else if (allTransactions[n][0] == 1) {
				for (int k = 1; k < allTransactions[n].length; k++) {
					 balance += allTransactions[n][k];
				  }
			}
			//runs if the transaction is quick withdraw
			else {
				 balance -= allTransactions[n][1] * 20;
				 balance -= allTransactions[n][2] * 40;
				 balance -= allTransactions[n][3] * 80;
				 balance -= allTransactions[n][4] * 100;
			}		
		}
		return balance;
	}
	
	/**
	 * Calculates the balance of all the transactions and then determines the number of overdrafts
	 * 
	 * @param allTransactions is the collection that newTransactions is being added to (oversize).
	 * @param allTransactionsCount is the number of transaction groups within allTransactions 
	 *       
	 * @return the number of overdrafts
	 */
	public static int calculateNumberOfOverdrafts(int[][] allTransactions, int allTransactionsCount) {
		int overdraftNum = 0;
		int tempBalance;
		for (int i = 0; i < allTransactionsCount; i++) {
			if (i == 0) {
				tempBalance = 0;
			}
			else {
				tempBalance = calculateCurrentBalance(allTransactions, i);
			}
			if (allTransactions[i][0] == 0) { //calculates tempBalance for binary
				  for (int l = 1; l < allTransactions[i].length; l++) {
					  if (allTransactions[i][l] == 1) {
						  tempBalance += 1;
					  }
					  if (allTransactions[i][l] == 0) {
						  tempBalance -= 1;
						  if (tempBalance < 0) {
							  overdraftNum += 1;
					  }
				  }
				}
				
			}
			else if (allTransactions[i][0] == 1) { //calculates tempBalance based on the number withdrawn
				for (int k = 1; k < allTransactions[i].length; k++) {
					 if ((tempBalance <= 0 && allTransactions[i][k] < 0) || (tempBalance > 0 && (tempBalance + allTransactions[i][k] < 0) )){
						 overdraftNum += 1;
						 tempBalance += allTransactions[i][k];
					 }
					 else {
						 tempBalance += allTransactions[i][k]; 
					 }
				}
			}
			else { //calculates tempBalance for quick withdraw
				if ((tempBalance <= 0 && allTransactions[i][1] > 0) || (tempBalance - (allTransactions[i][1] * 20)) < 0  ) {
					tempBalance -= allTransactions[i][1] * 20;
					overdraftNum += 1;
				}
				else {
					tempBalance -= allTransactions[i][1] * 20;
				}
				if ((tempBalance <= 0 && allTransactions[i][2] > 0) || (tempBalance - (allTransactions[i][2] * 40)) < 0  ) {
					tempBalance -= allTransactions[i][2] * 20;
					overdraftNum += 1;
				}
				else {
					tempBalance -= allTransactions[i][2] * 20;
				}
				if ((tempBalance <= 0 && allTransactions[i][3] > 0) || (tempBalance - (allTransactions[i][3] * 80)) < 0  ) {
					tempBalance -= allTransactions[i][3] * 20;
					overdraftNum += 1;
				}
				else {
					tempBalance -= allTransactions[i][3] * 20;
				}
				if ((tempBalance <= 0 && allTransactions[i][4] > 0) || (tempBalance - (allTransactions[i][4] * 100)) < 0  ) {
					tempBalance -= allTransactions[i][4] * 20;
					overdraftNum += 1;
				}
				else {
					tempBalance -= allTransactions[i][4] * 20;
				}
			}
		}
		return overdraftNum;
	}
}

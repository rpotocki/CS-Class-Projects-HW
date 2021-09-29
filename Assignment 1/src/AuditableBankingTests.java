import java.util.Arrays;

public class AuditableBankingTests {

	
	public static void main(String [ ] args){
		testCalculateNumberOfOverdrafts();
		testProcessCommand();
		testCalculateCurrentBalance();
	}
	
	public static boolean testProcessCommand() {
		 boolean foundProblem = false;
		 int[][] transactions = new int[10][];
		 int transactionNumber = AuditableBanking.processCommand("0 1 1 0 0 1 1 0", transactions, 0);
		 if (transactionNumber == 1) {
			 foundProblem = false;
			 System.out.println("PASSED TEST 1/2 of TestProcessCommand!!!");
		 }
		 else {
			 foundProblem = true;
			 System.out.println("FAILURE: testProcessCommand didn't return 1 when it should have.");
		 }
		 transactionNumber = AuditableBanking.processCommand("2 2 3 4 1", transactions, 5);
		 if (transactionNumber == 6) {
			 foundProblem = false;
			 System.out.println("PASSED TEST 2/2 of TestProcessCommand!!!");
		 }
		 else {
			 foundProblem = true;
			 System.out.println("FAILURE: testProcessCommand didn't return 6 when it should have.");
		 }
		 return foundProblem;
	}
	
	public static boolean testCalculateCurrentBalance() {
		 boolean foundProblem = false;
		  int[][] transactions = new int[][] {
		    {1,10,-20,+30,-20,-20}         
		  };
		  int balance = AuditableBanking.calculateCurrentBalance(transactions, 1);
		  if (balance == -20) {
			  foundProblem = false;
			  System.out.println("PASSED TEST 1/2 of TestCalculateCurrentBalance!!!");
		  }
		  else {
			  System.out.println("FAILURE: testCalculateCurrentBalance didn't return -20 when it should have.");
			  System.out.println(balance);
		  }
		  int[][] transactions1 = new int[][] {
			    {1,10,-20,+30,-20,-20}, 
			    {0,1,1,1,0,0,1,1,1,1},  
			    {1,115},                
			    {2,3,1,0,1},            
			  };
		  int balance1 = AuditableBanking.calculateCurrentBalance(transactions1, 4);
		  if (balance1 == -100) {
			  foundProblem = false;
			  System.out.println("PASSED TEST 2/2 of TestCalculateCurrentBalance!!!");
		  }
		  else {
			  System.out.println("FAILURE: testCalculateCurrentBalance didn't return -100 when it should have.");
			  System.out.println(balance);
		  }
		  return foundProblem;
	}
	
	public static boolean testCalculateNumberOfOverdrafts() {
		  boolean foundProblem = false;
		  int[][] transactions = new int[][] {
		    {1,10,-20,+30,-20,-20}, // +2 overdrafts (ending balance:  -20)
		    {0,1,1,1,0,0,1,1,1,1},  // +2 overdrafts (ending balance:  -15)
		    {1,115},                // +0 overdrafts (ending balance: +100)
		    {2,3,1,0,1},            // +1 overdrafts (ending balance: -100)
		  };
		  
		  // test with a single transaction of the Integer Amount encoding
		  int transactionCount = 1;    
		  int overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions,transactionCount);
		  if(overdrafts != 2) {
		    System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 2 when transactionCount = 1, and transactions contained: "+Arrays.deepToString(transactions));
		    foundProblem = true;
		  } else
		    System.out.println("PASSED TEST 1/2 of TestCalculateNumberOfOverdrafts!!!");
		 
		  // test with four transactions: including one of each encoding
		  transactionCount = 4;
		  overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions,transactionCount);
		  if(overdrafts != 5) {
		    System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 5 when transactionCount = 4, and transactions contained: "+Arrays.deepToString(transactions));
		    foundProblem = true;
		  } else
		    System.out.println("PASSED TESTS 2/2 of TestCalculateNumberOfOverdrafts!!!");
		  
		  return !foundProblem;
		}
	
	
}

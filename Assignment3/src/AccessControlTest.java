import java.util.Scanner;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AccessControlTest.java
// Files: User.java, AccessControl.java
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
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class AccessControlTest {
  /*
   * Testing main. Runs each test and prints which (if any) failed.
   */
  public static void main(String[] args) {
    int fails = 0;
    if (!testLogin1()) {
      System.out.println("testLogin1 [bad username] failed");
      fails++;
    }
    if (!testLogin2()) {
      System.out.println("testLogin2 [good login] failed");
      fails++;
    }
    if (!testLogin3()) {
      System.out.println("testLogin3 [bad username with default password] failed");
      fails++;
    }
    if (!testAddUser1()) {
      System.out.println("testAddUser1 failed");
      fails++;
    }
    if (!testChangePass()) {
      System.out.println("testChangePass failed");
      fails++;
    }
    if (!testRemoveUser()) {
      System.out.println("testRemoveUser failed");
      fails++;
    }
    if (!testResetPassword()) {
      System.out.println("testResetPassword failed");
      fails++;
    }
    if (fails == 0)
      System.out.println("All tests passed!");
  }

  /*
   * Create a new AccessControl and do not log in an admin. Verify that addUser(String username)
   * returns false and that the new user is not added.
   * 
   * @return boolean test passed
   */
  public static boolean testAddUser1() {
    AccessControl ac = new AccessControl();
    String user = "alexi";
    boolean addUserReport = ac.addUser(user);
    if (addUserReport)
      return false; // addUserReport should be false
    // Make sure user wasn't added anyway
    return !ac.isValidLogin(user, "changeme");
  }

  /*
   * This test tries to log in a user that doesn't exist * @return boolean test passed
   */
  public static boolean testLogin1() {
    AccessControl ac = new AccessControl();
    String user = "probablyNotInTheSystem1234";
    String pw = "password";
    return !ac.isValidLogin(user, pw);
  }

  /*
   * This test tries to see if a user can change their password * @return boolean test passed
   */
  public static boolean testChangePass() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin"); // log into admin users
    ac.addUser("kevin"); // adds new user Kevin
    ac.setCurrentUser("kevin"); // sets kevin as current user
    ac.changePassword("spacey"); // changes his password
    return ac.isValidLogin("kevin", "spacey"); // checks if changing password worked
  }

  /*
   * This test tries to log in a user that exists * @return boolean test passed
   */
  public static boolean testLogin2() {
    AccessControl ac = new AccessControl();
    String user = "admin";
    String pw = "root";
    return ac.isValidLogin(user, pw);
  }

  /*
   * This test tries to log in a user that has the wrong username, but right password * @return
   * boolean test passed
   */
  public static boolean testLogin3() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin"); // log into admin users
    ac.addUser("kevin"); // adds new user Kevin
    return !ac.isValidLogin("kevins", "changeme"); // checks if changing password worked
  }

  /*
   * This test add a user then removes them, and checks if they were successfully removed * @return
   * boolean test passed
   */
  public static boolean testRemoveUser() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin"); // log into admin users
    ac.addUser("kevin"); // adds new user Kevin
    ac.removeUser("kevin"); // removes user
    return !ac.isValidLogin("kevin", "changeme"); // checks if kevin is a user or not
  }

  public static boolean testResetPassword() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin"); // log into admin users
    ac.addUser("kevin"); // adds new user Kevin
    ac.setCurrentUser("kevin"); // log into admin users
    ac.changePassword("spacey"); // changes his password
    ac.setCurrentUser("admin");// sets admin back as current user
    ac.resetPassword("kevin"); // resets kevins password
    return ac.isValidLogin("kevin", "changeme"); // checks if kevins password is the default one
  }
}

import java.util.ArrayList;
import java.util.Scanner;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AccessControl.java
// Files: User.java
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
/**
 * This class contains the methods to create a AccessControl Object
 * 
 */
public class AccessControl {

  private static ArrayList<User> users; // An ArrayList of valid users.

  private User currentUser; // Who is currently logged in, if anyone?

  private static final String DEFAULT_PASSWORD = "changeme"; // Default password given to new users
                                                             // or when we reset a user's password.

  public AccessControl() {// A no-parameter constructor
    if (users == null) {
      users = new ArrayList<>();
      users.add(new User("admin", "root", true));
      currentUser = null;
    }
  }

  /*
   * Report whether a given username/password pair is a valid login
   * 
   * @return boolean
   */
  public static boolean isValidLogin(String username, String password) {
    for (int i = 0; i < users.size(); i++) {
      if ((users.get(i).getUsername().equals(username)) && (users.get(i).isValidLogin(password))) {
        return true;
      }
    }
    return false;
  }

  /*
   * Change the current user's password
   */
  public void changePassword(String newPassword) {
    currentUser.setPassword(newPassword);
  }

  /*
   * Change the current user's password
   */
  public void logout() { // Log out the current user
    currentUser = null;
  }

  /*
   * Change the current user's password
   */
  public void setCurrentUser(String username) { // A mutator you can use to write tests without
                                                // simulating user input
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        currentUser = users.get(i);
        break;
      }
    }
  }

  /*
   * Change the current user's password
   */
  public boolean addUser(String username) { // Create a new user with the default password and
                                            // isAdmin==false
    if (currentUser == null) {
      return false;
    }
    for (int i = 0; i < users.size(); i++) { // checks if the username is taken
      if (users.get(i).getUsername().equals(username)) {
        return false;
      }
    }
    if (currentUser.getIsAdmin()) { // makes sure the user accessing the command is an admin
      users.add(new User(username, DEFAULT_PASSWORD, false));
      return true;
    }
    return false;
  }

  /*
   * Create a new user and specify their admin status
   * 
   * @returns boolean
   */
  public boolean addUser(String username, boolean isAdmin) {
    if (currentUser == null) {
      return false;
    }
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) { // checks if the user already exists
        return false;
      }
    }
    if (currentUser.getIsAdmin()) { // makes sure current user is admin before adding the new user
      users.add(new User(username, DEFAULT_PASSWORD, isAdmin));
      return true;
    }
    return false;
  }

  /*
   * Remove a user (names should be unique)
   * 
   * @returns boolean
   */
  public boolean removeUser(String username) {
    if (currentUser == null) {
      return false;
    } else if (currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) {
          users.remove(i);
          return true;
        }
      }
    }
    return false;
  }

  /*
   * Give a user admin power
   * 
   * @returns boolean
   */
  public boolean giveAdmin(String username) {
    if (currentUser == null) {
      return false;
    } else if (currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) {
          users.get(i).setIsAdmin(true);
          return true;
        }
      }
    }
    return false;
  }

  /*
   * Remove a user's admin power
   * 
   * @returns boolean
   */
  public boolean takeAdmin(String username) {
    if (currentUser == null) {
      return false;
    } else if (currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) {
          users.get(i).setIsAdmin(false);
          return true;
        }
      }
    }
    return false;
  }

  /*
   * Reset a user's password
   * 
   * @returns boolean
   */
  public boolean resetPassword(String username) {
    if (currentUser == null) {
      return false;
    } else if (currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) {
          users.get(i).setPassword(DEFAULT_PASSWORD);
          return true;
        }
      }
    }
    return false;
  }

  /*
   * Prompt the user for login information calls the isValidLogin method. If the login is valid,
   * call the sessionScreen method
   */
  public void loginScreen(Scanner userInputScanner) {
    System.out.println("Welcome to Access Controller. Login Below.");
    while (true) { // promts a user for login information if one is not current logged in
      System.out.println("Please enter a username: ");
      String usernameResponse = userInputScanner.nextLine();
      System.out.println("Please enter a password: ");
      String passwordResponse = userInputScanner.nextLine();
      if (isValidLogin(usernameResponse, passwordResponse)) {
        sessionScreen(usernameResponse, userInputScanner);
      } else {
        System.out.println("Username or password invalid. Please try again.");
      }
    }
  }

  /*
   * This class runs the text control panel for the AccessControl system. It allows users to input
   * what command they'd like to do, and then executes them by calling the appropriate methods from
   * within the AccessControl class.
   */
  public void sessionScreen(String username, Scanner userInputScanner) {
    boolean login = true;
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        currentUser = users.get(i);
      }
    }
    System.out.println();
    System.out.println("~~~~~~~ Access Control Menu ~~~~~~~");
    System.out.println();
    while (login) { // for loop that runs as log as the user is logged in
      // below is the menu the user sees while accesing the Access Control
      System.out.println("Please type on of the following options below.");
      System.out.println();
      System.out.println("- logout");
      System.out.println("- newpw [newpassword]");
      System.out.println("- adduser [username]");
      System.out.println("- adduser [username] [true or false]");
      System.out.println("- rmuser [username]");
      System.out.println("- giveadmin [username]");
      System.out.println("- rmadmin [username]");
      System.out.println("- resetpw [username]");
      String userResponse = userInputScanner.nextLine();
      String[] userResponseArray = userResponse.split(" "); // splits the user input into an array
                                                            // of strings to process commands
      if (userResponseArray[0].equals("logout")) {
        System.out.println(currentUser.getUsername() + " has logged out.");
        logout();
        login = false;
      } else if (userResponseArray[0].equals("newpw")) {
        System.out.println(currentUser.getUsername() + " has successfully changed their password.");
        changePassword(userResponseArray[1]);
      } else if (userResponseArray[0].equals("adduser")) { // if user wants to add user
        if (userResponseArray.length == 2) { // makes sure the admin field isn't in the array
          if (addUser(userResponseArray[1])) {
            System.out.println("Successfully created regular user " + userResponseArray[1] + ".");
          } else {
            System.out.println("[Admin Command] Failed to create the user.");
          }
        } else { // if the admin field is in the array
          if (userResponseArray[2].equals("true")) {
            if (addUser(userResponseArray[1], true)) {
              System.out.println("Successfully created " + userResponseArray[1] + " as admin.");
            } else {
              System.out.println("[Admin Command] Failed to create the user.");
            }
          } else { // creates user with admin field as false
            if (addUser(userResponseArray[1], false)) {
              System.out.println("Successfully created regular user " + userResponseArray[1] + ".");
            } else {
              System.out.println("[Admin Command] Failed to create the user.");
            }
          }
        }
      }

      else if (userResponseArray[0].equals("rmuser")) { // removes a use from the users ArrayList
        if (removeUser(userResponseArray[1])) {
          System.out.println("Successfully removed " + userResponseArray[1] + ".");
        } else {
          System.out.println("[Admin Command] Failed to remove the user.");
        }
      } else if (userResponseArray[0].equals("giveadmin")) { // gives admin to a user if the current
                                                             // user logged in is admin
        if (giveAdmin(userResponseArray[1])) {
          System.out.println("Successfully gave " + userResponseArray[1] + " admin.");
        } else {
          System.out.println("[Admin Command] Failed to give the user admin.");
        }
      } else if (userResponseArray[0].equals("rmadmin")) { // removes admin from a user if current
                                                           // logged in user is an admin
        if (takeAdmin(userResponseArray[1])) {
          System.out.println("Successfully removed " + userResponseArray[1] + "'s admin.");
        } else {
          System.out.println("[Admin Command] Failed to remove the users admin.");
        }
      } else if (userResponseArray[0].equals("resetpw")) { // resets a users password if the current
                                                           // logged in user is an admin
        if (resetPassword(userResponseArray[1])) {
          System.out.println("Successfully reset the password for " + userResponseArray[1] + ".");
        } else {
          System.out.println("[Admin Command] Unable to reset the users password.");
        }
      } else {
        System.out.println("Invalid input. Please try again.");
      }
    }
  }

  public static void main(String[] args) {
    AccessControl ac = new AccessControl();
    Scanner userIn = new Scanner(System.in);
    ac.loginScreen(userIn);
  }
}

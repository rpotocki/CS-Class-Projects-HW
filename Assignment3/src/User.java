//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: User.java
// Files: none
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
 * This class contains the methods to create a User object
 * 
 */

public class User {
  private final String USERNAME; // The user's name
  private String password; // The user's password
  private boolean isAdmin; // Whether or not the user has Admin powers

  /**
   * Creates a new user with the given password and admin status
   */
  public User(String username, String password, boolean isAdmin) {
    this.USERNAME = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  /**
   * Report whether the password is correc
   */
  public boolean isValidLogin(String password) {
    if (this.password.equals(password)) {
      return true;
    }
    return false;
  }

  /**
   * Return the user's name
   */
  public String getUsername() {
    return this.USERNAME;
  }

  /**
   * Report whether the user is an admin
   */
  public boolean getIsAdmin() {
    return this.isAdmin;
  }

  /**
   * Creates a new user with the given password and admin status
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Set the new admin status
   */
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: GameList.java
// Files: GameNode.java
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
 * 
 * This class is the blueprint to a the GameList object that contains GameNodes
 * 
 */
public class GameList {
  private GameNode list; // reference to the first GameNode in this list

  public GameList() { // initializes list to start out empty
    list = null;
  }

  /**
   * 
   * This method adds the new node to the end of this list
   * 
   */
  public void addNode(GameNode newNode) {
    if (this.list == null) {
      this.list = newNode;
    } else {
      GameNode curNode = list;
      while (curNode.getNext() != null) {
        GameNode tempNode = curNode.getNext();
        curNode = tempNode;
      }
      curNode.setNext(newNode);
    }
  }

  /**
   * 
   * This method checks to see if a GameList contains a specific number
   *
   * @returns true if it contains the number
   */
  public boolean contains(int number) { // only returns true when this list contains a node with the
                                        // specified number
    GameNode curNode = list;
    if (curNode.getNumber() == number) { // checks if the first node contains the number
      return true;
    } else {
      while (curNode.getNext() != null) { // checks all nodes except the first and last
        if (curNode.getNumber() == number) {
          return true;
        }
        GameNode tempNode = curNode.getNext();
        curNode = tempNode;
      }
      if (curNode.getNumber() == number) { // checks last node in the list
        return true;
      }
    }
    return false;
  }


  /**
   * 
   * This methods returns a string with each number in the list separated by " -> "s, and ending
   * with " -> END"
   *
   */
  public String toString() {
    GameNode curNode = list;
    String tempString = "";
    if (this.list.getNext() == null) { // if there is only one node
      tempString = "" + curNode.getNumber() + " -> ";
    } else {
      while ((curNode.getNext() != null)) { // loops through all nodes that arent the last
        tempString += curNode.getNumber();
        tempString += " -> ";
        curNode = curNode.getNext();
      }
      // the next two lines add the final node to the string
      tempString += curNode.getNumber();
      tempString += " -> ";
    }
    tempString += "END";
    return tempString;
  }

  public void applyOperatorToNumber(int number, GameOperator operator) {
    GameNode curNode = list;
    if (curNode.getNumber() == number) { // checks if the first node contains the number
      curNode.applyOperator(operator);
    } else {
      while (curNode.getNext() != null) { // checks all nodes except the first and last
        if (curNode.getNumber() == number) {
          curNode.applyOperator(operator);
          return;
        }
        GameNode tempNode = curNode.getNext();
        curNode = tempNode;
      }
      if (curNode.getNumber() == number) { // checks last node in the list
        throw new NullPointerException();
      }
    }
  }
}

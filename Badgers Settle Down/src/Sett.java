//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P09 Badgers Settle Down
// Files:           Badger.java, BadgersSettleDown.java, Sett.java,
//					P9Tests.java
// Course:          CS300, Fall 2018, LEC01
//
// Author:          Ryan Potocki
// Email:           rpotocki@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Max Richter
// Partner Email:   mhrichter@wisc.edu
// Partner Lecturer's Name: Gary Dahl
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class represents a Sett, where a group of Badgers live together. 
 * Each Sett is organized as a BST of Badger nodes
 */
public class Sett {
  // represents the root badger
  private Badger topBadger;

  /**
   * Constructs an empty Sett
   */
  Sett() {
	this.topBadger = null;
  }

  /**
   * Empties this Sett, to no longer contain any Badgers
   */
  public void clear() {
	this.topBadger = null;
  }

  /**
   * Counts how many Badgers live in this Sett
   * @return the number of Badgers living in this Sett
   */
  public int countBadger() {
	return countHelper(this.topBadger);
  }

  /**
   * This recursive helper method is used to help count the number of 
   * Badgers in this Sett
   * @param current Badger that is the root of a (sub) tree
   * @return the number of Badgers living in the Sett rooted at the current Badger
   */
  private int countHelper(Badger current) {
	// checks if no badgers in the sett
	if (current == null) {
	  return 0;
	}
	// recursive call until base case is reached
	return 1 + countHelper(current.getLeftLowerNeighbor()) + 
		countHelper(current.getRightLowerNeighbor());
  }

  /**
   * Finds a Badger of a specified size in this Sett
   * @param size of the Badger object to search for and return
   * @return the Badger found with the specified size
   * @throws NoSuchElementException - when no badger is in Sett with the specified size
   */
  public Badger findBadger(int size) throws NoSuchElementException{
	// calls the recursive helper method
	return findHelper(this.topBadger, size);
  }

  /**
   * This recursive helper method is used to help find a Badger within this Sett
   * @param current Badger that is the root of a (sub) tree
   * @param size of the Badger object to search for and return
   * @return the Badger found with the specified size
   * @throws NoSuchElementException - when no badger is in Sett with the specified size
   */
  private Badger findHelper(Badger current, int size) throws NoSuchElementException {
	// checks if no badgers are in the sett
	if (current == null) {
	  throw new NoSuchElementException("WARNING: no badgers exist within the sett");
	}

	// if badger is the right lower neighbor
	if(size > current.getSize()) {
	  current = current.getRightLowerNeighbor();
	  return findHelper(current, size);
	} else if (size < current.getSize()) { // if badger is the left lower neighbor
	  current = current.getLeftLowerNeighbor();
	  return findHelper(current, size);
	} else if (size == current.getSize()) { // if badger's size is == to badger being searched for
	  return current;
	} else { // reached when no badger w/ specified size is in the sett
	  throw new NoSuchElementException("WARNING: failed to find a badger with size" + size + 
		  " in the sett");
	}
  }

  /**
   * Gets all Badgers living in the Sett as a list in ascending order of their size: 
   * smallest one in the front (at index zero), through the largest one at the end
   * (at index size-1)
   * @return a list of all Badgers living in the Sett in ascending order by size
   */
  public List<Badger> getAllBadgers() {
	// list holds all badgers in the sett
	List<Badger> allBadgers = new ArrayList<Badger>();
	getAllHelper(this.topBadger, allBadgers);
	return allBadgers;
  }

  /**
   * This recursive helper method is used to help collect the Badgers within this Sett into a List
   * @param current Badger that is the root of a (sub) tree
   * @param allBadgers list of all Badgers living in the Sett that is rooted at the current 
   * 	Badger node
   */
  private void getAllHelper(Badger current, List<Badger> allBadgers) {
	// recursively calls itself until no badgers left in sett to add
	if(current != null) {
	  getAllHelper(current.getLeftLowerNeighbor(), allBadgers);
	  allBadgers.add(current);
	  getAllHelper(current.getRightLowerNeighbor(), allBadgers);
	}
  }

  /**
   * Computes the height of the Sett, as the number of nodes from root to the deepest 
   * leaf Badger node
   * @return the depth of this Sett
   */
  public int getHeight() {
	int treeHeight = 0;
	treeHeight += getHeightHelper(this.topBadger);
	return treeHeight;
  }

  /**
   * This recursive helper method is used to help compute the height of this Sett
   * @param current Badger that is the root of a (sub) tree that we are calculating the height of
   * @return the height of the (sub) tree that we are calculating
   */
  private int getHeightHelper(Badger current) {
	// base case
	if (current == null) {
	  return 0;
	} else { 
	  // finds the depth of the right and left side of tree
	  int lDepth = getHeightHelper(current.getLeftLowerNeighbor()); 
	  int rDepth = getHeightHelper(current.getRightLowerNeighbor()); 

	  // finds which side was larger
	  if (lDepth > rDepth) {
		return (lDepth + 1); 
	  } else {
		return (rDepth + 1); 
	  }
	} 
  }

  /**
   * Retrieves the largest Badger living in this Sett
   * @return the largest Badger living in this Sett
   */
  public Badger getLargestBadger() {
	Badger current = this.topBadger;
	// finds the largest badger in the sett
	while (current.getRightLowerNeighbor() != null) {
	  current = current.getRightLowerNeighbor();
	}
	return current;
  }

  /**
   * Retrieve the top Badger within this Sett (the one that was settled first)
   * @return The Badger living on the top of the current Sett
   */
  public Badger getTopBadger() {
	return this.topBadger;
  }

  /**
   * Checks whether this Sett is empty
   * @return true if this Sett is empty, false otherwise
   */
  public boolean isEmpty() {
	// executes if sett is empty
	if (this.topBadger == null) {
	  return true;
	}
	return false;
  }

  /**
   * Creates a new Badger object with the specified size, and inserts them into this Sett (BST)
   * @param size of the new Badger that will be settled
   * @throws IllegalArgumentException when a Badger with the specified size already exists 
   * 	within this Sett
   */
  public void settleBadger(int size) throws IllegalArgumentException {
	Badger newBadger = new Badger(size);
	// calls recursive helper method
	settleHelper(this.topBadger, newBadger);
  }

  /**
   * This recursive helper method is used to help settle a new Badger within this Sett
   * @param current Badger (previously settled within this Sett) that we are considering 
   * 	settling the newBadger beneath (either to its left or right)
   * @param newBadger that needs to be settled within this Sett
   * @throws IllegalArgumentException when a Badger with the specified size already exists 
   * 	within this Sett
   */
  private void settleHelper(Badger current, Badger newBadger) throws IllegalArgumentException{
	// base case
	if (current == null) {
	  this.topBadger = newBadger;
	} else if (current.getSize() > newBadger.getSize()) {
	  // left lower neighbor contains a badger
	  if (current.getLeftLowerNeighbor() != null) {
		settleHelper(current.getLeftLowerNeighbor(), newBadger);
	  } else { // settles badger into left lower neighbor
		current.setLeftLowerNeighbor(newBadger);
	  }
	} else if (current.getSize() < newBadger.getSize()) {
	  // right lower neighbor contains a badger
	  if (current.getRightLowerNeighbor() != null) {
		settleHelper(current.getRightLowerNeighbor(), newBadger);
	  } else { // settles badger into right lower neighbor
		current.setRightLowerNeighbor(newBadger);
	  }
	} else { // badger with same size exists in sett
	  throw new IllegalArgumentException("WARNING: failed to settle the badger with size " + 
		  newBadger.getSize() + ", as there is already a badger with the same size in this sett");
	}
  }
}
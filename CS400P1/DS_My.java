//Title:           DS_My.java
//Files:           
//Course:          CS 400 Spring 2019
//
//Author:          Ryan Potocki
//Email:           rpotocki@wisc.edu
//Lecturer's Name: Deb Deppler
//


/**
 * This class is data structure that implements the DataStructureADT interface.
 * It was designed to be a linked list for generic types 
 * and consists of a unique key/value pair
 * 
 */

public class DS_My implements DataStructureADT {

    // Private Fields of the class
    private DataValueNode start;
    
    //initialize the data structure
    public DS_My() {
        start = null; 
    }

    /**
     * This method allows for a user to insert a dataValueNode into
     * the linked list datastructure
     * 
     * @param Key, @param Value
     * @throws an IllegalArgumentException if the key is null
     * @throws a RuntimeException if the key already exiists within the list
     */
    @Override
    public void insert(Comparable K, Object V) {
        if (K == null) { //checks if key is null
          throw new IllegalArgumentException("null key");
        }
        if (this.contains(K)) { //checks if the list already contains the unique key
          throw new RuntimeException("duplicate key");
        }
        else {
          DataValueNode newNode = new DataValueNode(K, V); //creates new node
          //if the list is empty it sets the new node as the starting node
          if (this.start == null) { 
            this.start = newNode;
          }
          else {
            DataValueNode curNode = this.start;
            //loops until it finds the last item in the list
            while (curNode.getNext() != null) { ;
              curNode = curNode.getNext();;
            }
            curNode.setNext(newNode);
          }
       }
    }

    /**
     * This method allows for an item to be removed from the linked list based on its key
     * @param Key
     * @throws IllegalArgumentException if the key you're looking to remove is null
     * @returns true if the item was remove, false if it was not
     */
    @Override
    public boolean remove(Comparable K) {
      if (K == null) { //makes sure the key isn't null
        throw new IllegalArgumentException("null key");
      }
      if (this.start == null) { //checks if the list is empty
        return false;
      }
      DataValueNode curNode = this.start;
      //checks if the start nodes Key is equal to the key passed as a parameter
      if (curNode.getKey().compareTo(K) == 0) { 
        if (curNode.getNext() != null) {
          this.start = curNode.getNext(); //sets the start node to the next one in the list
          return true;
        }
        else { //removes the head node (as it was the only node in the list)
          this.start = null;
          return true;
        }
      }
      while (curNode.getNext() != null) { //loop until the last node
        //checks if the next nodes key matches the one passed in
        if (curNode.getNext().getKey().compareTo(K) == 0) {
          //if the next node has another after
          if (curNode.getNext().getNext() != null) {
            curNode.setNext(curNode.getNext().getNext());
            return true;
          }
          else { //last node in the list
            curNode.setNext(null);
            return true;
          }
        }
        curNode = curNode.getNext();
      }
      return false;
    }

    /**
     * This method checks if an item is located within the list based on the unique key
     * @param Key
     * @returns true if the dataValueNode with key K was found, false if not
     */
    @Override
    public boolean contains(Comparable K) {
        DataValueNode curNode = this.start;
        if (this.start == null) { //checks if the list is empty
          return false;
        }
        else {
          while (curNode.getNext() != null) { //loop through entire list, excluding last node
            if (curNode.getKey().compareTo(K) == 0) {
              return true;
            }
            curNode = curNode.getNext();
            }
        }
        if (curNode.getKey().compareTo(K) == 0) { //checks the last item in the list
          return true;
        }
        return false;
    }

    /**
     * This method returns the value that is associated with the key
     * @param Key
     * @return The value associated back with the key, null if they key was not found
     */
    @Override
    public Object get(Comparable K) {
      DataValueNode curNode = start;
      if (K == null) { //checks if key is null
        throw new IllegalArgumentException("null key");
      }
      else {
        while (curNode.getNext() != null) { //loops through entire list, excluding last node
          if (curNode.getKey() == K) {
            return curNode.getValue();
          }
          curNode = curNode.getNext();
        }
        if (curNode.getKey() == K) { //checks the last node
          return curNode.getValue();
        }
      }
      return null;
    }
    
    /**
     * This method returns the size of the linked list
     * @return int size of the linked list
     */
    @Override
    public int size() {
        int size = 0;
        DataValueNode curNode = this.start;
        if (curNode == null) { //checks if list is empty
          return size;
        }
        //loops through entire list, excluding last node
        while (curNode.getNext() != null) {
          size++;
          curNode = curNode.getNext();
        }
        size++; //adds last node
        return size;
    }
    
    
    /**
     * This private inner class creates the DataValueNodes that the linked list is comprised of
     */
    private class DataValueNode {
      
      //private fields of the inner class
      private Comparable key;
      private Object value;
      private DataValueNode next;
      
      //creates an instance of a DataValueNode
      private DataValueNode(Comparable K, Object V) {
        key = K;
        value = V;
        next = null;
      }
      
      //retrieves the key of the node
      private Comparable getKey() {
        return this.key;
      }
      
      //retrieves the valu
      private Object getValue() {
        return this.value;
      }
      
      //pointer for the next node in the list
      private DataValueNode getNext() {
        return this.next;
      }
      
      //sets the pointer to the next value in the list
      //@param DataValueNode that is next in the list
      private void setNext(DataValueNode newNode) {
        this.next = newNode;
      }
    }
}

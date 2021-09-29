//Title:           AVL.java
//Files:           
//Course:          CS 400 Spring 2019
//
//Author:          Ryan Potocki
//Email:           rpotocki@wisc.edu
//Lecturer's Name: Deb Deppler
//


/**
 * This class is a A BST search tree that maintains its balance using AVL rotations.
 */


public class AVL<K extends Comparable<K>,V> extends BST<K, V> {
    
   public AVL() { 
        this.root = null;
        this.numKeys = super.numKeys();
    }
     
   @Override
   public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
     if (key == null) {
       throw new IllegalNullKeyException();
     }
     if (contains(key) == true) {
       throw new DuplicateKeyException();
     }
     else {
         BSTNode<K, V> newNode = new BSTNode<K, V>(key, value);
         this.root = insertAVLHelper(this.root, newNode);
     }
   }
   
   /**
    * This is a recursive method that inserts a node and rebalances the tree
    * if necessary. 
    * @param current node
    * @param new node
    * @returns BST node
    */
   private BSTNode<K, V> insertAVLHelper(BSTNode<K, V> current, BSTNode<K, V> newNode) {
       if (current == null) {
         return newNode;
       }
       if (newNode.key.compareTo(current.key) < 0) {
          current.left = insertAVLHelper(current.left, newNode);
       }
       else if ( newNode.key.compareTo(current.key) > 0) {
          current.right = insertAVLHelper(current.right, newNode);
       }
       else {
         return current;
       }
       
       calcNodeHeight(current);
       
       int balanceFactor = calcBalanceFactor(current);
       
       // checks if a right rotate is needed to rebalance
       if ((balanceFactor > 1) && (newNode.key.compareTo(current.left.key) < 0)) {
         return rightRotate(current);
       }
       
       //checks if a left rotate is needed to rebalance
       if ((balanceFactor < -1) && (newNode.key.compareTo(current.right.key) > 0)) {
         return leftRotate(current);
       }
       
       //checks if a left-right rotate is needed to rebalance
       if ((balanceFactor > 1) && (newNode.key.compareTo(current.left.key) > 0)) {
         current.left = leftRotate(current.left);
         return rightRotate(current);
       }
       
       //checks if a right-left rotate is needed to rebalance
       if ((balanceFactor < -1) && (newNode.key.compareTo(current.right.key) < 0)) {
         current.right = rightRotate(current.right);
         return leftRotate(current);
       }
       
       return current;
 
   }
   
   public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
     if (key == null) {
       throw new IllegalNullKeyException();
     }
     if (this.contains(key) == false) {
       throw new KeyNotFoundException();
     }
     else {
       this.root = removeAVLHelper(this.root, key);
       return true;
     }
   }
 
   /**
    * This is a recursive method that deletes a node and rebalances the tree
    * if necessary. 
    * @param current node
    * @param key of node to be deleted
    * @returns BST node
    */
   private BSTNode<K, V> removeAVLHelper(BSTNode<K, V> current, K key) {
     if (current == null) {
       return current;
     }
     //traverse through left size of the tree
     if (key.compareTo(current.key) < 0) {
       current.left = removeAVLHelper(current.left, key);
     }
     //traverse throught the right side of the tree
     else if (key.compareTo(current.key) > 0) {
       current.right = removeAVLHelper(current.right,key);
     }
     else { //the node that should be deleted
       if ((current.left == null) || (current.right == null)) {
         BSTNode<K,V> tempNode = null;
         if (tempNode == current.left) {
           tempNode = current.right;
         }
         else {
           tempNode = current.left;
         }
         if (tempNode == null) {
           tempNode = current;
           current = null;
         }
         else {
           current = tempNode;
         }
       }
       else {
         BSTNode<K,V> tempNode = minValueNode(current.right);
         current.key = tempNode.key;
         current.right = removeAVLHelper(current.right, tempNode.key);
       }
     }
     if (current == null) {
       return current;
     }
     //calculates the height of the node
     calcNodeHeight(current);
     //finds the balance factor of the current node
     int balanceFactor = calcBalanceFactor(current);
     //checks if a right rotate is necessary to rebalance
     if ((balanceFactor > 1) && (calcBalanceFactor(current.left) >= 0)) {
       return rightRotate(current);
     }
     //checks if a left-right rotate is necessary to rebalance
     if (balanceFactor > 1 && (calcBalanceFactor(current.left) < 0)) {
       current.left = leftRotate(current.left);
       return rightRotate(current);
     }
     //checks if a left rotate is necessary to rebalance
     if ((balanceFactor < -1) && (calcBalanceFactor(current.right) <= 0)) {
       return leftRotate(current);
     }
     //checks if a right-left rotate is necessary to rebalance
     if ((balanceFactor < -1) && (calcBalanceFactor(current.right) > 0)) {
       current.right = rightRotate(current.right);
       return leftRotate(current);
     }
     return current;
   }
   
   /**
    * This method finds the minimum value node within a tree when passed
    * the root node
    * @param BST node (root of the tree)
    * @returns BST Node with minimum value
    */
   public BSTNode<K,V> minValueNode(BSTNode<K,V> node) {
     BSTNode<K,V> current = node;  
     
     while (current.left != null) {  
       current = current.left;  
     }

     return current; 
   }
   
   /**
    * This method calculates the height of a node within a 
    * tree and recurisvely a
    * @param BST node (root of the tree)
    */
   private void calcNodeHeight(BSTNode<K,V> current) {
       if ((current.left == null) && (current.right == null)) {
           current.height = 0;
       }
       else if (current.left == null) {
           current.height = 1 + current.right.height;
       }
       else if (current.right == null) {
           current.height = 1 + current.left.height;
       }
       else {
           current.height = 1 + Math.max(current.right.height, current.left.height);
       }
   }
   
   /**
    * This method calculates the balance factor of a 
    * given node
    * @returns int balance factor
    */
   private int calcBalanceFactor(BSTNode<K,V> current) {
       if (current.left == null) { //no left side of tree
           return -1 * current.height;
       }
       else if (current.right == null) { //no right side of tree
           return current.height;
       }
       else { //tree has right/left children
           int rightHeight = current.right.height;
           int leftHeight = current.left.height;
           return leftHeight - rightHeight;
       }
   }
   
   /**
    * This method performs a right rotate on a node that is passed in
    * as a parameter in order to rebalance the tree
    * @param BST node to have rotate performed on
    * @returns BST node
    */
   public BSTNode<K, V> rightRotate(BSTNode<K,V> current) { 
       BSTNode<K,V> tempNode = current.left;
       BSTNode<K,V> tempNode2 = tempNode.right;
       
       tempNode.right = current;
       current.left = tempNode2;
       //recalculate the height of the nodes
       calcNodeHeight(current);
       calcNodeHeight(tempNode);
       
       return tempNode;
 } 
   
   /**
    * This method performs a left rotate on a node that is passed in
    * as a parameter in order to rebalance the tree
    * @param BST node to have rotate performed on
    * @returns BST node
    */
   public BSTNode<K, V> leftRotate(BSTNode<K,V> current) { 
       BSTNode<K,V> tempNode = current.right;
       BSTNode<K,V> tempNode2 = tempNode.left;
       
       tempNode.left = current;
       current.right = tempNode2;
       //recalculate the height of the nodes
       calcNodeHeight(current);
       calcNodeHeight(tempNode);
       
       return tempNode;
   
   }
}

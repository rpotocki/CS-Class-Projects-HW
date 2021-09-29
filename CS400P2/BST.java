import java.util.ArrayList;  // allowed for creating traversal lists
import java.util.LinkedList;
import java.util.List;       // required for returning List<K>
import java.util.Queue;
//Title:           BST.java
//Files:           
//Course:          CS 400 Spring 2019
//
//Author:          Ryan Potocki
//Email:           rpotocki@wisc.edu
//Lecturer's Name: Deb Deppler
//


/**
* This class is binary search tree that implements the BSTADT interface and extends the comparable class.
* 
*/

public class BST<K extends Comparable<K>,V> implements BSTADT<K, V> {

	// Tip: Use protected fields so that they may be inherited by AVL
	protected BSTNode<K,V> root;
	protected int numKeys; // number of keys in BST

	// Must have a public, default no-arg constructor
	public BST() { 
		this.root = null;
		this.numKeys = this.numKeys();
	}

	/* (non-Javadoc)
	 * @see SearchTreeADT#getPreOrderTraversal()
	 */
	@Override
	public List<K> getPreOrderTraversal() {
	    List<K> preOrderList = new ArrayList<K>();
		preOrderTraversalHelper(this.root, preOrderList);
		return preOrderList;
	}

	/* 
     * This method helps with the preOrder traversal by recursively calling itself and adding
     * the nodes to the array list
     * @param arrayList
     * @param node
     * 
     */
	private void preOrderTraversalHelper(BSTNode<K, V> node, List<K> preOrderList) {
  	    if (node == null) {
  	      return;
  	    }
  	    preOrderList.add(node.key);
  	    preOrderTraversalHelper(node.left, preOrderList);
  	    preOrderTraversalHelper(node.right, preOrderList);
	}
	
	/* (non-Javadoc)
	 * @see SearchTreeADT#getPostOrderTraversal()
	 */
	@Override
	public List<K> getPostOrderTraversal() {
        List<K> postOrderList = new ArrayList<K>();
        postOrderTraversalHelper(this.root, postOrderList);
        return postOrderList;
	}
	
	   
	/* 
     * This method helps with the postOrder traversal by recursively calling itself and adding
     * the nodes to the array list
     * @param arrayList
     * @param node
     * 
     */
    private void postOrderTraversalHelper(BSTNode<K, V> node, List<K> postOrderList) {
        if (node == null) {
          return;
        }
        postOrderTraversalHelper(node.left, postOrderList);
        postOrderTraversalHelper(node.right, postOrderList);
        postOrderList.add(node.key);
    }
    
	/* (non-Javadoc)
	 * @see SearchTreeADT#getLevelOrderTraversal()
	 */
	@Override
  	public List<K> getLevelOrderTraversal() {
	    List<K> levelOrderList = new ArrayList<K>();   
	    Queue<BSTNode<K, V>> orderQueue = new LinkedList<BSTNode<K, V>>();
	    if (this.root == null) {
	      return levelOrderList;
	    }
	    else {
    	    orderQueue.add(this.root);
    	    while(!orderQueue.isEmpty()) {
    	        BSTNode<K,V> tempNode = orderQueue.poll();
    	        levelOrderList.add(tempNode.key);
    	        if(tempNode.left != null) {
    	           orderQueue.add(tempNode.left);
    	        }
    	        if(tempNode.right != null) {
    	           orderQueue.add(tempNode.right);
    	        }
    	    }
	    }
	    return levelOrderList;
	}

	/* (non-Javadoc)
	 * @see SearchTreeADT#getInOrderTraversal()
	 */
	@Override
	public List<K> getInOrderTraversal() {
        List<K> inOrderList = new ArrayList<K>();
        inOrderTraversalHelper(this.root, inOrderList);
        return inOrderList;
	}

	
	/* 
     * This method helps with the inOrder traversal by recursively calling itself and adding
     * the nodes to the array list
     * @param arrayList
     * @param node
     * 
     */
    private void inOrderTraversalHelper(BSTNode<K, V> node, List<K> inOrderList) {
      if (node == null) {
        return;
      }
      inOrderTraversalHelper(node.left, inOrderList);
      inOrderList.add(node.key);
      inOrderTraversalHelper(node.right, inOrderList);
  }
	/* (non-Javadoc)
	 * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
	 */
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
    		insertHelper(this.root, newNode);
		}
	}
	 /*
     * This method is recursively called to traverse through the BST and insert the node in the correct spot
     * @param current node
     * @param newNode to be inserted
     */
	private void insertHelper(BSTNode<K, V> current, BSTNode<K, V> newNode) throws DuplicateKeyException {
	    if (current == null) { //checks if the root is null
	      this.root = newNode;
	    }
	    else if(current.key.compareTo(newNode.key) < 0) { //checks if node key is larger than current node
	      if (current.right != null) {
	         insertHelper(current.right, newNode); //recursively calls method to move down right tree
	      }
	      else {
	         current.right = newNode;
	      }
	    }
	    else if(current.key.compareTo(newNode.key) > 0) { //checks if the node key is less than the current node
	      if (current.left != null) {
	        insertHelper(current.left, newNode); //recursively calls the method to move further down left tree
	      } 
	      else { 
	        current.left = newNode;
	      }
	    }
	    else { //key already exists
	      throw new DuplicateKeyException(); 
	    }
	}
	
	 /* (non-Javadoc)
     * @see DataStructureADT#remove(java.lang.Comparable)
     */
    @Override
    public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if (key == null) {
          throw new IllegalNullKeyException();
        }
        if (this.contains(key) == false) {
          throw new KeyNotFoundException();
        }
        else {
          this.root = removeHelper(this.root, key);
          return true;
        }
    }
    
    /*
    * This method is recursively called to traverse through the BST and delete the node
    * @param current node
    * @param key of node to be deleted
    */
    private BSTNode<K, V> removeHelper(BSTNode<K, V> current, K key) {
        if (current == null) { //tree is null
          return null;
        }
        if (key.equals(current.key)) { //if it's the node to be deleted
          //below statements determine what the parent node should point at
          if (current.left == null && current.right == null) {
            return null;
          }
          if (current.left == null) {
            return current.right;
          }
          if (current.right == null) {
            return current.left;
          }
          else { //node to be deleted as two child nodes
            K successorValue = getSuccessor(current.right);
            current.key = successorValue;
            current.right = removeHelper(current.right, successorValue);
            return current;
          }
         }
        else if (key.compareTo(current.key) < 0) { //node to be deleted is to the left of the current node
          current.left = removeHelper(current.left, key);
          return current;
        }
        
        else { //node to be deleted is to the right of the current node
          current.right = removeHelper(current.right, key); 
          return current;
        }
    }
    
    /*
    * This method gets the successor of a node (smallest node)
    * @param node
    * @return key
    */
    private K getSuccessor(BSTNode<K, V> node) {
        if(node.left == null) {
          return node.key;
        }
        else {
          return getSuccessor(node.left);
        }
    }
    /* (non-Javadoc)
     * @see DataStructureADT#get(java.lang.Comparable)
     */
    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
      if (key == null) {
        throw new IllegalNullKeyException();
      }
      if (contains(key) == false) {
        throw new KeyNotFoundException();
      }
      else {
        return getHelper(this.root, key);      
      }
  }
    /*
    * This recursive method traverses through the tree to get the value associated with a specific key
    * @param current node
    * @param key of the node to be found
    * @returns value associated to key
    */
    private V getHelper(BSTNode<K,V> current, K key) throws IllegalNullKeyException, KeyNotFoundException {
        if (current == null) {
          return null;
        }
        //node will be left of current node based on key value
        if ((key.compareTo(current.key) < 0)) {
          if (current.left != null) {
            return getHelper(current.left, key);
          }
          else {
            return null;
          }
        }
        //node will be right of current node based on key value
        else if (key.compareTo(current.key) > 0) {  
            if (current.right != null) {
             return getHelper(current.right, key);
            }
            else {
              return current.value;
            }
        }
        else {
            return current.value;
        }
  }

	/* (non-Javadoc)
	 * @see DataStructureADT#contains(java.lang.Comparable)
	 */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
  	  if (key == null) {
  	    throw new IllegalNullKeyException();
  	  }
  	  else {
  	    return containsHelper(this.root, key);
  	  }
	}

	/* 
     * This is a recursive helper method to traverse through the BST and check if it
     * contains a node that has a specific key
     * @param current node
     * @param key to lookup in the tree
     */
	private boolean containsHelper(BSTNode<K, V> current, K key) {
	    if (current == null) {
	      return false;
	    }
	    if (key.compareTo(current.key) == 0) {
	      return true;
	    }
	    else if ((key.compareTo(current.key) < 0)) { //go down left side of tree
    	      if (current.left == null) {
    	        return false;
    	      }
    	      else {
    	        return containsHelper(current.left, key);
    	      }
	    }
	    else if (key.compareTo(current.key) > 0) { //go down right side of tree
	        if (current.right == null) {
	              return false;
	        }
	        else {
	          return containsHelper(current.right, key);
	        }
	    }
	    return false;
	 }

	
	/* (non-Javadoc)
	 * @see DataStructureADT#numKeys()
	 */
	@Override
	public int numKeys() {
      if (this.root == null) {
         return 0;
      }
      return this.root.count();
	}

	/* (non-Javadoc)
	 * @see BSTADT#getKeyAtRoot()
	 */
	@Override
	public K getKeyAtRoot() {
		if (this.root == null) {
		  return null;
		}
		return this.root.key;
	}

	/* (non-Javadoc)
	 * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
	 */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
      if (key == null) {
          throw new IllegalNullKeyException();
      }
      if (contains(key) == false) {
          throw new KeyNotFoundException();
      }
      else {
          return getKeyOfLeftChildOfHelper(this.root, key);      
      }
	}
	
    /* Recursive method to get the key of a left child to a specific node
    * @param key of node were trying to find left child of
    * @param current node 
    * @returns Key 
    */
    private K getKeyOfLeftChildOfHelper(BSTNode<K,V> current, K key) throws IllegalNullKeyException, KeyNotFoundException {
      if (current == null) {
        return null;
      }
      if ((key.compareTo(current.key) < 0)) { //left side of the tree
        if (current.right != null) {
          return getKeyOfRightChildOfHelper(current.right, key);
        }
        else {
          if (current.left == null) {
            return null;
          }
          else {
            return current.left.key;
          }
        }
      }
      else if (key.compareTo(current.key) > 0) { //right side of the tree
        if (current.left != null) {
          return getKeyOfRightChildOfHelper(current.left, key);
        }
        else {
          if (current.left == null) {
            return null;
          }
          else {
            return current.left.key;
          }
        }
      }
      else {
          if (current.left == null) {
            return null;
          }
          else {
            return current.left.key;
          }
      }
}
	/* (non-Javadoc)
	 * @see BSTADT#getKeyOfRightChildOf(java.lang.Comparable)
	 */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if (key == null) {
          throw new IllegalNullKeyException();
        }
        if (contains(key) == false) {
          throw new KeyNotFoundException();
        }
        else {
          return getKeyOfRightChildOfHelper(this.root, key);      
        }
    }
	
    /* 
     * Recursive method to get the key of a right child to a specific node
     * @param key of node were trying to find right child of
     * @param current node 
     * @returns Key 
     */
    private K getKeyOfRightChildOfHelper(BSTNode<K,V> current, K key) throws IllegalNullKeyException, KeyNotFoundException {
      if (current == null) {
        return null;
      }
      if ((key.compareTo(current.key) < 0)) {
        if (current.left != null) {
          return getKeyOfRightChildOfHelper(current.left, key);
        }
        else {
          if (current.right == null) {
            return null;
          }
          else {
            return current.right.key;
          }
        }
      }
      else if (key.compareTo(current.key) > 0) {
        if (current.right != null) {
          return getKeyOfRightChildOfHelper(current.right, key);
        }
        else {
          if (current.right == null) {
            return null;
          }
          else {
            return current.right.key;
          }
        }
      }
      else {
          if (current.right == null) {
            return null;
          }
          else {
            return current.right.key;
          }
      }
}

	/* (non-Javadoc)
	 * @see BSTADT#getHeight()
	 */
	@Override
	public int getHeight() {
	  if (this.root == null) {
	    return 0;
	  }
	  return heightHelper(this.root);
	}

	/* 
     * Recursive method to help determine the height of the tree
     */
	private int heightHelper(BSTNode<K,V> current) {
      if (current == null) {
          return -1;
      }
      int rightHeight = heightHelper(current.right);
      int leftHeight = heightHelper(current.left);
      //if left side of tree is taller
      if (leftHeight > rightHeight) {
          return leftHeight + 1;
      } 
      else {  //right side of tree is taller
          return rightHeight + 1;
      }

  }
	
	
}

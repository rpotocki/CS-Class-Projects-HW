// Students may use and edit this class as they choose
// Students may add or remove or edit fields, methods, constructors for this class
// Students may inherit from an use this class in any way internally in other classes.
// Students are not required to use this class. 
// BUT, IF YOUR CODE USES THIS CLASS, BE SURE TO SUBMIT THIS CLASS
//
// RECOMMENDED: do not use public or private visibility modifiers
// package level access means that all classes in the package can access directly.
//
// Classes that use this type:  BST
class BSTNode<K extends Comparable<K>,V> {
	
	K key;
	V value;
	BSTNode<K,V> left;
	BSTNode<K,V> right;
	int height;
    int balanceFactor;

	/**
	 * @param key
	 * @param value
	 * @param leftChild
	 * @param rightChild
	 */
	BSTNode(K key, V value, BSTNode<K,V>  leftChild, BSTNode<K,V> rightChild) {
		this.key = key;
		this.value = value;
		this.left = leftChild;
		this.right = rightChild;
		this.height = 0;
		this.balanceFactor = 0;
	}
	
	BSTNode(K key, V value) { 
	    this(key,value,null,null); 
	}
	
	void setLeft(BSTNode newNode) {
	  this.left = newNode;
	}
	
	void setRight(BSTNode newNode) {
	   this.right = newNode;
	}
	
	int count() {
	  int result = 1;
	  if (left != null) {
	    result += left.count();
	  }
	  if (right != null) {
	    result += right.count();
	  }
	  return result;
	}
}

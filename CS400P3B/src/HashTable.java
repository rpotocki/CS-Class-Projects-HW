import java.util.ArrayList;

/**
 * This class creates a hashtable data structure that uses an array of linked nodes
 * in order to deal with collisions
 * 
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
  
    private int numKeys; //total number of items in the hashtable
    private Double loadFactorThreshold;
    private int capacity; //max amount of items that can be in the hashtable before it needs to be restructured
    private ArrayList<hashNode<K,V>> hashTableList; //the arraylist containing the linked nodes
	
    /**
     * This is a default constructor that takes no arguments and creates
     * a hashtable with the standard capacity and load factor threshhold
     * from the java hashtable class
     */ 
	public HashTable() {
      hashTableList = new ArrayList<>();
      this.capacity = 11;
      this.numKeys = 0;
      this.loadFactorThreshold = 0.75;
      //populates the array list with null values (empty)
      for (int i = 0; i < 11; i++){
        hashTableList.add(null);
      } 
	}
	
	   /**
     * This constructor creates a hashtable with the parameters of the 
     * initial capacity and the load factor threshhold
     * 
     * @param initialCapacity
     * @param loadFactorThreshold
     */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
	      hashTableList = new ArrayList<>();
	       this.capacity = initialCapacity;
	       this.numKeys = 0;
	       this.loadFactorThreshold = loadFactorThreshold;
	       //populates the array list with the correct amount of buckets according to the capacity
	       for (int i = 0; i < initialCapacity; i++) {
	         hashTableList.add(null);
	       }
	}

	/**
     * This method computes the correct hash index for a given key
     * which points to the bucket that it should be added to
     * 
     */
    private int getHashIndex(K key) {
      return Math.abs((key.hashCode()) % this.capacity);
    }
    
    /**
     * This method inserts a given key/value pair into the hash table
     * 
     *@param key
     *@param value
     */
    @Override
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
      if (key == null) {
        throw new IllegalNullKeyException();
      }
      //gets the correct hash index for the key
      int bucketListIndex = getHashIndex(key);
      //accesses the correct bucket within the array list
      hashNode<K,V> curNode = hashTableList.get(bucketListIndex);
      //checks if there is a key that already exists in the bucket
      while (curNode != null) {
        if (curNode.key.equals(key)) {
          throw new DuplicateKeyException();
        }
        curNode = curNode.getNext();
      }
      
      //key doesn't exist so it will now be inserted into the bucket list
      hashNode<K,V> head = hashTableList.get(bucketListIndex);
      hashNode<K,V> newNode = new hashNode<K, V>(key, value);
      //inserts the new node at the head of the list
      newNode.setNext(head);
      this.numKeys++;
      
      //update the reference to the head of the linked list in the hash table
      hashTableList.set(bucketListIndex, newNode);
      
      //check if the table needs to be resizes/rehashed
      if (this.getLoadFactor() >= this.getLoadFactorThreshold()) {
        ArrayList<hashNode<K,V>> tempList = hashTableList;
        hashTableList = new ArrayList<>();
        this.capacity = 2 * this.capacity + 1;
        //resets the number of keys before they are readded into new hash table
        this.numKeys = 0; 
        //initialize the new hashtable with null values
        for (int i = 0; i < this.capacity; i++) {
          hashTableList.add(null);
        }
        //repopulate the new hashtable with the previous key/value pairs
        for (hashNode<K,V> headNode : tempList) {
          while (headNode != null) {
            insert(headNode.key, headNode.value);
            headNode = headNode.getNext();
          }
        }
      }
    }
  
    /**
     * This method deletes a key/value pair from the hashtable
     * based on the key that is passed in as a parameter
     * 
     *@param key
     *@return boolean value is the remove was successful or not
     */
    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
      if (key == null) {
        throw new IllegalNullKeyException();
      }
      
      //gets the correct bucket index
      int bucketListIndex = getHashIndex(key);
  
      hashNode<K,V> headNode = hashTableList.get(bucketListIndex);
      hashNode<K,V> prevNode = null;
      //loops through the correct list to see if the node exists
      while (headNode != null) {
        if (headNode.key.equals(key)) {
          break;
        }
        prevNode = headNode;
        headNode = headNode.getNext();
      }
      //if the node was not found
      if (headNode == null) {
        return false;
      }
      this.numKeys--;
      //if the list was larger than 1 node
      if (prevNode != null) {
        prevNode.setNext(headNode.getNext());
      }
      //list was empty/only 1 node
      else {
        hashTableList.set(bucketListIndex, headNode.getNext());
      }
      return true;
  
    }
    
    /**
     * This returns the value that is associated with a
     * key from within the hashtable
     * 
     *@param key
     *@return Value paired with key
     */
    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
      if (key == null) {
        throw new IllegalNullKeyException();
      }
      //gets the correct bucket index
      int bucketListIndex = getHashIndex(key);
  
      hashNode<K,V> headNode = hashTableList.get(bucketListIndex);
      //loops through the linked list
      while (headNode != null) {
        if (headNode.key.equals(key)) {
          return headNode.value;
        }
        headNode = headNode.getNext();
      }
      //if the key was not found
      throw new KeyNotFoundException();
    }
    
    /**
     * This method returns the number of elements
     * in the hashtable
     */
    @Override
    public int numKeys() {
      return this.numKeys;
    }
  
    /**
     * This method returns the load factor threshhold
     * of the hashtable
     */
    @Override
    public double getLoadFactorThreshold() {
      return this.loadFactorThreshold;
    }
  
    
    /**
     * This method returns the current load factor
     * of the hashtable
     */
    @Override
    public double getLoadFactor() {
      return (1.0*this.numKeys)/this.capacity;
    }
  
    
    /**
     * This method returns the current 
     * capacity of the hashtable
     */
    @Override
    public int getCapacity() {
      return this.capacity;
    }
  
    
    /**
     * This returns an integer indicating that
     * the collision resolution used was
     * array of linked nodes
     */
    @Override
    public int getCollisionResolution() {
      return 5;
    }
  
    /**
     * This is a private inner class that
     * outlines the structure for the linked nodes
     * that are used within the hashtable
     */
    @SuppressWarnings("hiding")
    private class hashNode<K,V> {
      private K key;
      private V value;
      private hashNode<K,V> next;
    
      private hashNode(K key, V Value) {
        this.key = key;
        this.value = Value;
      }
      
      private hashNode<K, V> getNext() {
        return this.next;
      }
      private void setNext(hashNode<K, V> nextNode) {
        this.next = nextNode;
      }
    
    }




		
}

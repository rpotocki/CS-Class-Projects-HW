//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Tiger.java
// Files:           JunglePark.java
// Course:          CS300 Fall
//
// Author:          Ryan Potocki    
// Email:           rpotocki@wisc.edu
// Lecturer's Name: Gary Dahl
//

///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class represents a Tiger in the JunglePark application
 *
 */

public class Tiger extends Animal {
  private static final int SCAN_RANGE = 100; // range dimension for scanning the neighborhood for food
  private static final String IMAGE_FILE_NAME = "images/tiger.png";
  private static int nextID = 1; // class variable that represents the identifier of the next tiger
                                 // to be created
  private int deerEatenCount = 0;
  // Tiger's identification fields
  private static final String TYPE = "TGR"; // A String that represents the tiger type
  private final int id; // Tiger's id: positive number that represents the order of the tiger


  /**
   * Creates a new Tiger object positioned at a random position of the display window
   * 
   * @param processing PApplet object that represents the display window
   */
  public Tiger(JunglePark processing) {
    // Set Tiger drawing parameters
    super(processing, IMAGE_FILE_NAME);

    // Set Tiger identification fields
    id = nextID;
    this.label = TYPE + id; // String that identifies the current tiger
    nextID++;
  }
  
  /**
   * This method returns the amount of deer a tiger has eaten
   * 
   * @returns number of deer eaten by a tiger
   */
  public int getDeerEatenCount() {
    return this.deerEatenCount;
  }

  /**
   * This method makes a tiger hop to a nearby deer to eat it
   * 
   */
  public void hop(Deer food) {
    if (this.isClose(food, SCAN_RANGE)) { //checks if the food is in range of the hop distance
      //sets new position for tiger as the position of the food
      this.setPositionX(food.getPositionX()); 
      this.setPositionY(food.getPositionY());
      processing.listGUI.remove(food); //removes food from the object list
      deerEatenCount++;
    }
  }
  
  /**
   * Tiger's behavior in the Jungle Park
   * Scans for food at the neighborhood of the current tiger. 
   * If the Tiger founds any deer at its proximity, it hops on it, and eats it
   */
  @Override
  public void action() {
    for (int i = 0; i < processing.listGUI.size(); i++ ) { //scans through the list of objects
      if (processing.listGUI.get(i) instanceof Deer) { //if the object is a deer it will call the hop method onto it
        hop((Deer) processing.listGUI.get(i));
      }
    }
    
    if(this.deerEatenCount > 0) {
      displayDeerEatenCount(); // display deerEatenCount
    }
  }
  
  
  /**
   * Displays the number of eaten deers if any on the top of the tiger image
   */
  public void displayDeerEatenCount() {
    this.processing.fill(0); // specify font color: black
    // display deerEatenCount on the top of the Tiger's image
    this.processing.text(deerEatenCount, this.getPositionX(), this.getPositionY() - this.image.height / 2 - 4);  
  }
  
 
}
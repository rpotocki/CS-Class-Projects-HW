//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           AddAnimalButton.java
// Files:           (a list of all source files used by that program)
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
 * This class creates a AddAnimalButton object that adds animals
 * to the park when clicked.
 * It extends from the Button Class
 *
 */
public class AddAnimalButton extends Button {
  private String type; // type of the animal to add
  
  /**
   * This constructor creates a button for adding animals
   * 
   */
  public AddAnimalButton(String type, float x, float y, JunglePark park) {
      super(x, y, park);
      this.type = type.toLowerCase();
      this.label = "Add " + type; //creates button label
  }
   
  
  /**
   * This determines which animal button was pressed and adds a new animal to the park
   * 
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      switch (type) { //determines which object should be created
        case "tiger":
          processing.listGUI.add(new Tiger(processing));
          break;
        case "deer":
          processing.listGUI.add(new Deer(processing));
          break;
      }
    }
  }

}

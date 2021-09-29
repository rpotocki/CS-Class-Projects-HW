//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           ClearButton.java
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
 * This class creates a ClearButton object that removes
 * all animals from the park.
 * It extends from the Button Class
 *
 */
public class ClearButton extends Button{
  public ClearButton(float x, float y, JunglePark park) {
    super(x, y, park);
    this.label = "Clear Park"; //adds the button label
  }
  
  
  /**
   * This method clears the animals from the park when the button is pressed
   * 
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      processing.clear();
    }
  }
}


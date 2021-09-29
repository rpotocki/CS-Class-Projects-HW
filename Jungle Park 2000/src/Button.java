//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Button.java
// Files:           ParkGUI
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
 * This class creates a Button object and impliments the ParkGUI Inferface
 * This a super class for any Button that can be added to a PApplet application
 *
 */

public class Button implements ParkGUI {
  private static final int WIDTH = 85; // Width of the Button
  private static final int HEIGHT = 32; // Height of the Button
  protected JunglePark processing; // PApplet object where the button will be displayed
  private float[] position; // array storing x and y positions of the Button with respect to 
                            // the display window 
  protected String label;   // text/label that represents the button

  /**
   * This constructor creates a button object
   * 
   */
  public Button(float x, float y, JunglePark processing) {
    this.processing = processing;
    this.position = new float[2];
    this.position[0] = x;
    this.position[1] = y;
    this.label = "Button";
  }

  /**
   * This method draws the button object onto the GUI, overridding the method from ParkGUI
   * 
   */
  @Override
  public void draw() {
    this.processing.stroke(0);// set line value to black
    if (isMouseOver())
      processing.fill(100); // set the fill color to dark gray if the mouse is over the button
    else
      processing.fill(200); // set the fill color to light gray otherwise

    // draw the button (rectangle with a centered text)
    processing.rect(position[0] - WIDTH / 2.0f, position[1] - HEIGHT / 2.0f,
        position[0] + WIDTH / 2.0f, position[1] + HEIGHT / 2.0f);
    processing.fill(0); // set the fill color to black
    processing.text(label, position[0], position[1]); // display the text of the current button
  }

  /**
   * This method runs everytime the mouse button is pressed and overrides the mousPressed() method from ParkGUI
   * 
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) //checks if mouse is over the button when clicked
      System.out.println("A button was pressed.");
  }

  /**
   * This method runs everytime a mouse button is released, doesn't do anything
   * 
   */
  @Override
  public void mouseReleased() {}

  /**
   * This method checks to see if the users cursor is over the button that is created
   * and it too overrides the isMouseOver() method from ParkGUI
   */
  @Override
  public boolean isMouseOver() {
    if (this.processing.mouseX > this.position[0] - WIDTH / 2
        && this.processing.mouseX < this.position[0] + WIDTH / 2
        && this.processing.mouseY > this.position[1] - HEIGHT / 2
        && this.processing.mouseY < this.position[1] + HEIGHT / 2)
      return true;
    return false;
  }
}
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Jungle Park 1000
//Files:           CS300JunglePark.jar and Image
//Course:          CS300 Fall 2018
//
//Author:          Ryan Potocki
//Email:           rpotocki@wisc.edu
//Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons:         NONE
//Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Random;

/**
 * This class contains the methods to start running the JunglePark simulation
 * 
 */

public class JunglePark {
  private static PApplet processing; // PApplet object that represents the graphic
  // interface of the JunglePark application
  private static PImage backgroundImage; // PImage object that represents the
  // background image
  private static Tiger[] tigers; // array storing the current tigers present
  // in the Jungle ParK
  private static Random randGen;

  public static void main(String[] args) {
    Utility.startApplication();

  }

  /**
   * Defines the initial environment properties of the application
   * 
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj; // initialize the processing field to the one passed into
    // the input argument parameter
    backgroundImage = processing.loadImage("images/background.png");
    randGen = new Random();
    tigers = new Tiger[8];
    for (int i = 0; i < tigers.length; i++) {
      tigers[i] = null;
    }
  }

  /**
   * This method is constantly called and updates the interface that shows the background
   * and current location of tigers on the screen
   * 
   */

  public static void update() {
    processing.background(245, 255, 250);
    // initialize and Sload the image of the background
    backgroundImage = processing.loadImage("images/background.png");
    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores the width
    // [resp. height] of the display window.
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) {
        tigers[i].draw(); //draws the tiger object to the image
      }
    }
  }

  /**
   * Checks if the cursor is over the tiger object
   * 
   */

  public static boolean isMouseOver(Tiger tiger) {
    float tigerX = tiger.getPositionX();
    float tigerY = tiger.getPositionY();
    PImage tigerImage = tiger.getImage();
    float tigerHeight = tigerImage.height;
    float tigerWidth = tigerImage.width;
    // the variables below plot out the rectangle box where the tiger image is located
    float tigerLocationXMin = tigerX - (tigerWidth / 2);
    float tigerLocationXMax = tigerX + (tigerWidth / 2);
    float tigerLocationYMin = tigerY - (tigerHeight / 2);
    float tigerLocationYMax = tigerY + (tigerHeight / 2);
    // checks if the mouse location is on the tiger image
    if (((processing.mouseX >= tigerLocationXMin) && (processing.mouseX <= tigerLocationXMax))
        && ((processing.mouseY >= tigerLocationYMin) && (processing.mouseY <= tigerLocationYMax))) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Runs everytime the mouse button is clicked down
   * and sets the setDragging field for the tiger object to true
   * if the cursor is over the object
   */
  
  public static void mouseDown() {
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) {
        if (isMouseOver(tigers[i])) {
          tigers[i].setPositionX(processing.mouseX);
          tigers[i].setPositionY(processing.mouseY);
          tigers[i].setDragging(true);
        }
      }
    }
  }

  /**
   * Runs everytime the mouse button is NOT clicked down
   * and sets the setDragging field for the tiger object to false
   * if the cursor is over the object
   */
  
  public static void mouseUp() {
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) {
        if (isMouseOver(tigers[i])) {
          tigers[i].setDragging(false);
        }
      }
    }
  }

  /**
   * Runs everytime a key is pressed and checks if it should
   * create a new tiger object or remove one from the screen
   * 
   */
  
  public static void keyPressed() {
    if ((processing.key == 't')) {
      for (int i = 0; i < tigers.length; i++) {
        if ((tigers[i] == null)) {
          tigers[i] = new Tiger(processing, (float) randGen.nextInt(processing.width),
              (float) randGen.nextInt(processing.height));
          break;
        }
      }
    }
    if (processing.key == 'r') {
      for (int j = 0; j < tigers.length; j++) {
        if ((tigers[j] != null) && isMouseOver(tigers[j])) {
          tigers[j] = null;
          break;
        }
      }
    }
  }
}


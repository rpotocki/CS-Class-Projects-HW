
public class Deer extends Animal {
  
  private static final int SCAN_RANGE = 175; // scan range area to check for a threat in the neighborhood
  private static final String IMAGE_FILE_NAME = "images/deer.png";
  private static int nextID = 1; // class variable that represents the identifier of the next deer
                                 // to be created
    
  private static final String TYPE = "DR"; // A String that represents the deer type
  private final int id; // Deer's id: positive number that represents the order of the deer
   
    
  // Constructor that creates a new Deer object positioned at a random position of the display window
  public Deer(JunglePark processing) { 
    // Set Tiger drawing parameters
    super(processing, IMAGE_FILE_NAME);
    
    // Set Tiger identification fields
    id = nextID;
    this.label = TYPE + id; // String that identifies the current deer
    nextID++;
  }
   
  // Checks if there is a threat (a Tiger for instance) at the neighborhood
  // scanRange is an integer that represents the range of the area to be scanned around the animal
  public boolean scanForThreat(int scanRange) {  
    for (int i = 0; i < processing.listGUI.size(); i++ ) {
      if (processing.listGUI.get(i) instanceof Tiger) {
        if (this.isClose((Animal) processing.listGUI.get(i), scanRange)) {
          return true;
        }
      }
    }
    return false;
  }
  
  // Defines the behavior of a Deer object in the Jungle park
    @Override
  public void action() {  
      if (scanForThreat(SCAN_RANGE)) {
        this.processing.fill(0); // specify font color: black
        this.processing.text("THREAT!", this.getPositionX(), this.getPositionY() - this.image.height / 2 - 4);
      }
    }
}

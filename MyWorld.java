import greenfoot.*;

/**
 * MyWorld class - represents the game world.
 * Initializes the world and manages game state.
 */
public class MyWorld extends World {

    // Static variables for game parameters
    public static int difficulty = 1;
    public static int distance = 0;
    public static int speed = 6;
    public static int grassOffset = 100;
    public static boolean started = false;
    public static int overlays = 0;
    public static int lvl = 0;

    // Timer to manage game events
    private SimpleTimer timer = new SimpleTimer();

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld() {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        setupWorld();
    }
    
    /**
     * Sets up the initial state of the world.
     */
    private void setupWorld() {
        int length = 50;

        // Add grass on the left side
        for (int i = 0; i < length; i++) {
            addObject(new Grass(), grassOffset + i * 5, getHeight() - i * 5);
        }

        // Add grass on the right side
        for (int i = 0; i < length; i++) {
            addObject(new Grass(), getWidth() - grassOffset - i * 5, getHeight() - i * 5);
        }

        // Add sky object in the center
        addObject(new Sky(), getWidth() / 2 - 15, getHeight() / 2);
        
        // Add road overlay
        addObject(new RoadOverlay(), getWidth() / 2, 275);
        
        // Add opponent car
        addObject(new Opponent(), getWidth() - (int) (getWidth() / 2), 300);
        
        // Add player car
        addObject(new Player(), getWidth() - (int) (getWidth() * 0.375), getHeight() - (int) (getHeight() * 0.1));
        
        // Add car trigger
        addObject(new carTrigger(), getWidth() - (int) (getWidth() * 0.375), 380);
        
        // Start the timer
        timer.mark();
        
        // Show instructions if game has not started
        if (!started) {
            Instructions i = new Instructions();
            addObject(i, getWidth() / 2, 100);
        }
    }
    
    /**
     * Act method called by Greenfoot. Manages game state during gameplay.
     */
    public void act() {
        if (started) {
            // Remove instructions after 5 seconds
            if (timer.millisElapsed() > 5000) {
                removeInstructions();
            }
        }
    }
    
    /**
     * Removes instruction objects from the world.
     */
    private void removeInstructions() {
        removeObjects(getObjects(Instructions.class));
    }
}

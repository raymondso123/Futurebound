import greenfoot.*;

/**
 * MyWorld class - represents the game world.
 * Initializes the world and manages game state.
 */
public class MyWorld extends World {

    // Static variables for game parameters
    public static double distance = 0;
    public static double offset = 0;
    public static int speed = 6;
    //public static int grassOffset = 100;
    public static boolean started = false;
    //public static int overlays = 0;
    public static int lvl = 0;
    
    // Tweening + Geenration Logic 
    private boolean isChangingDistance = false;
    private int changeDirection = 0; // 0 for increasing, 1 for decreasing
    private SimpleTimer genTimer = new SimpleTimer();
    private double tweenStartValue = 0;
    private double tweenEndValue = 0;
    private long tweenDuration = 5000; // Duration of the tween in milliseconds
    private long tweenStartTime = 0;

    // Timer to manage game events
    private SimpleTimer timer = new SimpleTimer();
    private SimpleTimer gen = new SimpleTimer();

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
        /*
        // Add grass on the left side
        for (int i = 0; i < length; i++) {
            addObject(new Grass(), grassOffset + i * 5, getHeight() - i * 5);
        }

        // Add grass on the right side
        for (int i = 0; i < length; i++) {
            addObject(new Grass(), getWidth() - grassOffset - i * 5, getHeight() - i * 5);
        }
        */
       
        for (int i = 0; i < length; i++) {
            addObject(new Road(), getWidth()/2, getHeight() - i * 5);
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
        
        // Show instructions if game has started
        if (started) {
            Instructions i = new Instructions();
            addObject(i, getWidth() / 2, 100);
            gen.mark();
        }
    }
    
    /**
     * Act method called by Greenfoot. Manages game state during gameplay.
     */
    
    private boolean isResettingToZero = false;

    public void act() {
        if (started) {
            handleInput();
            if (started && !isChangingDistance && !isResettingToZero) {
                if (genTimer.millisElapsed() > 5000) {
                    isChangingDistance = true;
                    changeDirection = Greenfoot.getRandomNumber(2); // Randomly choose direction
                    genTimer.mark();
                    
                    // Set tween start and end values
                    tweenStartValue = distance;
                    if (changeDirection == 0) {
                        tweenEndValue = 1.0; // Tween to 1.0 if increasing
                    } else {
                        tweenEndValue = -1.0; // Tween to -1.0 if decreasing
                    }
                    
                    // Start tweening
                    tweenStartTime = System.currentTimeMillis();
                }
            }
            
            if (isChangingDistance) {
                // Calculate elapsed time since tween start
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - tweenStartTime;
                
                // Check if tweening duration has passed
                if (elapsedTime >= tweenDuration) {
                    // Finish tweening
                    distance = tweenEndValue;
                    isChangingDistance = false;
                    isResettingToZero = true;
                    tweenStartValue = distance;
                    tweenEndValue = 0; // Tween back to 0 after waiting
                    tweenStartTime = currentTime; // Reset tween start time for zeroing
                } else {
                    // Calculate current tween value (linear interpolation)
                    double t = (double) elapsedTime / tweenDuration;
                    distance = lerp(tweenStartValue, tweenEndValue, t);
                }
            }
            
            if (isResettingToZero) {
                // Calculate elapsed time since tween start
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - tweenStartTime;
                
                // Check if tweening duration has passed
                if (elapsedTime >= tweenDuration) {
                    // Finish resetting to zero
                    distance = 0;
                    isResettingToZero = false;
                } else {
                    // Calculate current tween value (linear interpolation)
                    double t = (double) elapsedTime / tweenDuration;
                    distance = lerp(tweenStartValue, tweenEndValue, t);
                }
            }
        }
    }
    
    private double lerp(double startValue, double endValue, double t) {
        return startValue + t * (endValue - startValue);
    }
    
    private void handleInput() {
        if (Greenfoot.isKeyDown("right")) {
            removeInstructions();
            if (offset < 1 ) {
                offset += 0.01;
                if (distance !=0 ) {
                    distance+=1;
                }
            }
        } else if (Greenfoot.isKeyDown("left")) {
            removeInstructions();
            if (offset >0) {
                offset -= 0.01;
                if (distance != 0) {
                    distance-=1;
                }
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

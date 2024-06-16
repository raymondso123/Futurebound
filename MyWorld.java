import greenfoot.*;

/**
 * MyWorld class - represents the game world.
 * Initializes the world and manages game state.
 */
public class MyWorld extends World {

    // static variables for game parameters
    public static double distance = 0;
    public static double offset = 0;
    public static int speed = 6;
    //public static int grassOffset = 100;
    public static boolean started = false;
    //public static int overlays = 0;
    public static int lvl = 0;
    
    // tweening + Geenration Logic 
    private boolean changing = false;
    private int dir = 0; // 0 for increasing, 1 for decreasing
    private SimpleTimer genTimer = new SimpleTimer();
    private double tweenStart = 0;
    private double tweenEnd = 0;
    private long tweenFrames = 5000; // Frames of lerp
    private long tweenTime = 0;

    // timer to manage game events
    private SimpleTimer timer = new SimpleTimer();
    private SimpleTimer gen = new SimpleTimer();

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld() {    
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
        
        // Add road overlay
        addObject(new RoadOverlay(), getWidth() / 2, 275);
       
        
        // add road overlay (vert)
        //addObject(new Lines(), getWidth() / 2 - 15, getHeight() / 2);
        
        for (int i = 0; i < length; i++) {
            addObject(new Road(), getWidth()/2, getHeight() - i * 5);
        }
        
        // add sky object in the center
        addObject(new Sky(), getWidth() / 2 - 15, getHeight() / 2);
        
        // add opponent car
        addObject(new Opponent(), getWidth() - (int) (getWidth() / 2), 300);
        
        // add player car
        addObject(new Player(), getWidth() - (int) (getWidth() * 0.375), getHeight() - (int) (getHeight() * 0.1));
        
        // add car trigger
        addObject(new carTrigger(), getWidth() - (int) (getWidth() * 0.375), 380);
        
        // add nitro (for level 2)
            
        // start the timer
        timer.mark();
        
        // show instructions if game has started
        if (started) {
            Instructions i = new Instructions();
            addObject(i, getWidth() / 2, 100);
            gen.mark();
        }
        
        if (lvl >= 1) {
            addObject(new Nitro(), 46, 32);
        }
    }
    
    public void changeLvl(int newLevel) {
        lvl = newLevel;
    }
    
    private boolean zero = false; // changing to zero (default position)

    public void act() {
        if (started) {
            handleInput();
            
            if (started && !changing && !zero) {
                if (genTimer.millisElapsed() > 5000) {
                    changing = true;
                    dir = Greenfoot.getRandomNumber(2); // randomly choose direction
                    genTimer.mark();
                    
                    // set tween start and end values
                    tweenStart = distance;
                    if (dir == 0) {
                        tweenEnd = 1.0; // tween to 1.0 if increasing
                    } else {
                        tweenEnd = -1.0; // tween to -1.0 if decreasing
                    }
                    
                    // start tweening
                    tweenTime = System.currentTimeMillis();
                }
            }
            
            if (changing) {
                // calculate elapsed time since tween start
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - tweenTime;
                
                // check if tweening duration has passed
                if (elapsedTime >= tweenFrames) {
                    // finish tweening
                    distance = tweenEnd;
                    changing = false;
                    zero = true;
                    tweenStart = distance;
                    tweenEnd = 0; // tween back to 0 after waiting
                    tweenTime = currentTime; // reset tween start time for zeroing
                } else {
                    // calculate currrent tween value (linear interpolation formula)
                    double t = (double) elapsedTime / tweenFrames;
                    distance = lerp(tweenStart, tweenEnd, t);
                }
            }
            
            if (zero) {
                // calculate elapsed time since tween start
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - tweenTime;
                
                // check if tweening duration has passed
                if (elapsedTime >= tweenFrames) {
                    // finish resetting to zero
                    distance = 0;
                    zero = false;
                } else {
                    // calculate current tween value (linear interpolation)
                    double t = (double) elapsedTime / tweenFrames;
                    distance = lerp(tweenStart, tweenEnd, t);
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
    
    private void removeInstructions() {
        removeObjects(getObjects(Instructions.class));
    }
}

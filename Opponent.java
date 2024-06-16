import greenfoot.*;

/**
 * Opponent class - represents the opponent's car.
 * Handles AI movement, scaling based on y-position, and spinout animation.
 */
public class Opponent extends Actor {
    
    // constants for sprite indexes
    private static final int def = 14;
    private static final int initDef = 14;
    
    // array to store sprite images
    private GreenfootImage[] sprites;
    private int current; // index of the current sprite
    private boolean tweening = false; // for spinout animation
    private int ogWidth;
    private int ogHeight;
    
    private SimpleTimer delay = new SimpleTimer(); // timer for AI movement
    
    MyWorld world = (MyWorld)getWorld();
    
    /**
     * Constructor initializes the opponent.
     */
    public Opponent() {
        // load all 53 sprites and resize them to 1/4 of their og size
        sprites = new GreenfootImage[53];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new GreenfootImage("Vehicles-Sprites/OBSTACLE1/" + i + ".png");
            sprites[i].scale(sprites[i].getWidth() / 4, sprites[i].getHeight() / 4);
        }
        
        // set initial sprite and image properties
        current = def;
        setImage(sprites[current]);
        ogWidth = sprites[0].getWidth();
        ogHeight = sprites[0].getHeight();
        
        // start delay timer
        delay.mark();
    }

    /**
     * Act method called by Greenfoot. Handles opponent's behavior.
     */
    public void act() {
        // only act if the game has started and it's level 0
        if (world.started) {
            ai(); // perform AI movement
            update(); // update opponent's appearance
            handleInput(); // handle user input
        }
    }

    /**
     * AI method controls opponent's movement.
     */
    private void ai() {
        int neg = Greenfoot.getRandomNumber(2);
        int rand = Greenfoot.getRandomNumber(10);
        
        // perform movement every 75 milliseconds
        if (delay.millisElapsed() > 75) {
            delay.mark();
            if (neg == 0) {
                for (int i = 0; i < rand; i++) {
                    move(-1); // move left randomly
                }
            } else {
                for (int i = 0; i < rand; i++) {
                    move(1); // move right randomly
                }
            }
        }
        
        // adjust Y position based on game speed
        setLocation(getX(), getY() - (10 - world.speed));
        
        // check if opponent is below a certain Y threshold
        if (getY() < 260) {
            Greenfoot.setWorld(new Death1()); // switch to death screen
            Greenfoot.delay(10000); // delay before continuing
        }
        
        // check if opponent is touching grass
        if (!this.isTouching(Road.class)) {
            tweening = true; // trigger spinout animation
        }
        
        // perform spinout animation
        if (tweening) {
            spinout();
        }
        
        // remove opponent if it reaches the bottom
        if (getY() > 380) {
            change(1);
            getWorld().removeObject(this); // remove opponent from the world
        }
    }

    /**
     * Handle user input to move the opponent.
     */
    private void handleInput() {
        // allow movement only if not in spinout animation
        if (!tweening) {
            if (Greenfoot.isKeyDown("right")) {
                move(world.speed / 3); // move right
            } else if (Greenfoot.isKeyDown("left")) {
                move(-world.speed / 3); // move left
            }
        }
    }

    public void change(int lvl) {
        if (world!=null) {
            world.changeLvl(lvl);
        }
    }
    
    /**
     * Update the opponent's sprite based on its X position.
     */
    private void update() {
        // only update sprite if not in spinout animation
        if (!tweening) {
            int distanceFromCenter = getX() - getWorld().getWidth() / 2;
            int spriteIndexDelta = distanceFromCenter / 60; // adjust the divisor for finer control
            current = initDef + spriteIndexDelta;
            
            // clamp sprite index within raange
            if (current < 0) {
                current = 0;
            }
            if (current >= sprites.length) {
                current = sprites.length - 1;
            }
            
            // set the image to the current sprite
            setImage(sprites[current]);
        }
    }

    /**
     * Perform spinout animation.
     */
    private void spinout() {
        // cycle through sprites for spinout animation
        if (current < sprites.length - 1) {
            current++;
        } else {
            current = 0; // Loop back to the first sprite
        }
        
        // set the image to the current sprite
        setImage(sprites[current]);
        
        // move the opponent sideways during spinout based on its X position
        if (getX() < getWorld().getWidth() / 2) {
            setLocation(getX() - 15, getY() + 3); // move left
        } else {
            setLocation(getX() + 15, getY() + 3); //move right
            adjustSize(); // adjust size when moving right
        }
    }
    
    /**
     * Adjust size of the opponent's image.
     */
    private void adjustSize() {
        GreenfootImage img = new GreenfootImage(sprites[current]);
        img.scale((int)(img.getWidth() * 1.5), (int)(img.getHeight() * 1.5)); // scale the image
        setImage(img); // set the scaled image
    }
}

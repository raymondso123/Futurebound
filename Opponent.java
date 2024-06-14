import greenfoot.*;

/**
 * Opponent class - represents the opponent's car.
 * Handles AI movement, scaling based on y-position, and spinout animation.
 */
public class Opponent extends Actor {
    
    // Constants for sprite indices
    private static final int def = 14;
    private static final int initDef = 14;
    
    // Array to store sprite images
    private GreenfootImage[] sprites;
    private int currentSpriteIndex; // Index of the current sprite
    private boolean tweening = false; // Flag for spinout animation
    private int originalWidth;
    private int originalHeight;
    
    private SimpleTimer delay = new SimpleTimer(); // Timer for AI movement
    
    /**
     * Constructor initializes the opponent.
     */
    public Opponent() {
        // Load all 53 sprites and resize them to quarter of their original size
        sprites = new GreenfootImage[53];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new GreenfootImage("Vehicles-Sprites/OBSTACLE1/" + i + ".png");
            sprites[i].scale(sprites[i].getWidth() / 4, sprites[i].getHeight() / 4);
        }
        
        // Set initial sprite and image properties
        currentSpriteIndex = def;
        setImage(sprites[currentSpriteIndex]);
        originalWidth = sprites[0].getWidth();
        originalHeight = sprites[0].getHeight();
        
        // Start delay timer
        delay.mark();
    }

    /**
     * Act method called by Greenfoot. Handles opponent's behavior.
     */
    public void act() {
        // Only act if the game has started and it's level 0
        if (MyWorld.started && MyWorld.lvl == 0) {
            ai(); // Perform AI movement
            update(); // Update opponent's appearance
            handleInput(); // Handle user input
        }
    }

    /**
     * AI method controls opponent's movement.
     */
    private void ai() {
        int neg = Greenfoot.getRandomNumber(2);
        int rand = Greenfoot.getRandomNumber(6);
        
        // Perform movement every 100 milliseconds
        if (delay.millisElapsed() > 100) {
            delay.mark();
            if (neg == 0) {
                for (int i = 0; i < rand; i++) {
                    move(-1); // Move left randomly
                }
            } else {
                for (int i = 0; i < rand; i++) {
                    move(1); // Move right randomly
                }
            }
        }
        
        // Adjust Y position based on game speed
        setLocation(getX(), getY() - (10 - MyWorld.speed));
        
        // Check if opponent is below a certain Y threshold
        if (getY() < 260) {
            Greenfoot.setWorld(new Death1()); // Switch to death screen
            Greenfoot.delay(10000); // Delay before continuing
        }
        
        // Check if opponent is touching grass
        if (this.isTouching(Grass.class)) {
            tweening = true; // Trigger spinout animation
        }
        
        // Perform spinout animation
        if (tweening) {
            spinout();
        }
        
        // Remove opponent if it reaches the bottom
        if (getY() > 380) {
            MyWorld.lvl = 1; // Increase game level
            getWorld().removeObject(this); // Remove opponent from the world
        }
    }

    /**
     * Handle user input to move the opponent.
     */
    private void handleInput() {
        // Allow movement only if not in spinout animation
        if (!tweening) {
            if (Greenfoot.isKeyDown("right")) {
                move(MyWorld.speed / 4); // Move right
            } else if (Greenfoot.isKeyDown("left")) {
                move(-MyWorld.speed / 4); // Move left
            }
        }
    }

    /**
     * Update the opponent's sprite based on its X position.
     */
    private void update() {
        // Only update sprite if not in spinout animation
        if (!tweening) {
            int distanceFromCenter = getX() - getWorld().getWidth() / 2;
            int spriteIndexDelta = distanceFromCenter / 60; // Adjust the divisor for finer control
            currentSpriteIndex = initDef + spriteIndexDelta;
            
            // Clamp sprite index within valid range
            if (currentSpriteIndex < 0) {
                currentSpriteIndex = 0;
            }
            if (currentSpriteIndex >= sprites.length) {
                currentSpriteIndex = sprites.length - 1;
            }
            
            // Set the image to the current sprite
            setImage(sprites[currentSpriteIndex]);
        }
    }

    /**
     * Perform spinout animation.
     */
    private void spinout() {
        // Cycle through sprites for spinout animation
        if (currentSpriteIndex < sprites.length - 1) {
            currentSpriteIndex++;
        } else {
            currentSpriteIndex = 0; // Loop back to the first sprite
        }
        
        // Set the image to the current sprite
        setImage(sprites[currentSpriteIndex]);
        
        // Move the opponent sideways during spinout based on its X position
        if (getX() < getWorld().getWidth() / 2) {
            setLocation(getX() - 15, getY() + 3); // Move left
        } else {
            setLocation(getX() + 15, getY() + 3); // Move right
            adjustSize(); // Adjust size when moving right
        }
    }
    
    /**
     * Adjust size of the opponent's image.
     */
    private void adjustSize() {
        GreenfootImage img = new GreenfootImage(sprites[currentSpriteIndex]);
        img.scale((int)(img.getWidth() * 1.5), (int)(img.getHeight() * 1.5)); // Scale the image
        setImage(img); // Set the scaled image
    }
}

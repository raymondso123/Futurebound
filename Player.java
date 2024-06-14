import greenfoot.*;

/**
 * Player class - represents the player's car.
 * Handles user input, speed, collision detection, and costume changes.
 */
public class Player extends Actor {
    
    // Constants for sprite indices and turn thresholds
    private static final int def = 14;       // Default frame when not turning
    private static final int initDef = 14;   // Initial default frame
    private static final int thresL = 7;      // Left turn threshold min
    private static final int thresR = 21;     // Right turn threshold min
    
    // Array to store sprite images
    private GreenfootImage[] sprites;
    private int currentSpriteIndex;  // Index of the current sprite
    private boolean l = false;       // Turning left flag
    private boolean r = false;       // Turning right flag
    private boolean tweening = false;// Tweening (transitioning between frames) flag
    private int center;              // Center position of the sprite

    /**
     * Constructor initializes the player.
     */
    public Player() {
        // Load all 53 sprites and resize them to half their original size
        sprites = new GreenfootImage[53];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new GreenfootImage("Vehicles-Sprites/PLAYER/" + i + ".png");
            sprites[i].scale(sprites[i].getWidth() / 2, sprites[i].getHeight() / 2);
        }
        
        // Set initial sprite and image properties
        currentSpriteIndex = def;
        setImage(sprites[currentSpriteIndex]);
        center = getImage().getWidth() / 2; // Get the center position of the sprite
    }

    /**
     * Act method called by Greenfoot. Handles player's behavior.
     */
    public void act() {
        handleInput(); // Handle user input
        update();      // Update player's appearance based on position
    }

    /**
     * Handle user input to move the player and set turning flags.
     */
    private void handleInput() {
        if (Greenfoot.isKeyDown("right")) {
            r = true;      // Set turning right flag
            l = false;     // Clear turning left flag
            tweening = false; // Clear tweening flag
            move(MyWorld.speed / 2); // Move right
        } else if (Greenfoot.isKeyDown("left")) {
            l = true;      // Set turning left flag
            r = false;     // Clear turning right flag
            tweening = false; // Clear tweening flag
            move(-MyWorld.speed / 2); // Move left
        } else {
            if (l || r) {
                l = false;    // Clear turning left flag
                r = false;    // Clear turning right flag
                tweening = true; // Set tweening flag
            }
        }
    }

    /**
     * Update the player's sprite based on its X position.
     */
    private void update() {
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

import greenfoot.*;

/**
 * Player class - represents the player's car.
 * Handles user input, speed, collision detection, and costume changes.
 */
public class Player extends Actor {
    
    // Constants for sprite indices and turn thresholds
    private static int def = 13;       // Default frame when not turning
    private static final int initDef = 13;   // Initial default frame
    private static final int thresL = 12;    // Left turn threshold min
    private static final int thresR = 15;    // Right turn threshold min
    
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
        if (MyWorld.started) {
            handleInput(); // Handle user input
            update();      // Update player's appearance based on position
        }
    }

    /**
     * Handle user input to move the player and set turning flags.
     */
    private void handleInput() {
        if (Greenfoot.isKeyDown("right")) {
            if (!r) {
                currentSpriteIndex = initDef; // Reset to default frame when starting a new right turn
            }
            r = true;      // Set turning right flag
            l = false;     // Clear turning left flag
            tweening = false; // Clear tweening flag
            move(MyWorld.speed/3); // Move right
        } else if (Greenfoot.isKeyDown("left")) {
            if (!l) {
                currentSpriteIndex = initDef; // Reset to default frame when starting a new left turn
            }
            l = true;      // Set turning left flag
            r = false;     // Clear turning right flag
            tweening = false; // Clear tweening flag
            move(-MyWorld.speed/3); // Move left
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
        int spriteIndexDelta;
    
        // Calculate spriteIndexDelta based on the side of the center
        if (distanceFromCenter < 0) {
            // On the left side, increase index as it moves further left
            spriteIndexDelta = Math.abs(distanceFromCenter) / 60; // Adjust the divisor for finer control
        } else {
            // On the right side, decrease index as it moves further right
            spriteIndexDelta = -Math.abs(distanceFromCenter) / 60; // Adjust the divisor for finer control
        }
    
        currentSpriteIndex = initDef + spriteIndexDelta;
    
        // Clamp sprite index within the range of thresL to thresR
        if (currentSpriteIndex < thresL) {
            currentSpriteIndex = thresL;
        }
        if (currentSpriteIndex > thresR) {
            currentSpriteIndex = thresR;
        }
        
        // Perform tweening to transition back to initDef when tweening flag is true
        if (tweening) {
            if (currentSpriteIndex < initDef) {
                currentSpriteIndex++;
            } else if (currentSpriteIndex > initDef) {
                currentSpriteIndex--;
            } else {
                tweening = false; // Tweening complete
            }
        }
    
        // Set the image to the current sprite
        setImage(sprites[currentSpriteIndex]);
    }
}

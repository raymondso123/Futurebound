import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player class - represents the player's car.
 * Handles user input, speed, collision detection, and costume changes.
 * 
 */
public class Player extends Actor {
    private static final int DEFAULT_FRAME = 14;
    private int speed = 3;
    private int turnSpeed = 5;
    private GreenfootImage[] sprites;
    private int currentSpriteIndex;
    private boolean turningLeft = false;
    private boolean turningRight = false;

    public Player() {
        // Load all 53 sprites and resize them
        sprites = new GreenfootImage[53];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new GreenfootImage("Vehicles-Sprites/PLAYER/" + i + ".png");
            sprites[i].scale(50, 30); // Resize the car, adjust the size as needed
        }
        currentSpriteIndex = DEFAULT_FRAME;
        setImage(sprites[currentSpriteIndex]);
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        handleInput();
        updateSprite();
    }

    private void handleInput() {
        if (Greenfoot.isKeyDown("left")) {
            turningLeft = true;
            turningRight = false;
            currentSpriteIndex -= 1;
            if (currentSpriteIndex < 0) currentSpriteIndex = sprites.length - 1;
        } else if (Greenfoot.isKeyDown("right")) {
            turningRight = true;
            turningLeft = false;
            currentSpriteIndex += 1;
            if (currentSpriteIndex >= sprites.length) currentSpriteIndex = 0;
        } else {
            // If no key is pressed, return to default state
            if (turningLeft || turningRight) {
                turningLeft = false;
                turningRight = false;
                currentSpriteIndex = DEFAULT_FRAME;
            }
        }
    }

    private void updateSprite() {
        // Update the car's sprite based on the current direction
        setImage(sprites[currentSpriteIndex]);
    }
}

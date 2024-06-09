import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player class - represents the player's car.
 * Handles user input, speed, collision detection, and costume changes.
 * 
 */
public class Player extends Actor {
    private int speed = 3;
    private int turnSpeed = 5;
    private GreenfootImage[] sprites;
    private int currentSpriteIndex;

    public Player() {
        // Load all 53 sprites
        sprites = new GreenfootImage[53];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new GreenfootImage("Vehicles-Sprites/PLAYER/" + i + ".png");
        }
        currentSpriteIndex = 0;
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
            currentSpriteIndex -= 1;
            if (currentSpriteIndex < 0) currentSpriteIndex = sprites.length - 1;
        }
        if (Greenfoot.isKeyDown("right")) {
            currentSpriteIndex += 1;
            if (currentSpriteIndex >= sprites.length) currentSpriteIndex = 0;
        }
        if (Greenfoot.isKeyDown("up")) {
            speed += 1;
        }
        if (Greenfoot.isKeyDown("down")) {
            speed -= 1;
        }
    }

    private void updateSprite() {
        // Update the car's sprite based on the current direction
        setImage(sprites[currentSpriteIndex]);
    }

    public int getSpeed() {
        return speed;
    }
}

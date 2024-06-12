import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player class - represents the player's car.
 * Handles user input, speed, collision detection, and costume changes.
 * 
 */
public class Player extends Actor {
    private static final int def = 14; // Default frame when not turning
    private static final int initDef = 14; // Initial default frame
    private static final int thresL = 7; // Left turn threshold min
    private static final int thresR = 21; // Right turn threshold min
    private GreenfootImage[] sprites;
    private int currentSpriteIndex;
    private boolean l = false; // Turning left
    private boolean r = false; // Turning right
    private boolean tweening = false; // Tweening flag
    private int center;

    public Player() {
        // Load all 53 sprites and resize them to half their original size
        sprites = new GreenfootImage[53];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new GreenfootImage("Vehicles-Sprites/PLAYER/" + i + ".png");
            sprites[i].scale(sprites[i].getWidth() / 2, sprites[i].getHeight() / 2);
        }
        currentSpriteIndex = def;
        setImage(sprites[currentSpriteIndex]);
        center = getImage().getWidth() / 2; // Get the center position of the sprite
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        handleInput();
        update();
    }

    private void handleInput() {
        if (Greenfoot.isKeyDown("right")) {
            r = true;
            l = false;
            tweening = false;
            move(MyWorld.speed);
        } else if (Greenfoot.isKeyDown("left")) {
            l = true;
            r = false;
            tweening = false;
            move(-MyWorld.speed);
        } else {
            if (l || r) {
                l = false;
                r = false;
                tweening = true;
            }
        }
    }

    private void update() {
        int distanceFromCenter = getX() - getWorld().getWidth() / 2;
        int spriteIndexDelta = distanceFromCenter / 60; // Adjust the divisor for finer control
        currentSpriteIndex = initDef + spriteIndexDelta;
        if (currentSpriteIndex < 0) currentSpriteIndex = 0;
        if (currentSpriteIndex >= sprites.length) currentSpriteIndex = sprites.length - 1;
        setImage(sprites[currentSpriteIndex]);
    }
}

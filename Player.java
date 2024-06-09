import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player class - represents the player's car.
 * Handles user input, speed, collision detection, and costume changes.
 * 
 */
public class Player extends Actor {
    private static final int def = 14; //default
    private static final int thresL = 7;
    private static final int thresR = 21;
    private int s = 1; //speed
    private GreenfootImage[] sprites;
    private int currentSpriteIndex;
    private boolean l = false; //left
    private boolean r = false;
    private boolean tweening = false;

    public Player() {
        // Load all 53 sprites and resize them to half their original size
        sprites = new GreenfootImage[53];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new GreenfootImage("Vehicles-Sprites/PLAYER/" + i + ".png");
            sprites[i].scale(sprites[i].getWidth() / 2, sprites[i].getHeight() / 2);
        }
        currentSpriteIndex = def;
        setImage(sprites[currentSpriteIndex]);
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        handleInput();
        update();
        tween();
    }

    private void handleInput() {
        if (Greenfoot.isKeyDown("right")) {
            r = true;
            l = false;
            tweening = false;
            if (currentSpriteIndex > thresL) {
                currentSpriteIndex -= 1;
                if (currentSpriteIndex < 0) currentSpriteIndex = sprites.length - 1;
            }
        } else if (Greenfoot.isKeyDown("left")) {
            l = true;
            r = false;
            tweening = false;
            if (currentSpriteIndex < thresR) {
                currentSpriteIndex += 1;
                if (currentSpriteIndex >= sprites.length) currentSpriteIndex = 0;
            }
        } else {
            if (l || r) {
                l = false;
                r = false;
                tweening = true;
            }
        }
    }

    private void update() {
        setImage(sprites[currentSpriteIndex]);
    }

    private void tween() {
        if (tweening) {
            if (currentSpriteIndex < def) {
                currentSpriteIndex++;
                if (currentSpriteIndex >= def) {
                    currentSpriteIndex = def;
                    tweening = false;
                }
            } else if (currentSpriteIndex > def) {
                currentSpriteIndex--;
                if (currentSpriteIndex <= def) {
                    currentSpriteIndex = def;
                    tweening = false;
                }
            }
            setImage(sprites[currentSpriteIndex]);
        }
    }
}

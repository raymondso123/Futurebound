import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player class - represents the player's car.
 * Handles user input, speed, collision detection, and costume changes.
 * 
 */
public class Opponent extends Actor {
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
    SimpleTimer delay = new SimpleTimer();

    public Opponent() {
        // Load all 53 sprites and resize them to half their original size
        sprites = new GreenfootImage[53];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new GreenfootImage("Vehicles-Sprites/OBSTACLE1/" + i + ".png");
            sprites[i].scale(sprites[i].getWidth() / 4, sprites[i].getHeight() / 4);
        }
        currentSpriteIndex = def;
        setImage(sprites[currentSpriteIndex]);
        center = getImage().getWidth() / 2;
        delay.mark();
    }

    public void act() {
        ai();
        update();
        handleInput();
    }

    private void ai() {
        int neg = Greenfoot.getRandomNumber(2);
        int rand = Greenfoot.getRandomNumber(10);
        if (delay.millisElapsed()>100) {
            delay.mark();
            if (neg==0) {
                for (int i=0;i<rand;i++) {
                    move(-1);
                }
            } else {
                for (int i=0;i<rand;i++) {
                    move(1);
                };
            }
        }
        setLocation(getX(),getY()-(10-MyWorld.speed));
    }
    
    private void handleInput() {
        if (Greenfoot.isKeyDown("right")) {
            move(MyWorld.speed/4);
        } else if (Greenfoot.isKeyDown("left")) {
            move(-MyWorld.speed/4);
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

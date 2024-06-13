import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Opponent class - represents the opponent's car.
 * Handles AI movement and scaling based on y-position.
 */
public class Opponent extends Actor {
    private static final int def = 14;
    private static final int initDef = 14;
    private GreenfootImage[] sprites;
    private int currentSpriteIndex;
    private boolean tweening = false;
    private int center;
    private int originalWidth;
    private int originalHeight;
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
        originalWidth = sprites[0].getWidth();
        originalHeight = sprites[0].getHeight();
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
        if (delay.millisElapsed() > 100) {
            delay.mark();
            if (neg == 0) {
                for (int i = 0; i < rand; i++) {
                    move(-1);
                }
            } else {
                for (int i = 0; i < rand; i++) {
                    move(1);
                }
            }
        }
        setLocation(getX(), getY() - (10 - MyWorld.speed));
        
        if (getY()<260) {
            Greenfoot.delay(10000);
        }
        
        if (this.isTouching(Grass.class)) {
            spinout();
            tweening = true;
        }
    }

    private void handleInput() {
        if (!tweening) {
            if (Greenfoot.isKeyDown("right")) {
                move(MyWorld.speed / 4);
            } else if (Greenfoot.isKeyDown("left")) {
                move(-MyWorld.speed / 4);
            }
        }
    }

    private void update() {
        if (!tweening) {
            int distanceFromCenter = getX() - getWorld().getWidth() / 2;
            int spriteIndexDelta = distanceFromCenter / 60; // Adjust the divisor for finer control
            currentSpriteIndex = initDef + spriteIndexDelta;
            if (currentSpriteIndex < 0) currentSpriteIndex = 0;
            if (currentSpriteIndex >= sprites.length) currentSpriteIndex = sprites.length - 1;
            setImage(sprites[currentSpriteIndex]);
        }
    }
    
    private void spinout() {
        if (currentSpriteIndex < sprites.length - 1) {
            currentSpriteIndex++;
        } else {
            currentSpriteIndex = 0;
        }
        setImage(sprites[currentSpriteIndex]);
        
        if (getX()<getWorld().getWidth()) {
            setLocation(getX()-5,getY()+1);
        } else {
            setLocation(getX()+5,getY()+1);
        }
    }
}

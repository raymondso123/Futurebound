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
        if (MyWorld.started && MyWorld.lvl == 0) {
            ai();
            update();
            handleInput();
        }
    }

    private void ai() {
        int neg = Greenfoot.getRandomNumber(2);
        int rand = Greenfoot.getRandomNumber(6);
        
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
                Greenfoot.setWorld(new Death1());
                Greenfoot.delay(10000);
            }
            
            if (this.isTouching(Grass.class)) {
                tweening = true;
            }
            
            if (tweening) {
                spinout();
            }
            
            if (getY() > 380) {
                MyWorld.lvl = 1;
                getWorld().removeObject(this);
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

    private void adjustSize() {
        GreenfootImage img = new GreenfootImage(sprites[currentSpriteIndex]);
        img.scale((int)(img.getWidth()*1.5), (int)(img.getHeight()*1.5));
        setImage(img);
    }
    
    private void spinout() {
        if (currentSpriteIndex < sprites.length - 1) {
            currentSpriteIndex++;
        } else {
            currentSpriteIndex = 0;
        }
        setImage(sprites[currentSpriteIndex]);
        
        if (getX()<getWorld().getWidth()/2) {
            setLocation(getX()-15,getY()+3);
        } else {
            setLocation(getX()+15,getY()+3);
            adjustSize();
        }
    }
}

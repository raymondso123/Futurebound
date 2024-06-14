import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public static int difficulty = 1;
    public static int distance =0;
    private int spawnTimer = 0;
    private int spawnInterval;
    public static int speed = 6;
    public static int grassOffset = 100;
    public static boolean started = false;
    public static int lvl = 0;
    //public static boolean plrExists = false;
    public static int overlays = 0;
    SimpleTimer timer = new SimpleTimer();
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        int length = 50;
        
        
        //left
        for (int i = 0; i < length; i++) {
            Grass g = new Grass();
            addObject(g, grassOffset+i*5, getHeight() - i*5);
        }
        
        //right
        for (int i = 0; i < length; i++) {
            Grass g = new Grass();
            addObject(g, getWidth()-grassOffset-i*5, getHeight()-i*5);
        }
        
        Sky s = new Sky();
        addObject(s, getWidth()/2-15, getHeight()/2);
        
        RoadOverlay r = new RoadOverlay();
        addObject(r,getWidth()/2,275);
        
        Opponent opp = new Opponent();
        addObject(opp, getWidth()-(int)(getWidth()/2), 300);
        
        Player plr = new Player();
        addObject(plr, getWidth()-(int)(getWidth()*0.375), getHeight()-(int)(getHeight()*0.1));
        
        carTrigger t = new carTrigger();
        addObject(t, getWidth()-(int)(getWidth()*0.375), 380);
        timer.mark();
        
        Instructions i = new Instructions();
        addObject(i,getWidth()/2,100);
        
        
    }
    
    public void act() {
        if (started) {
            if (timer.millisElapsed()>5000) {
                removeObjects(getObjects(Instructions.class));
            }
        }
    }
}



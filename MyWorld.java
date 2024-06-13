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
    public static boolean turning = false;
    public static double angle = 0;
    public static int speed = 5;
    public static boolean plrExists = false;
    public static int overlays = 0;
    SimpleTimer timer = new SimpleTimer();
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        int grassOffset = 100;
        int length = 40;
        
        timer.mark();
        
        //left
        for (int i = 0; i < length; i++) {
            Grass g = new Grass();
            addObject(g, grassOffset+i*5, getHeight() - i*3);
        }
        
        //right
        for (int i = 0; i < length; i++) {
            Grass g = new Grass();
            addObject(g, getWidth()-grassOffset-i*5, getHeight()-i*3);
        }
        
        RoadOverlay r = new RoadOverlay();
        addObject(r,getWidth()/2,275);
    
        Player plr = new Player();
        addObject(plr, getWidth()-(int)(getWidth()*0.375), getHeight()-(int)(getHeight()*0.1));
    }
    
    public void act() {
        if (timer.millisElapsed() > 1000) {
            timer.mark();
            angle = 1;
            turning = true;
        }
    }
}



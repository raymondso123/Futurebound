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
    public static double distance = 0;
    public static int speed = 10;
    public static int grassOffset = 100;
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
        
        Player plr = new Player();
        addObject(plr, getWidth()-(int)(getWidth()*0.375), getHeight()-(int)(getHeight()*0.1));
        timer.mark();
        
        carTrigger t = new carTrigger();
        addObject(t, getWidth()-(int)(getWidth()*0.375), 380);
    }
    
    public int ran(int start, int end) {
        int normal = Greenfoot.getRandomNumber(end - start + 1);
        return normal + start;
    }
    
    public void act() {
        
            /*
        if (timer.millisElapsed() > 3000) {
            distance = 1;
            if (timer.millisElapsed() > 5000) {
                distance = 0;
                timer.mark();
        }
            }*/
    }
}



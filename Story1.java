import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story1 extends World
{
    SimpleTimer timer = new SimpleTimer();
    public Story1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        timer.mark();
    }
    
    public void act() {
        if (!MyWorld.started) {
            if (timer.millisElapsed()>1000) {
                Greenfoot.setWorld(new Story2());
            }
        }
    }
}

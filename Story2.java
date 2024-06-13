import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story2 extends World
{
    SimpleTimer timer = new SimpleTimer();
    public Story2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        timer.mark();
    }
    
    public void act() {
        if (timer.millisElapsed()>5000) {
            Greenfoot.setWorld(new MyWorld());
            MyWorld.started = true;
        }
    }
}

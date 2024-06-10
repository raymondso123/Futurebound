import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Grass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grass extends Actor
{
    /**
     * Act - do whatever the Grass wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (isAtEdge()) {
            if (MyWorld.turning == true) {
                if (this.getX() > (int)(getWorld().getWidth()/2) && MyWorld.dist > 0) {
                    this.setLocation(0,getWorld().getHeight()-140);
                } else if (this.getX() < (int)(getWorld().getWidth()/2) && MyWorld.dist < 0) {
                    this.setLocation(599,getWorld().getHeight()-140);
                }
            }
        }
    }
}

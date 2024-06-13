import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cone class - represents a cone actor that spawns randomly and scales based on difficulty and position.
 */
public class Cone extends Actor
{
    private GreenfootImage image;
        
    public Cone() {
        image = new GreenfootImage("cone.png");
        setImage(image);
    }
    
    public void act()
    {
        int factor = (getY()/5);
        image.scale(factor,factor);
        
        setLocation(getX(),getY()+MyWorld.speed/2);
    }
}

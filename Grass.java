import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Grass class - represents grass objects that stretch horizontally based on their position.
 * 
 */
public class Grass extends Actor
{
    private GreenfootImage image;
    public Grass() {
        image = new GreenfootImage("grass2.jpg");
        setImage(image);
    }
    
    public void act()
    {
        road();
        fill();
        colour();
    }

    private void road() {
        int last = getWorld().getWidth();
        int mid = getWorld().getWidth() / 2;
        int x = getX();
        
        if (MyWorld.turning == true) {
        }
    }
    
    private void colour() {
        
    }
    
    private void fill() {
        int last = getWorld().getWidth();
        int mid = getWorld().getWidth() / 2;
        int x = getX();
        
        if (x < mid) {
            int newWidth = x+((image.getWidth()+1)/2);
            int newX = image.getWidth()/2;
            image.scale(newWidth, image.getHeight());
            setLocation(newX, getY());
        } else if (x > mid) {
            int newWidth = (last - x) + ((image.getWidth()+1) / 2);
            int newX = last - (image.getWidth() / 2);
            image.scale(newWidth, image.getHeight());
            setLocation(newX, getY());
        }
    }
}

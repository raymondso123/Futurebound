import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Grass class - represents grass objects that stretch horizontally based on their position.
 * 
 */
public class Grass extends Actor
{
    private GreenfootImage image; 
    private int offset = 0;
        
    public Grass() {
        image = new GreenfootImage("grass.jpg");
        setImage(image);
    }
    
    public void act()
    {
        turn();
        fill();
        handleInput();
    }

    private void turn() {
        int last = getWorld().getWidth();
        int mid = getWorld().getWidth() / 2;
        int x = getX();
        int y = getY();
        int yy = y / 5;
        
        int newX = x;
        
        double curveIntensity = 1.03 * MyWorld.distance;
        
        if (MyWorld.distance > 0) {
            if (x < mid) {
                newX = (int) (mid - Math.pow(mid - x, curveIntensity) * (1 - (double) yy / mid)) - 15;
            } else {
                newX = (int) (last - Math.pow(last - x, curveIntensity) * (1 - (double) yy / last)) + 15;
            }
        } else {
            int straightX;
            
            // Calculate the straight road position based on the grass offset and distance
            if (x < mid) {
                newX = MyWorld.grassOffset + y + (int) (1 - MyWorld.distance) * 100;
            } else {
                newX = getWorld().getWidth() - MyWorld.grassOffset - y - (int) (1 - MyWorld.distance) * 100;
            }
        }
        
        setLocation(newX, y);
    }

    private void handleInput() {
        if (Greenfoot.isKeyDown("right")) {
            offset++;
        } else if (Greenfoot.isKeyDown("left")) {
            offset--;
        }
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

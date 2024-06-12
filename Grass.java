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
    public void act() {
        stretchToViewport();
    }

    /**
     * Stretch the grass actor horizontally to fill the viewport based on its position.
     */
    private void stretchToViewport() {
        int worldWidth = getWorld().getWidth();
        int centerX = worldWidth / 2;
        int grassWidth = getImage().getWidth();
        int grassX = getX();

        if (grassX < centerX) {
            // Grass is below the center of the screen, stretch to the left
            int newWidth = grassWidth + (centerX - grassX);
            getImage().scale(newWidth, getImage().getHeight());
        } else {
            // Grass is above or at the center of the screen, stretch to the right
            int newWidth = grassWidth + (grassX - centerX);
            getImage().scale(newWidth, getImage().getHeight());
        }
    }
}

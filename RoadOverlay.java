import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RoadOverlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoadOverlay extends Actor
{
    private GreenfootImage image;
    private boolean hitEdge = false;
    SimpleTimer timer = new SimpleTimer();
   
    public RoadOverlay() {
        image = new GreenfootImage("overlay.png");
        image.scale(600,2);
        setImage(image);
        timer.mark();
    }
    
    public void act()
    {
        /*
        if (timer.millisElapsed() == (5-MyWorld.speed)/2) {
            if (MyWorld.overlays<2) {
                RoadOverlay r = new RoadOverlay();
                getWorld().addObject(r,getWorld().getWidth()/2,275);
                MyWorld.overlays++;
            }
        }*/
        
        if (image.getHeight() > 1) {
            if (timer.millisElapsed() > 5-MyWorld.speed) {
                timer.mark();
                if (!hitEdge) {
                    setLocation(getX(),getY()+MyWorld.speed*2);
                    if (image.getHeight() < 150) {
                        image.scale(image.getWidth(),image.getHeight()+(int)(MyWorld.speed*1.5));
                    }
                    
                    /*
                    if (getY()>310 && getY()<340) {
                        if (MyWorld.overlays<1) {
                            RoadOverlay r = new RoadOverlay();
                            getWorld().addObject(r,getWorld().getWidth()/2,275);
                            MyWorld.overlays++;
                            
                            if (!MyWorld.plrExists) {
                                MyWorld.plrExists = true;
                                Player plr = new Player();
                                getWorld().addObject(plr, getWorld().getWidth()-(int)(getWorld().getWidth()*0.375), getWorld().getHeight()-(int)(getWorld().getHeight()*0.1));                            
                            }
                        }
                    }*/
                    
                    if (isAtEdge()) {
                        hitEdge = true;
                    }
                } else {
                    if (image.getHeight() > MyWorld.speed*2) {
                        image.scale(image.getWidth(),image.getHeight()-MyWorld.speed*1);
                    } else {
                        hitEdge = false;
                        setLocation(getWorld().getWidth()/2,275);
                        image.scale(getWorld().getWidth(),2);
                        
                        timer.mark();
                    }
                }
            }
        }
    }
}

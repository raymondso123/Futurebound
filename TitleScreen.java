import greenfoot.*;

public class TitleScreen extends World {

    public TitleScreen() {
        super(600, 400, 1);
        prepare();
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new MyWorld());
            MyWorld.started = true;
        }
    }
    
    private void prepare() {
        // Add title image or text
        GreenfootImage titleImage = new GreenfootImage("title.png");
    }
}

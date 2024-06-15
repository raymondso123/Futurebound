import greenfoot.*;

public class TitleScreen extends World {

    public TitleScreen() {
        super(600, 400, 1);
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            MyWorld.started = true;
            Greenfoot.setWorld(new MyWorld());
            MyWorld.started = true;
        }
    }
}

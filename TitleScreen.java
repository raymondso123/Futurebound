import greenfoot.*;

public class TitleScreen extends World {

    public TitleScreen() {
        super(600, 400, 1);
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new Story1());
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public static boolean turning = true;
    public static double dist = 0;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        genGrass();
        
        Player plr = new Player();
        addObject(plr, getWidth()-(int)(getWidth()*0.375), getHeight()-(int)(getHeight()*0.1));
        //addObject(plr, getWidth() / 2, getHeight() / 2);
    }
    
    void genGrass() {
        //left
        for (int i = 0; i < 29; i++) {
            Grass g = new Grass();
            if ((int)(i*5+i*i*0.5*(turning?1:0)*dist) > 1) {
                addObject(g, (int)(i*5+i*i*0.5*(turning?1:0)*dist), getHeight() - i*5);
            }
        }
        for (int i = 0; i < 29; i++) {
            Grass g = new Grass();
            if ((int)(i*5+i*i*0.5*(turning?1:0)*dist) > 1) {
                addObject(g, (int)(i*5+i*i*0.5*(turning?1:0)*dist)-150, getHeight() - i*5);
            }
        }
        if (turning==true) { 
            for (int i = 0; i < 29; i++) {
                Grass g = new Grass();
                addObject(g, (int)(i*5+i*i*0.5*(turning?1:0)*dist)-300, getHeight() - i*5);
            }
            for (int i = 0; i < 29; i++) {
                Grass g = new Grass();
                addObject(g, (int)(i*5+i*i*0.5*(turning?1:0)*dist)-450, getHeight() - i*5);
            }
        }
        
        //right
        for (int i = 0; i < 29; i++) {
            Grass g = new Grass();
            if ((int)(getWidth()-i*5+i*i*0.5*(turning?1:0)*dist) < getWidth()-1) {
                addObject(g, (int)(getWidth()-i*5+i*i*0.5*(turning?1:0)*dist), getHeight()-i*5);
            }
        }
        for (int i = 0; i < 29; i++) {
            Grass g = new Grass();
            if ((int)(getWidth()-i*5+i*i*0.5*(turning?1:0)*dist) < getWidth()-1) {
                addObject(g, (int)(getWidth()-i*5+i*i*0.5*(turning?1:0)*dist)+150, getHeight()-i*5);
            }
        }
        if (turning==true) { 
            for (int i = 0; i < 29; i++) {
            Grass g = new Grass();
                addObject(g, (int)(getWidth()-i*5+i*i*0.5*(turning?1:0)*dist)+300, getHeight()-i*5);
            }
            for (int i = 0; i < 29; i++) {
                Grass g = new Grass();
                    addObject(g, (int)(getWidth()-i*5+i*i*0.5*(turning?1:0)*dist)+450, getHeight()-i*5);
            }
        }
    }
}



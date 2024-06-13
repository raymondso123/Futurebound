import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class opponentTrigger extends Actor {
    public void act() {
        followOpponent();
    }

    private void followOpponent() {
        // Get a list of all Opponent objects in the world
        java.util.List<Opponent> opponents = getWorld().getObjects(Opponent.class);

        // If there is at least one Opponent in the list
        if (!opponents.isEmpty()) {
            // Get the first Opponent (assuming there's only one Opponent in this case)
            Opponent opponent = opponents.get(0);

            // Set the x position of opponentTrigger to match the x position of the Opponent
            setLocation(opponent.getX(), getY());
        }
    }
}

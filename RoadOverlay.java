import greenfoot.*;

/**
 * RoadOverlay class - represents a road overlay that dynamically scales and moves vertically.
 */
public class RoadOverlay extends Actor {

    private GreenfootImage image;
    private boolean hitEdge = false;
    private SimpleTimer timer = new SimpleTimer();

    /**
     * Constructor initializes the road overlay with its image and initial scaling.
     */
    public RoadOverlay() {
        image = new GreenfootImage("Road.png");
        image.scale(5, 5); // Initial scaling of the overlay
        setImage(image);
        timer.mark(); // Start the timer
    }

    /**
     * Act method called by Greenfoot. Handles the behavior of the road overlay.
     */
    public void act() {
        // Check if the image height is greater than 1 (to prevent unnecessary processing)
        if (image.getHeight() > 1) {
            // Control the scaling and movement based on elapsed time and game speed
            if (timer.millisElapsed() > 5 - MyWorld.speed) {
                timer.mark(); // Reset the timer

                if (!hitEdge) {
                    // Move the overlay downwards and scale up if not at the edge
                    setLocation(getX(), getY() + MyWorld.speed * 2);
                    if (image.getHeight() < 2) {
                        image.scale(image.getWidth(), image.getHeight() + (int) (MyWorld.speed * 1.5));
                    }

                    // Check if the overlay has reached the edge of the world
                    if (isAtEdge()) {
                        hitEdge = true;
                    }
                } else {
                    // Shrink the overlay and reset position if it has reached the edge
                    if (image.getHeight() > MyWorld.speed * 2) {
                        image.scale(image.getWidth(), image.getHeight() - MyWorld.speed * 1);
                    } else {
                        hitEdge = false;
                        setLocation(getWorld().getWidth() / 2, 200); // Reset position
                        image.scale(getWorld().getWidth(), 2); // Reset scaling

                        timer.mark(); // Restart the timer
                    }
                }
            }
        }
    }
}

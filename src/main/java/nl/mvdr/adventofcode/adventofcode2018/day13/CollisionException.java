package nl.mvdr.adventofcode.adventofcode2018.day13;

/**
 * Exception indicating that a collision occurred when attempting to perform a tick.
 *
 * @author Martijn van de Rijdt
 */
@SuppressWarnings("serial") // this non-public class is not intended for serialization
class CollisionException extends Exception {
    
    private final int x;
    private final int y;
    
    /**
     * Constructor.
     * 
     * @param x x coordinate of the collision
     * @param y y coordinate of the collision
     */
    CollisionException(int x, int y) {
        super("Collision occurred at " + x + ", " + y);
        
        this.x = x;
        this.y = y;
    }
    
    int getX() {
        return x;
    }
    
    int getY() {
        return y;
    }
}

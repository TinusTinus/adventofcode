package nl.mvdr.adventofcode.adventofcode2022.day22;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * A position on the map.
 *
 * @author Martijn van de Rijdt
 */
record Position(Point location, Direction facing) {
    
    /**
     * Convenience costructor.
     * 
     * @param x location's x coordinate
     * @param y location's y coordinate
     * @param facing the direction we're currently facing
     */
    public Position(int x, int y, Direction facing) {
        this(new Point(x, y), facing);
    }
    
    /**
     * Computes the password.
     * 
     * The final password is the sum of 1000 times the row, 4 times the column, and the facing.
     * 
     * @return password
     */
    int computePassword() {
        return 1_000 * location.y()
                + 4 * location.x()
                + facing.getPasswordValue();
    }
}

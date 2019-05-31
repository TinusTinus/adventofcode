package nl.mvdr.adventofcode.adventofcode2018.day10;

import java.util.Objects;

import javax.annotation.processing.Generated;

/**
 * A point in two dimensions.
 *
 * @author Martijn van de Rijdt
 */
class Point {

    private final int x;

    private final int y;

    /**
     * Constructor.
     * 
     * @param x x coordinate (horizontal)
     * @param y y coordinate (vertical)
     */
    Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /** @return x coordinate (horizontal) */
    int getX() {
        return x;
    }
    
    /** @return y coordinate (vertical) */
    int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    @Override
    @Generated("Eclipse")
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    @Generated("Eclipse")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        return x == other.x && y == other.y;
    }
}

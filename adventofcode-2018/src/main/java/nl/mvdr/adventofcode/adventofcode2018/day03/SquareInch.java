package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.util.Objects;

import javax.annotation.processing.Generated;

/**
 * A square inch of fabric, identified by its x and y coordinates.
 *
 * @author Martijn van de Rijdt
 */
class SquareInch {
    private final int x;
    private final int y;
    
    /**
     * Constructor.
     * 
     * @param x x coordinate
     * @param y y coordinate
     */
    SquareInch(int x, int y) {
        super();
        
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(Integer.valueOf(x), Integer.valueOf(y));
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
        SquareInch other = (SquareInch) obj;
        return x == other.x && y == other.y;
    }
}

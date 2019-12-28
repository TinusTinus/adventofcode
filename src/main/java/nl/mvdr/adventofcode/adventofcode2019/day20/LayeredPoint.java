package nl.mvdr.adventofcode.adventofcode2019.day20;

import java.util.Objects;

import javax.annotation.processing.Generated;

import nl.mvdr.adventofcode.point.Point;

/**
 * A point in the maze at a given layer in recursive space.
 *
 * @author Martijn van de Rijdt
 */
class LayeredPoint {
    
    private final Point point;
    private final int layer;

    /**
     * Constructor.
     * 
     * @param point
     * @param layer
     */
    LayeredPoint(Point point, int layer) {
        super();
        this.point = point;
        this.layer = layer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Integer.valueOf(layer), point);
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
        LayeredPoint other = (LayeredPoint) obj;
        return layer == other.layer && Objects.equals(point, other.point);
    }
    
    
}

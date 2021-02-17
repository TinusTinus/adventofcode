package nl.mvdr.adventofcode.adventofcode2017.day11;

import java.util.Objects;

import javax.annotation.processing.Generated;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of a hexagon.
 *
 * @author Martijn van de Rijdt
 */
public class Hexagon {
    /** A point which uniquely identifies this hexagon. */
    private final Point point;
    
    /** Starting point. */
    static final Hexagon ORIGIN = new Hexagon(Point.ORIGIN);
    
    /**
     * Constructor.
     * 
     * @param point point
     */
    private Hexagon(Point point) {
        super();
        this.point = point;
    }
    
    /**
     * Takes one step in the given direction from the current hexagon.
     * 
     * @param direction direction
     * @return new hexagon
     */
    Hexagon move(HexagonalDirection direction) {
        Point newLocation;
        if (direction == HexagonalDirection.NORTHEAST) {
            newLocation = point.northNeighbour().eastNeighbour();
        } else if (direction == HexagonalDirection.SOUTHEAST) {
            newLocation = point.southNeighbour().eastNeighbour();
        } else if (direction == HexagonalDirection.SOUTHWEST) {
            newLocation = point.southNeighbour().westNeighbour();
        } else if (direction == HexagonalDirection.NORTHWEST) {
            newLocation = point.northNeighbour().westNeighbour();
        } else if (direction == HexagonalDirection.NORTH) {
            // North twice, to maintain manhattanDistance = 2 for each step
            newLocation = point.northNeighbour().northNeighbour();
        } else if (direction == HexagonalDirection.SOUTH) {
            // South twice, to maintain manhattanDistance = 2 for each step
            newLocation = point.southNeighbour().southNeighbour();
        } else {
            throw new IllegalArgumentException("Unexpected direction: " + direction);
        }
        
        return new Hexagon(newLocation);
    }
    
    /** @return minimum number of steps to get from origin to this hexagon */
    int distanceToOrigin() {
        return this.point.manhattanDistance(ORIGIN.point) / 2;
    }

    @Override
    @Generated("Eclipse")
    public int hashCode() {
        return Objects.hash(point);
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
        Hexagon other = (Hexagon) obj;
        return Objects.equals(point, other.point);
    }
}

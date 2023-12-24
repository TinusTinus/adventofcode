package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.Objects;
import java.util.Optional;

/**
 * A line between two points.
 *
 * @author Martijn van de Rijdt
 */
record Line(BigPoint firstPoint, BigPoint secondPoint) {
    
    Line(BigPoint firstPoint, BigPoint secondPoint) {
        this.firstPoint = Objects.requireNonNull(firstPoint);
        if (firstPoint.equals(secondPoint)) {
            throw new IllegalArgumentException("Points must be different to form a line: " + firstPoint + ", " + secondPoint);
        }
        this.secondPoint = Objects.requireNonNull(secondPoint);
    }
    
    /**
     * Given another line, determines where they intersect.
     * 
     * @param other other line
     * @return intersection; empty if they are equal or parallel
     */
    Optional<BigPoint> findPathIntersection(Line other) {
        return null; // TODO implement
    }
}

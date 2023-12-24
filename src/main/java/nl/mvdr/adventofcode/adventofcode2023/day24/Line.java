package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.math.BigDecimal;
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
        Optional<BigPoint> result;
        
        var x1 = firstPoint.x();
        var x2 = secondPoint.x();
        var x3 = other.firstPoint.x();
        var x4 = other.secondPoint.x();
        
        var y1 = firstPoint.y();
        var y2 = secondPoint.y();
        var y3 = other.firstPoint.y();
        var y4 = other.secondPoint.y();
        
        var divisorLhs = x1.subtract(x2).multiply(y3.subtract(y4));
        var divisorRhs = y1.subtract(y2).multiply(x3.subtract(x4));
        var divisor = divisorLhs.subtract(divisorRhs);
        
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            result = Optional.empty();
        } else {
            result = null; // TODO
        }
        return result;
    }
}

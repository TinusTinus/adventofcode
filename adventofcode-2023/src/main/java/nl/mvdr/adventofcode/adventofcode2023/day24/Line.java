package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

/**
 * A line between two two-dimensional points.
 *
 * @author Martijn van de Rijdt
 */
record Line(BigPoint2D firstPoint, BigPoint2D secondPoint) {
    
    Line(BigPoint2D firstPoint, BigPoint2D secondPoint) {
        this.firstPoint = Objects.requireNonNull(firstPoint);
        if (firstPoint.equals(secondPoint)) {
            throw new IllegalArgumentException("Points must be different to form a line: " + firstPoint + ", " + secondPoint);
        }
        this.secondPoint = Objects.requireNonNull(secondPoint);
    }
    
    /**
     * Given another line, determines where they intersect.
     * 
     * See also the explanation on <a href=
     * "https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection">Wikipedia</a>.
     * 
     * @param other other line
     * @return intersection; empty if they are equal or parallel
     */
    Optional<BigPoint2D> findPathIntersection(Line other) {
        Optional<BigPoint2D> result;
        
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
            // Lines are parallel. They have no intersection.
            // (Technically, the two lines could be equal, in which case they would intersect everywhere.)
            result = Optional.empty();
        } else {
            var numeratorXLhs = x1.multiply(y2).subtract(y1.multiply(x2)).multiply(x3.subtract(x4));
            var numeratorXRhs = x1.subtract(x2).multiply(x3.multiply(y4).subtract(y3.multiply(x4)));
            var numeratorX = numeratorXLhs.subtract(numeratorXRhs);
            var intersectionX = numeratorX.divide(divisor, BigPoint2D.SCALE, RoundingMode.HALF_UP);
            
            var numeratorYLhs = x1.multiply(y2).subtract(y1.multiply(x2)).multiply(y3.subtract(y4));
            var numeratorYRhs = y1.subtract(y2).multiply(x3.multiply(y4).subtract(y3.multiply(x4)));
            var numeratorY = numeratorYLhs.subtract(numeratorYRhs);
            var intersectionY = numeratorY.divide(divisor, BigPoint2D.SCALE, RoundingMode.HALF_UP);
            
            result = Optional.of(new BigPoint2D(intersectionX, intersectionY));
        }
        return result;
    }
}

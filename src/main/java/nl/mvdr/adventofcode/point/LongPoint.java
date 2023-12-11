package nl.mvdr.adventofcode.point;

/**
 * A point in two dimensions.
 * 
 * The difference with {@link Point} is that each coordinate is represented by a {@code long},
 * rather than an {@code int}.
 *
 * @author Martijn van de Rijdt
 */
public record LongPoint(long x, long y) {
    
    /**
     * Computes the <a href="https://en.wikipedia.org/wiki/Taxicab_geometry">Manhattan distance</a> between this point and another.
     * 
     * @param other other point
     * @return Manhattan distance
     */
    public long manhattanDistance(LongPoint other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }
    
    @Override
    public String toString() {
        return x + "," + y;
    }
}

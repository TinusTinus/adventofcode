package nl.mvdr.adventofcode.adventofcode2023.day14;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the large metal platform holding a bunch of rocks.
 *
 * @author Martijn van de Rijdt
 */
record Platform(Set<Point> roundedRocks, Set<Point> cubeRocks, int width, int height) {
    
    /**
     * Parses a string representation of a platform.
     * 
     * @param lines puzzle input
     * @return platform
     */
    static Platform parse(List<String> lines) {
        var height = lines.size();
        var width = lines.getFirst().length();
        
        Set<Point> roundedRocks = new HashSet<>();
        Set<Point> cubeRocks = new HashSet<>();
        for (var y = 0; y != height; y++) {
            var line = lines.get(y);
            for (var x = 0; x != width; x++) {
                var c = line.charAt(x);
                if (c == 'O') {
                    roundedRocks.add(new Point(x, y));
                } else if (c == '#') {
                    cubeRocks.add(new Point(x, y));
                } else if (c != '.') {
                    throw new IllegalArgumentException("Unexpected character found in input: " + c);
                }
            }
        }
        
        return new Platform(roundedRocks, cubeRocks, width, height);
    }
    
    /**
     * Tilts the platform so that all rounded rocks shift north as far as they will go.
     * 
     * @return updated platform
     */
    Platform tiltNorthAllTheWay() {
        var previousPlatform = this;
        var result = previousPlatform.tiltNorth();
        while (!previousPlatform.equals(result)) {
            previousPlatform = result;
            result = previousPlatform.tiltNorth();
        }
        return result;
    }
    
    /**
     * Briefly tilts the platform, so that all rounded rocks shift north one step
     * (unless blocked by a different rock or the northern edge).
     * 
     * @return updated platform
     */
    private Platform tiltNorth() {
        return tilt(Direction.UP);
    }
    
    /**
     * Briefly tilts the platform, so that all rounded rocks shift one step in the given direction
     * (unless blocked by a different rock or the northern edge).
     * 
     * @return updated platform
     */
    private Platform tilt(Direction direction) {
        Set<Point> newRoundedRocks = new HashSet<>();
        
        // Determine in which order to consider rocks.
        Comparator<Point> comparator =
                switch(direction) {
                    case UP -> Comparator.comparing(Point::y).thenComparing(Point::x);
                    case LEFT -> Comparator.comparing(Point::x).thenComparing(Point::y);
                    case DOWN -> Comparator.comparing(Point::y).reversed().thenComparing(Point::x);
                    case RIGHT -> Comparator.comparing(Point::x).reversed().thenComparing(Point::y);
                    case DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT -> throw new IllegalArgumentException("Unsupported diagonal direction: " + direction);
                    default -> throw new IllegalArgumentException("Unexpected direction: " + direction);
                };
        roundedRocks.stream()
                .sorted(comparator)
                .map(roundedRock -> moveIfPossible(roundedRock, direction, newRoundedRocks))
                .forEach(newRoundedRocks::add);
        return new Platform(newRoundedRocks, cubeRocks, width, height);
    }

    /**
     * Moves a rounded rock one position in the given direction, if possible.
     * 
     * @param roundedRock current position of the rounded rock
     * @param newRoundedRocks new position of other rounded rocks
     *      (note: these must include all rounded rocks which started further in the given direction that this one!)
     * @return new position of the given rock
     */
    private Point moveIfPossible(Point roundedRock, Direction direction, Set<Point> newRoundedRocks) {
        Point newPosition;
        var candidatePosition = direction.move(roundedRock);
        if (candidatePosition.x() < 0 // blocked by west edge?
                || width <= candidatePosition.x() // blocked by east edge?
                || candidatePosition.y() < 0 // blocked by north edge?
                || height <= candidatePosition.y() // blocked by south edge? 
                || cubeRocks.contains(candidatePosition) // blocked by cube rock?
                || newRoundedRocks.contains(candidatePosition)) { // blocked by another rounded rock?
            newPosition = roundedRock;
        } else {
            newPosition = candidatePosition;
        }
        return newPosition;
    }
    
    /**
     * Computes the load on the north support beam,
     * assuming that this platform has already been tilted so that all rocks roll north.
     * 
     * @return load on the north support beam
     * @see #tiltNorthAllTheWay()
     */
    int computeLoad() {
        return roundedRocks.stream()
                .mapToInt(Point::y)
                .map(y -> height - y)
                .sum();
    }
}

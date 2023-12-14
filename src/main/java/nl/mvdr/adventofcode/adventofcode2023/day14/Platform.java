package nl.mvdr.adventofcode.adventofcode2023.day14;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the large metal platform holding a bunch of rocks.
 *
 * @author Martijn van de Rijdt
 */
record Platform(Set<Point> roundedRocks, Set<Point> cubeRocks, int width, int height) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Platform.class);
    
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
        return tiltAllTheWay(Direction.UP);
    }
    
    /**
     * Tilts the platform so that all rounded rocks shift in the given direction as far as they will go.
     * 
     * @param direction the direction to tilt in (where north = UP, etc.)
     * @return updated platform
     */
    private Platform tiltAllTheWay(Direction direction) {
        var previousPlatform = this;
        var result = previousPlatform.tilt(direction);
        while (!previousPlatform.equals(result)) {
            previousPlatform = result;
            result = previousPlatform.tilt(direction);
        }
        return result;
    }
    
    /**
     * Briefly tilts the platform, so that all rounded rocks shift one step in the given direction
     * (unless blocked by a different rock or the northern edge).
     * 
     * @param direction the direction to tilt in (where north = UP, etc.)
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
     * @param direction the direction to tilt in (where north = UP, etc.)
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
     * Performs the given number of cycles.
     * 
     * @param cycles number of cycles
     * @return updated platform
     */
    Platform performCycles(int cycles) {
        // Keep a map of platform state -> number of cycles.
        // This way we can keep track of which states of the platform we've already encountered.
        // This will let us detect repeating patterns.
        Map<Platform, Integer> previousStates = new HashMap<>();
        
        var result = this;
        var i = 0;
        while(!previousStates.containsKey(result)) {
            previousStates.put(result, Integer.valueOf(i));
            result = result.performCycle();
            i++;
        }
        
        LOGGER.debug("Repeating pattern detected. Platform after {} cycles is equal to the one after {} cycles.",
                previousStates.get(result), Integer.valueOf(i));
        
        var firstOccurrence = previousStates.get(result).intValue();
        var repeatingPatternLength = i - firstOccurrence;
        
        var remainingCycles = cycles - i;
        
        // TODO previousStates.get(firstOccurrence + remainingCycles % repeatingPatternLength); // TODO no
        return result; // TODO fix
    }
    
    /**
     * Performs a single cycle.
     * 
     * @return updated platform
     */
    private Platform performCycle() {
        var result = this;
        result = result.tiltAllTheWay(Direction.UP); // Tilt north
        result = result.tiltAllTheWay(Direction.LEFT); // Tilt west
        result = result.tiltAllTheWay(Direction.DOWN); // Tilt south
        result = result.tiltAllTheWay(Direction.RIGHT); // Tilt east
        return result;
    }
    
    /**
     * Computes the load on the north support beam.
     * 
     * @return load on the north support beam
     */
    int computeLoad() {
        return roundedRocks.stream()
                .mapToInt(Point::y)
                .map(y -> height - y)
                .sum();
    }
}

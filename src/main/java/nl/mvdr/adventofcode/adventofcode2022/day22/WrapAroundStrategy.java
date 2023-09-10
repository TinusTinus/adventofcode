package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.Comparator;
import java.util.Map;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Rules for wrapping around the edges of the map.
 *
 * @author Martijn van de Rijdt
 */
enum WrapAroundStrategy {
    
    /**
     * The rules from part 1 of the puzzle.
     * 
     * Wrapping around works the way it does in Pac-man:
     * leaving the map on the right moves you to the far-left, etc..
     */
    MODULO {
        @Override
        Position wrapAround(Position startingPosition, Map<Point, Terrain> map) {
            var startingLocation = startingPosition.location();
            var direction = startingPosition.facing();
            Point newLocation;
            if (direction == Direction.RIGHT) {
                newLocation = map.keySet()
                        .stream()
                        .filter(point -> point.y() == startingLocation.y())
                        .min(Comparator.comparing(Point::x))
                        .orElseThrow();
            } else if (direction == Direction.LEFT) {
                newLocation = map.keySet()
                        .stream()
                        .filter(point -> point.y() == startingLocation.y())
                        .max(Comparator.comparing(Point::x))
                        .orElseThrow();
            } else if (direction == Direction.DOWN) {
                newLocation = map.keySet()
                        .stream()
                        .filter(point -> point.x() == startingLocation.x())
                        .min(Comparator.comparing(Point::y))
                        .orElseThrow();
            } else if (direction == Direction.UP) {
                newLocation = map.keySet()
                        .stream()
                        .filter(point -> point.x() == startingLocation.x())
                        .max(Comparator.comparing(Point::y))
                        .orElseThrow();
            } else {
                throw new IllegalArgumentException("Unexpected direction: " + direction);
            }
            return new Position(newLocation, direction);
        }
    },
    
    /**
     * The rules from part 2 of the puzzle.
     * 
     * The map represents the sides of a cube.
     */
    CUBE {
        @Override
        Position wrapAround(Position startingPosition, Map<Point, Terrain> map) {
            // TODO implement!
            return startingPosition;
        }
    };
    
    /**
     * Finds the next tile, taking into account that we need to wrap around the edges of the map.
     * 
     * @param startingPosition starting position
     * @param direction direction
     * @param map the map
     * @return the next location when moving 1 position in the given direction
     */
    Position findNextLocation(Position startingPosition, Map<Point, Terrain> map) {
        Position result;
        var newLocation = startingPosition.facing().move(startingPosition.location());
        if (map.containsKey(newLocation)) {
            result = new Position(newLocation, startingPosition.facing());
        } else {
            result = wrapAround(startingPosition, map);
        }
        return result;
    }

    /**
     * Wraps around the map.
     * 
     * @param startingPosition starting position at the edge of the map
     * @param map map
     * @return the next position when moving 1 position in the given direction
     */
    abstract Position wrapAround(Position startingPosition, Map<Point, Terrain> map);
}

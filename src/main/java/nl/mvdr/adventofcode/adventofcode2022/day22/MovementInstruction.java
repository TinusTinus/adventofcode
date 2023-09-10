package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.Comparator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Indicates the number of tiles to move in the direction you are facing.
 * 
 * If you run into a wall, you stop moving forward and continue with the next
 * instruction.
 *
 * @param tiles the number of tiles to move
 * @author Martijn van de Rijdt
 */
record MovementInstruction(int tiles) implements Instruction {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MovementInstruction.class);
    
    /**
     * Parses a string representation of a movement instruction.
     * 
     * @param stringRepresentation string representation of the number of tiles to move, for example: "10"
     * @return movement instruction
     */
    static MovementInstruction parse(String stringRepresentation) {
        var tiles = Integer.parseInt(stringRepresentation);
        return new MovementInstruction(tiles);
    }

    /**
     * Finds the next tile, taking into account that we need to wrap around the edges of the map.
     * 
     * @param startingPosition starting position
     * @param direction direction
     * @param map the map
     * @return the next location when moving 1 position in the given direction
     */
    private static Position findNextLocation(Position startingPosition, Map<Point, Terrain> map) {
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
    private static Position wrapAround(Position startingPosition, Map<Point, Terrain> map) {
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
    
    @Override
    public Position execute(Position startingPosition, Map<Point, Terrain> map) {
        LOGGER.debug("Attempting to move {} tiles, starting position: {}.", Integer.valueOf(tiles), startingPosition);
        
        var newPosition = startingPosition;
        var remainingTiles = tiles;
        while (0 < remainingTiles) {
            var nextPosition = findNextLocation(newPosition, map);
            var terrain = map.get(nextPosition.location());
            if (terrain == Terrain.OPEN_TILE) {
                newPosition = nextPosition;
                remainingTiles--;
            } else if (terrain == Terrain.SOLID_WALL) {
                LOGGER.debug("Hit a wall, unable to move the remaining {} tiles.", Integer.valueOf(remainingTiles));
                remainingTiles = 0;
            } else {
                throw new IllegalStateException("Unexpected terrain: " + terrain);
            }
        }
        LOGGER.debug("New position: {}.", newPosition);
        
        return newPosition;
    }
}

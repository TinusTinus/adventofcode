package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.Comparator;
import java.util.Map;

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
     * @param startingLocation starting location
     * @param direction direction
     * @param map the map
     * @return the next tile
     */
    private static Point findNextLocation(Point startingLocation, Direction direction, Map<Point, Terrain> map) {
        var result = direction.move(startingLocation);
        if (!map.containsKey(result)) {
            // Wrap around
            if (direction == Direction.RIGHT) {
                result = map.keySet()
                        .stream()
                        .filter(point -> point.x() == startingLocation.x())
                        .min(Comparator.comparing(Point::x))
                        .orElseThrow();
            } else if (direction == Direction.LEFT) {
                result = map.keySet()
                        .stream()
                        .filter(point -> point.x() == startingLocation.x())
                        .max(Comparator.comparing(Point::x))
                        .orElseThrow();
            } else if (direction == Direction.DOWN) {
                result = map.keySet()
                        .stream()
                        .filter(point -> point.y() == startingLocation.y())
                        .min(Comparator.comparing(Point::x))
                        .orElseThrow();
            } else if (direction == Direction.UP) {
                result = map.keySet()
                        .stream()
                        .filter(point -> point.y() == startingLocation.y())
                        .max(Comparator.comparing(Point::x))
                        .orElseThrow();
            } else {
                throw new IllegalArgumentException("Unexpected direction: " + direction);
            }
        }
        return result;
    }
    
    @Override
    public Position execute(Position startingPosition, Map<Point, Terrain> map) {
        
        var facing = startingPosition.facing();
        
        var newLocation = startingPosition.location();
        var remainingTiles = tiles;
        while (0 < remainingTiles) {
            var nextLocation = findNextLocation(newLocation, facing, map);
            var terrain = map.get(nextLocation);
            if (terrain == Terrain.OPEN_TILE) {
                newLocation = nextLocation;
                remainingTiles--;
            } else if (terrain == Terrain.SOLID_WALL) {
                // hit a wall
                remainingTiles = 0;
            } else {
                throw new IllegalStateException("Unexpected terrain: " + terrain);
            }
        }
        
        return new Position(newLocation, facing);
    }
}

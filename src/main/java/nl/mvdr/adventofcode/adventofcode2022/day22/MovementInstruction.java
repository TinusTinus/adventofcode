package nl.mvdr.adventofcode.adventofcode2022.day22;

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
    
    @Override
    public Position execute(Position startingPosition, Map<Point, Terrain> map) {
        var remainingTiles = tiles;
        var facing = startingPosition.facing();
        var newLocation = startingPosition.location();
        
        while (0 < remainingTiles) {
            var nextLocation = findNextPosition(newLocation, facing, map);
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

    private Point findNextPosition(Point location, Direction facing, Map<Point, Terrain> map) {
        var result = facing.move(location);
        if (!map.containsKey(result)) {
            // TODO wrap around the map!
        }
        return result;
    }
}

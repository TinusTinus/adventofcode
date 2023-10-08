package nl.mvdr.adventofcode.adventofcode2022.day22;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Override
    public Position execute(Position startingPosition, TerrainMap map) {
        LOGGER.debug("Attempting to move {} tiles, starting position: {}.", Integer.valueOf(tiles), startingPosition);
        
        var newPosition = startingPosition;
        var remainingTiles = tiles;
        while (0 < remainingTiles) {
            var nextPosition = map.findNextLocation(newPosition);
            var terrain = map.map().get(nextPosition.location());
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

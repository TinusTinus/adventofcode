package nl.mvdr.adventofcode.adventofcode2022.day22;

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
    MODULO,
    
    /**
     * The rules from part 2 of the puzzle.
     * 
     * The map represents the sides of a cube.
     */
    CUBE;
}

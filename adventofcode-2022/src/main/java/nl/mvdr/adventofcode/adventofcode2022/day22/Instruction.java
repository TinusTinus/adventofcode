package nl.mvdr.adventofcode.adventofcode2022.day22;

/**
 * An instruction indicating how to move through the map.
 *
 * @author Martijn van de Rijdt
 */
interface Instruction {
    
    /**
     * Performs this instruction.
     * 
     * @param startingPosition starting position
     * @param map map
     * @return end position after executing the instruction
     */
    Position execute(Position startingPosition, TerrainMap map);
}

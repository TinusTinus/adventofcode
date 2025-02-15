package nl.mvdr.adventofcode.adventofcode2023.day17;

/**
 * A city block.
 *
 * @author Martijn van de Rijdt
 */
record Block(int heatLoss) {
    /**
     * Parses a single character from the puzzle input as a block.
     * 
     * @param character character representation of the heat loss in the block
     * @return block
     */
    static Block parse(char character) {
        var heatLoss = Integer.parseInt("" + character);
        return new Block(heatLoss);
    }
}

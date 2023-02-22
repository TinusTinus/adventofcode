package nl.mvdr.adventofcode.adventofcode2022.day02;

/**
 * Value from the second column in the puzzle input.
 *
 * @author Martijn van de Rijdt
 */
interface SecondColumnValue {
    
    /**
     * Determines the shape to play for a round of Rock Paper Scissors,
     * depending on this value from the puzzle input's second column.
     * 
     * @param opponentShape shape to be played by the opponent (from the puzzle input's first column)
     * @return shape to play
     */
    Shape determineOwnShape(Shape opponentShape);

}

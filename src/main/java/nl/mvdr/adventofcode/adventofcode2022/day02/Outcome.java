package nl.mvdr.adventofcode.adventofcode2022.day02;

import java.util.stream.Stream;

/**
 * Outcome of a round of Rock Paper Scissors.
 *
 * @author Martijn van de Rijdt
 */
enum Outcome {
    WIN(6, 'Z'),
    DRAW(3, 'Y'),
    LOSS(0, 'X');

    private final int score;
    private final char representation;

    /**
     * Parses puzzle input.
     * 
     * @param input value of the second column of the puzzle input
     * @return desired outcome
     */
    static Outcome parse(char input) {
        return Stream.of(values())
                .filter(shape -> shape.representation == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid outcome representation: " + input));
    }
    
    /**
     * Constructor.
     * 
     * @param score score for this outcome
     * @param representation the character representation of this outcome in the puzzle input
     */
    Outcome(int score, char representation) {
        this.score = score;
        this.representation = representation;
    }
    
    /**
     * @return score for this outcome
     */
    int getScore() {
        return score;
    }
}

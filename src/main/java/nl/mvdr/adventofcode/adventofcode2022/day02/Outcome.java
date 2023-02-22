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
     * Determines the outcome for the given two shapes.
     * 
     * @param ownShape our own shape
     * @param opponentShape our opponent's shape
     * @return outcome of the round
     */
    static Outcome of(Shape ownShape, Shape opponentShape) {
        Outcome outcome;
        if (opponentShape.beats(ownShape)) {
            outcome = Outcome.LOSS;
        } else if (ownShape == opponentShape) {
            outcome = Outcome.DRAW;
        } else if (ownShape.beats(opponentShape)) {
            outcome = Outcome.WIN;
        } else {
            throw new IllegalStateException("Unable to determine score for " + ownShape + " vs " + opponentShape);
        }
        return outcome;
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

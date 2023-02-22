package nl.mvdr.adventofcode.adventofcode2022.day02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A round of Rock Paper Scissors.
 *
 * @author Martijn van de Rijdt
 */
record Round(Shape opponentShape, Shape ownShape) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Round.class);
    
    /**
     * Parses a line of the puzzle input.
     * 
     * This is done according to the rules from part 1 of the puzzle.
     * 
     * @param input line from the puzzle input
     * @return round
     */
    static Round parse(String input) {
        Shape opponentShape = Shape.parseOpponentRepresentation(input.charAt(0));
        Shape ownShape = Shape.parseResponseRepresentation(input.charAt(2));
        return new Round(opponentShape, ownShape);
    }
    
    /**
     * @return score for this round
     */
    int score() {
        Outcome outcome;
        if (opponentShape.beats(ownShape)) {
            outcome = Outcome.LOSS;
        } else if (ownShape == opponentShape) {
            outcome = Outcome.DRAW;
        } else if (ownShape.beats(opponentShape)) {
            outcome = Outcome.WIN;
        } else {
            throw new IllegalStateException("Unable to determine score for round " + this);
        }
        
        int result = ownShape.getScore() + outcome.getScore();
        LOGGER.debug("{} outcome: {}, score: {}", this, outcome, Integer.valueOf(result));
        return result;
    }
}

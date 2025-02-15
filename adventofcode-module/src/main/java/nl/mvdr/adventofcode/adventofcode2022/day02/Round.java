package nl.mvdr.adventofcode.adventofcode2022.day02;

import java.util.function.Function;

/**
 * A round of Rock Paper Scissors.
 *
 * @author Martijn van de Rijdt
 */
record Round(Shape opponentShape, Shape ownShape) {
    
    /**
     * Parses a line of the puzzle input.
     * 
     * @param input line from the puzzle input
     * @param secondColumnParser parser for the second column of the puzzle input
     * @return round
     */
    static Round parse(String input, Function<String, SecondColumnValue> secondColumnParser) {
        var inputValues = input.split(" ");
        if (inputValues.length != 2) {
            throw new IllegalArgumentException("Unable to parse input: " + input);
        }
        
        var opponentShape = Shape.parse(inputValues[0]);
        
        var secondColumnValue = secondColumnParser.apply(inputValues[1]);
        var ownShape = secondColumnValue.determineOwnShape(opponentShape);
        
        return new Round(opponentShape, ownShape);
    }
    
    /**
     * @return score for this round
     */
    int score() {
        var outcome = Outcome.of(ownShape, opponentShape);
        return ownShape.getScore() + outcome.getScore();
    }
}

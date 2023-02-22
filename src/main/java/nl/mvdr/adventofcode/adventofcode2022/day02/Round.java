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
        String[] inputValues = input.split(" ");
        if (inputValues.length != 2) {
            throw new IllegalArgumentException("Unable to parse input: " + input);
        }
        
        Shape opponentShape = Shape.parse(inputValues[0]);
        
        SecondColumnValue secondColumnValue = secondColumnParser.apply(inputValues[1]);
        Shape ownShape = secondColumnValue.determineOwnShape(opponentShape);
        
        return new Round(opponentShape, ownShape);
    }
    
    /**
     * @return score for this round
     */
    int score() {
        Outcome outcome = Outcome.of(ownShape, opponentShape);
        return ownShape.getScore() + outcome.getScore();
    }
}

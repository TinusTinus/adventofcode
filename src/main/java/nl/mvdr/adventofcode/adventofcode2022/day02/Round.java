package nl.mvdr.adventofcode.adventofcode2022.day02;

/**
 * A round of Rock Paper Scissors.
 *
 * @author Martijn van de Rijdt
 */
record Round(Shape opponentShape, Shape ownShape) {
    
    /**
     * Parses a line of the puzzle input.
     * 
     * This is done according to the rules from part 1 of the puzzle:
     * the second column represents the shape to play.
     * 
     * @param input line from the puzzle input
     * @return round
     */
    static Round parseShapes(String input) {
        Shape opponentShape = Shape.parse(input.charAt(0));
        Shape ownShape = Shape.parseResponse(input.charAt(2));
        return new Round(opponentShape, ownShape);
    }
    
    /**
     * Parses a line of the puzzle input.
     * 
     * This is done according to the rules from part 2 of the puzzle:
     * the second column represents the desired outcome.
     * 
     * @param input line from the puzzle input
     * @return round
     */
    static Round parseShapeAndOutcome(String input) {
        Shape opponentShape = Shape.parse(input.charAt(0));
        Outcome outcome = Outcome.parse(input.charAt(2));
        Shape ownShape = switch(outcome) {
            case DRAW -> opponentShape;
            case WIN -> Shape.find(shape -> shape.beats(opponentShape));
            case LOSS -> Shape.find(shape -> opponentShape.beats(shape));
        };
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

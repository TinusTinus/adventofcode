package nl.mvdr.adventofcode.adventofcode2022.day02;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A play in a game of Rock Paper Scissors.
 *
 * @author Martijn van de Rijdt
 */
enum Shape {
    
    ROCK(1, 'A', 'X') {
        @Override
        boolean beats(Shape shape) {
            return shape == SCISSORS;
        }
    },
    PAPER(2, 'B', 'Y') {
      @Override
        boolean beats(Shape shape) {
            return shape == ROCK;
        }  
    },
    SCISSORS(3, 'C', 'Z') {
        @Override
        boolean beats(Shape shape) {
            return shape == PAPER;
        }
    };

    /**
     * Parses puzzle input.
     * 
     * @param input value of the first column of the puzzle input
     * @return shape played by the opponent
     */
    static Shape parse(char input) {
        return find(shape -> shape.representation == input);
    }
    
    /**
     * Parses puzzle input.
     * 
     * @param input value of the second column of the puzzle input
     * @return shape to be played, according to part 1 of the puzzle
     */
    static Shape parseResponse(char input) {
        return find(shape -> shape.responseRepresentation == input);
    }
    
    /**
     * Finds the shape that matches the given predicate.
     * 
     * @param predicate the predicate to search for; should match exactly one shape
     * @return shape
     */
    static Shape find(Predicate<Shape> predicate) {
        return Stream.of(values())
                .filter(predicate)
                .findFirst()
                .orElseThrow();
    }
    
    private final int score;
    private final char representation;
    private final char responseRepresentation;
    
    /**
     * Constructor.
     * 
     * @param score the score for this shape (yes, seriously)
     * @param representation textual representation of this shape
     * @param representation textual representation of this shape in the second column of the puzzle input, according to part 1
     */
    Shape(int score, char representation, char responseRepresentation) {
        this.score = score;
        this.representation = representation;
        this.responseRepresentation = responseRepresentation;
    }
    
    /**
     * @return the score for this shape (yes, seriously)
     */
    int getScore() {
        return score;
    }
    
    /**
     * Determines whether this shape beats the given shape.
     * 
     * @param shape other shape
     * @return whether this shape beats the given shape
     */
    abstract boolean beats(Shape shape);
}

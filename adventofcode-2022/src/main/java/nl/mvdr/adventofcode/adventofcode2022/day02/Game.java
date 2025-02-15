package nl.mvdr.adventofcode.adventofcode2022.day02;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of a game of rock, paper, scissors.
 *
 * @author Martijn van de Rijdt
 */
record Game(List<Round> rounds) {
    
    /**
     * Parses the puzzle input.
     * 
     * @param lines puzzle input
     * @param secondColumnParser parser for the second column of the puzzle input
     * @return game
     */
    static Game parse(Stream<String> lines, Function<String, SecondColumnValue> secondColumnParser) {
        var rounds = lines.map(line -> Round.parse(line, secondColumnParser))
                .collect(Collectors.toList());
        return new Game(rounds);
    }
    
    /**
     * @return score for this game
     */
    int score() {
        return rounds.stream()
                .mapToInt(Round::score)
                .sum();
    }
}

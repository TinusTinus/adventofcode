package nl.mvdr.adventofcode.adventofcode2022.day02;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of a game of rock, paper, scissors
 *
 * @author Martijn van de Rijdt
 */
record Game(List<Round> rounds) {
    
    /**
     * Parses the puzzle input.
     * 
     * This is done according to the rules from part 1 of the puzzle.
     * 
     * @param lines puzzle input
     * @return game
     */
    static Game parse(Stream<String> lines) {
        List<Round> rounds = lines.map(Round::parse).collect(Collectors.toList());
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

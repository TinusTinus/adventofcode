package nl.mvdr.adventofcode.adventofcode2023.day02;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;

/**
 * Representation of a single game.
 *
 * @author Martijn van de Rijdt
 */
record Game(int id, List<MultiSet<Color>> subsets) {
    /** Prefix of the textual representation of every game. */
    private static final String GAME_PREFIX = "Game ";

    /**
     * Parses the textual representation of a single game.
     * 
     * @param text textual representation of a game, for example: "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
     * @return the game represented by the given text
     */
    static Game parse(String text) {
        var parts = text.split(": ");
        if (parts.length != 2 || !parts[0].startsWith(GAME_PREFIX)) {
            throw new IllegalArgumentException("Invalid input: " + text);
        }
        var id = Integer.parseInt(parts[0].substring(GAME_PREFIX.length()));
        var subsets = parseSubsets(parts[1]);
        return new Game(id, subsets);
    }
    
    /**
     * Parses subsets.
     * 
     * @param text textual representation of a list of subsets, for example: "3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
     * @return subsets
     */
    private static List<MultiSet<Color>> parseSubsets(String text) {
        var parts = text.split("; ");
        return Stream.of(parts)
                .map(Game::parseSubset)
                .toList();
    }
    
    /**
     * Parses the textual representation of a single subset.
     * 
     * @param text textual representation of a subset, for example: "3 blue, 4 red"
     * @return subset
     */
    private static MultiSet<Color> parseSubset(String text) {
        MultiSet<Color> result = new HashMultiSet<>();
        var parts = text.split(", ");
        Stream.of(parts)
                .map(part -> part.split(" "))
                .forEach(part -> {
                    if (part.length != 2) {
                        throw new IllegalArgumentException("Unable to parse subset: " + text);
                    }
                    var count = Integer.parseInt(part[0]);
                    var color = Color.of(part[1]);
                    result.add(color, count);
                });
        return result;
    }
    
    /**
     * Checks whether the given subset is possible.
     * 
     * @param subset subset to check
     * @return whether the given subset is possible if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes
     */
    private static boolean isPossible(MultiSet<Color> subset) {
        return subset.getCount(Color.RED) <= 12
                && subset.getCount(Color.GREEN) <= 13
                && subset.getCount(Color.BLUE) <= 14;
    }
    
    /**
     * @return whether this game is possible if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes
     */
    boolean isPossible() {
        return subsets().stream()
                .allMatch(Game::isPossible);
    }
}

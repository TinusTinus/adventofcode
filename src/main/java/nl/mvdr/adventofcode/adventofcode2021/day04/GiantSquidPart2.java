package nl.mvdr.adventofcode.adventofcode2021.day04;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2021/day/4">Giant Squid</a>.
 *
 * @author Martijn van de Rijdt
 */
public class GiantSquidPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiantSquidPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        var game = BingoGame.parse(lines.toList());
        return game.calculateLastWinnerScore();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new GiantSquidPart2();

        var result = instance.solve("input-day04-2021.txt");

        LOGGER.info(result);
    }
}
 
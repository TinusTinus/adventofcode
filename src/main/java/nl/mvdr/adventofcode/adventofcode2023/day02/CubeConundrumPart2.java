package nl.mvdr.adventofcode.adventofcode2023.day02;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/02">Cube Conundrum</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CubeConundrumPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CubeConundrumPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        return lines.map(Game::parse)
                .mapToInt(Game::computeMinimumSetPower)
                .sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new CubeConundrumPart2();

        var result = instance.solve("input-day02-2023.txt");

        LOGGER.info(result);
    }
}
 
package nl.mvdr.adventofcode.adventofcode2022.day12;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/12">Hill Climbing Algorithm</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HillClimbingAlgorithmPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HillClimbingAlgorithmPart2.class);
    
    @Override
    public int solve(Stream<String> lines) {
        var heightMap = HeightMap.parse(lines.toList());
        return heightMap.computeShortestPathLengthFromAnyLowestPoint();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new HillClimbingAlgorithmPart2();

        var result = instance.solve("input-day12-2022.txt");

        LOGGER.info(result);
    }
}
 
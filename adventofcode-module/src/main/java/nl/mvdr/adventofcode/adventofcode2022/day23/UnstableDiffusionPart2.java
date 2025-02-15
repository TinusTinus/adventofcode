package nl.mvdr.adventofcode.adventofcode2022.day23;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/23">Unstable Diffusion</a>.
 *
 * @author Martijn van de Rijdt
 */
public class UnstableDiffusionPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnstableDiffusionPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        var grove = Grove.parse(lines.toList());
        LOGGER.debug("Elves: {}, Initial {}", grove.elves(), grove);
        grove = grove.simulate();
        return grove.rounds();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new UnstableDiffusionPart2();

        var result = instance.solve("input-day23-2022.txt");

        LOGGER.info(result);
    }
}
 
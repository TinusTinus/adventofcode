package nl.mvdr.adventofcode.adventofcode2022.day19;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/19">Not Enough Minerals</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NotEnoughMineralsPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotEnoughMineralsPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        var blueprints = lines.limit(3L)
                .map(Blueprint::parse)
                .collect(Collectors.toSet());
        
        return blueprints.parallelStream()
                .mapToInt(blueprint -> blueprint.computeMaxGeodes(32))
                .reduce(1, (i, j) -> i * j);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new NotEnoughMineralsPart2();

        var result = instance.solve("input-day19-2022.txt");

        LOGGER.info(result);
    }
}
 
package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/15">Beacon Exclusion Zone</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BeaconExclusionZonePart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeaconExclusionZonePart1.class);

    @Override
    public int solve(Stream<String> lines) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BeaconExclusionZonePart1();

        var result = instance.solve("input-day15-2022.txt");

        LOGGER.info(result);
    }
}
 
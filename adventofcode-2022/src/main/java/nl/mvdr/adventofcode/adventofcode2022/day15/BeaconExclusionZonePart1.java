package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.intrange.IntRange;
import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/15">Beacon Exclusion Zone</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BeaconExclusionZonePart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeaconExclusionZonePart1.class);

    private final int y;
    
    /**
     * Constructor.
     */
    public BeaconExclusionZonePart1() {
        this(2_000_000);
    }
    
    /**
     * Constructor.
     * 
     * @param y the y coordinate of the row to inspect
     */
    BeaconExclusionZonePart1(int y) {
        super();
        this.y = y;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var pairs = SensorBeaconPair.parse(lines);
        var ranges = pairs.stream()
                .map(pair -> pair.xCoordinatesWithoutBeacon(y))
                .flatMap(List::stream)
                .toList();
        ranges = IntRange.reduce(ranges);
        return ranges.stream()
                .mapToInt(IntRange::size)
                .sum();
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
 
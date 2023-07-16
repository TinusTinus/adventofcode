package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/15">Beacon Exclusion Zone</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BeaconExclusionZonePart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeaconExclusionZonePart2.class);

    private final int maxCoordinate;
    
    /**
     * Constructor.
     */
    public BeaconExclusionZonePart2() {
        this(4_000_000);
    }
    
    /**
     * Constructor.
     * 
     * @param maxCoordinate maximum coordinate value for both the x and y axis
     */
    BeaconExclusionZonePart2(int maxCoordinate) {
        super();
        this.maxCoordinate = maxCoordinate;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var pairs = SensorBeaconPair.parse(lines);
        Point source = findDistressSignalSource(pairs);
        return computeTuningFrequency(source);
    }

    /**
     * Determines the source of the distress signal.
     * 
     * @param pairs sensor / beacon pairs
     * @return source of the distress signal
     */
    private Point findDistressSignalSource(Set<SensorBeaconPair> pairs) {
        return pairs.stream()
                .sorted(Comparator.comparing(SensorBeaconPair::distance))
                .map(pair -> pair.sensor().pointsAtDistance(pair.distance() + 1, 0, maxCoordinate, 0, maxCoordinate))
                .flatMap(Set::stream)
//                .parallel()
                .filter(position -> pairs.stream().allMatch(pair -> pair.canContainSource(position)))
                .findAny()
                .orElseThrow();
    }
    
    /**
     * Computes the tuning frequency for the given beacon.
     * 
     * @param beacon beacon
     * @return tuning frequency
     */
    private static long computeTuningFrequency(Point beacon) {
        return (4_000_000L * beacon.x()) + beacon.y();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BeaconExclusionZonePart2();

        var result = instance.solve("input-day15-2022.txt");

        LOGGER.info(result);
    }
}
 
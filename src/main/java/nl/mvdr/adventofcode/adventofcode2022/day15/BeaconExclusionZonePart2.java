package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        Optional<Point> distressSignalSource = Optional.empty();
        int y = 0;
        while (y <= maxCoordinate && distressSignalSource.isEmpty()) {
            distressSignalSource = findDistressSignalSource(pairs, y);
            y++;
        }
        Point source = distressSignalSource.orElseThrow();
        return source;
    }
    
    /**
     * Determines the source of the distress signal on the given row.
     * 
     * @param pairs sensor / beacon pairs
     * @param y y coordinate of the row to inspect
     * @return source of the distress signal, if present on the given row
     */
    private Optional<Point> findDistressSignalSource(Set<SensorBeaconPair> pairs, int y) {
        Set<Integer> exclusions = pairs.stream()
                .map(pair -> pair.xCoordinatesInRange(y))
                .flatMap(IntStream::boxed)
                .collect(Collectors.toSet());
        return IntStream.range(0, maxCoordinate + 1)
                .filter(x -> !exclusions.contains(Integer.valueOf(x)))
                .boxed()
                .findFirst()
                .map(x -> new Point(x.intValue(), y));
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
 
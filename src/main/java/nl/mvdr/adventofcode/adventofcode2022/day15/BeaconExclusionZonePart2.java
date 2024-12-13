package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;
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
        LOGGER.debug("Source found: {}", source);
        return computeTuningFrequency(source);
    }

    /**
     * Determines the source of the distress signal.
     * 
     * @param pairs sensor / beacon pairs
     * @return source of the distress signal
     */
    private Point findDistressSignalSource(Set<SensorBeaconPair> pairs) {
        return IntStream.range(0, maxCoordinate + 1)
                .parallel()
                .mapToObj(y -> findDistressSignalSource(pairs, y))
                .filter(Optional::isPresent)
                .map(Optional::orElseThrow)
                .findAny()
                .orElseThrow();
    }
    
    /**
     * Determines the source of the distress signal on the given row.
     * 
     * @param pairs sensor / beacon pairs
     * @param y y coordinate of the row to inspect
     * @return source of the distress signal, if present on the given row
     */
    private Optional<Point> findDistressSignalSource(Set<SensorBeaconPair> pairs, int y) {
        var exclusions = pairs.stream()
                .map(pair -> pair.xCoordinatesInRange(y))
                .map(range -> new IntRange(Math.max(0, range.min()), Math.min(range.max(), maxCoordinate)))
                .filter(Predicate.not(IntRange::isEmpty))
                .sorted()
                .toList();
        LOGGER.debug("Exclusions for y = {}: {}", Integer.valueOf(y), exclusions);
        exclusions = IntRange.reduce(exclusions);
        LOGGER.debug("Merged exclusions for y = {}: {}", Integer.valueOf(y), exclusions);
        Optional<Point> result;
        if (exclusions.size() == 1) {
            result = Optional.empty();
        } else if (exclusions.size() == 2) {
            var x = exclusions.get(0).max() + 1;
            result = Optional.of(new Point(x, y));
        } else {
            throw new IllegalStateException(exclusions.toString());
        }
        return result;
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
 
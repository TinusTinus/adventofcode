package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

/**
 * A pair of a sensor and its (unique) closest beacon.
 *
 * @author Martijn van de Rijdt
 */
record SensorBeaconPair(Point sensor, Point beacon) {
    
    private static final String PREFIX = "Sensor at ";
    private static final String INFIX = ": closest beacon is at ";

    /**
     * Parses the textual representation of a list of sensor/beacon pairs.
     * 
     * @param lines puzzle input
     * @return the pair represented by the given input
     */
    static Set<SensorBeaconPair> parse(Stream<String> lines) {
        return lines.map(SensorBeaconPair::parse)
                    .collect(Collectors.toSet());
    }
    
    /**
     * Parses the textual representation of a sensor/beacon pair.
     * 
     * @param text string representation of the pair, for example: "Sensor at x=2, y=18: closest beacon is at x=-2, y=15"
     * @return the pair represented by the given string
     */
    private static SensorBeaconPair parse(String text) {
        var infixIndex = text.indexOf(INFIX);
        var sensorCoordinatesString = text.substring(PREFIX.length(), infixIndex);
        var sensor = parsePoint(sensorCoordinatesString);
        var beaconCoordinatesString = text.substring(infixIndex + INFIX.length(), text.length());
        var beacon = parsePoint(beaconCoordinatesString);
        return new SensorBeaconPair(sensor, beacon);
    }
    
    /**
     * Helper method to parse the textual representation of a point in two-dimensional space.
     * 
     * {@link Point#parse(String)} expects the textual representation of a point to be slightly different.
     * 
     * @param text string representation of a point, for example: "x=2, y=18"
     * @return point
     */
    private static Point parsePoint(String text) {
        var points = Point.parseRanges(Stream.of(text));
        if (points.size() != 1) {
            throw new IllegalArgumentException("Failed to parse: " + text);
        }
        return points.iterator().next();
    }

    /**
     * Determines the number of positions on the given row where there cannot be a beacon.
     * 
     * @param pairs sensor / beacon pairs
     * @param y y coordinate of the row to inspect
     * @return number of positions
     */
    static long positionsWithoutBeacon(Set<SensorBeaconPair> pairs, int y) {
        return pairs.stream()
                .flatMap(pair -> pair.positionsWithoutBeacon(y))
                .distinct()
                .count();
    }
    
    /**
     * Determines the positions on the given row where there cannot be a beacon.
     * 
     * @param y y coordinate of the row to search
     * @return the positions guaranteed not to contain a beacon, based on this pair
     */
    private Stream<Point> positionsWithoutBeacon(int y) {
        var distance = sensor.manhattanDistance(beacon);
        return sensor.pointsInRange(distance).stream()
                .filter(point -> point.y() == y)
                .filter(Predicate.not(beacon::equals));
    }
}

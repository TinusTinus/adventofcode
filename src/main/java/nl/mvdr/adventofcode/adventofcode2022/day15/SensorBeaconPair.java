package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
     * Note: {@link Point#parse(String)} expects the textual representation of a point to be slightly different.
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
     * Determines the x coordinates on the given row where there cannot be a beacon, purely based on this pair.
     * 
     * @param y y coordinate of the row to inspect
     * @return x coordinates
     */
    IntStream xCoordinatesWithoutBeacon(int y) {
        return xCoordinatesInRange(y)
                .filter(x -> !beacon.equals(new Point(x, y)));
    }
    
    /**
     * Determines the x coordinates on the given row which are in range of this sensor.
     * 
     * @param y y coordinate of the row to inspect
     * @return x coordinates
     */
    IntStream xCoordinatesInRange(int y) {
        var distance = sensor.manhattanDistance(beacon);
        var distanceToY = Math.abs(y - sensor.y());
        var remainingDistance = distance - distanceToY;
        return IntStream.range(sensor.x() - remainingDistance, sensor.x() + remainingDistance + 1);
    }
}

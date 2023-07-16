package nl.mvdr.adventofcode.adventofcode2022.day15;

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
     * Parses the textual representation of a sensor/beacon pair.
     * 
     * @param text string representation of the pair, for example: "Sensor at x=2, y=18: closest beacon is at x=-2, y=15"
     * @return the pair represented by the given string
     */
    static SensorBeaconPair parse(String text) {
        var infixIndex = text.indexOf(INFIX);
        var sensorCoordinatesString = text.substring(PREFIX.length(), infixIndex);
        var sensor = Point.parse(sensorCoordinatesString);
        var beaconCoordinatesString = text.substring(infixIndex + INFIX.length(), text.length());
        var beacon = Point.parse(beaconCoordinatesString);
        return new SensorBeaconPair(sensor, beacon);
    }
}

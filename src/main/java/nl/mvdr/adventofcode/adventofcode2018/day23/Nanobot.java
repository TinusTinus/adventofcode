package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A tiny robot.
 * 
 * @author Martijn van de Rijdt
 */
class Nanobot {
    
    private final Point3D position;
    private final int radius;

    /**
     * Parses a text file containing a set of nanobots.
     * 
     * @param inputFilePath path to the input text file
     * @return nanobots
     * @throws IOException in case the input text file could not be read
     */
    static Set<Nanobot> parse(Path inputFilePath) throws IOException {
        return Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .map(Nanobot::parse)
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a line of text representing a nanobot.
     * 
     * For example: "pos=<0,0,0>, r=4".
     * 
     * @param line line of text from the puzzle input
     * @return nanobot
     */
    private static Nanobot parse(String line) {
        Pattern pattern = Pattern.compile("pos=(<-?\\d+,-?\\d+,-?\\d+>), r=(\\d+)");

        Matcher matcher = pattern.matcher(line);
        matcher.matches();

        Nanobot result;

        if (matcher.matches()) {
            Point3D position = Point3D.parse(matcher.group(1));
            int radius = Integer.parseInt(matcher.group(2));
            result = new Nanobot(position, radius);
        } else {
            throw new IllegalArgumentException("Unable to parse line: " + line);
        }

        return result;
    }
    
    /**
     * Given a nonempty set of nanobots, returns the nanobot with the largest radius.
     * 
     * @param nanobots nanobots
     * @return strongest nanobot in the set
     */
    static Nanobot strongest(Set<Nanobot> nanobots) {
        return nanobots.stream()
                .max(Comparator.comparing(Nanobot::getRadius))
                .get();
    }
    
    /**
     * Constructor.
     * 
     * @param position location of the nanobot
     * @param radius radius of the nanobot
     */
    Nanobot(Point3D position, int radius) {
        super();
        this.position = position;
        this.radius = radius;
    }
    
    /**
     * Determines whether the given location is in range of this nanobot.
     * 
     * @param point location
     * @return whether the location is in range of this nanobot
     */
    boolean inRange(Point3D point) {
        return position.manhattanDistance(point) <= radius;
    }
    
    int getRadius() {
        return radius;
    }
    
    Point3D getPosition() {
        return position;
    }
    
    /**
     * Determines how many of the given nanobots are within this nanobot's range.
     * 
     * @param nanobots nanobots (may include this bot itself)
     * @return number of bots in range
     */
    long botsInRange(Set<Nanobot> nanobots) {
        return nanobots.stream()
                .map(Nanobot::getPosition)
                .filter(this::inRange)
                .count();
    }
    
    /** @return vertices of the octahedron represented by this nanobot */
    private Set<Point3D> vertices() {
        return getPosition().offsetOnAxes(getRadius());
    }
    
    /**
     * Calculates whether this nanobot's range overlaps with the given nanobot's.
     * 
     * That is, whether a point exists that is within both of these nanobots' ranges.
     *  
     * @param other other nanobot
     * @return whether ranges overlap
     */
    private boolean rangeOverlaps(Nanobot other) {
        // Check whether any of this octahedron's vertices are inside the other's radius,
        // or the other way around.
        // I'm pretty sure this is not correct for octahedrons in general,
        // but it should be for the ones occurring in this puzzle.
        return this.vertices().stream().anyMatch(other::inRange)
                || other.vertices().stream().anyMatch(this::inRange);
    }
    
    /**
     * Calculates the number of nanobots whose ranges overlap with this one.
     * 
     * @param nanobots nanobots
     * @return the number of nanobots whose ranges overlap with this one's
     */
    long overlap(Set<Nanobot> nanobots) {
        return nanobots.parallelStream()
                .filter(this::rangeOverlaps)
                .count();
    }
    
    @Override
    public String toString() {
        return "pos=" + position + ", r=" + radius;
    }
}

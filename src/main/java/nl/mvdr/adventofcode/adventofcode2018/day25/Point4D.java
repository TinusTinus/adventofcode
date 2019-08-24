package nl.mvdr.adventofcode.adventofcode2018.day25;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.Generated;

/**
 * A point in four dimensions (spacetime).
 * 
 * Not to be confused with {@link nl.mvdr.adventofcode.point.Point},
 * which represents a point in two dimensions.
 *
 * @author Martijn van de Rijdt
 */
class Point4D {

    private final int coordinate0;
    private final int coordinate1;
    private final int coordinate2;
    private final int coordinate3;

    /**
     * Parses a text file containing string representations of points.
     * 
     * @param path path to the text file 
     * @return set of points
     * @throws IOException if the input file cannot be read
     */
    static Set<Point4D> parse(Path path) throws IOException {
        return Files.lines(path)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .map(Point4D::parsePoint)
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a string containing a set of coordinates, for example: "1,3,1,-6".
     * 
     * @param text text to be parsed
     * @return point
     */
    private static Point4D parsePoint(String text) {
        String[] coordinateStrings = text.split(",");
        return new Point4D(
                Integer.parseInt(coordinateStrings[0]),
                Integer.parseInt(coordinateStrings[1]),
                Integer.parseInt(coordinateStrings[2]),
                Integer.parseInt(coordinateStrings[3]));
    }
    
    /**
     * Constructor.
     * 
     * @param coordinate0 first coordinate
     * @param coordinate1 second coordinate
     * @param coordinate2 third coordinate
     * @param coordinate3 dourth coordinate
     */
    private Point4D(int coordinate0, int coordinate1, int coordinate2, int coordinate3) {
        super();
        this.coordinate0 = coordinate0;
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
        this.coordinate3 = coordinate3;
    }

    /**
     * Computes the <a href="https://en.wikipedia.org/wiki/Taxicab_geometry"> Manhattan distance</a> between this point and another.
     * 
     * @param other other point
     * @return Manhattan distance
     */
    int manhattanDistance(Point4D other) {
        return Math.abs(this.coordinate0 - other.coordinate0)
                + Math.abs(this.coordinate1 - other.coordinate1)
                + Math.abs(this.coordinate2 - other.coordinate2)
                + Math.abs(this.coordinate3 - other.coordinate3);
    }
    
    /**
     * Determines whether this point is close enough to the given constellation to
     * be a part of it.
     * 
     * Two points are in the same constellation if their manhattan distance apart is
     * no more than 3 or if they can form a chain of points, each a manhattan
     * distance no more than 3 from the last, between the two of them. (That is, if
     * a point is close enough to a constellation, it "joins" that constellation.)
     * 
     * @param constellation constellation, represented as a (nonempty) set of points
     * @return whether this point is in range
     */
    boolean inRange(Set<Point4D> constellation) {
        return constellation.stream()
                .mapToInt(this::manhattanDistance)
                .anyMatch(i -> i <= 3);
    }
    
    @Override
    public String toString() {
        return coordinate0 + "," + coordinate1 + "," + coordinate2 + "," + coordinate3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Integer.valueOf(coordinate0), Integer.valueOf(coordinate1), Integer.valueOf(coordinate2), Integer.valueOf(coordinate3));
    }

    @Override
    @Generated("Eclipse")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point4D other = (Point4D) obj;
        return coordinate0 == other.coordinate0 && coordinate1 == other.coordinate1 && coordinate2 == other.coordinate2
                && coordinate3 == other.coordinate3;
    }
}

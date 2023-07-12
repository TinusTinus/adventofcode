package nl.mvdr.adventofcode.point;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of a path.
 *
 * @param points the points defining this path
 * @author Martijn van de Rijdt
 */
public record Path(List<Point> points) {
    
    /**
     * Parses puzzle input as a list of paths.
     * 
     * @param lines contents of the puzzle input
     * @return paths represented by the given input
     */
    public static List<Path> parse(Stream<String> lines) {
        return lines.map(Path::parse).toList();
    }
    
    /**
     * Parses a single line from the puzzle input as a path.
     * 
     * @param text textual representation of a path; for example: "498,4 -> 498,6 -> 496,6"
     * @return path represented by the given text
     */
    private static Path parse(String text) {
        var points = Stream.of(text.split(" -> "))
                .map(Point::parse)
                .toList();
        return new Path(points);
    }
    
    /**
     * @return all points covered by the lines in this path
     */
    public Set<Point> allPoints() {
        return lines().stream()
                .flatMap(line -> line.points().stream())
                .collect(Collectors.toSet());
    }
    
    /**
     * @return lines in this path
     */
    private List<Line> lines() {
        List<Line> result = new ArrayList<>();
        for (int i = 0; i != points.size() - 1; i++) {
            result.add(new Line(points.get(i), points.get(i + 1)));
        }
        return result;
    }
}

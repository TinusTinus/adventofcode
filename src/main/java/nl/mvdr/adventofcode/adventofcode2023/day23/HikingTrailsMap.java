package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.mvdr.adventofcode.point.Point;

/**
 * Map of hiking trails.
 *
 * @author Martijn van de Rijdt
 */
public record HikingTrailsMap(Map<Point, Terrain> map, Point start, Point goal) {

    /**
     * Parses a hiking trails map.
     * 
     * @param lines puzzle input
     * @return map
     */
    static HikingTrailsMap parse(List<String> lines) {
        var map = Point.parse2DMap(lines, Terrain::of);
        var start = findOnlyPoint(map.keySet(), 0);
        var goal = findOnlyPoint(map.keySet(), lines.size() - 1);
        return new HikingTrailsMap(map, start, goal);
    }

    /**
     * Finds the only point in the given set with the given y coordinate. 
     * 
     * @param points points
     * @param y y coordinate
     * @return the point
     */
    private static Point findOnlyPoint(Set<Point> points, int y) {
        return points.stream()
                .filter(point -> point.y() == y)
                .reduce((point0, point1) -> {
                    throw new IllegalArgumentException(
                            "Multiple points found with y = " + y + ": " + point0 + ", " + point1);
                })
                .orElseThrow(() -> new IllegalArgumentException("No point found at y = " + y));
    }
}

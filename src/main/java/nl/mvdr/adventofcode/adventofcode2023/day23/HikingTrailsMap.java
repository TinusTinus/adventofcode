package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Map of hiking trails.
 *
 * @author Martijn van de Rijdt
 */
public record HikingTrailsMap(Map<Point, Terrain> terrainMap, Point start, Point goal) {

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
    
    /**
     * Creates a set of points of interest.
     * 
     * @param slipperySlopes whether the slopes are slippery
     * @return points of interest
     */
    Set<PointOfInterest> getPointsOfInterest(boolean slipperySlopes) {
        var pointsOfInterest = terrainMap.keySet()
                .stream()
                .filter(this::isPointOfInterest)
                .map(PointOfInterest::new)
                .collect(Collectors.toSet());
        
        // Initialize path lengths for direct paths from each point of interest to another
        pointsOfInterest.forEach(pointOfInterest -> {
            // Find all direct paths to other points of interest.
            step(pointOfInterest.point(), slipperySlopes)
                    .forEach(firstStep -> {
                        List<Point> path = new ArrayList<>();
                        path.add(pointOfInterest.point());
                        path.add(firstStep);
                        var deadEnd = false;
                        while (!deadEnd && pointsOfInterest.stream().noneMatch(p -> p.point().equals(path.getLast()))) {
                            var nextLocation = step(path.getLast(), slipperySlopes)
                                    .filter(point -> !point.equals(path.get(path.size() - 2)))
                                    .reduce((point0, point1) -> {throw new IllegalStateException("Multiple exits found");});
                            if (nextLocation.isPresent()) {
                                path.add(nextLocation.orElseThrow());
                            } else {
                                deadEnd = true;
                            }
                        }
                        if (!deadEnd) {
                            // Found a path
                            var targetPointOfInterest = pointsOfInterest.stream()
                                    .filter(target -> target.point().equals(path.getLast()))
                                    .reduce((poi0, poi1) -> {throw new IllegalStateException();})
                                    .orElseThrow();
                            pointOfInterest.pathLengths().put(targetPointOfInterest, Integer.valueOf(path.size() - 1));
                        }
                    });
        });
        
        return pointsOfInterest;
    }

    /**
     * Checks whether the given point is of interest.
     * 
     * That is, whether it is the start, goal or an intersection
     * 
     * @param point the point; must be a (non-forest) point in the terrain map
     * @return whether the given point is of interest
     */
    private boolean isPointOfInterest(Point point) {
        return start.equals(point) || goal.equals(point) || isIntersection(point);
    }
    
    
    /**
     * Checks whether the given point is an intersection.
     * 
     * That is, whether it has more than two possible entry / exit points.
     * 
     * @param point the point; must be a (non-forest) point in the terrain map
     * @return whether the given point is an intersection
     */
    private boolean isIntersection(Point point) {
        return 2L < step(point, false).count();
    }
    
    /**
     * Takes a single step from the given point.
     * 
     * @param point starting point; must be a (non-forest) point in the terrain map
     * @param slipperySlopes whether the slopes are considered to be slippery
     * @return possible next locations by taking a single step
     */
    private Stream<Point> step(Point point, boolean slipperySlopes) {
        return Stream.of(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT)
                .filter(direction -> !slipperySlopes || terrainMap.get(point).canExit(direction))
                .map(direction -> direction.move(point))
                .filter(terrainMap::containsKey);
    }
}

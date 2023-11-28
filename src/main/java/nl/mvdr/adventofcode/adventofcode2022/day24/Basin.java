package nl.mvdr.adventofcode.adventofcode2022.day24;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the state of the basin.
 *
 * @param start the starting point
 * @param goal the goal
 * @param walls spaces occupied by walls
 * @param blizzards current state of the blizzards
 * @param reachablePoints locations in the basin which can be reached from the last visited point
 * @param minutesPassed the number of minutes for which the blizzards have been simulated
 * @author Martijn van de Rijdt
 */
record Basin(int width, int height, Point start, Point goal, Set<Point> walls, Set<Blizzard> blizzards, Set<Point> reachablePoints, int minutesPassed) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Basin.class);
    
    /**
     * Parses the puzzle input.
     * 
     * @param lines textual representation of the basin
     * @return the basin
     */
    static Basin parse(List<String> lines) {
        var width = lines.get(0).length();
        var height = lines.size();
        
        Set<Point> walls = new HashSet<>();
        Set<Blizzard> blizzards = new HashSet<>();
        for (var y = 0; y != height; y++) {
            var line = lines.get(y);
            if (line.length() != width) {
                throw new IllegalArgumentException("Input is not rectangular, not all lines have the same length");
            }
            for (var x = 0; x != width; x++) {
                var c = line.charAt(x);
                var direction = Direction.of(c);
                if (direction.isPresent()) {
                    blizzards.add(new Blizzard(new Point(x, y), direction.orElseThrow()));
                } else if (c == '#') {
                    walls.add(new Point(x, y));
                } else if (c != '.') {
                    throw new IllegalArgumentException("Unexpected character found: " + c);
                }
            }
        }
        
        var start = IntStream.range(0, width)
                .mapToObj(x -> new Point(x, 0))
                .filter(Predicate.not(walls::contains))
                .findFirst()
                .orElseThrow();
        var goal = IntStream.range(0, width)
                .mapToObj(x -> new Point(x, height - 1))
                .filter(Predicate.not(walls::contains))
                .findFirst()
                .orElseThrow();
        var reachablePoints = Set.of(start);
        var minutesPassed = 0;
        
        return new Basin(width, height, start, goal, walls, blizzards, reachablePoints, minutesPassed);
    }
    
    /**
     * @return updated basin, after moving to the goal in the shortest amount of time possible
     */
    Basin moveToGoal() {
        return moveTo(goal);
    }
    
    /**
     * @return updated basin, after moving to the goal, then the start, then the goal again in the shortest amount of time possible
     */
    Basin moveToGoalStartGoal() {
        Basin basin = this;
        basin = basin.moveTo(goal);
        basin = basin.moveTo(start);
        basin = basin.moveTo(goal);
        return basin;
    }
    
    /**
     * Moves to the given goal, in the shortest amount of time possible.
     * 
     * @param target the location to move to
     * @return updated basin, after moving to the given target in the shortest amount of time possible
     */
    private Basin moveTo(Point target) {
        Basin basin = this;
        while (!basin.reachablePoints().contains(target)) {
            basin = basin.tick();
        }
        // Path to target found
        basin = basin.visit(target);
        LOGGER.debug("{} reached after {} minutes", target, Integer.valueOf(basin.minutesPassed()));
        return basin;
    }
    
    /**
     * @return updated basin, after a single minute has passed
     */
    private Basin tick() {
        var newBlizzards = moveBlizzards();
        
        var newReachablePoints = Point.points(0, width - 1, 0, height - 1)
                .parallel()
                .filter(Predicate.not(walls::contains)) // not a wall
                .filter(point -> newBlizzards.stream().noneMatch(blizzard -> blizzard.location().equals(point))) // not in a blizzard
                .filter(point -> reachablePoints.stream().anyMatch(reachablePoint -> reachablePoint.andNeighbours().contains(point))) // reachable in 1 step from a point that was previously reachable
                .collect(Collectors.toSet());
        
        return new Basin(width, height, start, goal, walls, newBlizzards, newReachablePoints, minutesPassed + 1);
    }

    /**
     * @return updated blizzard positions
     */
    private Set<Blizzard> moveBlizzards() {
        return blizzards.stream()
                .map(this::moveBlizzard)
                .collect(Collectors.toSet());
    }
    
    /**
     * Determines the next position for the given blizzard.
     * 
     * @param blizzard blizzard to move
     * @return updated blizzard
     */
    private Blizzard moveBlizzard(Blizzard blizzard) {
        Point newLocation = blizzard.direction().move(blizzard.location());
        if (walls.contains(newLocation)) {
            // Wrap around
            newLocation = switch(blizzard.direction()) {
                case UP -> new Point(newLocation.x(), height - 2);
                case LEFT -> new Point(width - 2, newLocation.y());
                case RIGHT -> new Point(1, newLocation.y());
                case DOWN -> new Point(newLocation.x(), 1);
                case DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT -> throw new IllegalArgumentException("Unexpected diagonal direction: " + blizzard.direction());
                default -> throw new IllegalArgumentException("Unexpected direction: " + blizzard.direction());
            };
        }
        return new Blizzard(newLocation, blizzard.direction());
    }
    
    /**
     * Returns a copy of this basin, with the current location updated to the given point.
     * 
     * @param target target point 
     * @return updated basin
     */
    private Basin visit(Point target) {
        if (!reachablePoints.contains(target)) {
            throw new IllegalArgumentException("Target is not reachable: " + target);
        }
        return new Basin(width, height, start, goal, walls, blizzards, Set.of(target), minutesPassed);
    }
}

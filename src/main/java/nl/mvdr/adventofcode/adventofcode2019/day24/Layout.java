package nl.mvdr.adventofcode.adventofcode2019.day24;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.processing.Generated;

import nl.mvdr.adventofcode.point.Point;

/**
 * A layout of bugs and empty spaces on the planet Eris.
 *
 * @author Martijn van de Rijdt
 */
class Layout {
    /** Width of the grid. */
    private static final int WIDTH = 5;
    /** Height of the grid. */
    private static final int HEIGHT = 5;
    
    /** Character representation of a bug. */
    private static final char BUG = '#';
    /** Character representation of an empty space. */
    private static final char EMPTY_SPACE = '.';

    /** Locations occupied by bugs. */
    private final Set<Point> bugs;

    /**
     * Parses puzzle input into a layout.
     * 
     * @param linesStream lines from the puzzle input
     * @return layout
     */
    static Layout parse(Stream<String> linesStream) {
        List<String> lines = linesStream.collect(Collectors.toList());
        
        Set<Point> bugLocations = new HashSet<>();
        for (int y = 0; y != lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x != lines.size(); x++) {
                if (line.charAt(x) == BUG) {
                    bugLocations.add(new Point(x, y));
                }
            }
        }
        
        return new Layout(bugLocations);
    }
    
    /**
     * Constructor.
     * 
     * @param bugs locations occupied by bugs
     */
    private Layout(Set<Point> bugs) {
        super();
        this.bugs = bugs;
    }
    
    /**
     * Determines the next layout, after a minute has passed.
     * 
     * @return new layout
     */
    Layout next() {
        Set<Point> newBugLocations = new HashSet<>();
        for (int x = 0; x != WIDTH; x++) {
            for (int y = 0; y != HEIGHT; y++) {
                Point location = new Point(x, y);
                long adjacentBugs = location.neighbours().stream()
                        .filter(bugs::contains)
                        .count();
                if (bugs.contains(location)) {
                    // A bug dies (becoming an empty space) unless there is exactly one bug adjacent to it.
                    if (adjacentBugs == 1L) {
                        newBugLocations.add(location);
                    }
                } else {
                    // An empty space becomes infested with a bug if exactly one or two bugs are adjacent to it.
                    if (adjacentBugs == 1L || adjacentBugs == 2L) {
                        newBugLocations.add(location);
                    }
                }
            }
        }
        return new Layout(newBugLocations);
    }
    
    /** @return the biodiversity in this layout */
    int biodiversity() {
        return bugs.stream()
                .mapToInt(this::biodiversityPoints)
                .sum();
    }
    
    /**
     * Calculates biodiversity points.
     * 
     * @param bug location of a bug
     * @return biodiversity points for the bug in the given location
     */
    private int biodiversityPoints(Point bug) {
        int exponent = bug.getY() * WIDTH + bug.getX();
        return 1 << exponent;
    }

    @Override
    @Generated("Eclipse")
    public int hashCode() {
        return Objects.hash(bugs);
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
        Layout other = (Layout) obj;
        return Objects.equals(bugs, other.bugs);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Layout:\n");
        for (int y = 0; y != HEIGHT; y++) {
            for (int x = 0; x != WIDTH; x++) {
                if (bugs.contains(new Point(x, y))) {
                    result.append(BUG);
                } else {
                    result.append(EMPTY_SPACE);
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

}

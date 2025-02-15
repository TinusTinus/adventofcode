package nl.mvdr.adventofcode.adventofcode2019.day24;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
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

    /** Whether the apply the rules for recursively folded space. */
    private final boolean recursivelyFoldedSpace;
    
    /** Locations occupied by bugs. */
    private final Set<Point> bugs;
    
    /** The layout contained within this one. If empty, no deeper layouts contain any bugs. */
    private final Optional<Layout> deeperLayout;

    /**
     * Parses puzzle input into a layout.
     * 
     * @param recursivelyFoldedSpace whether to apply the rules for recursively folded space;
     *      that is, whether this is part 2 of the puzzle
     * @param lines lines from the puzzle input
     * @return layout
     */
    static Layout parse(boolean recursivelyFoldedSpace, Stream<String> lines) {
        Set<Point> bugLocations = new HashSet<>();
        Point.parse2DMap(lines.toList(), (point, character) -> {
            if (character == BUG) {
                bugLocations.add(point);
            }
        });
        return new Layout(recursivelyFoldedSpace, bugLocations);
    }
    
    /**
     * Constructor.
     * 
     * @param recursivelyFoldedSpace whether to apply the rules for recursively folded space
     * @param bugs locations occupied by bugs
     */
    private Layout(boolean recursivelyFoldedSpace, Set<Point> bugs) {
        this(recursivelyFoldedSpace, bugs, Optional.empty());
    }
    
    /**
     * Constructor.
     * 
     * @param recursivelyFoldedSpace
     * @param bugs
     * @param deeperLayout
     * @param containingLayout
     */
    private Layout(boolean recursivelyFoldedSpace, Set<Point> bugs, Optional<Layout> deeperLayout) {
        super();
        this.recursivelyFoldedSpace = recursivelyFoldedSpace;
        this.bugs = bugs;
        this.deeperLayout = deeperLayout;
    }

    /**
     * Determines the next layout, after a minute has passed.
     * 
     * @return new layout
     */
    Layout tick() {
        Layout result;
        if (recursivelyFoldedSpace) {
            result = tickRecursively();
        } else {
            result = tickNonRecursively();
        }
        
        return result;
    }

    private Layout tickNonRecursively() {
        Set<Point> newBugLocations = new HashSet<>();
        for (int x = 0; x != WIDTH; x++) {
            for (int y = 0; y != HEIGHT; y++) {
                Point location = new Point(x, y);

                long adjacentBugs = location.neighbours().stream()
                        .filter(bugs::contains)
                        .count();
                if (willContainBug(location, adjacentBugs)) {
                    newBugLocations.add(location);
                }
            }
        }
        return new Layout(recursivelyFoldedSpace, newBugLocations);
    }
    
    /**
     * Determines whether the given location will contain a bug after the next tick.
     * 
     * @param location location within this layout
     * @param adjacentBugs number of adjacent bugs
     * @return whether the location will contain a bug after the next tick
     */
    private boolean willContainBug(Point location, long adjacentBugs) {
        boolean willContainBug;
        if (bugs.contains(location)) {
            // A bug dies (becoming an empty space) unless there is exactly one bug adjacent to it.
            willContainBug = adjacentBugs == 1L;
        } else {
            // An empty space becomes infested with a bug if exactly one or two bugs are adjacent to it.
            willContainBug = adjacentBugs == 1L || adjacentBugs == 2L;
        }
        return willContainBug;
    }

    private Layout tickRecursively() {
        Layout result;
        Layout containingLayout = new Layout(true, Set.of(), Optional.of(this));
        if (bugs.isEmpty()) {
            result = tickRecursively(containingLayout);
        } else {
            result = containingLayout.tickRecursively();
        }
        return result;
    }
    
    private Layout tickRecursively(Layout containingLayout) {
        Set<Point> newBugLocations = new HashSet<>();
        for (int x = 0; x != WIDTH; x++) {
            for (int y = 0; y != HEIGHT; y++) {
                if (x == 2 && y == 2) {
                    // Special case: this is the cell containing the deeper layout.
                    // This is represented by an empty cell.
                    // Therefore: no bug.
                } else {

                    Point location = new Point(x, y);

                    long adjacentBugs = location.neighbours().stream().filter(bugs::contains).count();
                    
                    if (y == 0 && containingLayout.bugs.contains(new Point(2, 1))) {
                        adjacentBugs++;
                    }
                    
                    if (y == 4 && containingLayout.bugs.contains(new Point(2, 3))) {
                        adjacentBugs++;
                    }
                    
                    if (x == 0 && containingLayout.bugs.contains(new Point(1, 2))) {
                        adjacentBugs++;
                    }
                    
                    if (x == 4 && containingLayout.bugs.contains(new Point(3, 2))) {
                        adjacentBugs++;
                    }
                    
                    if (x == 2 && y == 1 && deeperLayout.isPresent()) {
                        adjacentBugs = adjacentBugs + IntStream.range(0, 5)
                                .mapToObj(cx -> new Point(cx, 0))
                                .filter(deeperLayout.orElseThrow().bugs::contains)
                                .count();
                    }
                    
                    if (x == 2 && y == 3 && deeperLayout.isPresent()) {
                        adjacentBugs = adjacentBugs + IntStream.range(0, 5)
                                .mapToObj(cx -> new Point(cx, 4))
                                .filter(deeperLayout.orElseThrow().bugs::contains)
                                .count();
                    }
                    
                    if (x == 1 && y == 2 && deeperLayout.isPresent()) {
                        adjacentBugs = adjacentBugs + IntStream.range(0, 5)
                                .mapToObj(cy -> new Point(0, cy))
                                .filter(deeperLayout.orElseThrow().bugs::contains)
                                .count();
                    }
                    
                    if (x == 3 && y == 2 && deeperLayout.isPresent()) {
                        adjacentBugs = adjacentBugs + IntStream.range(0, 5)
                                .mapToObj(cy -> new Point(4, cy))
                                .filter(deeperLayout.orElseThrow().bugs::contains)
                                .count();
                    }
                    
                    if (willContainBug(location, adjacentBugs)) {
                        newBugLocations.add(location);
                    }
                }
            }
        }
        
        Optional<Layout> newDeeperLayout;
        if (deeperLayout.isPresent()) {
            newDeeperLayout = Optional.of(deeperLayout.orElseThrow().tickRecursively(this));
        } else if (bugs.isEmpty()) {
            newDeeperLayout = Optional.empty();
        } else {
            newDeeperLayout = Optional.of(new Layout(true, Set.of()).tickRecursively(this));
        }
        
        return new Layout(true, newBugLocations, newDeeperLayout);
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
        int exponent = bug.y() * WIDTH + bug.x();
        return 1 << exponent;
    }
    
    /** @return total number of bugs in the layout */
    int totalNumberOfBugs() {
        int result = bugs.size();
        if (deeperLayout.isPresent()) {
            result = result + deeperLayout.orElseThrow().totalNumberOfBugs();
        }
        return result;
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

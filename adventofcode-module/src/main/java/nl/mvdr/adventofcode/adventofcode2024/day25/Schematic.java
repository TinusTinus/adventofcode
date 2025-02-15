package nl.mvdr.adventofcode.adventofcode2024.day25;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import nl.mvdr.adventofcode.point.Point;

interface Schematic {
    static final int MAX_HEIGHT = 6;
    
    static Set<Point> toPointSet(List<String> lines) {
        Set<Point> points = new HashSet<>();
        Point.parse2DMap(lines, (point, character) -> {
            if (character == '#') {
                points.add(point);
            } else if (character != '.') {
                throw new IllegalArgumentException("Unable to parse character: " + character);
            }
        });
        return points;
    }
    
    List<Integer> pinHeights();
    
    default boolean overlaps(Schematic other) {
        if (pinHeights().size() != other.pinHeights().size()) {
            throw new IllegalArgumentException();
        }
        return IntStream.range(0, pinHeights().size())
                .anyMatch(i -> MAX_HEIGHT <= pinHeights().get(i).intValue() + other.pinHeights().get(i).intValue());
    }
}

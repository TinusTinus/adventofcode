package nl.mvdr.adventofcode.adventofcode2024.day04;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

record WordSearch(Map<Point, Character> map) {

    static WordSearch parse(List<String> input) {
        Map<Point, Character> map = Point.parse2DMap(input, Character::valueOf);
        return new WordSearch(map);
    }
    
    long countMatches() {
        return map.keySet()
                .stream()
                .mapToLong(this::countMatchesStartingAt)
                .sum();
    }
    
    private long countMatchesStartingAt(Point startingPoint) {
        return Stream.of(Direction.values())
                .filter(direction -> matchesInDirection(startingPoint, direction))
                .count();
    }
    
    private boolean matchesInDirection(Point startingPoint, Direction direction) {
        return Character.valueOf('X').equals(map.get(startingPoint))
                && Character.valueOf('M').equals(map.get(direction.move(startingPoint, 1)))
                && Character.valueOf('A').equals(map.get(direction.move(startingPoint, 2)))
                && Character.valueOf('S').equals(map.get(direction.move(startingPoint, 3)));
    }
}

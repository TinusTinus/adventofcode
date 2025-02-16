package nl.mvdr.adventofcode.adventofcode2024.day04;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

record WordSearch(Map<Point, Character> map) {

    static WordSearch parse(List<String> input) {
        Map<Point, Character> map = Point.parse2DMap(input, Character::valueOf);
        return new WordSearch(map);
    }
    
    long countXmasMatches() {
        return map.keySet()
                .stream()
                .mapToLong(this::countXmasMatchesStartingAt)
                .sum();
    }
    
    private long countXmasMatchesStartingAt(Point startingPoint) {
        return Stream.of(Direction.values())
                .filter(direction -> matchesInDirection(startingPoint, direction, "XMAS"))
                .count();
    }
    
    private boolean matchesInDirection(Point startingPoint, Direction direction, String text) {
        return IntStream.range(0, text.length())
                .allMatch(i -> Character.valueOf(text.charAt(i)).equals(map.get(direction.move(startingPoint, i))));
    }
    
    long countCrossMasMatches() {
        return map.keySet()
                .stream()
                .filter(this::hasCrossMasMatch)
                .count();
    }
    
    private boolean hasCrossMasMatch(Point centerPoint) {
        return (matchesInDirection(Direction.UP_LEFT.move(centerPoint), Direction.DOWN_RIGHT, "MAS")
                    || matchesInDirection(Direction.DOWN_RIGHT.move(centerPoint), Direction.UP_LEFT, "MAS"))
                && (matchesInDirection(Direction.DOWN_LEFT.move(centerPoint), Direction.UP_RIGHT, "MAS")
                        || matchesInDirection(Direction.UP_RIGHT.move(centerPoint), Direction.DOWN_LEFT, "MAS"));
    }
}

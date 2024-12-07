package nl.mvdr.adventofcode.adventofcode2024.day06;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

record Area(int width, int height, Set<Point> obstructions) {
    boolean contains(Point point) {
        return 0 <= point.x() && point.x() < width
                && 0 <= point.y() && point.y() < height;
    }
    
    Stream<Area> addObstruction() {
        return IntStream.range(0, width)
                .boxed()
                .flatMap(x -> IntStream.range(0, height)
                        .mapToObj(y -> new Point(x.intValue(), y)))
                .filter(Predicate.not(obstructions::contains))
                .map(this::addObstruction);
    }
    
    private Area addObstruction(Point obstruction) {
        Set<Point> newObstructions = new HashSet<>(obstructions);
        newObstructions.add(obstruction);
        return new Area(width, height, newObstructions);
    }
}

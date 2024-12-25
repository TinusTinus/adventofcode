package nl.mvdr.adventofcode.adventofcode2024.day25;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import nl.mvdr.adventofcode.point.Point;

record Lock(List<Integer> pinHeights) implements Schematic {
    
    static Lock parse(List<String> lines) {
        Set<Point> points = Schematic.toPointSet(lines);
        
        List<Integer> pinHeights = IntStream.range(0, Point.maxX(points) + 1)
                .map(x -> points.stream()
                        .filter(point -> point.x() == x)
                        .mapToInt(Point::y)
                        .max()
                        .orElseThrow())
                .boxed()
                .toList();
        
        return new Lock(pinHeights);
    }
}

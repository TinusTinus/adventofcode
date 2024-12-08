package nl.mvdr.adventofcode.adventofcode2024.day08;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

record CityMap(int width, int height, Map<Signal, Set<Point>> antennas) {
    
    static CityMap parse(List<String> lines) {
        var height = lines.size();
        var width = lines.getFirst().length();
        
        Map<Signal, Set<Point>> antennas = new HashMap<>();
        Point.parse2DMap(lines,
                (point, character) -> {
                    if (character != '.') {
                        antennas.computeIfAbsent(new Signal(character), p -> new HashSet<>()).add(point);
                    }
                });
        
        return new CityMap(width, height, antennas);
    }

    long countAntinodes() {
        return antinodes().count();
    }
    
    private Stream<Point> antinodes() {
        return antennas.keySet()
                .stream()
                .flatMap(this::antinodes)
                .distinct();
    }
    
    /// Returns all antinodes for the given signal.
    /// Note that the resulting stream may contain duplicates.
    /// (In fact, every antinode will occur at least twice.)
    private Stream<Point> antinodes(Signal signal) {
        var antennasForSignal = antennas.get(signal);
        return antennasForSignal.stream()
                .flatMap(firstAntenna -> antennasForSignal.stream()
                        .filter(secondAntenna -> !secondAntenna.equals(firstAntenna))
                        .flatMap(secondAntenna -> antinodes(firstAntenna, secondAntenna)));
    }
    
    Stream<Point> antinodes(Point firstAntenna, Point secondAntenna) {
        var difference = secondAntenna.subtract(firstAntenna);
        
        var firstAntinode = firstAntenna.subtract(difference);
        var secondAntinode = secondAntenna.add(difference);
        
        return Stream.of(firstAntinode, secondAntinode)
                .filter(antinode -> 0 <= antinode.x())
                .filter(antinode -> antinode.x() < width)
                .filter(antinode -> 0 <= antinode.y())
                .filter(antinode -> antinode.y() < height);
    }

}

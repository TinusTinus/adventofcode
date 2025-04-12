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
                        antennas.computeIfAbsent(new Signal(character), _ -> new HashSet<>()).add(point);
                    }
                });
        
        return new CityMap(width, height, antennas);
    }

    long countAntinodes(boolean resonantHarmonics) {
        return antennas.keySet()
                .stream()
                .flatMap(signal -> antinodes(signal, resonantHarmonics))
                .distinct()
                .count();
    }
    
    /// Returns all antinodes for the given signal.
    /// Note that the resulting stream may contain duplicates.
    /// (In fact, every antinode will occur at least twice.)
    private Stream<Point> antinodes(Signal signal, boolean resonantHarmonics) {
        var antennasForSignal = antennas.get(signal);
        return antennasForSignal.stream()
                .flatMap(firstAntenna -> antennasForSignal.stream()
                        .filter(secondAntenna -> !secondAntenna.equals(firstAntenna))
                        .flatMap(secondAntenna -> antinodes(firstAntenna, secondAntenna, !resonantHarmonics).stream()));
    }
    
    Set<Point> antinodes(Point firstAntenna, Point secondAntenna, boolean ignoreResonantHarmonics) {
        Set<Point> result = new HashSet<>();
        
        var difference = secondAntenna.subtract(firstAntenna);
        
        if (ignoreResonantHarmonics) {
            var antinode = firstAntenna.subtract(difference);
            if (isInBounds(antinode)) {
                result.add(antinode);
            }
            
            antinode = secondAntenna.add(difference);
            if (isInBounds(antinode)) {
                result.add(antinode);
            }
        } else {
            var antinode = firstAntenna;
            while (isInBounds(antinode)) {
                result.add(antinode);
                antinode = antinode.subtract(difference);
            }
            antinode = secondAntenna;
            while (isInBounds(antinode)) {
                result.add(antinode);
                antinode = antinode.add(difference);
            }
        }
        
        return result;
    }
    
    private boolean isInBounds(Point point) {
        return 0 <= point.x() && point.x() < width
                && 0 <= point.y() && point.y() < height;
    }
    

}

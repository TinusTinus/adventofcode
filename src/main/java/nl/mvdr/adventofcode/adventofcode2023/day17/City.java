package nl.mvdr.adventofcode.adventofcode2023.day17;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.point.Point;

/**
 * The city through which lava needs to be moved.
 *
 * @author Martijn van de Rijdt
 */
record City(Map<Point, Block> blocks) {
    /**
     * Parses the string representation of a city.
     * 
     * @param lines lines from the puzzle input
     * @return map
     */
    static City parse(List<String> lines) {
        var blocks = Point.parse2DMap(lines, Block::parse);
        return new City(blocks);
    }
    
    /**
     * @return minimum heat loss of a path from start to finish
     */
    int computeMinimumHeatLoss() {
        // Determine all possible states of the crucible.
        // Considering we can only move three steps in a straight line, this should be a set of a pretty limited size.
        Set<Crucible> crucibles = new HashSet<>();
        crucibles.add(Crucible.START);
        Set<Crucible> nextCrucibles = Crucible.START.possibleSteps();
        while (crucibles.addAll(nextCrucibles)) {
            nextCrucibles = nextCrucibles.stream()
                    .map(Crucible::possibleSteps)
                    .flatMap(Set::stream)
                    .filter(crucible -> blocks.containsKey(crucible.location())) // Stay within city bounds
                    .collect(Collectors.toSet());
        }
        
        return 0; // TODO implement
    }
}

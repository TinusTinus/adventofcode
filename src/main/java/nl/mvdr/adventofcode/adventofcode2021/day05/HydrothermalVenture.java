package nl.mvdr.adventofcode.adventofcode2021.day05;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.point.Line;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to <a href="https://adventofcode.com/2021/day/5">Hydrothermal Venture</a>.
 *
 * @author Martijn van de Rijdt
 */
class HydrothermalVenture implements LongSolver {

    private final boolean includeDiagonals;
    
    /**
     * Constructor.
     * 
     * @param includeDiagonals whether diagonal lines should be included
     */
    HydrothermalVenture(boolean includeDiagonals) {
        super();
        this.includeDiagonals = includeDiagonals;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        MultiSet<Point> points = lines.map(Line::parse)
                .filter(line -> includeDiagonals || line.isHorizontalOrVertical())
                .map(Line::points)
                .flatMap(Set::stream)
                .collect(Collectors.toCollection(HashMultiSet::new));
        
        return points.entrySet()
                .stream()
                .filter(entry -> 1 < entry.getCount())
                .count();
    }
}
 
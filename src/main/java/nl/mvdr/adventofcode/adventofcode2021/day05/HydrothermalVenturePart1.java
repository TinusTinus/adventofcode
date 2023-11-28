package nl.mvdr.adventofcode.adventofcode2021.day05;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.point.Line;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to <a href="https://adventofcode.com/2021/day/5">Hydrothermal Venture</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HydrothermalVenturePart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HydrothermalVenturePart1.class);

    @Override
    public long solve(Stream<String> lines) {
        MultiSet<Point> points = lines.map(Line::parse)
                .filter(Line::isHorizontalOrVertical)
                .map(Line::points)
                .flatMap(Set::stream)
                .collect(Collectors.toCollection(HashMultiSet::new));
        
        return points.entrySet()
                .stream()
                .filter(entry -> 1 < entry.getCount())
                .count();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new HydrothermalVenturePart1();

        var result = instance.solve("input-day05-2021.txt");

        LOGGER.info(result);
    }
}
 
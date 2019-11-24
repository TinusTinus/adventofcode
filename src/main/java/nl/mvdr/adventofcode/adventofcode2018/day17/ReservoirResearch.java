package nl.mvdr.adventofcode.adventofcode2018.day17;

import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 17 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/17">Reservoir Research</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ReservoirResearch implements LinesSolver<String> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservoirResearch.class);
    
    @Override
    public String solve(Stream<String> lines) {
        Point spring = new Point(500, 0);
        Set<Point> claySquareMeters = Point.parseRanges(lines);
        
        VerticalSlice slice = new VerticalSlice(spring, claySquareMeters);
        
        slice = slice.tickUntilDone();
        
        LOGGER.info("Done: {}", slice);
        
        return "Reached by water: " + slice.reachedByWater() + ", settled water: " + slice.settledWater();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ReservoirResearch solver = new ReservoirResearch();
        String solution = solver.solve("input-day17-2018.txt");
        LOGGER.info(solution);
    }
}

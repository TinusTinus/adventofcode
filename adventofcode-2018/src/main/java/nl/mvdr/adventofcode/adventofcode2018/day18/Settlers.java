package nl.mvdr.adventofcode.adventofcode2018.day18;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to the day 18 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/18">Settlers of the North Pole</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class Settlers implements LongSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Settlers.class);
    
    private final int minutes;
    
    protected Settlers(int minutes) {
        super();
        
        this.minutes = minutes;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        LumberCollectionArea area = LumberCollectionArea.parse(lines);
        
        area = area.tick(minutes);
        
        LOGGER.debug("Area:\n{}", area);

        return area.computeResourceValue();
    }
}

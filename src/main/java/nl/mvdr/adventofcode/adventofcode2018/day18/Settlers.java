package nl.mvdr.adventofcode.adventofcode2018.day18;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 18 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/18">Settlers of the North Pole</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class Settlers implements PathSolver<Long> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Settlers.class);
    
    private final int minutes;
    
    protected Settlers(int minutes) {
        super();
        
        this.minutes = minutes;
    }
    
    @Override
    public Long solve(Path inputFilePath) throws IOException {
        
        LumberCollectionArea area = LumberCollectionArea.parse(inputFilePath);
        
        area = area.tick(minutes);
        
        LOGGER.debug("Area:\n{}", area);

        long resourceValue = area.computeResourceValue();
        
        return Long.valueOf(resourceValue);
    }
}

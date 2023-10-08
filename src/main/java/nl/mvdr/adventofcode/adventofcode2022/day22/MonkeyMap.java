package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/22">Monkey Map</a>.
 *
 * @author Martijn van de Rijdt
 */
class MonkeyMap implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonkeyMap.class);

    private final WrapAroundStrategy strategy;
    
    /**
     * Constructor.
     * 
     * @param strategy rules for wrapping around the edges of the map
     */
    MonkeyMap(WrapAroundStrategy strategy) {
        this.strategy = strategy;
    }
    
    @Override
    public int solve(Stream<String> linesStream) {
        // Parse the puzzle input
        List<String> lines = linesStream.toList();
        var map = TerrainMap.parse(lines.subList(0, lines.size() - 1)); // Skip the last line, which contains the path
        var path = Path.parse(lines.get(lines.size() - 1), strategy);
        
        var startingPosition = map.startingPosition();
        LOGGER.debug("Starting position: {}", startingPosition);
        
        Position finalPosition = path.execute(startingPosition, map);
        
        return finalPosition.computePassword();
    }
}
 
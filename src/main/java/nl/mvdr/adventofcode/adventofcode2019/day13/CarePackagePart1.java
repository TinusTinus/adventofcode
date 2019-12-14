package nl.mvdr.adventofcode.adventofcode2019.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongConsumer;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 13 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/13">Care Package</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CarePackagePart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarePackagePart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return how many block tiles are on the screen when the game exits
     */
    @Override
    public long solve(Stream<String> lines) {
        
        Map<Point, Tile> tiles = new HashMap<>();
        
        List<Long> unprocessedOutputs = new ArrayList<>(2);
        
        LongConsumer output = code -> {
            if (unprocessedOutputs.size() == 2) {
                int x = Math.toIntExact(unprocessedOutputs.get(0).longValue());
                int y = Math.toIntExact(unprocessedOutputs.get(1).longValue());
                Tile tile = Tile.of(code);
                tiles.put(new Point(x, y), tile);
                unprocessedOutputs.clear();
            } else {
                unprocessedOutputs.add(Long.valueOf(code));
            }
        };
        
        Program.parse(lines.findFirst().orElseThrow())
            .withOutput(output)
            .execute();
        
        return tiles.values()
                .stream()
                .filter(tile -> tile == Tile.BLOCK)
                .count();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CarePackagePart1 instance = new CarePackagePart1();

        String result = instance.solve("input-day13-2019.txt");

        LOGGER.info(result);
    }
}

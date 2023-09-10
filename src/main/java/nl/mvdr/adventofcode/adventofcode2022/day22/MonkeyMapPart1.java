package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/22">Monkey Map</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMapPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonkeyMapPart1.class);

    @Override
    public int solve(Stream<String> linesStream) {
        List<String> lines = linesStream.toList();
        
        // Parse the map
        Map<Point, Terrain> map = new HashMap<>();
        for (var y = 1; y != lines.size(); y++) {
            String line = lines.get(y - 1);
            for (var x = 1; x != line.length() + 1; x++) {
                var terrain = Terrain.parse(line.charAt(x - 1));
                if (terrain.isPresent()) {
                    map.put(new Point(x, y), terrain.orElseThrow());
                }
            }
        }
        
        var path = Path.parse(lines.get(lines.size() - 1));
        
        return path.execute(Position.START, map).computePassword();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new MonkeyMapPart1();

        var result = instance.solve("input-day22-2022.txt");

        LOGGER.info(result);
    }
}
 
package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * Solution to the day 22 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/22">Mode Maze</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ModeMaze implements PathSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModeMaze.class);

    @Override
    public String solve(Path inputFilePath) throws IOException {
        List<String> lines = Files.lines(inputFilePath)
                .collect(Collectors.toList());
        int depth = Integer.parseInt(lines.get(0).substring("depth: ".length()));
        Point target = Point.parse(lines.get(1).substring("target: ".length()));
        
        Map<Point, Region> regions = new HashMap<>();
        
        for (int y = 0; y <= target.getY(); y++) {
            regions.put(new Point(0, y), new Region(y * 48_271, depth));
        }
        
        for (int x = 1; x <= target.getX(); x++) {
            regions.put(new Point(x, 0), new Region(x * 16_807, depth));
            for (int y = 1; y <= target.getY(); y++) {
                Point point = new Point(x, y);
                int geologicalIndex;
                if (target.equals(point)) {
                    geologicalIndex = 0;
                } else {
                    geologicalIndex = regions.get(point.aboveNeighbour()).getErosionLevel()
                            * regions.get(point.leftNeighbour()).getErosionLevel();
                }
                Region region = new Region(geologicalIndex, depth);
                regions.put(point, region);
                LOGGER.debug("new Region {}: {}", point, region);
            }
        }
        
        int totalRiskLevel = regions.values().stream()
                .map(Region::getType)
                .mapToInt(Type::getRiskLevel)
                .sum();
        
        return "" + totalRiskLevel;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ModeMaze solver = new ModeMaze();
        String solution = solver.solve("input-day22-2018.txt");
        LOGGER.info(solution);
    }
}

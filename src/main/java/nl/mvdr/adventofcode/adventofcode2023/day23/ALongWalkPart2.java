package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/23">A Long Walk</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ALongWalkPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ALongWalkPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        var map = HikingTrailsMap.parse(lines.toList());
        var pointsOfInterest = map.getPointsOfInterest(false);
        var start = pointsOfInterest.stream()
                .filter(poi -> poi.point().equals(map.start()))
                .findFirst()
                .orElseThrow();
        var goal = pointsOfInterest.stream()
                .filter(poi -> poi.point().equals(map.goal()))
                .findFirst()
                .orElseThrow();
        
        Set<Hike> hikes = Set.of(new Hike(start));
        var result = 0;
        while (!hikes.isEmpty()) {
            hikes = hikes.stream()
                    .flatMap(Hike::step)
                    .collect(Collectors.toSet());
            for (Hike hike: hikes) {
                if (hike.endsAt(goal)) {
                    result = Math.max(result, hike.length());
                }
            }
        }
        
        if (result == 0) {
            throw new IllegalStateException("No path found.");
        }
        
        return result;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new ALongWalkPart2();

        var result = instance.solve("input-day23-2023.txt");

        LOGGER.info(result);
    }
}
 
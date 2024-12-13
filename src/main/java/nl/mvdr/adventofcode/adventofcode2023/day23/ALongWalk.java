package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/23">A Long Walk</a>.
 *
 * @author Martijn van de Rijdt
 */
class ALongWalk implements IntSolver {

    private final boolean slipperySlopes;
    
    /**
     * Constructor.
     * 
     * @param slipperySlopes whether the slopes are considered to be slippery
     */
    ALongWalk(boolean slipperySlopes) {
        this.slipperySlopes = slipperySlopes;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var map = HikingTrailsMap.parse(lines.toList());
        var pointsOfInterest = map.getPointsOfInterest(slipperySlopes);
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
            throw new IllegalStateException("No hike found.");
        }
        
        return result;
    }
}
 
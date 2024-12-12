package nl.mvdr.adventofcode.adventofcode2024.day12;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.solver.IntSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(Stream<String> lines) {
    	Set<Region> regions = new HashSet<>();
    	
    	Point.parse2DMap(lines.toList(), (plot, plant) -> {
    		var neighbouringRegions = regions.stream()
    			.filter(region -> region.plant() == plant)
    			.filter(region -> plot.neighbours().stream().anyMatch(neighbour -> region.plots().contains(neighbour)))
    			.collect(Collectors.toSet());
    		
    		neighbouringRegions.forEach(regions::remove);
    		Set<Point> newRegionPlots = new HashSet<>();
    		newRegionPlots.add(plot);
    		neighbouringRegions.forEach(neighbouringRegion -> newRegionPlots.addAll(neighbouringRegion.plots()));
    		regions.add(new Region(plant, newRegionPlots));
    	});
    	
        return regions.stream()
        		.mapToInt(Region::cost)
        		.sum();
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day12-2024.txt");

        LOGGER.info(result);
    }
}
 
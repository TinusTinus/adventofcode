package nl.mvdr.adventofcode.adventofcode2024.day12;

import java.util.HashSet;
import java.util.Set;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.solver.IntSolver;

class GardenGroupsSolver implements IntSolver {

    private final ToIntFunction<Region> costFunction;
    
    GardenGroupsSolver(ToIntFunction<Region> costFunction) {
        super();
        this.costFunction = costFunction;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        Set<Region> regions = new HashSet<>();

        Point.parse2DMap(lines.toList(), (plot, plant) -> {
            var neighbouringRegions = regions.stream()
                    .filter(region -> region.plant() == plant)
                    .filter(region -> plot.neighbours().stream()
                            .anyMatch(neighbour -> region.plots().contains(neighbour)))
                    .collect(Collectors.toSet());

            neighbouringRegions.forEach(regions::remove);
            
            Set<Point> newRegionPlots = new HashSet<>();
            newRegionPlots.add(plot);
            neighbouringRegions.forEach(neighbouringRegion -> newRegionPlots.addAll(neighbouringRegion.plots()));
            regions.add(new Region(plant, newRegionPlots));
        });

        return regions.stream().mapToInt(costFunction).sum();
    }
}

package nl.mvdr.adventofcode.adventofcode2020.day17;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point3D;
import nl.mvdr.adventofcode.point.Point4D;

/**
 * Representation of the state of a pocket dimension.
 *
 * @param <P> representation of a point
 * @author Martijn van de Rijdt
 */
interface PocketDimension<P> {
    
    /**
     * Parses the initial state of a three-dimensional pocket dimension.
     * 
     * @param input lines from the puzzle input
     * @return initial state of the pocket dimension
     */
    static PocketDimension<Point3D> parseInitialState3D(Stream<String> input) {
        return parseInitialState(input, (x, y) -> new Point3D(x.intValue(), y.intValue(), 0), PocketDimension3D::new);
    }
    
    /**
     * Parses the initial state of a four-dimensional pocket dimension.
     * 
     * @param input lines from the puzzle input
     * @return initial state of the pocket dimension
     */
    static PocketDimension<Point4D> parseInitialState4D(Stream<String> input) {
        return parseInitialState(input, (x, y) -> new Point4D(x.intValue(), y.intValue(), 0, 0), PocketDimension4D::new);
    }
    
    /**
     * Parses the initial state of a pocket dimension.
     * 
     * @param input lines from the puzzle input
     * @param pointCreator function to create an n-dimensional point based on two coordinates from the initial input
     * @param pocketDimensionCreator function to create a concrete pocket dimension instance
     * @return initial state of the pocket dimension
     */
    private static <P> PocketDimension<P> parseInitialState(Stream<String> input, BiFunction<Integer, Integer, P> pointCreator,
            Function<Set<P>, PocketDimension<P>> pocketDimensionCreator) {
        List<String> lines = input.collect(Collectors.toList());
        
        Set<P> activeCubes = new HashSet<>();
        for (int y = 0; y != lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x != line.length(); x++) {
                if (line.charAt(x) == '#') {
                    P point = pointCreator.apply(Integer.valueOf(x), Integer.valueOf(y));
                    activeCubes.add(point);
                }
            }
        }
        
        return pocketDimensionCreator.apply(activeCubes);
    }
    
    /**
     * Simulates the given number of cycles.
     * 
     * @param cycles number of cycles to simulate
     * @return updated pocket dimension after simulating the cycles
     */
    default PocketDimension<P> simulateCycles(int cycles) {
        PocketDimension<P> result = this;
        for (int cycle = 0; cycle != cycles; cycle++) {
            result = result.simulateCycle();
        }
        return result;
    }
    
    /** @return updated pocket dimension after simulating a single cycle */
    private PocketDimension<P> simulateCycle() {
        Set<P> newActiveCubes = new HashSet<>();
        
        for (P activeCube : activeCubes()) {
            Set<P> neighbours = neighbours(activeCube);
            long activeNeighbours = countActiveCubes(neighbours);
            // If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active.
            // Otherwise, the cube becomes inactive.
            if (activeNeighbours == 2L || activeNeighbours == 3L) {
                newActiveCubes.add(activeCube);
            }
            
            // If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active.
            // Otherwise, the cube remains inactive.
            for (P neighbour : neighbours) {
                if (!activeCubes().contains(neighbour)) {
                    if (countActiveCubes(neighbours(neighbour)) == 3L) {
                        newActiveCubes.add(neighbour);
                    }
                }
            }
        }
        
        return create(newActiveCubes);
    }

    /**
     * Counts how many of the given cubes are active.
     * 
     * @param cubes cubes
     * @return number of active elements in the given set of cubes
     */
    private long countActiveCubes(Set<P> cubes) {
        return cubes.stream()
                .filter(activeCubes()::contains)
                .count();
    }
    
    /** @return active cubes (or hypercubes) in this pocket dimension */
    Set<P> activeCubes();
    
    /**
     * Creates a new pocket dimension.
     * 
     * @param newActiveCubes active cubes
     * @return new pocket dimension
     */
    PocketDimension<P> create(Set<P> newActiveCubes);
    
    /**
     * Gives the neighbours of the given point.
     * 
     * @param point point
     * @return the other points where any of their coordinates differ by at most 1 from the given point
     */
    Set<P> neighbours(P point);
}

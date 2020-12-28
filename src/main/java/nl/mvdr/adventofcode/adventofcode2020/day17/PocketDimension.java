package nl.mvdr.adventofcode.adventofcode2020.day17;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point3D;

/**
 * Representation of the state of a pocket dimension.
 *
 * @author Martijn van de Rijdt
 */
record PocketDimension(Set<Point3D> activeCubes) {
    /**
     * Parses the initial state of a pocket dimension.
     * 
     * @param input lines from the puzzle input
     * @return initial state of the pocket dimension
     */
    static PocketDimension parseInitialState(Stream<String> input) {
        List<String> lines = input.collect(Collectors.toList());
        
        Set<Point3D> activeCubes = new HashSet<>();
        for (int y = 0; y != lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x != line.length(); x++) {
                if (line.charAt(x) == '#') {
                    activeCubes.add(new Point3D(x, y, 0));
                }
            }
        }
        
        return new PocketDimension(activeCubes);
    }
    
    /** @return updated pocket dimension after simulating a single cycle */
    private PocketDimension simulateCycle() {
        Set<Point3D> newActiveCubes = new HashSet<>();
        
        
        for (Point3D activeCube : activeCubes) {
            Set<Point3D> neighbours = activeCube.neighbours();
            long activeNeighbours = neighbours.stream()
                    .filter(activeCubes::contains)
                    .count();
            // If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active.
            // Otherwise, the cube becomes inactive.
            if (activeNeighbours == 2L || activeNeighbours == 3L) {
                newActiveCubes.add(activeCube);
            }
            
            // If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active.
            // Otherwise, the cube remains inactive.
            for (Point3D neighbour : neighbours) {
                if (!activeCubes.contains(neighbour)) {
                    activeNeighbours = neighbour.neighbours().stream()
                            .filter(activeCubes::contains)
                            .count();
                    if (activeNeighbours == 3L) {
                        newActiveCubes.add(neighbour);
                    }
                }
            }
        }
        
        return new PocketDimension(newActiveCubes);
    }
    
    /**
     * Simulates the given number of cycles.
     * 
     * @param cycles number of cycles to simulate
     * @return updated pocket dimension after simulating the cycles
     */
    PocketDimension simulateCycles(int cycles) {
        PocketDimension result = this;
        for (int cycle = 0; cycle != cycles; cycle++) {
            result = result.simulateCycle();
        }
        return result;
    }
}

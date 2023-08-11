package nl.mvdr.adventofcode.adventofcode2022.day18;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.point.Point3D;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/18">Boiling Boulders</a>.
 *
 * @author Martijn van de Rijdt
 */
class BoilingBoulders implements LongSolver {

    private final boolean excludeAirPockets;
    
    /**
     * Constructor.
     * 
     * @param excludeAirPockets whether air pockets should be excluded
     */
    BoilingBoulders(boolean excludeAirPockets) {
        super();
        this.excludeAirPockets = excludeAirPockets;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var cubes = lines.map(Point3D::parse)
                .collect(Collectors.toSet());
        
        var unoccupiedSpaces = findUnoccupiedSpaces(cubes);
        
        return cubes.parallelStream()
                .mapToLong(cube -> countUnconnectedSides(cube, unoccupiedSpaces))
                .sum();
    }

    /**
     * Find all unoccupied spaces, within a bounding shape of the cubes, to be counted when checking the sides of each cube.
     * 
     * @param cubes cubes
     * @return unoccupied spaces to consider
     */
    private Set<Point3D> findUnoccupiedSpaces(Set<Point3D> cubes) {
        var minX = Point3D.minX(cubes);
        var maxX = Point3D.maxX(cubes);
        var minY = Point3D.minY(cubes);
        var maxY = Point3D.maxY(cubes);
        var minZ = Point3D.minZ(cubes);
        var maxZ = Point3D.maxZ(cubes);
        
        var unoccupiedSpaces = Point3D.points(minX - 1, maxX + 1, minY - 1, maxY + 1, minZ - 1, maxZ + 1)
                .filter(space -> !cubes.contains(space))
                .collect(Collectors.toSet());
        
        if (excludeAirPockets) {
            unoccupiedSpaces = filterOutAirPockets(unoccupiedSpaces, new Point3D(minX - 1, minY - 1, minZ - 1));
        }
        return unoccupiedSpaces;
    }

    /**
     * Returns the unoccupied spaces, without the spaces that are part of an air pocket.
     * 
     * @param unoccupiedSpaces unoccupied spaces, including air pockets
     * @param spaceOutside an unoccupied space which is definitely not in an air pocket
     * @return unoccupied spaces, excluding air pockets
     */
    private static Set<Point3D> filterOutAirPockets(Set<Point3D> unoccupiedSpaces, Point3D spaceOutside) {
        // Make a graph of all unoccupied spaces.
        Graph<Point3D, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        unoccupiedSpaces.forEach(graph::addVertex);
        for (Point3D unoccupiedSpace : unoccupiedSpaces) {
            for (Point3D neighbour : unoccupiedSpace.neighbours()) {
                if (unoccupiedSpaces.contains(neighbour)) {
                    graph.addEdge(unoccupiedSpace, neighbour);
                }
            }
        }
        
        ShortestPathAlgorithm<Point3D, DefaultEdge> shortestPathAlgorithm = new DijkstraShortestPath<>(graph);
        SingleSourcePaths<Point3D, DefaultEdge> paths = shortestPathAlgorithm.getPaths(spaceOutside);
        
        // Only consider the unoccupied spaces with a path to the space outside.
        return unoccupiedSpaces.stream()
                .filter(unoccupiedSpace -> paths.getPath(unoccupiedSpace) != null)
                .collect(Collectors.toSet());
    }
    
    /**
     * Returns the number of unconnected sides of the given cube.
     * 
     * @param cube cube whose sides to inspect
     * @param unoccupiedSpaces unoccupied spaces
     * @return number of unconnected sides
     */
    private static long countUnconnectedSides(Point3D cube, Set<Point3D> unoccupiedSpaces) {
        return Stream.of(Side.values())
                .map(side -> side.neighbour(cube))
                .filter(unoccupiedSpaces::contains)
                .count();
    }
}
 
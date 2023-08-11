package nl.mvdr.adventofcode.adventofcode2022.day18;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.point.Point3D;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/18">Boiling Boulders</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BoilingBouldersPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoilingBouldersPart2.class);

    @Override
    public long solve(Stream<String> lines) {
        var cubes = lines.map(Point3D::parse)
                .collect(Collectors.toSet());
        
        var minX = Point3D.minX(cubes);
        var maxX = Point3D.maxX(cubes);
        var minY = Point3D.minY(cubes);
        var maxY = Point3D.maxY(cubes);
        var minZ = Point3D.minZ(cubes);
        var maxZ = Point3D.maxZ(cubes);
        var unoccupiedSpaces = Point3D.points(minX - 1, maxX + 1, minY - 1, maxY + 1, minZ - 1, maxZ + 1)
                .filter(space -> !cubes.contains(space))
                .collect(Collectors.toSet());
        
        // Determine an unoccupied space which is definitely not in an air pocket.
        var spaceOutside = new Point3D(minX - 1, minY - 1, minZ - 1);

        // Let's make a graph of all unoccupied spaces.
        Graph<Point3D, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);
        unoccupiedSpaces.forEach(graph::addVertex);
        for (Point3D unoccupiedSpace : unoccupiedSpaces) {
            for (Point3D neighbour : unoccupiedSpace.neighbours()) {
                if (unoccupiedSpaces.contains(neighbour)) {
                    graph.addEdge(unoccupiedSpace, neighbour);
                }
            }
        }
        ShortestPathAlgorithm<Point3D, DefaultEdge> shortestPathAlgorithm = new DijkstraShortestPath<>(graph);
        
        return cubes.parallelStream()
                .mapToLong(cube -> countUnconnectedSides(cube, cubes, shortestPathAlgorithm, spaceOutside))
                .sum();
    }
    
    /**
     * Returns the number of unconnected sides of the given cube.
     * 
     * @param cube cube whose sides to inspect
     * @param cubes set of all cubes
     * @return number of unconnected sides
     */
    private static long countUnconnectedSides(Point3D cube, Set<Point3D> cubes, ShortestPathAlgorithm<Point3D, DefaultEdge> shortestPathAlgorithm, Point3D spaceOutside) {
        return Stream.of(Side.values())
                .map(side -> side.neighbour(cube))
                .filter(neighbour -> !cubes.contains(neighbour)) // Neighbour is an unoccupied space
                .filter(neighbour -> shortestPathAlgorithm.getPath(neighbour, spaceOutside) != null) // Neighbour is not part of an air pocket
                .count();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BoilingBouldersPart2();

        var result = instance.solve("input-day18-2022.txt");

        LOGGER.info(result);
    }
}
 
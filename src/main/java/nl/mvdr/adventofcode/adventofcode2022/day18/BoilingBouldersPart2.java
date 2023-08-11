package nl.mvdr.adventofcode.adventofcode2022.day18;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
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

        // Make a graph of all unoccupied spaces.
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
        SingleSourcePaths<Point3D, DefaultEdge> paths = shortestPathAlgorithm.getPaths(spaceOutside);
        var unoccupiedSpacesOutside = unoccupiedSpaces.stream()
                .filter(unoccupiedSpace -> paths.getPath(unoccupiedSpace) != null)
                .collect(Collectors.toSet());
        
        return cubes.parallelStream()
                .mapToLong(cube -> countUnconnectedSides(cube, unoccupiedSpacesOutside))
                .sum();
    }
    
    /**
     * Returns the number of unconnected sides of the given cube.
     * 
     * @param cube cube whose sides to inspect
     * @param unoccupiedSpacesOutside unoccupied spaces outside the droplet (that is, all unoccupied spaces except air pockets) within a bounding box of the droplet
     * @return number of unconnected sides
     */
    private static long countUnconnectedSides(Point3D cube, Set<Point3D> unoccupiedSpacesOutside) {
        return Stream.of(Side.values())
                .map(side -> side.neighbour(cube))
                .filter(neighbour -> unoccupiedSpacesOutside.contains(neighbour)) // Neighbour is not part of an air pocket
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
 
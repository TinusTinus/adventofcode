package nl.mvdr.adventofcode.adventofcode2018.day22;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to part 2 of the day 22 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/22">Mode Maze</a>.
 * 
 * @author Martijn van de Rijdt
 */
public class ModeMazePart2 extends ModeMaze {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ModeMazePart2.class);
    
    /**
     * Solver method.
     * 
     * @param cave the cave
     * @return integer representing the puzzle's result
     */
    @Override
    protected int solve(Cave cave) {
        Graph<State, Object> graph = new SimpleWeightedGraph<>(Object.class);
        
        graph.addVertex(State.initialState());
        
        // TODO add vertices and edges
        
        ShortestPathAlgorithm<State, Object> algorithm = new DijkstraShortestPath<>(graph);
        double weight = algorithm.getPathWeight(State.initialState(), State.targetState(cave));
        
        // TODO convert to an integer properly
        return (int)weight; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ModeMazePart2 solver = new ModeMazePart2();
        String solution = solver.solve("input-day22-2018.txt");
        LOGGER.info(solution);
    }
}

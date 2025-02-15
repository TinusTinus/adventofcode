package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.util.HashSet;
import java.util.Set;

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
        // Build a weighted graph, with states as vertices, transitions as edges and costs in minutes as weights.
        // Then use a shortest path algorithm.
        
        // Note: JGrapht does not seem to support graphs of infinite size
        // (even though Dijkstra's shortest path algorithm supports those just fine in theory).
        // Build a finite graph which is large enough that it is likely to contain the actual shortest path.
        
        Graph<State, Transition> graph = new SimpleWeightedGraph<>(Transition.class);
        
        State initialState = State.initialState();
        graph.addVertex(initialState);
        Set<State> lastAdded = Set.of(initialState);
        
        while (!lastAdded.isEmpty()) {
            Set<State> addedThisIteration = new HashSet<>();
            
            lastAdded.stream()
                .flatMap(state -> state.getTransitions(cave).stream())
                .filter(transition -> transition.getNextState().getLocation().x() < 2 * cave.getTarget().x())
                .filter(transition -> transition.getNextState().getLocation().y() < 2 * cave.getTarget().y())
                .forEach(transition -> {
                    if (!graph.containsVertex(transition.getNextState())) {
                        graph.addVertex(transition.getNextState());
                        addedThisIteration.add(transition.getNextState());
                    }
                    if (!graph.containsEdge(transition.getSource(), transition.getNextState())) {
                        graph.addEdge(transition.getSource(), transition.getNextState(), transition);
                        graph.setEdgeWeight(transition, transition.getCost());
                    }
                });
            lastAdded = addedThisIteration;
        }
        
        ShortestPathAlgorithm<State, Transition> algorithm = new DijkstraShortestPath<>(graph);
        double weight = algorithm.getPathWeight(initialState, State.targetState(cave));
        
        return (int)Math.round(weight);
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

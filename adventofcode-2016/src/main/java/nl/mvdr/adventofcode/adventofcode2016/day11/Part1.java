package nl.mvdr.adventofcode.adventofcode2016.day11;

import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);
    
    @Override
    public int solve(Stream<String> lines) {
        var initialState = State.parse(lines);
        
        Graph<State, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        
        initialState.allValidStates().forEach(graph::addVertex);
        
        graph.vertexSet()
                .stream()
                .forEach(state -> state.takeElevator().forEach(targetState -> graph.addEdge(state, targetState)));
        
        
        ShortestPathAlgorithm<State, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);
        var path = algorithm.getPath(initialState, initialState.endState());
        return path.getLength();
    }
    
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day11-2016.txt");

        LOGGER.info(result);
    }
}

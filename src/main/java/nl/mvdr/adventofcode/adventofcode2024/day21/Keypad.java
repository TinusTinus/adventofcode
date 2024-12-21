package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

record Keypad<B extends Enum<B> & KeypadButton<B>>(Graph<B, DirectionalKeypadButtonPress> graph) {

    private static final Logger LOGGER = LoggerFactory.getLogger(Keypad.class);
    
    static <B extends Enum<B> & KeypadButton<B>> Keypad<B> create(Class<B> buttonClass) {
        Graph<B, DirectionalKeypadButtonPress> graph = new SimpleDirectedGraph<>(DirectionalKeypadButtonPress.class);
        
        Stream.of(buttonClass.getEnumConstants())
                .forEach(graph::addVertex);
        
        graph.vertexSet()
                .forEach(vertex -> Stream.of(DirectionalKeypadButton.values())
                        .filter(button -> button.getDirection() != null)
                        .filter(button -> vertex.neighbouringButton(button.getDirection()).isPresent())
                        .forEach(button -> graph.addEdge(vertex, vertex.neighbouringButton(button.getDirection()).orElseThrow(), new DirectionalKeypadButtonPress(button))));
        
        return new Keypad<>(graph);
    }
    
    // TODO return all possible shortest paths, instead of just a shortest path!
    // TODO caching
    List<DirectionalKeypadButton> fewestButtonPresses(List<B> code, B initialButton) {
        ShortestPathAlgorithm<B, DirectionalKeypadButtonPress> algorithm = new DijkstraShortestPath<>(graph);
        
        List<B> remainingCode = new ArrayList<>(code);
        var button = initialButton;
        
        List<DirectionalKeypadButton> result = new ArrayList<>();
        
        while (!remainingCode.isEmpty()) {
            algorithm.getPath(button, remainingCode.getFirst())
                    .getEdgeList()
                    .stream()
                    .map(DirectionalKeypadButtonPress::getButton)
                    .forEach(result::add);
            result.add(DirectionalKeypadButton.A);
            button = remainingCode.removeFirst();
        }
        
        LOGGER.info("Found path of length {}: {}, to enter code: {}", Integer.valueOf(result.size()), result, code); // TODO debug
        
        return result;
    }
}

package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Keypad<B extends Enum<B> & KeypadButton<B>> {

    static final Keypad<NumericKeypadButton> NUMERIC = new Keypad<>(NumericKeypadButton.class, NumericKeypadButton.KEY_A);
    private static final Keypad<DirectionalKeypadButton> DIRECTIONAL = new Keypad<>(DirectionalKeypadButton.class, DirectionalKeypadButton.A);
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Keypad.class);
    
    private final B initialButton;
    private final AllDirectedPaths<B, DirectionalKeypadButtonPress> allPathsAlgorithm;
    private final Map<CacheKey<B>, Long> cache;

    private Keypad(Class<B> buttonClass, B initialButton) {
        this.initialButton = initialButton;
        allPathsAlgorithm = new AllDirectedPaths<>(createGraph(buttonClass));
        cache = new HashMap<>();
    }
    
    private static <B extends Enum<B> & KeypadButton<B>> Graph<B, DirectionalKeypadButtonPress> createGraph(Class<B> buttonClass) {
        Graph<B, DirectionalKeypadButtonPress> graph = new SimpleDirectedGraph<>(DirectionalKeypadButtonPress.class);
        Stream.of(buttonClass.getEnumConstants())
                .forEach(graph::addVertex);
        graph.vertexSet()
                .forEach(vertex -> Stream.of(DirectionalKeypadButton.values())
                        .filter(button -> button.getDirection() != null)
                        .filter(button -> vertex.neighbouringButton(button.getDirection()).isPresent())
                        .forEach(button -> graph.addEdge(vertex, vertex.neighbouringButton(button.getDirection()).orElseThrow(), new DirectionalKeypadButtonPress(button))));
        return graph;
    }
    
    /// Determines the minimum required button presses to enter the given code on this keypad.
    ///
    /// @param code the input to enter into the keypad
    /// @param robots the number of robots between us and the keypad
    long fewestButtonPresses(List<B> code, int robots) {
        List<B> remainingCode = new ArrayList<>(code);
        B currentButton = initialButton;
        var result = 0L;
        while (!remainingCode.isEmpty()) {
            var nextButton = remainingCode.removeFirst();
            result += fewestButtonPresses(nextButton, currentButton, robots);
            currentButton = nextButton;
        }
        LOGGER.debug("Fewest button presses to enter {} with {} robot(s): {}",
                code, Integer.valueOf(robots), Long.valueOf(result));
        return result;
    }
    
    /// Determines the minimum required button presses, to press the given button on this keypad.
    ///
    /// @param button the button to press
    /// @param currentButton the button on this keypad on which an arm is hovering
    /// @param robots the number of robots between us and the keypad
    private long fewestButtonPresses(B button, B currentButton, int robots) {
        var cacheKey = new CacheKey<>(button, currentButton, robots);
        var cached = cache.get(cacheKey);
        
        long result;
        if (cached == null) {
            if (robots == 0) {
                // Just press the button
                result = 1L;
            } else {
                var paths = allPathsAlgorithm.getAllPaths(currentButton, button, false, Integer.valueOf(5)); // max path length is 5 for numeric, 3 for directional
                var shortestPathLength = paths.stream()
                        .mapToInt(GraphPath::getLength)
                        .min()
                        .orElseThrow();
                result = paths.stream()
                        .filter(path -> path.getLength() == shortestPathLength)
                        .map(path -> path.getEdgeList()
                                .stream()
                                .map(DirectionalKeypadButtonPress::getButton))
                        // append the A button
                        .map(buttonStream -> Stream.concat(buttonStream, Stream.of(DirectionalKeypadButton.A)))
                        .map(Stream::toList)
                        .peek(buttons -> LOGGER.debug("Shortest path found to enter {}, starting on button {}, with {} robot(s): {}",
                                button, currentButton, Integer.valueOf(robots), buttons))
                        .mapToLong(buttons -> DIRECTIONAL.fewestButtonPresses(buttons, robots - 1))
                        .min()
                        .orElseThrow();
            }
            cache.put(cacheKey, Long.valueOf(result));
        } else {
            result = cached.longValue();
        }
        return result;
    }
}

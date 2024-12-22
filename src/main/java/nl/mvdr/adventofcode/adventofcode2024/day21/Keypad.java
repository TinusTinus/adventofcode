package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

record Keypad<B extends Enum<B> & KeypadButton<B>>(AllDirectedPaths<B, DirectionalKeypadButtonPress> allPathsAlgorithm) {

    static final Keypad<NumericKeypadButton> NUMERIC = new Keypad<>(NumericKeypadButton.class);
    static final Keypad<DirectionalKeypadButton> DIRECTIONAL = new Keypad<>(DirectionalKeypadButton.class);
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Keypad.class);

    private Keypad(Class<B> buttonClass) {
        this(new AllDirectedPaths<>(createGraph(buttonClass)));
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
    /// @param initialButton the button on this keypad on which an arm is hovering
    /// @param robots the number of robots between us and the keypad
    int fewestButtonPresses(List<B> code, B initialButton, int robots) {
        List<B> remainingCode = new ArrayList<>(code);
        B currentButton = initialButton;
        int result = 0;
        while (!remainingCode.isEmpty()) {
            var nextButton = remainingCode.removeFirst();
            result += fewestButtonPresses(nextButton, currentButton, robots);
            currentButton = nextButton;
        }
        LOGGER.debug("Fewest button presses to enter {}, starting on button {}, with {} intermediate robot(s): {}",
                code, initialButton, Integer.valueOf(robots), Integer.valueOf(result));
        return result;
    }
    
    /// Determines the minimum required button presses, to press the given button on this keypad.
    ///
    /// @param button the button to press
    /// @param initialButton the button on this keypad on which an arm is hovering
    /// @param robots the number of robots between us and the keypad
    int fewestButtonPresses(B button, B initialButton, int robots) {
        int result;
        
        if (robots == 0) {
            // Just press the button
            result = 1;
        } else {
            // TODO caching
            
            var paths = allPathsAlgorithm.getAllPaths(initialButton, button, false, Integer.valueOf(5)); // TODO could be reduced: 5 for numeric, 3 for directional
            var shortestPathLength = paths.stream()
                    .mapToInt(GraphPath::getLength)
                    .min()
                    .orElseThrow();
            return paths.stream()
                    .filter(path -> path.getLength() == shortestPathLength)
                    .map(path -> path.getEdgeList()
                            .stream()
                            .map(DirectionalKeypadButtonPress::getButton))
                    // append the A button
                    .map(buttonStream -> Stream.concat(buttonStream, Stream.of(DirectionalKeypadButton.A)))
                    .map(Stream::toList)
                    .peek(buttons -> LOGGER.debug("Shortest path found to enter {}, starting on button {}, with {} intermediate robot(s): {}",
                            button, initialButton, Integer.valueOf(robots), buttons))
                    .mapToInt(buttons -> DIRECTIONAL.fewestButtonPresses(buttons, DirectionalKeypadButton.A, robots - 1))
                    .min()
                    .orElseThrow();
        }
        
        return result;
    }

}

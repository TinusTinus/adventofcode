package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;

record Robot<B extends Enum<B> & KeypadButton<B>>(B armPosition, List<B> remainingCode, Class<B> buttonClass, B aButton) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Robot.class);
    
    /// Constructor to create a new robot in its initial state: with the arm pointing at the A button.
    ///
    /// @param code the code to be input by this robot
    /// @param buttonClass the class of button
    /// @param aButton the A button on the keypad 
    Robot(List<B> code, Class<B> buttonClass, B aButton) {
        this(aButton, code, buttonClass, aButton);
    }
    
    static Robot<NumericKeypadButton> createFirstRobot(String code) {
        List<NumericKeypadButton> remainingCode = code.chars()
                .mapToObj(c -> NumericKeypadButton.of((char)c))
                .toList();
        return new Robot<>(remainingCode, NumericKeypadButton.class, NumericKeypadButton.KEY_A);
    }
    
    static Robot<DirectionalKeypadButton> createIntermediateRobot(List<DirectionalKeypadButton> remainingInput) {
        return new Robot<>(remainingInput, DirectionalKeypadButton.class, DirectionalKeypadButton.A);
    }
    
    /// Presses the given button on the directional keypad of this robot.
    /// Returns an empty value if the resulting state would result in a panic state,
    /// or if it would result in an this robot pressing an incorrect keypad button.
    private Optional<Robot<B>> press(DirectionalKeypadButton button) {
        Optional<Robot<B>> result;
        if (button == DirectionalKeypadButton.A) {
            result = pressA();
        } else {
            result = pressDirection(button.getDirection());
        }
        return result;
    }
    
    private Optional<Robot<B>> pressA() {
        Optional<Robot<B>> result;
        if (!remainingCode.isEmpty() && remainingCode.getFirst() == armPosition) {
            result = Optional.of(new Robot<>(armPosition, remainingCode.subList(1, remainingCode.size()), buttonClass, aButton));
        } else {
            result = Optional.empty();
        }
        return result;
    }
    
    private Optional<Robot<B>> pressDirection(Direction direction) {
        return armPosition.neighbouringButton(direction)
                .map(newPosition -> new Robot<>(newPosition, remainingCode, buttonClass, aButton));
    }
    
    private Graph<Robot<B>, DirectionalKeypadButtonPress> createGraph() {
        Graph<Robot<B>, DirectionalKeypadButtonPress> result = new SimpleDirectedGraph<>(DirectionalKeypadButtonPress.class);
        
        IntStream.range(0, remainingCode.size() + 1)
                .mapToObj(i -> remainingCode.subList(i, remainingCode.size()))
                .flatMap(code -> Stream.of(buttonClass.getEnumConstants())
                        .map(button -> new Robot<B>(button, code, buttonClass, aButton)))
                .forEach(result::addVertex);
        
        result.vertexSet()
                .forEach(robot -> Stream.of(DirectionalKeypadButton.values())
                        .forEach(button -> robot.press(button)
                                .ifPresent(nextRobot -> result.addEdge(robot, nextRobot, new DirectionalKeypadButtonPress(button)))));
        
        return result;
    }
    
    // TODO return all possible shortest sequences instead of just one
    List<DirectionalKeypadButton> fewestButtonPresses() {
        var graph = createGraph();
        
        ShortestPathAlgorithm<Robot<B>, DirectionalKeypadButtonPress> algorithm = new DijkstraShortestPath<>(graph);
        
        var path = algorithm.getPath(this, new Robot<>(aButton, List.of(), buttonClass, aButton));
        
        LOGGER.debug("Shortest path of length {} found: {}", Integer.valueOf(path.getLength()), path);
        
        return path.getEdgeList()
                .stream()
                .map(DirectionalKeypadButtonPress::getButton)
                .toList();
    }
}

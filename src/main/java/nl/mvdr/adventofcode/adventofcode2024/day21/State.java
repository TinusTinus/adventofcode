package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;

record State(List<NumericKeypadButton> remainingCode,
        NumericKeypadButton firstRobotArmPosition,
        List<DirectionalKeypadButton> intermediateRobotPositions) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(State.class);
    
    /// Constructor for the initial state.
    ///
    /// @param code the code to be entered by the first robot
    /// @param intermediateRobots the number of robots between us and the first robot
    State(String code, int intermediateRobots) {
        this(code.chars().mapToObj(c -> NumericKeypadButton.of((char)c)).toList(),
                NumericKeypadButton.KEY_A,
                Collections.nCopies(intermediateRobots, DirectionalKeypadButton.A));
    }
    
    /// Presses the given button on the directional keypad of the last robot.
    /// Returns an empty value if the resulting state would result in a robot panic,
    /// or if it would result in an incorrect keypad button being pressed.
    private Optional<State> press(DirectionalKeypadButton button) {
        Optional<State> result;
        if (button == DirectionalKeypadButton.A) {
            result = pressA();
        } else {
            result = pressDirection(button.getDirection());
        }
        return result;
    }
    
    /// Returns the updated state, after pressing the given directional button on the third robot's directional keypad.
    private Optional<State> pressDirection(Direction direction) {
        return intermediateRobotPositions.getLast()
                .neighbouringButton(direction)
                .map(thirdPosition -> moveIntermediateRobot(intermediateRobotPositions.size() - 1, thirdPosition));
    }
    
    private State moveIntermediateRobot(int index, DirectionalKeypadButton newPosition) {
        List<DirectionalKeypadButton> newIntermediateRobotPositions = new ArrayList<>(intermediateRobotPositions);
        newIntermediateRobotPositions.set(index, newPosition);
        return new State(remainingCode, firstRobotArmPosition, newIntermediateRobotPositions);
    }
    
    /// Returns the updated state, after pressing the A button (that is, the confirm button) on the third robot's directional keypad.
    private Optional<State> pressA() {
        Optional<State> result;
        
        if (intermediateRobotPositions.stream().allMatch(position -> position == DirectionalKeypadButton.A)) {
            // All the intermediate robots are positioned over the A button.
            // Therefore the first robot would hit a numpad button if we were to press A.
            if (!remainingCode.isEmpty() && remainingCode.getFirst() == firstRobotArmPosition) {
                // The first robot arm is hovering over the correct numpad button!
                var newRemainingCode = remainingCode.subList(1, remainingCode.size());
                result = Optional.of(new State(newRemainingCode, firstRobotArmPosition, intermediateRobotPositions));
            } else {
                // The first robot arm is hovering over the wrong button. Let's not press it.
                result = Optional.empty();
            }
        } else {
            
            // Find the index of the robot closest to us not hovering over the A button.
            int index = IntStream.range(0, intermediateRobotPositions.size())
                    .filter(i -> intermediateRobotPositions.get(i) != DirectionalKeypadButton.A)
                    .reduce((first, second) -> second)
                    .orElseThrow();
            
            if (index == 0) {
                // Move the first robot arm.
                result = firstRobotArmPosition.neighbouringButton(intermediateRobotPositions.get(0).getDirection())
                        .map(newFirstPosition -> new State(remainingCode, newFirstPosition, intermediateRobotPositions));
            } else {
                // Move an intermediate robot arm.
                result = intermediateRobotPositions.get(index - 1)
                        .neighbouringButton(intermediateRobotPositions.get(index).getDirection())
                        .map(newPosition -> moveIntermediateRobot(index - 1, newPosition));
            }
        }
        
        return result;
    }
    
    private Graph<State, DirectionalKeypadButtonPress> createGraph() {
        Graph<State, DirectionalKeypadButtonPress> result = new SimpleDirectedGraph<>(DirectionalKeypadButtonPress.class);

        IntStream.range(0, remainingCode.size() + 1)
                .mapToObj(i -> remainingCode.subList(i, remainingCode.size()))
                .forEach(code -> Stream.of(NumericKeypadButton.values())
                        .forEach(firstPosition -> DirectionalKeypadButton.getAllPermutations(intermediateRobotPositions.size())
                                .stream()
                                .map(intermediatePositions -> new State(code, firstPosition, intermediatePositions))
                                .forEach(result::addVertex)));
        
        result.vertexSet()
                .forEach(state -> Stream.of(DirectionalKeypadButton.values())
                        .forEach(button -> state.press(button)
                                .ifPresent(nextState -> result.addEdge(state, nextState, new DirectionalKeypadButtonPress(button)))));
        
        return result;
    }
    
    /// Fewest number of button presses needed to cause the robot in front of the door to type the code
    private int buttonPresses() {
        var graph = createGraph();
        
        ShortestPathAlgorithm<State, DirectionalKeypadButtonPress> algorithm = new DijkstraShortestPath<>(graph);
        
        var path = algorithm.getPath(this, new State("", intermediateRobotPositions.size()));
        LOGGER.debug("Path found: {}", path);
        return path.getLength();
    }
    
    private int numericCode() {
        String numericCodeString = remainingCode.stream()
                .filter(button -> button != NumericKeypadButton.KEY_A)
                .map(NumericKeypadButton::toString)
                .collect(Collectors.joining());
        
        return Integer.parseInt(numericCodeString, 10);
    }
    
    int complexity() {
        return Math.multiplyExact(buttonPresses(), numericCode());
    }
    
}

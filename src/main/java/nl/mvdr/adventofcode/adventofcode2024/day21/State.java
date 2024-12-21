package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import nl.mvdr.adventofcode.point.Direction;

record State(List<NumericKeypadButton> remainingCode,
        NumericKeypadButton firstRobotArmPosition,
        DirectionalKeypadButton secondRobotArmPosition,
        DirectionalKeypadButton thirdRobotArmPosition) {
    
    /// End state.
    /// All codes end with an A, so all robotic arms should end up hovering over an A button.
    static final State END_STATE = new State("");
    
    /// Constructor for the initial state.
    ///
    /// @param code the code to be entered by the first robot
    State(String code) {
        this(code.chars().mapToObj(c -> NumericKeypadButton.of((char)c)).toList(),
                NumericKeypadButton.KEY_A,
                DirectionalKeypadButton.A,
                DirectionalKeypadButton.A);
    }
    
    /// Returns the updated state, after pressing the given directional button on the third robot's directional keypad.
    private Optional<State> pressDirection(Direction direction) {
        return thirdRobotArmPosition.neighbouringButton(direction)
                .map(thirdPosition -> new State(remainingCode, firstRobotArmPosition, secondRobotArmPosition, thirdPosition));
    }
    
    /// Returns the updated state, after pressing the A button (that is, the confirm button) on the third robot's directional keypad.
    private Optional<State> pressA() {
        Optional<State> result;
        
        if (thirdRobotArmPosition == DirectionalKeypadButton.A) {
            // The third robot is also about to press the A button!
            
            if (secondRobotArmPosition == DirectionalKeypadButton.A) {
                // The second robot is also about to press the A button!!
                
                if (!remainingCode.isEmpty() && remainingCode.getFirst() == firstRobotArmPosition) {
                    // The first robot arm is hovering over the correct numpad button!!!
                    var newRemainingCode = remainingCode.subList(1, remainingCode.size());
                    result = Optional.of(new State(newRemainingCode, firstRobotArmPosition, secondRobotArmPosition, thirdRobotArmPosition));
                } else {
                    // The first robot arm is hovering over the wrong button. Let's not press it.
                    result = Optional.empty();
                }
            } else {
                // The second robot is pressing a directional button. Move the first arm.
                result = firstRobotArmPosition.neighbouringButton(secondRobotArmPosition.getDirection())
                        .map(newFirstPosition -> new State(remainingCode, newFirstPosition, secondRobotArmPosition, thirdRobotArmPosition));
            }
        } else {
            // The third robot arm is pressing a directional button. Move the second arm.
            result = secondRobotArmPosition.neighbouringButton(thirdRobotArmPosition.getDirection())
                    .map(newSecondPosition -> new State(remainingCode, firstRobotArmPosition, newSecondPosition, thirdRobotArmPosition));
        }
        
        return result;
    }
    
    private Stream<State> stateTransitions() {
        return Stream.concat(        
            Stream.of(Direction.values())
                    .map(this::pressDirection)
                    .filter(Optional::isPresent)
                    .map(Optional::orElseThrow),
            pressA().stream());
    }


    private Graph<State, DefaultEdge> createGraph() {
        Graph<State, DefaultEdge> result = new SimpleDirectedGraph<>(DefaultEdge.class);
        
        IntStream.range(0, remainingCode.size() + 1)
                .mapToObj(i -> remainingCode.subList(i, remainingCode.size()))
                .forEach(code -> Stream.of(NumericKeypadButton.values())
                        .forEach(firstPosition -> Stream.of(DirectionalKeypadButton.values())
                                .forEach(secondPosition -> Stream.of(DirectionalKeypadButton.values())
                                        .map(thirdPosition -> new State(code, firstPosition, secondPosition, thirdPosition))
                                        .forEach(result::addVertex))));
        
        result.vertexSet()
                .forEach(state -> state.stateTransitions()
                        .forEach(nextState -> result.addEdge(state, nextState)));
        
        return result;
    }
    
    /// Fewest number of button presses needed to cause the robot in front of the door to type the code
    int buttonPresses() {
        var graph = createGraph();
        
        ShortestPathAlgorithm<State, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);
        
        return algorithm.getPath(this, END_STATE).getLength();
    }
    
}

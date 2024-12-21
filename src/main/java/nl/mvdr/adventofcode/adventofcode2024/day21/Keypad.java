package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;

record Keypad<B extends Enum<B> & KeypadButton<B>>(Graph<B, DirectionalKeypadButtonPress> graph) {

    static <B extends Enum<B> & KeypadButton<B>> Keypad<B> create(Class<B> buttonClass) {
        Graph<B, DirectionalKeypadButtonPress> graph = new SimpleGraph<>(DirectionalKeypadButtonPress.class);
        
        Stream.of(buttonClass.getEnumConstants())
                .forEach(graph::addVertex);
        
        graph.vertexSet()
                .forEach(vertex -> Stream.of(DirectionalKeypadButton.values())
                        .filter(button -> button.getDirection() != null)
                        .filter(button -> vertex.neighbouringButton(button.getDirection()) != null)
                        .forEach(button -> graph.addEdge(vertex, vertex.neighbouringButton(button.getDirection()).orElseThrow(), new DirectionalKeypadButtonPress(button))));
        
        return new Keypad<>(graph);
    }
}

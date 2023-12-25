package nl.mvdr.adventofcode.adventofcode2023.day25;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.Node;
import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/25">Snowverload</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SnowverloadPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnowverloadPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var nodes = lines.map(SnowverloadPart1::toNode).toList();
        var graph = Factory.graph()
                .with(nodes);
        
        try {
            File file = new File("C:\\temp\\graph.png"); // TODO use an actual temp file
            Graphviz.fromGraph(graph).render(Format.PNG).toFile(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        
        return 0; // TODO
    }
    
    /**
     * Creates a node and its edges, based on a single line from the wiring diagram.
     * 
     * @param line a line from the wiring diagram, for example: "jqt: rhn xhk nvd"
     */
    private static Node toNode(String text) {
        var parts = text.split(": ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse: " + text);
        }
        var result = Factory.node(parts[0]);
        for (String connectedNode : parts[1].split(" ")) {
            result = result.link(Factory.node(connectedNode));
        }
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new SnowverloadPart1();

        var result = instance.solve("input-day25-2023.txt");

        LOGGER.info(result);
    }
}
 
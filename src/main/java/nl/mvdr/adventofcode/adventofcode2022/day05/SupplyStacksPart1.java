package nl.mvdr.adventofcode.adventofcode2022.day05;

import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/05">Supply Stacks</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SupplyStacksPart1 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplyStacksPart1.class);

    @Override
    public String solve(Stream<String> linesStream) {
        var lines = linesStream.collect(Collectors.toList());
        var emptyLineIndex = lines.indexOf("");
        if (emptyLineIndex < 0) {
            throw new IllegalArgumentException("Unable to parse input");
        }
        
        var stacks = Stacks.parse(lines.subList(0, emptyLineIndex));
        LOGGER.debug("Stacks: {}", stacks);
        lines.subList(emptyLineIndex + 1, lines.size())
                .stream()
                .map(RearrangementProcedureStep::parse)
                .forEach(step -> step.perform(stacks));
        
        return stacks.stream()
                .map(Queue::peek)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new SupplyStacksPart1();

        var result = instance.solve("input-day05-2022.txt");

        LOGGER.info(result);
    }
}
 
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
class SupplyStacks implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplyStacks.class);

    private final Crane crane;
    
    /**
     * Constructor.
     * 
     * @param crane the crane to perform the reorganization
     */
    SupplyStacks(Crane crane) {
        super();
        this.crane = crane;
    }
    
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
                .map(Step::parse)
                .forEach(step -> crane.perform(step, stacks));
        
        return stacks.stream()
                .map(Queue::peek)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
 
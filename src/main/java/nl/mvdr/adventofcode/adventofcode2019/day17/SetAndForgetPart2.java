package nl.mvdr.adventofcode.adventofcode2019.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 17 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/17">Set and Forget</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SetAndForgetPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetAndForgetPart2.class);

    /** Buffer for unprocessed output of the Intcode computer. */
    private final List<Long> programOutputBuffer;

    /** Constructor. */
    public SetAndForgetPart2() {
        super();
        this.programOutputBuffer = new ArrayList<>();
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return amount of space dust collected
     */
    @Override
    public long solve(Stream<String> lines) {
        Program program = Program.parse(lines.findFirst().orElseThrow(),
                () -> { throw new NoSuchElementException("No input available"); }, 
                this::handleOutput);
        program = program.set(0, 2L);
        program.execute();
        
        return programOutputBuffer.get(programOutputBuffer.size() - 1).longValue();
    }
    
    /**
     * Handles an output value from the Intcode program.
     * 
     * @param value value to be handled
     */
    private void handleOutput(long value) {
        if ((char) value == '\n') {
            // Complete line of output received. Flush to log.
            String scaffoldString = programOutputBuffer.stream()
                    .mapToInt(Math::toIntExact)
                    .mapToObj(i -> "" + (char) i)
                    .collect(Collectors.joining());
            LOGGER.info(scaffoldString);
            programOutputBuffer.clear();
        } else {
            programOutputBuffer.add(Long.valueOf(value));
        }
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SetAndForgetPart2 instance = new SetAndForgetPart2();

        String result = instance.solve("input-day17-2019.txt");

        LOGGER.info(result);
    }
}
 
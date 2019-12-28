package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for handling output from Intcode programs in ASCII format.
 *
 * @author Martijn van de Rijdt
 */
public class AsciiOutputDebugLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsciiOutputDebugLogger.class);
    
    /** Buffer for unprocessed output of the Intcode computer. */
    private final List<Long> programOutputBuffer = new ArrayList<>();
    
    /**
     * Handles an output value from the Intcode program.
     * 
     * This method is intended to be passed to {@link Program#withOutput(java.util.function.LongConsumer)}.
     * 
     * @param value value to be handled
     */
    public void handleOutput(long value) {
        if ((char) value == '\n') {
            // Complete line of ASCII output received.
            if (LOGGER.isDebugEnabled()) {
                String outputLine = programOutputBuffer.stream()
                        .mapToInt(Math::toIntExact)
                        .mapToObj(i -> "" + (char) i)
                        .collect(Collectors.joining());
                LOGGER.debug(outputLine);
            }
            programOutputBuffer.clear();
        } else {
            programOutputBuffer.add(Long.valueOf(value));
        }
    }
    
    /** @return the latest unprocessed value, received from the Intcode computer */
    public long getValue() {
        return programOutputBuffer.get(programOutputBuffer.size() - 1).longValue();
    }
}

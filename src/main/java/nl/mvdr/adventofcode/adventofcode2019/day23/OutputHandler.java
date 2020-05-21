package nl.mvdr.adventofcode.adventofcode2019.day23;

import java.util.LinkedList;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Queue;

/**
 * Output handler for a single computer.
 *
 * Note that this class is mutable. Instances cannot be reused for multiple computers.
 *
 * @author Martijn van de Rijdt
 */
class OutputHandler {
    /** Input queues for computers. Keys are addresses, values are queues of input values for the computer with the given address. */
    private final Map<Long, Queue<Long>> inputs;
    
    /** Target address for the packet currently being sent. */
    private OptionalLong address;
    
    /** X coordinate for the packet currently being sent. */
    private OptionalLong x;
    
    /**
     * Constructor.
     * 
     * @param inputs inputs
     */
    OutputHandler(Map<Long, Queue<Long>> inputs) {
        super();
        this.inputs = inputs;
        this.address = OptionalLong.empty();
        this.x = OptionalLong.empty();
    }
    
    /**
     * Handles an output value from the computer.
     * 
     * @param outputValue output value
     */
    void handleOutput(long outputValue) {
        if (address.isEmpty()) {
            // output value is an address
            address = OptionalLong.of(outputValue);
        } else if (x.isEmpty()) {
            // output value is an x coordinate
            x = OptionalLong.of(outputValue);
        } else {
            // output value is a y coordinate: package complete
            Queue<Long> targetQueue = inputs.computeIfAbsent(Long.valueOf(address.getAsLong()), a -> new LinkedList<>());
            targetQueue.add(Long.valueOf(x.getAsLong()));
            targetQueue.add(Long.valueOf(outputValue));
            address = OptionalLong.empty();
            x = OptionalLong.empty();
        }
    }
}

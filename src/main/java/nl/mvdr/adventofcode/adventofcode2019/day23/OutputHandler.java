package nl.mvdr.adventofcode.adventofcode2019.day23;

import java.util.Map;
import java.util.OptionalLong;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Output handler for a single computer.
 *
 * Note that this class is mutable. Instances cannot be reused for multiple computers.
 *
 * @author Martijn van de Rijdt
 */
class OutputHandler {
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(OutputHandler.class);
    
    /** Input queues for computers. Keys are addresses, values are queues of input values for the computer with the given address. */
    private final Map<Long, Queue<Long>> inputs;
    
    /** The Not Always Transmitting Device. */
    private final Nat nat;
    
    /** Target address for the packet currently being sent. */
    private OptionalLong targetAddress;
    
    /** X coordinate for the packet currently being sent. */
    private OptionalLong x;
    
    /**
     * Constructor.
     * 
     * @param inputs inputs
     */
    OutputHandler(Map<Long, Queue<Long>> inputs, Nat nat) {
        super();
        this.inputs = inputs;
        this.nat = nat;
        this.targetAddress = OptionalLong.empty();
        this.x = OptionalLong.empty();
    }
    
    /**
     * Handles an output value from the computer.
     * 
     * @param outputValue output value
     */
    void handleOutput(long outputValue) {
        if (targetAddress.isEmpty()) {
            // output value is an address
            targetAddress = OptionalLong.of(outputValue);
        } else if (x.isEmpty()) {
            // output value is an x coordinate
            x = OptionalLong.of(outputValue);
        } else {
            // Packet complete.
            LOGGER.debug("{} <- ({}, {})", Long.valueOf(targetAddress.getAsLong()), Long.valueOf(x.getAsLong()), Long.valueOf(outputValue));
            
            if (targetAddress.getAsLong() == Nat.ADDRESS) {
                // Send to NAT.
                nat.handlePacket(x.getAsLong(), outputValue);
            } else {
                // Send to target computer.
                Queue<Long> targetQueue = inputs.get(Long.valueOf(targetAddress.getAsLong()));
                targetQueue.add(Long.valueOf(x.getAsLong()));
                targetQueue.add(Long.valueOf(outputValue));
            }
            targetAddress = OptionalLong.empty();
            x = OptionalLong.empty();
        }
    }
}

package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A pair of packets.
 *
 * @author Martijn van de Rijdt
 */
record PacketPair(PacketValue leftPacket, PacketValue rightPacket) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PacketPair.class);
    
    /**
     * Parses puzzle input.
     * 
     * @param lines puzzle input
     * @return list of packet pairs
     */
    static List<PacketPair> parsePairs(List<String> lines) {
        List<PacketPair> result = new ArrayList<>();
        var index = 0;
        while (index < lines.size()) {
            var leftPacket = PacketValue.parse(lines.get(index));
            var rightPacket = PacketValue.parse(lines.get(index + 1));
            result.add(new PacketPair(leftPacket, rightPacket));
            index = index + 3;
        }
        
        return result;
    }
    
    /**
     * @return whether this pair's values are in the correct order
     */
    boolean isInCorrectOrder() {
        LOGGER.debug("Comparing left packet {} to right packet {}", leftPacket, rightPacket);
        boolean result = leftPacket.compareTo(rightPacket) <= 0;
        if (result) {
            LOGGER.debug("Packets are in the correct order.");
        } else {
            LOGGER.debug("Packets are NOT in the correct order.");
        }
        return result;
    }
}

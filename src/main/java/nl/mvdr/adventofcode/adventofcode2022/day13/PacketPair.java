package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.List;

/**
 * A pair of packets.
 *
 * @author Martijn van de Rijdt
 */
record PacketPair(PacketValue leftPacket, PacketValue rightPacket) {
    
    /**
     * Parses puzzle input.
     * 
     * @param lines puzzle input
     * @return list of packet pairs
     */
    static List<PacketPair> parse(List<String> lines) {
        return List.of(); // TODO implement
    }
    
    /**
     * @return whether this pair's values are in the correct order
     */
    boolean isInCorrectOrder() {
        return leftPacket.compareTo(rightPacket) <= 0;
    }
}

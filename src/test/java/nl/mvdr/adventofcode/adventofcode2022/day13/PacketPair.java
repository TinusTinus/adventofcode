package nl.mvdr.adventofcode.adventofcode2022.day13;

/**
 * A pair of packets.
 *
 * @author Martijn van de Rijdt
 */
record PacketPair(PacketValue leftPacket, PacketValue rightPacket) {
    
    /**
     * @return whether this pair's values are in the correct order
     */
    boolean isInCorrectOrder() {
        return leftPacket.compareTo(rightPacket) <= 0;
    }
}

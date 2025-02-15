package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.List;
import java.util.stream.Stream;

/**
 * A value in a packet.
 *
 * @author Martijn van de Rijdt
 */
interface PacketValue extends Comparable<PacketValue> {
    
    /**
     * Parses puzzle input.
     * 
     * @param lines puzzle input
     * @return all packets in the given puzzle input
     */
    static List<PacketValue> parsePackets(Stream<String> lines) {
        return PacketPair.parsePairs(lines.toList())
                .stream()
                .flatMap(pair -> Stream.of(pair.leftPacket(), pair.rightPacket()))
                .toList();
    }
    
    /**
     * Parses puzzle input.
     * 
     * @param text textual representation of a packet value
     * @return the packet value
     */
    static PacketValue parse(String text) {
        PacketValue result;
        if (text.startsWith("[")) {
            result = ListValue.parse(text);
        } else {
            result = IntValue.parse(text);
        };
        return result;
    }
}

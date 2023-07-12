package nl.mvdr.adventofcode.adventofcode2022.day13;

/**
 * A value in a packet.
 *
 * @author Martijn van de Rijdt
 */
interface PacketValue extends Comparable<PacketValue> {
    
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

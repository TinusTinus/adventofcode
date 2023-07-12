package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.List;

/**
 * An integer value within a packet.
 *
 * @author Martijn van de Rijdt
 */
record IntValue(int value) implements PacketValue {

    /**
     * Parses puzzle input.
     * 
     * @param text textual representation of a packet value
     * @return the packet value
     */
    static IntValue parse(String text) {
        var value = Integer.parseInt(text);
        return new IntValue(value);
    }
    
    @Override
    public int compareTo(PacketValue other) {
        int result;
        if (other instanceof IntValue otherIntValue) {
            result = Integer.compare(value, otherIntValue.value);
        } else if (other instanceof ListValue) {
            // convert the integer to a list which contains that integer as its only value
            var wrapped = new ListValue(List.of(this));
            // then retry the comparison
            result = wrapped.compareTo(other);
        } else {
            throw new IllegalArgumentException("Unsupported packet value type: " + other);
        }
        return result;
    }
    
    @Override
    public String toString() {
        return "" + value;
    }
}

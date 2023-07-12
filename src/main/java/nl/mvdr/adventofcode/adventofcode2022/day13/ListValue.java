package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.List;
import java.util.stream.Stream;

/**
 * A list value within a packet.
 *
 * @author Martijn van de Rijdt
 */
record ListValue(List<PacketValue> elements) implements PacketValue {

    private static final ListComparator LIST_COMPARATOR = new ListComparator();

    /**
     * Parses puzzle input.
     * 
     * @param text textual representation of a packet value
     * @return the packet value
     */
    static ListValue parse(String text) {
        if (!text.startsWith("[") || !text.endsWith("]")) {
            throw new IllegalArgumentException("Not a valid list: " + text);
        }
        var elementsString = text.substring(1, text.length() - 1);
        var elements = Stream.of(elementsString.split(",")) // TODO nope, there may be sublists!
                .map(PacketValue::parse)
                .toList();
        return new ListValue(elements);
    }
    
    @Override
    public int compareTo(PacketValue other) {
        int result;
        if (other instanceof ListValue otherListValue) {
            result = LIST_COMPARATOR.compare(elements, otherListValue.elements);
        } else if (other instanceof IntValue) {
            result = -(other.compareTo(this));
        } else {
            throw new IllegalArgumentException("Unsupported packet value type: " + other);
        }
        return result;
    }
}

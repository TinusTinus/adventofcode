package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.List;

/**
 * A list value within a packet.
 *
 * @author Martijn van de Rijdt
 */
record ListValue(List<PacketValue> elements) implements PacketValue {

    private static final ListComparator LIST_COMPARATOR = new ListComparator();

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

package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.ArrayList;
import java.util.List;

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
        List<PacketValue> elements = new ArrayList<>();
        if (!elementsString.isEmpty()) {
            var index = 0;
            var listDepth = 0;
            var startOfElement = index;
            while (index < elementsString.length()) {
                var character = elementsString.charAt(index);
                if (character == ',' && listDepth == 0) {
                    elements.add(PacketValue.parse(elementsString.substring(startOfElement, index)));
                    startOfElement = index + 1;
                } else if (character == '[') {
                    listDepth++;
                } else if (character == ']') {
                    listDepth--;
                }
                index++;
            }
            if (listDepth != 0) {
                throw new IllegalStateException();
            }
            elements.add(PacketValue.parse(elementsString.substring(startOfElement, index)));
        }
        
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
    
    @Override
    public String toString() {
        return elements.toString();
    }
}

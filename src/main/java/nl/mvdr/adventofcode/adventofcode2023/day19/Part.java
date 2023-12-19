package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A part.
 * 
 * @author Martijn van de Rijdt
 */
record Part(Map<Property, Integer> properties) {
    
    /**
     * Parses a list of parts.
     * 
     * @param lines list of strings where each string represents a part
     * @return parts
     */
    static List<Part> parse(List<String> lines) {
        return lines.stream()
                .map(Part::parse)
                .toList();
    }
    
    /**
     * Parses a part.
     * 
     * @param text textual representation of a part, for example: "{x=787,m=2655,a=1222,s=2876}"
     * @return part
     */
    private static Part parse(String text) {
        if (!text.startsWith("{") || !text.endsWith("}")) {
            throw new IllegalArgumentException("Missing braces: " + text);
        }
        // Drop the brackets and split on commas
        var propertyStrings = text.substring(1, text.length() - 1).split(",");
        // Parse each property
        var properties = Stream.of(propertyStrings)
                .map(propertyString -> propertyString.split("="))
                .collect(Collectors.toMap(sides -> Property.parse(sides[0]), sides -> Integer.valueOf(sides[1])));
        return new Part(properties);
    }
    
    /**
     * @return total ratings of this part
     */
    int totalRatings() {
        return properties.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}

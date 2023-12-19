package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.List;

/**
 * A part.
 * 
 * @param x extremely cool looking
 * @param m musical (it makes a noise when you hit it)
 * @param a aerodynamic
 * @param s shiny
 * @author Martijn van de Rijdt
 */
// TODO consider representing the properties as a Map<String, Integer> instead?
record Part(int x, int m, int a, int s) {
    
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
        var propertyStrings = text.substring(1, text.length() - 1).split(",");
        if (propertyStrings.length != 4) {
            throw new IllegalArgumentException("Unexpected number of properties: " + text);
        }
        var x = parseProperty("x", propertyStrings[0]);
        var m = parseProperty("m", propertyStrings[1]);
        var a = parseProperty("a", propertyStrings[2]);
        var s = parseProperty("s", propertyStrings[3]);
        
        return new Part(x, m, a, s);
    }
    
    /**
     * Parses a property value.
     * 
     * @param expectedPropertyName expected property name: "x", "m", "a" or "s"
     * @param text textual representation of a property, for example: "x=787"
     * @return property value
     */
    private static int parseProperty(String expectedPropertyName, String text) {
        var expectedPrefix = expectedPropertyName + "=";
        if (!text.startsWith(expectedPrefix)) {
            throw new IllegalArgumentException("Property does not start with '" + expectedPrefix + "': " + text);
        }
        var valueString = text.substring(2);
        return Integer.parseInt(valueString);
    }
}

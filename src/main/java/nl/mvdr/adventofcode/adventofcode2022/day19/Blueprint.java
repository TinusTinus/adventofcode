package nl.mvdr.adventofcode.adventofcode2022.day19;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Representation of a single blueprint.
 *
 * @param id the identification of this blueprint
 * @param resourceRequirements per resource type: the resources required to build a robot for gathering that resource
 * @author Martijn van de Rijdt
 */
record Blueprint(int id, Map<Resource, ResourceRequirement> resourceRequirements) {
    
    /**
     * Parses a textual representation of a blueprint.
     * 
     * @param text line from the puzzle input
     * @return blueprint
     */
    static Blueprint parse(String text) {
        var indexOfColon = text.indexOf(":");
        var id = Integer.parseInt(text.substring("Blueprint ".length(), indexOfColon));
        var remainingText = text.substring(indexOfColon + 1);
        
        Map<Resource, ResourceRequirement> resourceRequirements = new EnumMap<>(Resource.class);
        
        Pattern pattern = Pattern.compile("([a-z]*) robot costs ([a-z \\d]*)\\.");
        Stream.of(remainingText.split(" Each "))
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .forEach(matcher -> {
                    var robotType = Resource.parse(matcher.group(1));
                    var requirement = ResourceRequirement.parse(matcher.group(2));
                    resourceRequirements.put(robotType, requirement);
                });
        
        return new Blueprint(id, resourceRequirements);
    }
    
    /**
     * @return quality level of this blueprint
     */
    int computeQualityLevel() {
        return id * computeMaxGeodes();
    }
    
    /**
     * @return the maximum number of geodes that can be opened with this blueprint
     */
    private int computeMaxGeodes() {
        return 0; // TODO implement
    }
}

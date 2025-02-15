package nl.mvdr.adventofcode.adventofcode2022.day19;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * The amount of resources required to build a new robot of a certain type.
 *
 * @author Martijn van de Rijdt
 */
record ResourceRequirement(Map<Resource, Integer> requiredResources) {
    /**
     * Parses a textual representation of a resource requirement.
     * 
     * @param text textual representation of a resource requirement, for example: "4 ore" or "2 ore and 12 clay"
     * @return resource requirement
     */
    static ResourceRequirement parse(String text) {
        Map<Resource, Integer> requiredResources = new EnumMap<>(Resource.class);
        Stream.of(text.split(" and "))
                .map(part -> part.split(" "))
                .forEach(parts -> {
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Unable to parse: " + text);
                    }
                    int amount = Integer.parseInt(parts[0]);
                    Resource resource = Resource.parse(parts[1]);
                    requiredResources.put(resource, Integer.valueOf(amount));
                });
        return new ResourceRequirement(requiredResources);
    }
    
    /**
     * Returns how many units of the given resource are required.
     * 
     * @param resource resource
     * @return resource amount
     */
    int requires(Resource resource) {
        return requiredResources.getOrDefault(resource, Integer.valueOf(0)).intValue();
    }
}

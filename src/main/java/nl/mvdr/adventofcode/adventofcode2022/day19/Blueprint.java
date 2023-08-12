package nl.mvdr.adventofcode.adventofcode2022.day19;

import java.util.Map;

/**
 * Representation of a single blueprint.
 *
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
        return null; // TODO
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

package nl.mvdr.adventofcode.adventofcode2023.day15;

/**
 * A step.
 *
 * @author Martijn van de Rijdt
 */
sealed interface Step permits RemoveLensStep, AddLensStep {
    
    /**
     * Parses the given string representation of a step.
     * 
     * @param stringRepresentation string representation of a step, such as "rn=1" or "cm-"
     * @return step
     */
    static Step parse(String stringRepresentation) {
        Step result;
        if (stringRepresentation.endsWith("-")) {
            result = RemoveLensStep.parse(stringRepresentation);
        } else if (stringRepresentation.contains("=")) {
            result = AddLensStep.parse(stringRepresentation);
        } else {
            throw new IllegalArgumentException(stringRepresentation);
        }
        return result;
    }
    
    /**
     * Performs this step.
     * 
     * @param boxes array of the 256 boxes
     */
    default void performStep(Box[] boxes) {
        var hash = HashAlgorithm.hash(label());
        performStep(boxes[hash]);
    }
    
    /**
     * @return label for this step
     */
    String label();
    
    /**
     * Performs this step.
     * 
     * @param box the applicable box
     */
    void performStep(Box box);
}

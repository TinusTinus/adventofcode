package nl.mvdr.adventofcode.adventofcode2023.day15;

/**
 * Step to remove a lens from a box.
 *
 * @author Martijn van de Rijdt
 */
record RemoveLensStep(String label) implements Step {
    
    /**
     * Parses a "remove lens" step from the initialization sequence.
     * 
     * @param stringRepresentation string representation of a step, for example: "cm-"
     * @return step
     */
    static RemoveLensStep parse(String stringRepresentation) {
        if (!stringRepresentation.endsWith("-")) {
            throw new IllegalArgumentException(stringRepresentation);
        }
        var label = stringRepresentation.substring(0, stringRepresentation.length() - 1);
        return new RemoveLensStep(label);
    }
    
    @Override
    public void performStep(Box box) {
        box.remove(label);
    }
}

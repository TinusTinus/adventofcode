package nl.mvdr.adventofcode.adventofcode2023.day15;

/**
 * Representation of a lens.
 *
 * @author Martijn van de Rijdt
 */
record Lens(String label, int focalLength) {
    /**
     * Parses an "add lens" step from the initialization sequence.
     * 
     * @param addLensStep string representation of a step, for example: "rn=1"
     * @return lens specified in the given step
     */
    static Lens parse(String addLensStep) {
        var parts = addLensStep.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException(addLensStep);
        }
        var label = parts[0];
        var focalLength = Integer.parseInt(parts[1]);
        return new Lens(label, focalLength);
    }
    
    /**
     * Determines the focusing power of this lens.
     * 
     * @param slotNumber slot number within the box; note that numbering starts at 1
     */
    int focusingPower(int slotNumber) {
        var boxNumber = HashAlgorithm.hash(label);
        return (1 + boxNumber) * slotNumber * focalLength;
    }
}

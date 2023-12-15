package nl.mvdr.adventofcode.adventofcode2023.day15;

/**
 * Step to add a lens to a box.
 *
 * @author Martijn van de Rijdt
 */
record AddLensStep(Lens lens) implements Step {

    /**
     * Parses an "add lens" step from the initialization sequence.
     * 
     * @param stringRepresentation string representation of a step, for example: "rn=1"
     * @return step
     */
    static AddLensStep parse(String stringRepresentation) {
        var step = Lens.parse(stringRepresentation);
        return new AddLensStep(step);
    }
    
    @Override
    public String label() {
        return lens.label();
    }

    @Override
    public void performStep(Box box) {
        box.add(lens);
    }
}

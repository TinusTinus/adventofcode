package nl.mvdr.adventofcode.adventofcode2020.day21;

/**
 * Representation of an allergen.
 *
 * Each allergen is found in exactly one ingredient.
 *
 * @author Martijn van de Rijdt
 */
record Allergen(String name) {
    
    @Override
    public String toString() {
        return name;
    }
}

package nl.mvdr.adventofcode.adventofcode2020.day21;

/**
 * Representation of an ingredient.
 *
 * Each ingredient contains zero or one allergen.
 *
 * @author Martijn van de Rijdt
 */
record Ingredient(String name) {
    
    @Override
    public String toString() {
        return name;
    }
}

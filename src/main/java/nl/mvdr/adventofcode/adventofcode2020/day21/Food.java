package nl.mvdr.adventofcode.adventofcode2020.day21;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of a food.
 *
 * @author Martijn van de Rijdt
 */
record Food(Set<Ingredient> ingredients, Set<Allergen> allergens) {

    /**
     * Parses puzzle input.
     * 
     * @param lines lines from the puzzle input
     * @return list of foods
     */
    static Set<Food> parse(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isEmpty))
                .map(Food::parse)
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a single line from the puzzle input.
     * 
     * @param line line from the puzzle input, for example: "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)"
     * @return food represented by the given line
     */
    private static Food parse(String line) {
        String[] parts = line.split(" \\(contains ");
        
        Set<Ingredient> ingredients = Stream.of(parts[0].split(" "))
                .map(Ingredient::new)
                .collect(Collectors.toSet());
        
        // Drop the closing bracket
        String allergenPart = parts[1].substring(0, parts[1].length() - 1);
        Set<Allergen> allergens = Stream.of(allergenPart.split(", "))
                .map(Allergen::new)
                .collect(Collectors.toSet());
        
        return new Food(ingredients, allergens);
    }
}

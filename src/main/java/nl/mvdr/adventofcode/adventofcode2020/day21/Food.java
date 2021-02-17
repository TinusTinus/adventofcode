package nl.mvdr.adventofcode.adventofcode2020.day21;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of a food.
 *
 * @author Martijn van de Rijdt
 */
record Food(List<String> ingredients, List<String> allergens) {

    /**
     * Parses puzzle input.
     * 
     * @param lines lines from the puzzle input
     * @return list of foods
     */
    static List<Food> parse(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isEmpty))
                .map(Food::parse)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a single line from the puzzle input.
     * 
     * @param line line from the puzzle input, for example: "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)"
     * @return food represented by the given line
     */
    private static Food parse(String line) {
        String[] parts = line.split(" \\(contains");
        
        List<String> ingredients = Arrays.asList(parts[0].split(" "));
        
        // Drop the closing bracket
        String allergenPart = parts[1].substring(0, parts[1].length() - 1);
        List<String> allergens = Arrays.asList(allergenPart.split(", "));
        
        return new Food(ingredients, allergens);
    }
}

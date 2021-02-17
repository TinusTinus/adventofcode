package nl.mvdr.adventofcode.adventofcode2020.day21;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representation of a food.
 *
 * @author Martijn van de Rijdt
 */
record Food(Set<Ingredient> ingredients, Set<Allergen> allergens) {

    private static final Logger LOGGER = LoggerFactory.getLogger(Food.class);
    
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
    
    /**
     * Finds, for each known allergen, which ingredient contains that allergen.
     * 
     * @param foods foods
     * @return mapping of allergen to the ingredient containing it
     */
    static Map<Allergen, Ingredient> findIngredientsWithAllergens(Set<Food> foods) {
        Set<Allergen> allAllergens = foods.stream()
                .flatMap(food -> food.allergens().stream())
                .collect(Collectors.toSet());
        
        Map<Allergen, Ingredient> result = new HashMap<>();
        
        while (result.size() != allAllergens.size()) {
            for (Allergen allergen : allAllergens) {
                if (!result.containsKey(allergen)) {
                    Set<Food> foodsWithAllergen = foods.stream()
                            .filter(food -> food.allergens().contains(allergen))
                            .collect(Collectors.toSet());
                    
                    Set<Ingredient> possibleIngredients = foodsWithAllergen.stream()
                            .flatMap(food -> food.ingredients().stream())
                            .filter(Predicate.not(result.values()::contains))
                            .filter(ingredient -> foodsWithAllergen.stream().allMatch(food -> food.ingredients().contains(ingredient)))
                            .collect(Collectors.toSet());
                            
                    if (possibleIngredients.size() == 1) {
                        Ingredient ingredient = possibleIngredients.iterator().next();
                        LOGGER.debug("Allergen {} occurs in ingredient {}", allergen, ingredient);
                        result.put(allergen, ingredient);
                    }
                }
            }
        }
        return result;
    }
}

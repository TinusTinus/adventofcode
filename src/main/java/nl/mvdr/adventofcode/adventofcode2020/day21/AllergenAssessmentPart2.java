package nl.mvdr.adventofcode.adventofcode2020.day21;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;

/**
 * Solution to the day 21 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/21">Allergen Assessment</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AllergenAssessmentPart2 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllergenAssessmentPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return canonical dangerous ingredient list
     */
    @Override
    public String solve(Stream<String> lines) {
        Set<Food> foods = Food.parse(lines);
        
        return Food.findIngredientsWithAllergens(foods)
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().name()))
                .map(Entry::getValue)
                .map(Ingredient::name)
                .collect(Collectors.joining(","));
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AllergenAssessmentPart2 instance = new AllergenAssessmentPart2();

        String result = instance.solve("input-day21-2020.txt");

        LOGGER.info(result);
    }
}
 
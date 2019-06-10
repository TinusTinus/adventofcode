package nl.mvdr.adventofcode.adventofcode2018.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 14 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/14">Chocolate Charts</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChocolateChartsPart2 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChocolateChartsPart2.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        String inputString = Files.lines(inputFilePath)
                .findFirst()
                .get();
        LOGGER.debug("Input: {}", inputString);
        
        List<Integer> recipes = new ArrayList<>(List.of(Integer.valueOf(3), Integer.valueOf(7)));
        int firstElfIndex = 0;
        int secondElfIndex = 1;
        
        String result = null;
        
        log(recipes, firstElfIndex, secondElfIndex);
        while (result == null) {
            int firstElfRecipe = recipes.get(firstElfIndex).intValue();
            int secondElfRecipe = recipes.get(secondElfIndex).intValue();
            int sum = firstElfRecipe + secondElfRecipe;
            
            // sum is the sum of two digits, so it is at most 9 + 9 = 18: one or two digits
            if (10 <= sum) {
                recipes.add(Integer.valueOf(sum / 10));
            }
            recipes.add(Integer.valueOf(sum % 10));
            
            firstElfIndex = (firstElfIndex + 1 + firstElfRecipe) % recipes.size();
            secondElfIndex = (secondElfIndex + 1 + secondElfRecipe) % recipes.size();
            
            log(recipes, firstElfIndex, secondElfIndex);
            
            // We just added 1 or 2 recipes.
            // Only inspect the last part of the recipe list; the rest has already been inspected before.
            int skipped = Math.max(recipes.size() - inputString.length() - 1, 0);
            int index = recipes.subList(skipped, recipes.size())
                    .stream()
                    .map(i -> i.toString())
                    .collect(Collectors.joining())
                    .indexOf(inputString);
            if (0 <= index) {
                result = "" + (index + skipped);
            }
            
            if (recipes.size() % 100_000 < 2) {
                LOGGER.debug("Generated {} recipes", Integer.valueOf(recipes.size()));
            }
        }
        
        return result;
    }
    
    private static void log(List<Integer> recipes, int firstElfIndex, int secondElfIndex) {
        if (LOGGER.isDebugEnabled()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i != recipes.size(); i++) {
                if (i == firstElfIndex) {
                    builder.append("(");
                } else if (i == secondElfIndex) {
                    builder.append("[");
                } else {
                    builder.append(" ");
                }
                
                builder.append(recipes.get(i));
                
                if (i == firstElfIndex) {
                    builder.append(")");
                } else if (i == secondElfIndex) {
                    builder.append("]");
                } else {
                    builder.append(" ");
                }
            }
            LOGGER.debug(builder.toString());
        }
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChocolateChartsPart2 solver = new ChocolateChartsPart2();
        String solution = solver.solve("input-day14-2018.txt");
        LOGGER.info(solution);
    }
}

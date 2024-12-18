package nl.mvdr.adventofcode.adventofcode2018.day02;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

/**
 * Solution to the day 2 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/2">Inventory Management
 * System</a>.
 *
 * @author Martijn van de Rijdt
 */
public class InventoryManagementSystemPart2 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryManagementSystemPart2.class);
    
    @Override
    public String solve(Stream<String> lines) {
        List<String> boxIds = lines
                // ignore empty lines (the last line in the file)
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        
        String result = null;
        for (String boxId0 : boxIds) {
            for (String boxId1 : boxIds) {
                if (boxId0.length() == boxId1.length()) {
                    int differingCharacters = 0;
                    StringBuilder resultBuilder = new StringBuilder();
                    for (int i = 0; i != boxId0.length(); i++) {
                        if (boxId0.charAt(i) == boxId1.charAt(i)) {
                            resultBuilder.append(boxId0.charAt(i));
                        } else {
                            differingCharacters++;
                        }
                    }
                    if (differingCharacters == 1) {
                        result = resultBuilder.toString();
                    }
                }
            }
        }
        
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        InventoryManagementSystemPart2 instance = new InventoryManagementSystemPart2();

        String result = instance.solve("input-day02-2018.txt");

        LOGGER.info(result);
    }
}

package nl.mvdr.adventofcode.adventofcode2022.day08;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/08">Treetop Tree House</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TreetopTreeHousePart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreetopTreeHousePart1.class);
    
    @Override
    public long solve(Stream<String> lines) {
        return Forest.parse(lines).countVisibleTrees();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new TreetopTreeHousePart1();

        var result = instance.solve("input-day08-2022.txt");

        LOGGER.info(result);
    }
}
 
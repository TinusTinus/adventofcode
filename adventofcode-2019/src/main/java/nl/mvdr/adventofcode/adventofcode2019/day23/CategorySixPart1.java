package nl.mvdr.adventofcode.adventofcode2019.day23;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 23 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/23">Category Six</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CategorySixPart1 extends CategorySix {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategorySixPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return the Y value of the first packet sent to address 255
     */
    @Override
    long solve(Nat nat, Map<Long, Queue<Long>> inputs, List<Program> computers) {
        int i = 0;
        while (!nat.containsValue()) {
            Program computer = computers.get(i);
            computer = computer.executeInstruction();
            computer = computer.executeUntilNextInput();
            computers.set(i, computer);
            i = (i + 1) % computers.size();
        }
        
        return nat.getY();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CategorySixPart1 instance = new CategorySixPart1();

        String result = instance.solve("input-day23-2019.txt");

        LOGGER.info(result);
    }
}
 
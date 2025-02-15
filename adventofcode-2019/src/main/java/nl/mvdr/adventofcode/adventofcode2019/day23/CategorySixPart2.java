package nl.mvdr.adventofcode.adventofcode2019.day23;

import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
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
public class CategorySixPart2 extends CategorySix {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategorySixPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the first Y value delivered by the NAT to the computer at address 0 twice in a row
     */
    @Override
    long solve(Nat nat, Map<Long, Queue<Long>> inputs, List<Program> computers) {
        OptionalLong previousY = OptionalLong.empty();
        boolean done = false;
        int i = 0;
        while (!done) {
            if (nat.containsValue() && inputs.values().stream().allMatch(Queue::isEmpty)) {
                // No more inputs: all computers are idling.
                if (previousY.isPresent() && nat.getY() == previousY.getAsLong()) {
                    // Same y value twice in a row
                    done = true;
                } else {
                    LOGGER.debug("{} <- {}", Long.valueOf(0L), nat);
                    Queue<Long> targetQueue = inputs.get(Long.valueOf(0L));
                    targetQueue.add(Long.valueOf(nat.getX()));
                    targetQueue.add(Long.valueOf(nat.getY()));
                    previousY = OptionalLong.of(nat.getY());
                }
            } else {
                Program computer = computers.get(i);
                computer = computer.executeInstruction();
                computer = computer.executeUntilNextInput();
                computers.set(i, computer);
                i = (i + 1) % computers.size();
            }
        }
        
        return nat.getY();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CategorySixPart2 instance = new CategorySixPart2();

        String result = instance.solve("input-day23-2019.txt");

        LOGGER.info(result);
    }
}
 
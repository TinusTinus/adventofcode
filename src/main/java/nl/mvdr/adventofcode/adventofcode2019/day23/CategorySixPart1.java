package nl.mvdr.adventofcode.adventofcode2019.day23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 23 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/23">Category Six</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CategorySixPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategorySixPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return position of card 2019, after shuffling a factory order deck of 10007 cards 
     */
    @Override
    public long solve(Stream<String> lines) {
        // Parse the input.
        Program program = Program.parse(lines.findFirst().orElseThrow());
        
        Nat nat = new Nat();
        
        // Input values, indexed by their address.
        Map<Long, Queue<Long>> inputs = new HashMap<>();
        
        List<Program> computers = LongStream.range(0L, 50L)
                // Make sure each program is provided with its address first.
                .mapToObj(address -> inputs.computeIfAbsent(Long.valueOf(address), a -> new LinkedList<>(Set.of(Long.valueOf(address)))))
                .map(queue -> program.withQueueInput(queue))
                .map(computer -> computer.withOutput(new OutputHandler(inputs, nat)::handleOutput))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        
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
 
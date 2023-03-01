package nl.mvdr.adventofcode.adventofcode2022.day05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Crane type.
 *
 * @author Martijn van de Rijdt
 */
enum Crane {
    /**
     * Moves a single crate at a time.
     */
    CRATE_MOVER_9000 {
        @Override
        void addCrates(List<Character> crates, Deque<Character> targetStack) {
            crates.forEach(targetStack::push);
        }
    },
    /**
     * Moves all crates in a step at once.
     */
    CRATE_MOVER_9001 {
        @Override
        void addCrates(List<Character> cratesTopToBottom, Deque<Character> targetStack) {
            // Reverse the list of crates to restore the original order
            List<Character> crates = new ArrayList<>(cratesTopToBottom);
            Collections.reverse(crates);
            
            crates.forEach(targetStack::push);
        }
    };

    private static final Logger LOGGER = LoggerFactory.getLogger(Crane.class);

    /**
     * Performs a step.
     * 
     * @param step   step to be performed
     * @param stacks the stacks of crates to be updated
     */
    void perform(Step step, List<Deque<Character>> stacks) {
        LOGGER.debug("Performing step: {}", step);

        List<Character> crates = IntStream.range(0, step.numberOfCrates())
                .mapToObj(i -> stacks.get(step.sourceStack() - 1).pop())
                .collect(Collectors.toList());
        
        LOGGER.debug("Moving crates from stack {} to stack {}: {}",
                Integer.valueOf(step.sourceStack()),
                Integer.valueOf(step.targetStack()),
                crates);

        addCrates(crates, stacks.get(step.targetStack() - 1));

        LOGGER.debug("Updated stacks: {}", stacks);
    }

    /**
     * Adds the given crates to the given stack.
     * 
     * @param crates the crates to add, in top-to-bottom order as they were stacked on their source stack!
     * @param targetStack target stack
     */
    abstract void addCrates(List<Character> crates, Deque<Character> targetStack);
}

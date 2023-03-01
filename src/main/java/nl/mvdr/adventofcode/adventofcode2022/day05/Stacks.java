package nl.mvdr.adventofcode.adventofcode2022.day05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Utility class for handling stacks.
 *
 * @author Martijn van de Rijdt
 */
class Stacks {
    
    /**
     * Private constructor to prevent accidental instantiation.
     */
    private Stacks() {
        // do nothing
    }
    
    /**
     * Parses the input representation of the stacks.
     * 
     * Note that the resulting list is 0-based (as are all standard list types in Java),
     * but the stack ids in the puzzle are 1-based!
     * 
     * Also note that the resulting queues are mutable.
     * 
     * @param input part of the puzzle input representing the stacks (up until and including the line containing the stack ids)
     * @return representation of the stacks, where each crate is represented by its letter
     */
    static List<Deque<Character>> parse(List<String> input) {
        var stackIdsString = input.get(input.size() - 1);
        var numberOfStacks = determineNumberOfStacks(stackIdsString);

        List<Deque<Character>> result = new ArrayList<>(numberOfStacks);
        IntStream.range(0, numberOfStacks).forEach(i -> result.add(new LinkedList<>()));
        
        // Process lines in reverse order (bottom of stack to top)
        for (int lineNumber = input.size() - 2; 0 <= lineNumber; lineNumber--) {
            String line = input.get(lineNumber);
            
            for (int stack = 0; stack * 4 + 1 < line.length(); stack++) {
                char crate = line.charAt(stack * 4 + 1);
                if (crate != ' ') {
                    result.get(stack).push(Character.valueOf(crate));
                }
            }
        }
        
        return Collections.unmodifiableList(result);
    }

    /**
     * Determines the number of stacks for this puzzle input.
     * 
     * @param stackIdsString line of the input containin stack ids, for example: " 1   2   3"
     * @return number of stacks
     */
    private static int determineNumberOfStacks(String stackIdsString) {
        return stackIdsString.chars()
                .filter(c -> c != ' ')
                .map(c -> Integer.parseInt("" + (char)c))
                .max()
                .orElseThrow(() -> new IllegalArgumentException("Unable to parse line: " + stackIdsString));
    }

}

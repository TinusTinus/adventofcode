package nl.mvdr.adventofcode.adventofcode2017.day10;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

/**
 * Solution to the day 10 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/10">Knot Hash</a>.
 * 
 * @param <R> result type
 *
 * @author Martijn van de Rijdt
 */
abstract class KnotHash<R> implements LinesSolver<R> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnotHash.class);
    
    private final int listSize;
    
    /** Constructor. */
    KnotHash() {
        super();
        this.listSize = 256;
    }
    
    /**
     * Constructor.
     * 
     * @param listSize list size
     */
    KnotHash(int listSize) {
        super();
        this.listSize = listSize;
    }

    @Override
    public R solve(Stream<String> lines) {
        String inputText = lines.findFirst().orElseThrow();
        return solveKnotHash(inputText);
    }

    /**
     * Solver method.
     * 
     * @param inputText input
     * @return solution to the puzzle for the given input
     */
    protected R solveKnotHash(String inputText) {
        List<Integer> inputLengths = inputLengths(inputText);
        return solve(sparseHash(inputLengths));
    }
    
    /**
     * Converts the given text into a list of input lengths, for computing the sparse hash.
     * 
     * @param inputText text
     * @return input lengths
     */
    abstract List<Integer> inputLengths(String inputText);

    /**
     * Performs the sparse hashing part of the computation.
     * 
     * @param inputLengths lengths from the puzzle input
     * @return sparse hash of the input
     */
    private Deque<Integer> sparseHash(List<Integer> inputLengths) {
        Deque<Integer> numbers = IntStream.range(0, listSize)
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

        // Invariant: the head of the deque is the current position.
        int skipSize = 0;
        int skipped = 0;
        for (int round = 0; round != rounds(); round++) {
            for (Integer inputLength : inputLengths) {
                LOGGER.debug("Processing input length {}", inputLength);
                // Reverse the first inputLength elements of the numbers.
                Deque<Integer> temp = new LinkedList<>();
                for (int i = 0; i != inputLength.intValue(); i++) {
                    temp.offerFirst(numbers.pollFirst());
                }
                while (!temp.isEmpty()) {
                    numbers.offerLast(temp.pollFirst());
                    skipped++;
                }
                for (int i = 0; i != skipSize; i++) {
                    numbers.offerLast(numbers.pollFirst());
                    skipped++;
                }
                skipSize++;
                LOGGER.debug("Numbers: {}", numbers);
            }
            LOGGER.debug("{} round(s) completed", Integer.valueOf(round));
        }
        
        // Move current position back to the original spot
        for (int i = skipped % listSize; i != listSize; i++) {
            numbers.offerLast(numbers.pollFirst());
        }
        LOGGER.debug("Final numbers: {}", numbers);
        return numbers;
    }
    
    /** @return the number of rounds used to calculate the sparse hash */
    abstract int rounds();
    
    /**
     * Solves the puzzle.
     * 
     * @param sparseHash sparse hash
     * @return puzzle solution
     */
    abstract R solve(Deque<Integer> sparseHash);
}

package nl.mvdr.adventofcode.adventofcode2020.day15;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 15 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/15">Rambunctious Recitation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RambunctiousRecitationPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RambunctiousRecitationPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return the 2020th number spoken
     */
    @Override
    public int solve(Stream<String> linesStream) {
        String startingNumbersLine = linesStream.findFirst().orElseThrow();
        List<Integer> startingNumbers = Stream.of(startingNumbersLine.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        // Array contains, per index, the number of the last turn when it was spoken, or 0 if it was never spoken.
        int[] spokenOnTurn = new int[2021];
        
        // First the starting numbers are spoken.
        for (int i = 0; i != startingNumbers.size() - 1; i++) {
            spokenOnTurn[startingNumbers.get(i).intValue()] = i + 1;
        }
        
        int lastSpoken = startingNumbers.get(startingNumbers.size() - 1).intValue();
        for (int turn = startingNumbers.size() + 1; turn != 2021; turn++) {
            int nextToSpeak;
            if (spokenOnTurn[lastSpoken] == 0) {
                nextToSpeak = 0;
            } else {
                nextToSpeak = turn - 1 - spokenOnTurn[lastSpoken];
            }
            spokenOnTurn[lastSpoken] = turn - 1;
            lastSpoken = nextToSpeak;
        }
        return lastSpoken;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RambunctiousRecitationPart1 instance = new RambunctiousRecitationPart1();

        String result = instance.solve("input-day15-2020.txt");

        LOGGER.info(result);
    }
}
 
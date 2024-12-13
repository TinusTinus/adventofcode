package nl.mvdr.adventofcode.adventofcode2020.day15;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 15 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/15">Rambunctious Recitation</a>.
 *
 * @author Martijn van de Rijdt
 */
class RambunctiousRecitation implements IntSolver {

    private final int turns;
    
    /**
     * Constructor.
     * 
     * @param turns number of turns to play
     */
    RambunctiousRecitation(int turns) {
        super();
        this.turns = turns;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return the last number spoken after the given number of turns
     */
    @Override
    public int solve(Stream<String> linesStream) {
        String startingNumbersLine = linesStream.findFirst().orElseThrow();
        List<Integer> startingNumbers = Stream.of(startingNumbersLine.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        // Array contains, per index, the number of the last turn when it was spoken, or 0 if it was never spoken.
        int[] spokenOnTurn = new int[turns];
        
        // First the starting numbers are spoken.
        for (int i = 0; i != startingNumbers.size() - 1; i++) {
            spokenOnTurn[startingNumbers.get(i).intValue()] = i + 1;
        }
        
        int lastSpoken = startingNumbers.get(startingNumbers.size() - 1).intValue();
        for (int turn = startingNumbers.size() + 1; turn != turns + 1; turn++) {
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
}
 
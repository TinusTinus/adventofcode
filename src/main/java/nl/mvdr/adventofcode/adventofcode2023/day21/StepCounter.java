package nl.mvdr.adventofcode.adventofcode2023.day21;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/21">?</a>.
 *
 * @author Martijn van de Rijdt
 */
public class StepCounter implements IntSolver {

    private final int steps;
    
    /**
     * Constructor.
     * 
     * @param infiniteGarden whether the garden stretches out infinitely
     * @param steps the elf's remaining number of steps for the day
     */
    StepCounter(int steps) {
        super();
        this.steps = steps;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var map = GardenMap.parse(lines.toList());
        return map.countReachablePlots(steps);
    }
}
 
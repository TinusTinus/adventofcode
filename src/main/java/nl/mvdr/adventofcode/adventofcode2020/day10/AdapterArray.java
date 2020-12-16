package nl.mvdr.adventofcode.adventofcode2020.day10;

import java.util.function.Predicate;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 10 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/10">Adapter Array</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class AdapterArray implements LongSolver {

    /**
     * {@inheritDoc}
     * 
     * @return the number of 1-jolt differences multiplied by the number of 3-jolt differences
     */
    @Override
    public long solve(Stream<String> lines) {
        int[] adapterJoltages = lines.filter(Predicate.not(String::isEmpty))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        
        int[] joltages = new int[adapterJoltages.length + 2];
        // Charging outlet: effective joltage rating of 0.
        joltages[0] = 0;
        // Adapter joltages, in increasing order.
        System.arraycopy(adapterJoltages, 0, joltages, 1, adapterJoltages.length);
        // Device: built-in joltage adapter rated for 3 jolts higher than the highest-rated adapter.
        joltages[joltages.length - 1] = joltages[joltages.length - 2] + 3;
        
        return solve(joltages);
    }

    /**
     * Solver method.
     * 
     * @param joltages all joltages, including the charging outlet, all adapters and the device, in increasing order
     * @return puzzle solution
     */
    abstract long solve(int[] joltages);
}
 
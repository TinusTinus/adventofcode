package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link BeverageBanditsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BeverageBanditsPart1Test extends SolverTest<BeverageBanditsPart1> {
    /** Constructor. */
    public BeverageBanditsPart1Test() {
        super(BeverageBanditsPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("27730", "example-day15-2018-0.txt"),
                Arguments.of("36334", "example-day15-2018-1.txt"),
                Arguments.of("39514", "example-day15-2018-2.txt"),
                Arguments.of("27755", "example-day15-2018-3.txt"),
                Arguments.of("28944", "example-day15-2018-4.txt"),
                Arguments.of("18740", "example-day15-2018-5.txt"),
                // Test case based on a modified version of example 5 from the puzzle.
                // The input contains only elves, so combat should end immediately before a single round can complete.
                // Expected result is; 0 * total hitpoints = 0.
                Arguments.of("0", "example-day15-2018-6.txt"),
                // Test case based on a modified version of example 5 from the puzzle.
                // The input contains two units who start in adjacent squares.
                // The units should not move, and instead only attack each other.
                // It is expected to take 200 / 3 = 67 rounds to whittle down one unit's health.
                // (Note that the last round is completed.)
                // The remaining unit is expected to have 200 % 3 = 2 hit points remaining.
                // Expected result: 67 * 2 = 134.
                Arguments.of("134", "example-day15-2018-7.txt"),
                Arguments.of("198744", "input-day15-2018.txt"));
    }
}

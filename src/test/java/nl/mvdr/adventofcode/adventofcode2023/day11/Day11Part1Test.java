package nl.mvdr.adventofcode.adventofcode2023.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day11Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day11Part1Test extends SolverTest<Day11Part1> {

    /** Constructor. */
    public Day11Part1Test() {
        super(Day11Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day11-2023.txt"),
                Arguments.of("?", "input-day11-2023.txt"));
    }
}

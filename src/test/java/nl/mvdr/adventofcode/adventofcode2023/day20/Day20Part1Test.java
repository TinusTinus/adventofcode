package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day20Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day20Part1Test extends SolverTest<Day20Part1> {

    /** Constructor. */
    public Day20Part1Test() {
        super(Day20Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day20-2023.txt"),
                Arguments.of("?", "input-day20-2023.txt"));
    }
}

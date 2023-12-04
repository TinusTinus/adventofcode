package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day5Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day5Part1Test extends SolverTest<Day5Part1> {

    /** Constructor. */
    public Day5Part1Test() {
        super(Day5Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day05-2023.txt"),
                Arguments.of("?", "input-day05-2023.txt"));
    }
}

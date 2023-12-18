package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day19Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day19Part1Test extends SolverTest<Day19Part1> {

    /** Constructor. */
    public Day19Part1Test() {
        super(Day19Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day19-2023.txt"),
                Arguments.of("?", "input-day19-2023.txt"));
    }
}

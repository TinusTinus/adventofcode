package nl.mvdr.adventofcode.adventofcode2023.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day2Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day2Part1Test extends SolverTest<Day2Part1> {

    /** Constructor. */
    public Day2Part1Test() {
        super(Day2Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day02-2023.txt"),
                Arguments.of("?", "input-day02-2023.txt"));
    }
}

package nl.mvdr.adventofcode.adventofcode2023.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day6Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day6Part1Test extends SolverTest<Day6Part1> {

    /** Constructor. */
    public Day6Part1Test() {
        super(Day6Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day06-2023.txt"),
                Arguments.of("?", "input-day06-2023.txt"));
    }
}

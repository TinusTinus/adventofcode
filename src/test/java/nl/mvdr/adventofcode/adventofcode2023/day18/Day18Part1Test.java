package nl.mvdr.adventofcode.adventofcode2023.day18;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day18Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day18Part1Test extends SolverTest<Day18Part1> {

    /** Constructor. */
    public Day18Part1Test() {
        super(Day18Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day18-2023.txt"),
                Arguments.of("?", "input-day18-2023.txt"));
    }
}

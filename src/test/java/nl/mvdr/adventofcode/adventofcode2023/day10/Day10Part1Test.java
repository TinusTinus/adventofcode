package nl.mvdr.adventofcode.adventofcode2023.day10;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day10Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day10Part1Test extends SolverTest<Day10Part1> {

    /** Constructor. */
    public Day10Part1Test() {
        super(Day10Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("114", "example-day10-2023.txt"),
                Arguments.of("1666172641", "input-day10-2023.txt"));
    }
}

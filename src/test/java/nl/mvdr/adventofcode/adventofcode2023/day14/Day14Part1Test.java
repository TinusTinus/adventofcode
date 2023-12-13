package nl.mvdr.adventofcode.adventofcode2023.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day14Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day14Part1Test extends SolverTest<Day14Part1> {

    /** Constructor. */
    public Day14Part1Test() {
        super(Day14Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day14-2023.txt"),
                Arguments.of("?", "input-day14-2023.txt"));
    }
}

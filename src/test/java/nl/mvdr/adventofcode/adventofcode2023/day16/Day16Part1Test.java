package nl.mvdr.adventofcode.adventofcode2023.day16;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day16Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day16Part1Test extends SolverTest<Day16Part1> {

    /** Constructor. */
    public Day16Part1Test() {
        super(Day16Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day16-2023.txt"),
                Arguments.of("?", "input-day16-2023.txt"));
    }
}

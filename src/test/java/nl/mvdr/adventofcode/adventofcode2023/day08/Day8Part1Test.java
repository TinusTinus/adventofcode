package nl.mvdr.adventofcode.adventofcode2023.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day8Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day8Part1Test extends SolverTest<Day8Part1> {

    /** Constructor. */
    public Day8Part1Test() {
        super(Day8Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day08-2023.txt"),
                Arguments.of("?", "input-day08-2023.txt"));
    }
}

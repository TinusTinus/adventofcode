package nl.mvdr.adventofcode.adventofcode2023.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day9Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day9Part1Test extends SolverTest<Day9Part1> {

    /** Constructor. */
    public Day9Part1Test() {
        super(Day9Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day09-2023.txt"),
                Arguments.of("?", "input-day09-2023.txt"));
    }
}

package nl.mvdr.adventofcode.adventofcode2023.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day3Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day3Part1Test extends SolverTest<Day3Part1> {

    /** Constructor. */
    public Day3Part1Test() {
        super(Day3Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day03-2023.txt"),
                Arguments.of("?", "input-day03-2023.txt"));
    }
}

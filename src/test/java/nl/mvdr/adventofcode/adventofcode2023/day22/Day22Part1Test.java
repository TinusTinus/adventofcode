package nl.mvdr.adventofcode.adventofcode2023.day22;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day22Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day22Part1Test extends SolverTest<Day22Part1> {

    /** Constructor. */
    public Day22Part1Test() {
        super(Day22Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day22-2023.txt"),
                Arguments.of("?", "input-day22-2023.txt"));
    }
}
package nl.mvdr.adventofcode.adventofcode2023.day21;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day21Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day21Part1Test extends SolverTest<Day21Part1> {

    /** Constructor. */
    public Day21Part1Test() {
        super(Day21Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day21-2023.txt"),
                Arguments.of("?", "input-day21-2023.txt"));
    }
}

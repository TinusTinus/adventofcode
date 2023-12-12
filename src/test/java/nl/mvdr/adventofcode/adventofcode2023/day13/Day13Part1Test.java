package nl.mvdr.adventofcode.adventofcode2023.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day13Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day13Part1Test extends SolverTest<Day13Part1> {

    /** Constructor. */
    public Day13Part1Test() {
        super(Day13Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day13-2023.txt"),
                Arguments.of("?", "input-day13-2023.txt"));
    }
}

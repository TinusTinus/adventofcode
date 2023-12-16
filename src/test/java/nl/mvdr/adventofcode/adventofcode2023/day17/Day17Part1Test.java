package nl.mvdr.adventofcode.adventofcode2023.day17;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day17Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day17Part1Test extends SolverTest<Day17Part1> {

    /** Constructor. */
    public Day17Part1Test() {
        super(Day17Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day17-2023.txt"),
                Arguments.of("?", "input-day17-2023.txt"));
    }
}

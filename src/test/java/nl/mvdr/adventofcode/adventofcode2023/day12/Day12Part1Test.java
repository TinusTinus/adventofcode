package nl.mvdr.adventofcode.adventofcode2023.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day12Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day12Part1Test extends SolverTest<Day12Part1> {

    /** Constructor. */
    public Day12Part1Test() {
        super(Day12Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day12-2023.txt"),
                Arguments.of("?", "input-day12-2023.txt"));
    }
}

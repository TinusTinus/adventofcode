package nl.mvdr.adventofcode.adventofcode2023.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day15Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day15Part2Test extends SolverTest<Day15Part1> {

    /** Constructor. */
    public Day15Part2Test() {
        super(Day15Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("145", "example-day15-2023.txt"),
                Arguments.of("?", "input-day15-2023.txt"));
    }
}

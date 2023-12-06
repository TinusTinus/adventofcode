package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day7Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day7Part1Test extends SolverTest<Day7Part1> {

    /** Constructor. */
    public Day7Part1Test() {
        super(Day7Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day07-2023.txt"),
                Arguments.of("?", "input-day07-2023.txt"));
    }
}

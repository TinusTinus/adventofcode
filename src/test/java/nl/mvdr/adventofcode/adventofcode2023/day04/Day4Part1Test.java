package nl.mvdr.adventofcode.adventofcode2023.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day4Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day4Part1Test extends SolverTest<Day4Part1> {

    /** Constructor. */
    public Day4Part1Test() {
        super(Day4Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "example-day04-2023.txt"),
                Arguments.of("?", "input-day04-2023.txt"));
    }
}

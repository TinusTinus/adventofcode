package nl.mvdr.adventofcode.adventofcode2020.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CustomCustomsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CustomCustomsPart2Test extends SolverTest<CustomCustomsPart2> {

    /** Constructor. */
    public CustomCustomsPart2Test() {
        super(CustomCustomsPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("6", "example-day06-2020.txt"),
                Arguments.of("3476", "input-day06-2020.txt"));
    }
}

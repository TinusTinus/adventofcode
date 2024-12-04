package nl.mvdr.adventofcode.adventofcode2020.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CustomCustomsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CustomCustomsPart1Test extends SolverTest<CustomCustomsPart1> {

    /** Constructor. */
    public CustomCustomsPart1Test() {
        super(CustomCustomsPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("11", "example-day06-2020.txt"));
    }
}

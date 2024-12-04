package nl.mvdr.adventofcode.adventofcode2023.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HotSpringsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HotSpringsPart1Test extends SolverTest<HotSpringsPart1> {

    /** Constructor. */
    public HotSpringsPart1Test() {
        super(HotSpringsPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("21", "example-day12-2023.txt"),
                Arguments.of("6", "example-day12-2023-no-questionmarks.txt")); // exactly one arrangement per line
    }
}

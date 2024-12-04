package nl.mvdr.adventofcode.adventofcode2023.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HotSpringsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HotSpringsPart2Test extends SolverTest<HotSpringsPart2> {

    /** Constructor. */
    public HotSpringsPart2Test() {
        super(HotSpringsPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("525152", "example-day12-2023.txt"));
    }
}

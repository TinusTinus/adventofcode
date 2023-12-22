package nl.mvdr.adventofcode.adventofcode2023.day22;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SandSlabsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SandSlabsPart2Test extends SolverTest<SandSlabsPart2> {

    /** Constructor. */
    public SandSlabsPart2Test() {
        super(SandSlabsPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("7", "example-day22-2023.txt"),
                Arguments.of("?", "input-day22-2023.txt"));
    }
}

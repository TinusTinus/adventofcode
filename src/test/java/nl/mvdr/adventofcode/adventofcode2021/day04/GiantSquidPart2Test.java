package nl.mvdr.adventofcode.adventofcode2021.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link GiantSquidPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class GiantSquidPart2Test extends SolverTest<GiantSquidPart2> {

    /** Constructor. */
    public GiantSquidPart2Test() {
        super(GiantSquidPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1924", "example-day04-2021.txt"));
    }
}

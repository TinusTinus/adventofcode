package nl.mvdr.adventofcode.adventofcode2021.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link GiantSquidPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class GiantSquidPart1Test extends SolverTest<GiantSquidPart1> {

    /** Constructor. */
    public GiantSquidPart1Test() {
        super(GiantSquidPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4512", "example-day04-2021.txt"));
    }
}

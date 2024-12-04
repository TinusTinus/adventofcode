package nl.mvdr.adventofcode.adventofcode2022.day18;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BoilingBouldersPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BoilingBouldersPart1Test extends SolverTest<BoilingBouldersPart1> {

    /** Constructor. */
    public BoilingBouldersPart1Test() {
        super(BoilingBouldersPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("10", "example-day18-2022-0.txt"),
                Arguments.of("64", "example-day18-2022-1.txt"));
    }
}

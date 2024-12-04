package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DistressSignalPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DistressSignalPart2Test extends SolverTest<DistressSignalPart2> {

    /** Constructor. */
    public DistressSignalPart2Test() {
        super(DistressSignalPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("140", "example-day13-2022.txt"));
    }
}

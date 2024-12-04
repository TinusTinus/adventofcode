package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DistressSignalPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DistressSignalPart1Test extends SolverTest<DistressSignalPart1> {

    /** Constructor. */
    public DistressSignalPart1Test() {
        super(DistressSignalPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("13", "example-day13-2022.txt"));
    }
}

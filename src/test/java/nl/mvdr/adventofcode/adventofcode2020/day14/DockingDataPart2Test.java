package nl.mvdr.adventofcode.adventofcode2020.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DockingDataPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DockingDataPart2Test extends SolverTest<DockingDataPart2> {

    /** Constructor. */
    public DockingDataPart2Test() {
        super(DockingDataPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("208", "example-day14-2020-1.txt"));
    }
}

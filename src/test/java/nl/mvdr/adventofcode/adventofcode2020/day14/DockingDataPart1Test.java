package nl.mvdr.adventofcode.adventofcode2020.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DockingDataPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DockingDataPart1Test extends SolverTest<DockingDataPart1> {

    /** Constructor. */
    public DockingDataPart1Test() {
        super(DockingDataPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("165", "example-day14-2020-0.txt"),
                Arguments.of("9628746976360", "input-day14-2020.txt"));
    }
}

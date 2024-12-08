package nl.mvdr.adventofcode.adventofcode2018.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test for {@link ChronalCoordinatesPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCoordinatesPart2Test extends SolverTest<ChronalCoordinatesPart2> {
    /** Constructor. */
    public ChronalCoordinatesPart2Test() {
        super(ChronalCoordinatesPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("16", "example-day06-2018.txt"));
    }
}

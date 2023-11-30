package nl.mvdr.adventofcode.adventofcode2018.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalCoordinatesPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCoordinatesPart1Test extends SolverTest<ChronalCoordinatesPart1> {
    /** Constructor. */
    public ChronalCoordinatesPart1Test() {
        super(ChronalCoordinatesPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("17", "example-day06-2018.txt"));
    }
}

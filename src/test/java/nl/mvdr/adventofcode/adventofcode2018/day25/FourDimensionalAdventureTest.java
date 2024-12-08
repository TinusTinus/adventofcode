package nl.mvdr.adventofcode.adventofcode2018.day25;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test for {@link FourDimensionalAdventure}.
 *
 * @author Martijn van de Rijdt
 */
public class FourDimensionalAdventureTest extends SolverTest<FourDimensionalAdventure> {
    /** Constructor. */
    public FourDimensionalAdventureTest() {
        super(FourDimensionalAdventure.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day25-2018-0.txt"),
                Arguments.of("1", "example-day25-2018-1.txt"),
                Arguments.of("4", "example-day25-2018-2.txt"),
                Arguments.of("3", "example-day25-2018-3.txt"),
                Arguments.of("8", "example-day25-2018-4.txt"));
    }
}

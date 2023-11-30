package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ModeMazePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ModeMazePart1Test extends SolverTest<ModeMazePart1> {
    /** Constructor. */
    public ModeMazePart1Test() {
        super(ModeMazePart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("114", "example-day22-2018.txt"),
                Arguments.of("9899", "input-day22-2018.txt"));
    }
}

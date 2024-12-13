package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test for {@link ModeMazePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ModeMazePart2Test extends SolverTest<ModeMazePart2> {
    /** Constructor. */
    public ModeMazePart2Test() {
        super(ModeMazePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("45", "example-day22-2018.txt"));
    }
}

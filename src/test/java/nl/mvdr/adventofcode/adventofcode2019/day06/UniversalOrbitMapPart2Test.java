package nl.mvdr.adventofcode.adventofcode2019.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link UniversalOrbitMapPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class UniversalOrbitMapPart2Test extends SolverTest<UniversalOrbitMapPart2> {

    /** Constructor. */
    public UniversalOrbitMapPart2Test() {
        super(UniversalOrbitMapPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day06-2019-1.txt"));
    }
}

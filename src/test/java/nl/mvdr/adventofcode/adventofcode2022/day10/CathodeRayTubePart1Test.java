package nl.mvdr.adventofcode.adventofcode2022.day10;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link CathodeRayTubePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CathodeRayTubePart1Test extends SolverTest<CathodeRayTubePart1> {

    /** Constructor. */
    public CathodeRayTubePart1Test() {
        super(CathodeRayTubePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("13140", "example-day10-2022.txt"));
    }
}

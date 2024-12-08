package nl.mvdr.adventofcode.adventofcode2020.day22;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link CrabCombatPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CrabCombatPart1Test extends SolverTest<CrabCombatPart1> {

    /** Constructor. */
    public CrabCombatPart1Test() {
        super(CrabCombatPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("306", "example-day22-2020-0.txt"));
    }
}

package nl.mvdr.adventofcode.adventofcode2022.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link RockPaperScissorsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RockPaperScissorsPart1Test extends SolverTest<RockPaperScissorsPart1> {

    /** Constructor. */
    public RockPaperScissorsPart1Test() {
        super(RockPaperScissorsPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("15", "example-day02-2022.txt"));
    }
}

package nl.mvdr.adventofcode.adventofcode2022.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link RockPaperScissorsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RockPaperScissorsPart2Test extends SolverTest<RockPaperScissorsPart2> {

    /** Constructor. */
    public RockPaperScissorsPart2Test() {
        super(RockPaperScissorsPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("12", "example-day02-2022.txt"));
    }
}

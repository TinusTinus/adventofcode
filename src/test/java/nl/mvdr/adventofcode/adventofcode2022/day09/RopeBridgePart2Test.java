package nl.mvdr.adventofcode.adventofcode2022.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link RopeBridgePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RopeBridgePart2Test extends SolverTest<RopeBridgePart2> {

    /** Constructor. */
    public RopeBridgePart2Test() {
        super(RopeBridgePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1", "example-day09-2022-0.txt"),
                Arguments.of("36", "example-day09-2022-1.txt"));
    }
}

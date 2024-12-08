package nl.mvdr.adventofcode.adventofcode2020.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link TobogganTrajectoryPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TobogganTrajectoryPart2Test extends SolverTest<TobogganTrajectoryPart2> {

    /** Constructor. */
    public TobogganTrajectoryPart2Test() {
        super(TobogganTrajectoryPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("336", "example-day03-2020.txt"));
    }
}

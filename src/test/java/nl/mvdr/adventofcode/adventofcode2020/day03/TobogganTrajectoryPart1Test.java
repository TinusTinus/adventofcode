package nl.mvdr.adventofcode.adventofcode2020.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link TobogganTrajectoryPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TobogganTrajectoryPart1Test extends SolverTest<TobogganTrajectoryPart1> {

    /** Constructor. */
    public TobogganTrajectoryPart1Test() {
        super(TobogganTrajectoryPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("7", "example-day03-2020.txt"));
    }
}

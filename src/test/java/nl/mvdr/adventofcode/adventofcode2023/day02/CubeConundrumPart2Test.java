package nl.mvdr.adventofcode.adventofcode2023.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link CubeConundrumPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CubeConundrumPart2Test extends SolverTest<CubeConundrumPart2> {

    /** Constructor. */
    public CubeConundrumPart2Test() {
        super(CubeConundrumPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2286", "example-day02-2023.txt"));
    }
}

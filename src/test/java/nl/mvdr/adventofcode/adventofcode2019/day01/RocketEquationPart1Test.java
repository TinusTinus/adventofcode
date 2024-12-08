package nl.mvdr.adventofcode.adventofcode2019.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link RocketEquationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RocketEquationPart1Test extends SolverTest<RocketEquationPart1> {

    /** Constructor. */
    public RocketEquationPart1Test() {
        super(RocketEquationPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day01-2019-0.txt"),
                Arguments.of("2", "example-day01-2019-1.txt"),
                Arguments.of("654", "example-day01-2019-2.txt"),
                Arguments.of("33583", "example-day01-2019-3.txt"));
    }
}

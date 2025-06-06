package nl.mvdr.adventofcode.adventofcode2021.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link DivePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DivePart1Test extends SolverTest<DivePart1> {

    /** Constructor. */
    public DivePart1Test() {
        super(DivePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("150", "example-day02-2021.txt"));
    }
}

package nl.mvdr.adventofcode.adventofcode2017.day25;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HaltingProblem}.
 *
 * @author Martijn van de Rijdt
 */
public class HaltingProblemTest extends SolverTest<HaltingProblem> {

    /** Constructor. */
    public HaltingProblemTest() {
        super(HaltingProblem.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3", "example-day25-2017.txt"),
                Arguments.of("3145", "input-day25-2017.txt"));
    }
}

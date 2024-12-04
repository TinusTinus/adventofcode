package nl.mvdr.adventofcode.adventofcode2019.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NBodyProblemPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class NBodyProblemPart2Test extends SolverTest<NBodyProblemPart2> {

    /** Constructor. */
    public NBodyProblemPart2Test() {
        super(NBodyProblemPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2772", "example-day12-2019-0.txt"),
                Arguments.of("4686774924", "example-day12-2019-1.txt"));
    }
}

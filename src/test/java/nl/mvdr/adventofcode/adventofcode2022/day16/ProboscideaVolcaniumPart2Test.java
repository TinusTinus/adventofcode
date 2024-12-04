package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ProboscideaVolcaniumPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ProboscideaVolcaniumPart2Test extends SolverTest<ProboscideaVolcaniumPart2> {

    /** Constructor. */
    public ProboscideaVolcaniumPart2Test() {
        super(ProboscideaVolcaniumPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1707", "example-day16-2022.txt"));
    }
}
